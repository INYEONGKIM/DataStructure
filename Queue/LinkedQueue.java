package Queue;

import DoublyLinkedLists.SinglyLinkedList;

public class LinkedQueue<E> implements Queue {
    //Field
    private SinglyLinkedList<E> list = new SinglyLinkedList<>();

    //Constructor
    public LinkedQueue(){

    }

    //Method
    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void enqueue(Object element) {
        list.addFirst((E) element);
    }

    @Override
    public E first() {
        return list.first();
    }

    @Override
    public E dequeue() {
        return list.removeFirst();
    }
}
