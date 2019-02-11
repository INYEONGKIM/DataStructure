package HW1;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

public class ArraySparseMatrix implements SparseMatrix { //구현하는 class

    //field
    public static final int DEFAULT_CAPACITY = 1024;

    private int rowCount; //A[0]에 해당하는 내용, 책과 다름(그냥 0번부터 데이터가 들어있음)
    private int columnCount; //전체가 모두 유효한 값을 가짐
    private int elemCount;
    private Entry[] elements = new Entry[0]; //실제 데이터가 들어있는 배열

    //constructor
    public ArraySparseMatrix(int rowCount, int columnCount, int capacity) {
        elements = new Entry[capacity];
        this.rowCount = rowCount; //매개값을 field의 rowCount에 대입
        this.columnCount = columnCount; //매개값을 field의 columnCount에 대입
        this.elemCount = 0;
     }

    public ArraySparseMatrix(int rowCount, int columnCount) {
        this(rowCount, columnCount, DEFAULT_CAPACITY);
    }
    /*
     * This routine simulates reading from files using two-dimensional matrix.
     */

    //method
    public static SparseMatrix create(double[][] aMatrix, int rowCount, int columnCount, int elemCount) {
        ArraySparseMatrix matrix = new ArraySparseMatrix(rowCount, columnCount, elemCount);

        int nonZeroCount = 0;
        for (int i = 0; i < aMatrix.length; i++)
            for (int j = 0; j < aMatrix[i].length; j++) { //넘겨 받은 배열의 크기만큼
                if (Double.compare(aMatrix[i][j], 0.0) != 0) { //Double.compare = 두 double을 비교하여 값이 큰 값을 return, aMatrix값이 0이 가니라면
                    matrix.put(new Entry(i, j, aMatrix[i][j])); //matrix에 Entry(좌표, 값)을 만들어 대입
                    nonZeroCount++;
                }
            }

        if (nonZeroCount != elemCount) //secure coding
            throw new IllegalStateException("Non zero count doesn't match");

        return matrix;
    }


    @Override
    public SparseMatrix transpose() {

        Entry entryTemp = null; //객체정렬시 사용할 변수

        //row,col값 치환
        for(int i=0; i<this.elemCount; i++){
            int temp = this.elements[i].col;
            this.elements[i].col = this.elements[i].row;
            this.elements[i].row = temp;
        }

        //치환 후 row, col개수가 서로 바뀌었으므로 치환
        int temp = this.rowCount;
        this.rowCount = this.columnCount;
        this.columnCount = temp;

        //row정렬
        int min;
        for(int i=0; i<this.elemCount; i++){
            min = i;
            for(int j=i+1; j<this.elemCount; j++){
                if(this.elements[min].row > this.elements[j].row){
                    min = j;
                }
            }
            entryTemp = this.elements[min];
            this.elements[min] = this.elements[i];
            this.elements[i] = entryTemp;
        }

        //col정렬
        for(int i=0; i<this.elemCount; i++){
            min = i;

            for(int j=i+1; j<this.elemCount; j++){
                if( (this.elements[min].row == this.elements[j].row) &&
                        (this.elements[min].col > this.elements[j].col)){
                    min = j;
                }
            }
            entryTemp = this.elements[min];
            this.elements[min] = this.elements[i];
            this.elements[i] = entryTemp;
        }

        return this;
    }


    @Override
    public SparseMatrix add(SparseMatrix other) { //SparseMatrix other 객체를 넘겨받음, other = sp2 this = sp1
        if (this.rowCount != other.getRowCount() || this.columnCount != other.getColumnCount())
            throw new IllegalArgumentException("Matrix size doesn't match");

        ArraySparseMatrix matrixResult = new ArraySparseMatrix(this.rowCount, this.columnCount, this.elemCount+other.getElemCount()); //sp1 + sp2를 담을 객체
        Entry[] entryOther = ((ArraySparseMatrix) other).elements; //sp2를 담을 변수

        Entry entryTemp = null;

        //sp1 삽입
        for(int i=0; i<this.elemCount; i++){
            matrixResult.put(this.elements[i]);
        }

        //sp2 삽입
        for(int i=0; i<entryOther.length; i++){
            matrixResult.put(entryOther[i]);
        }

        //row정렬
        int min;
        for(int i=0; i<matrixResult.elemCount; i++){
            min = i;

            for(int j=i+1; j<matrixResult.elemCount; j++){
                if(matrixResult.elements[min].row > matrixResult.elements[j].row){
                    min = j;
                }
            }
            entryTemp = matrixResult.elements[min];
            matrixResult.elements[min] = matrixResult.elements[i];
            matrixResult.elements[i] = entryTemp;
        }

        //col정렬
        for(int i=0; i<matrixResult.elemCount; i++){
            min = i;

            for(int j=i+1; j<matrixResult.elemCount; j++){
                if( (matrixResult.elements[min].row == matrixResult.elements[j].row) &&
                        (matrixResult.elements[min].col > matrixResult.elements[j].col)){
                    min = j;
                }
            }
            entryTemp = matrixResult.elements[min];
            matrixResult.elements[min] = matrixResult.elements[i];
            matrixResult.elements[i] = entryTemp;
        }

        //동일한 좌표값 발견시 더함
        for(int i=0; i<matrixResult.elemCount-1; i++){
            if((matrixResult.elements[i].row == matrixResult.elements[i+1].row )
                    && (matrixResult.elements[i].col == matrixResult.elements[i+1].col) ){
                //동일한 row와 col을 가진 entry 값을 발견시

                matrixResult.elements[i].value += matrixResult.elements[i+1].value; // 두 값을 저장해서 윗쪽 배열에 둔 후

                for(int k=i+1; k<matrixResult.elemCount-1; k++){ //저장한 값 바로 다음 배열(i+1) 부터 끝까지 하나씩 위로 올림
                    matrixResult.elements[k] = matrixResult.elements[k+1];
                }
                matrixResult.elemCount--; //그리고 총 개수가 줄었으므로 count를 하나 줄임
            }
        }

        return matrixResult;
    }

    public void put(Entry entry) {
        elements[elemCount++] = entry;
    }

    @Override
    public SparseMatrix multiply(SparseMatrix other) {
        throw new IllegalStateException("Not implemented");
    }

    @Override
    public int getRowCount() {
        return rowCount;
    }

    @Override
    public int getColumnCount() {
        return columnCount;
    }

    @Override
    public int getElemCount() {
        return elemCount;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof ArraySparseMatrix)) return false;
        ArraySparseMatrix other = (ArraySparseMatrix) obj;

        if (rowCount != other.rowCount || columnCount != other.columnCount || elemCount != other.elemCount)
            return false;

        for (int i = 0; i < elements.length; i++) {
            if (!elements[i].equals(other.elements[i])) return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(); //StringBuilder.append = 문자열 합칠 때 사용
        builder.append(rowCount + "\t" + columnCount + "\t" + elemCount + "\n"); //맨 위의 정리 값 추가

        for (int i = 0; i < elemCount; i ++){
            builder.append(elements[i] + "\n"); //elements[i] 값을 추가
        }

        return builder.toString(); //문자열 toSting()으로 변환
    }

    public static void main(String[] args){ //test code
        double m1[][] = {
                {0,     0, 1.0,   0},
                {1.0, 2.0,   0,   0},
                {0,     0,   0, 3.0}
        };

        double m2[][] = {
                {1.0,   0,   0, 2.0},
                {  0, 3.0,   0,   0},
                {4.0,   0, 5.0,   0}
        };

        System.out.println("Matrix 1"); //일단 찍음
        SparseMatrix sp1 = ArraySparseMatrix.create(m1, 3,4,4);
        System.out.println(sp1);

        System.out.println("Matrix 2");
        SparseMatrix sp2 = ArraySparseMatrix.create(m2, 3,4,5);
        System.out.println(sp2);

        System.out.println("add");
        SparseMatrix sp3 = sp1.add(sp2); //sp1, sp2가 합해진 새로운 matrix를 만들어야 함
        System.out.println(sp3);

        System.out.println("transpose");
        System.out.println(sp1.transpose());
    }

}

