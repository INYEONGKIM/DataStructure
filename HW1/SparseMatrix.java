package HW1;

public interface SparseMatrix {
    SparseMatrix transpose(); //도전
    SparseMatrix add(SparseMatrix other); //이걸 만들어야 됨(과제)

    // You don't have to implement multiply.
    SparseMatrix multiply(SparseMatrix other);

    int getRowCount();
    int getColumnCount();
    int getElemCount();
}
