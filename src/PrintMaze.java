/**
 * The type Print maze.
 */
public class PrintMaze {
    /**
     * Print maze.
     *
     * @param map the map
     */
    public static void printMaze(Field[][] map) {
        for (Field[] row : map) {
            for (Field field : row) {
                System.out.print(field);
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
        Field[][] map = gen.generate(25, 31);
        printMaze(map);
        System.out.println();
    }
}
