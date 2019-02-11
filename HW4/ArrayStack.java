package HW4;

public class ArrayStack<E> implements Stack<E> {
    //Field
    public static final int CAPACITY = 1000;
    private E[] data;
    private int t = -1; //top index
    //Constructor
    public ArrayStack(){
        this(CAPACITY);
    } //없으면 초기 1000

    public ArrayStack(int capacity){
        data = (E[]) new Object[capacity]; //input에 맞는 배열 생성
    }
    //Method
    @Override
    public int size() {
        return t+1;
    }

    @Override
    public boolean isEmpty() {
        return (t == -1);
    }

    @Override
    public void push(E e) throws IllegalStateException {
        if(size() == data.length){
            throw new IllegalStateException("Stack is full");
        }
        data[++t] = e;
    }

    @Override
    public E top() {
        if(isEmpty()){
            return null;
        }
        return data[t];
    }

    @Override
    public E pop() {
        if(isEmpty()){
            return null;
        }
        E temp = data[t];
        data[t] = null;
        t--;
        return temp;
    }
}
