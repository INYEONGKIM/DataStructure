package hw2;

public class DLinkedList<T> {

    //field
    private Node<T> header;
    private Node<T> trailer;
    private int size = 0;

    //constructor
    public DLinkedList() {
        header = new Node<>(null, null, null);
        trailer = new Node<>(null, header, null);
        header.setNext(trailer);
    }


    public void setHeaderInfo(T info) {
        header.setItem(info);
    }

    public void setTrailerInfo(T info) {
        trailer.setItem(info);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() { return size; }

    public Node<T> getFirst() {
        return header.getNext();
    }

    public Node<T> getLast() {
        return trailer.getPrev();
    }


    //여기부터 추가하기
    public void addFirst(Node<T> n) {
        addAfter(header,n);
    }

    public void addLast(Node<T> n) {
        addBefore(trailer,n);
    }

    public T removeFirst() {
        if(isEmpty()) return null;

        return remove(header.getNext());
    }

    public T removeLast() {
        if(isEmpty()) return null;
        return remove(trailer.getPrev());
}

    public void addAfter(Node<T> p, Node<T> n) { //p뒤에 n을 만들기
       n.setNext(p.getNext());
       n.setPrev(p); //n을 먼저 수정한 후
       p.getNext().setPrev(n);
       p.setNext(n); //p값 수정

       size++;
    }

    public void addBefore(Node<T> p, Node<T> n) { //p이전에 n만들기
        n.setNext(p);
        n.setPrev(p.getPrev()); //n을 먼저 수정한 후
        p.getPrev().setNext(n);
        p.setPrev(n); //p값 수정

        size++;
    }

    public T remove(Node<T> n) {
        n.getPrev().setNext(n.getNext()); //n의 이전값을 기존의 다음값으로 둠
        n.getNext().setPrev(n.getPrev()); //n의 다음값을 기존의 이전값으로 둠
        n.setPrev(null); //그리고 null 대입(날림)
        n.setNext(null); //garbage collector가 알아서 가져감

        size--;
        return n.getItem();
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(
            "List: size = " + size + " [");
        Node<T> current = header.getNext();

        while (current != trailer) {
            builder.append(current.getItem().toString());
            current = current.getNext();
        }
        builder.append("]");

        return builder.toString();
    }

}
