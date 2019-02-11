package HW1;

public class Entry {
    int row;
    int col;
    double value;

    //constructor
    public Entry(int row, int col, double value) {
        this.row = row;
        this.col = col;
        this.value = value;
    }

    //method
    public Entry copy() {
        return new Entry(row, col, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Entry)) return false;
        Entry entry = (Entry) o;
        return row == entry.row &&
            col == entry.col &&
            Double.compare(entry.value, value) == 0;
    }


    @Override
    public String toString() {
        return row + "\t" + col + "\t" + value;
    }
}
