public class Triangle {
    public static void main(String[] args) {
        int col = 0;
        int row = 0;
        int SIZE = 5;
        while (row < SIZE) {
            row++;
            while (col < row) {
                System.out.print("*");
                col++;
            }
            while (col == row) {
                System.out.println();
                col = 0;
            }
        }
    }
}