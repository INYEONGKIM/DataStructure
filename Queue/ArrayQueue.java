package Queue;

public class ArrayQueue<E> implements Queue<E> {
    //Field
    private E[] data;
    private int first = 0; //현재 가장 앞에 위치한 값의 index
    private int size = 0;
    public static final int CAPACITY = 1000;

    //Constructor
    public ArrayQueue(){
        this(CAPACITY);
    }
    public ArrayQueue(int capacity){
        data = (E[]) new Object[capacity];
    }

    //Method
    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return (this.size == 0);
    }

    @Override
    public void enqueue(E e) throws IllegalStateException {
        if(size == data.length ){
            throw new IllegalStateException("Queue is full");
        }
        else {
            int input = ( first + size) % data.length;
            data[input] = e;
            size++;
        }
    }

    @Override
    public E first() {
        if(isEmpty()) return null;

        return data[first];
    }

    @Override
    public E dequeue() {
        if(isEmpty()) return null;

        E result = data[first];
        data[first] = null;
        first = (first+1) % data.length;
        size--;

        return result;
    }


    public static void main(String[] args){
        ArrayQueue<Integer> queue = new ArrayQueue(3);
//        LinkedQueue<Integer> queue = new LinkedQueue<Integer>();

        System.out.println(queue.size());

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

//        System.out.println(queue.first());

        System.out.println(queue.dequeue());
//        queue.enqueue(3);

    }

}
