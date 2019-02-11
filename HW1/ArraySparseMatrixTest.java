package HW1;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ArraySparseMatrixTest {

    @Test
    public void transpose() {
        double input[][] = {
            {0,     0, 1.0,   0},
            {1.0, 2.0,   0,   0},
            {0,     0,   0, 3.0}
        };

        double output[][] = {
            {  0, 1.0,   0},
            {  0, 2.0,   0},
            {1.0,   0,   0},
            {  0,   0, 3.0}
        };

        SparseMatrix given =
            ArraySparseMatrix.create(input, 3, 4,4);

        SparseMatrix expected =
            ArraySparseMatrix.create(output, 4, 3, 4);

        assertTrue("Transpose failed", expected.equals(given.transpose()));
    }

    @Test
    public void add() {
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

        double output[][] = { //정확히 됐을때 결과 값
            {1.0,   0, 1.0, 2.0},
            {1.0, 5.0,   0,   0},
            {4.0,   0, 5.0, 3.0}
        };

        SparseMatrix sm1 = ArraySparseMatrix.create(m1, 3, 4, 4);
        SparseMatrix sm2 = ArraySparseMatrix.create(m2, 3, 4, 5);
        SparseMatrix sm3 = sm1.add(sm2); //만든 함수로 완성된 결과

        SparseMatrix expected = ArraySparseMatrix.create(output, 3,4, 8);

        assertTrue("Addition failed", expected.equals(sm3)); //run 했을때 잘 되면 초록불, 아니면 빨간불이 뜸

    }
}