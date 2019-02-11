package hw2;

import hw2.DLinkedList;

class Block {
    public int size;
    public int start;
    public int end;

    public Block(int size, int start, int end) {
        this.size = size;
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "(" + size +", " + start + ", " + end + ")";
    }
}

public class MemoryManager {

    //field
    private DLinkedList<Block> heap = new DLinkedList<>();

    //constructor
    public MemoryManager(int capacity) { //capacity 만큼 초기화
        heap.addFirst(new Node<Block>(new Block(capacity, 0, capacity-1), null, null));
        //값에는 Block(int 3개 객체), prev, next 위치정보
        heap.setHeaderInfo(new Block(capacity, -1, -1 ) ); //크게 의미 있는것은 아님
        heap.setTrailerInfo(new Block(capacity, -1, -1 ) );
    }

    //method
    public Block malloc(int size) { //블럭 할당 = heap에서 떼어낼 블럭
        Node<Block> temp = heap.getFirst(); //첫번째 블록을 그대로 가져옴

        //size 검사과정
        while(temp.getItem().size < size){ //받은 값이 temp의 size보다 클 경우
            if(temp.getNext() == null ){ //temp가 끝까지 도착한 경우
                throw new OutOfMemoryException("Error : OutOfMemoryException");
            }
            temp = temp.getNext(); //수정 대상을 찾음 ( =temp)
        }

        temp.setItem(new Block(temp.getItem().size -size, temp.getItem().start + size, temp.getItem().end)); //heap 수정

        return new Block(size, temp.getItem().start-size, temp.getItem().start-1);
    }

    public void free(Block block) { //블럭 날리기
        Node<Block> toFreeNode = new Node<>(block, null, null); //호출한 block 반환을 위한 변수 manager.free(b1);
        Node<Block> temp = heap.getFirst(); //빈공간을 찾기 위한 변수(첫번째 블록을 그대로 가져옴)

        //size 검사과정
        while(toFreeNode.getItem().start > temp.getItem().end ){ //H - toFreeNode + temp - T 이렇게 만들기 위한 temp 설정
            if(temp.getNext() == null ){
                throw new OutOfMemoryException("Error");
            }
            temp = temp.getNext(); //수정 대상을 찾음 ( =temp)
        }

        heap.addBefore(temp, toFreeNode);

        merge(toFreeNode); //toFreeNode를 추가한 이후 합쳐야 할 경우, 메소드로 따로 뺌

    }

    private void merge(Node<Block> toFreeNode){
        Node<Block> beforeNode = toFreeNode.getPrev(); //위치 비교를 위한 Node 변수 2개 생성(앞이랑 이어지는 경우)
        Node<Block> afterNode = toFreeNode.getNext(); //뒤랑 이어지는 경우

        if (beforeNode.getItem().end + 1 == toFreeNode.getItem().start) { //beforeNode와 toFreeNode가 붙어 있을 경우
            if(toFreeNode.getItem().start == 0 ){
                //edge case
            }else{
                toFreeNode.getItem().size += beforeNode.getItem().size;
                toFreeNode.getItem().start = beforeNode.getItem().start;

                heap.remove(beforeNode); //지우고 (remove)
            }
        }

        if (toFreeNode.getItem().end + 1 == afterNode.getItem().start) { //toFreeNode와 afterNode가 붙어 있을 경우
            toFreeNode.getItem().size += afterNode.getItem().size;
            toFreeNode.getItem().end = afterNode.getItem().end;

            heap.remove(afterNode); //지우고 (remove)
        }

    }

    // for debugging purpose only
    public DLinkedList<Block> getHeap() {
        return heap;
    }

    @Override
    public String toString() {
        return heap.toString();
    }


    public static void main(String[] args){
        MemoryManager manager = new MemoryManager(1000);
        System.out.println("start "+manager.toString());

        Block b1 = manager.malloc(200); //b1 = Block(200, 0, 199)
        System.out.println(manager);
        Block b2 = manager.malloc(300);
        System.out.println(manager);
        Block b3 = manager.malloc(150);
        System.out.println(manager);


        System.out.println("==============================");


        manager.free(b3);
        System.out.println(manager.toString()+" ----> free(b3)");

        manager.free(b1);
        System.out.println(manager.toString()+" ----> free(b1)");

        Block b4 = manager.malloc(10);
        System.out.println(manager);

        manager.free(b2);
        System.out.println(manager.toString()+" ----> free(b2)");

        manager.free(b4);
        System.out.println(manager.toString()+" ----> free(b4)");

        Block b5 = manager.malloc(10);
        System.out.println(manager);

    }

}
