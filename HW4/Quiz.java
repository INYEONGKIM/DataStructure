package HW4;

public class Quiz {

    static boolean Solve(int start, int[] boxes){

        int flag = start;
        int count =0;

        while(true){
            if(count > (boxes.length)*2){
                break;
            }

            if(flag + boxes[flag] > boxes.length-1){ //boundary condition
                if(flag-boxes[start] <= 0 ){ //boundary condition
                    flag = 0;
                }else flag -= boxes[flag];
            }
            else flag += boxes[flag]; //오른쪽 우선 탐색

            if(boxes[flag] == boxes[boxes.length-1]){ //일치하는 경우 true return
                return true;
            }
            count++;
        }
        return false;
    }

    public static void main(String[] argv){

        int[] boxes = {3,6,4,1,3,4,2,5,3,0};

        boolean test = Solve(0, boxes);

        System.out.println("Answer is  " + test);

    }

}