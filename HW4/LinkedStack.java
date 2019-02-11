package HW4;

import DoublyLinkedLists.SinglyLinkedList;

public class LinkedStack<E> implements Stack<E>{
    //Field
    private SinglyLinkedList<E> list = new SinglyLinkedList<>();

    //Constructor
    public LinkedStack(){

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
    public void push(E e) {
        list.addFirst(e);
    }

    @Override
    public E top() {
        return list.first();
    }

    @Override
    public E pop() {
        return list.removeFirst();
    }

//    public static void main(String[] args){
//        LinkedStack<Integer> S = new LinkedStack<>();
//        S.push(20);
//        S.push(21);
//
//
//        System.out.println(S.top());
//        System.out.println(S.pop());
//
//
//    }
}
