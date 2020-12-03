public class Point {
    private int row;
    private int column;

    public Point(int row, int column){
        this.row = row;
        this.column = column;
    }
    public int getRow() {return this.row;}
    public int getColumn() {return this.column;}

    @Override
    public String toString() {
        return String.format("[%d, %d]", this.row, this.column);
    }
}

