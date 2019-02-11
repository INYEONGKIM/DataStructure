package HW4;

import java.util.Scanner;

public class DecimalToBinary {
    //using stack
    public void deciTobin(int n){
        ArrayStack<Integer> stack = new ArrayStack<>(100);

        while(n != 0 ){
            stack.push(n%2);
            n = n/2;
        }

        int size = stack.size();

        System.out.print("Binary(stack) : ");
        for(int i=0; i<size; i++){
            System.out.print(stack.pop());
        }
    }

    //using recursion
    public void deciTobinRec(int n){
        if(n > 0){
            int temp = n/2;
            deciTobinRec(temp);
            System.out.print(n%2);
        }
    }

    public static void main(String[] args){
        System.out.print("put number : ");
        Scanner s = new Scanner(System.in);
        int num = s.nextInt();

        if(num == 0){
            System.out.print("Binary(stack) : 0\nBinary(recursion) : 0");
        }
        else{
            DecimalToBinary DTB = new DecimalToBinary();
            DecimalToBinary DTBR = new DecimalToBinary();

            DTB.deciTobin(num);

            System.out.print("\nBinary(recursion) : ");
            DTBR.deciTobinRec(num);
        }

    }

}
