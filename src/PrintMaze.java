/**
 * The type Print maze.
 */
public class PrintMaze {
    /**
     * Print maze.
     *
     * @param map the map
     */
    public static void printMaze(FieldType[][] map) {
        for (FieldType[] row : map) {
            for (FieldType field : row) {
                System.out.print(field.getRepresentation());
            }
            System.out.println();
        }
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        MazeGenerator gen = new RecursiveBacktracker();
        FieldType[][] map = gen.generate(25,31);
        printMaze(map);
        System.out.println();
    }
}
