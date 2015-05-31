import java.util.Random;

/**
 * The type Recursive backtracker.
 */
public class RecursiveBacktracker implements MazeGenerator {

    /**
     * The Offsets.
     * (x, y)
     */
    private static int[][] offsets = {
        {-1, 0},
        {1, 0},
        {0, -1},
        {0, 1}
    };

    /**
     * The constant HALLWAY_OFFSET.
     */
    private static int HALLWAY_OFFSET = 2;

    /**
     * The Random Instance.
     */
    private Random r = new Random();

    /**
     * The Goal set.
     */
    private boolean goalSet = false;

    /**
     * Valid new position.
     *
     * @param maze   the maze
     * @param x      the x
     * @param y      the y
     * @param offset the offset
     * @return the boolean
     */
    private boolean validNewPosition(FieldType[][] maze, int x, int y, int[] offset) {
        int newX = x + HALLWAY_OFFSET * offset[1];
        int newY = y + HALLWAY_OFFSET * offset[0];
        if (newY < 0 || newY >= maze.length || newX < 0 || newX >= maze[0].length) {
            return false;
        }
        return maze[newY][newX] == FieldType.WALL;

    }

    /**
     * Init maze.
     *
     * @param height the height
     * @param width  the width
     * @return the map as FieldType[][]
     */
    private FieldType[][] initMaze(int height, int width) {
        assert height % 2 == 0 && width % 2 == 0;
        FieldType[][] map = new FieldType[height][width];
        for (int i = 0; i< map.length; ++i) {
            for (int j = 0; j < map[i].length; ++j) {
                map[i][j] = FieldType.WALL;
            }
        }
        return map;
    }

    /**
     * In maze.
     *
     * @param maze the maze
     * @param x the x
     * @param y the y
     * @return true, wenn sich die Koordinatem im Spielfeld befinden
     */
    private boolean inMaze(FieldType[][] maze, int x, int y) {
        return !(y < 0 ||  y >= maze.length || x < 0 || x >= maze[0].length);
    }

    /**
     * Count visitable neighbors.
     *
     * @param maze the maze
     * @param x the x
     * @param y the y
     * @return the neighbors
     */
    private int countVisitableNeighbors(FieldType[][] maze, int x, int y) {
        int n = 0;
        for (int[] offset: offsets) {
            int newX = x + offset[1];
            int newY = y + offset[0];
            if (inMaze(maze, newX, newY) && maze[newY][newX] != FieldType.WALL) {
                n++;
            }
        }
        return n;
    }

    /**
     * Place special fields.
     *
     * @param maze the maze
     */
    private void placeSpecialFields(FieldType[][] maze) {
        for (int i = 0; i < maze.length; ++i) {
            for (int j = 0; j < maze[0].length; ++j) {
                if (maze[i][j] == FieldType.PLAIN) {
                    int neighbors = countVisitableNeighbors(maze, j, i);
                    if (neighbors >= 3) {
                        maze[i][j] = FieldType.BATTLE;
                    } else if (neighbors == 1) {
                        if (r.nextDouble() > 0.5) {
                            maze[i][j] = FieldType.FOUNTAIN;
                        } else {
                            maze[i][j] = FieldType.SMITHY;
                        }
                    }  
                }
            }
        }
    }

    /**
     * Generate FieldType [ ] [ ].
     *
     * @param height the height
     * @param width the width
     * @return the map as FieldType[][]
     */
    @Override
    public FieldType[][] generate(int height, int width) {
        if (height < 2 || width < 2) {
            throw new IllegalArgumentException("Non-valid maze dimensions");
        }
        goalSet = false;
        FieldType[][] maze = initMaze(height, width);
        int startx = 2*r.nextInt(width/2)+1;
        int starty = 2*r.nextInt(height/2)+1;
        maze = generate(startx, starty,  maze);
        maze[starty][startx] = FieldType.START;
        //maze = border(maze);
        placeSpecialFields(maze);
        return maze;
    }

    /**
     * Build hallway.
     *
     * @param maze the maze
     * @param curX the cur x
     * @param curY the cur y
     * @param offset the offset
     * @param length the length
     */
    private void buildHallway(FieldType[][] maze, int curX, int curY, int[] offset, int length) {

        for (int i = 1; i <= length; ++i) {
            
            curX += offset[1];
            curY += offset[0];

            if (curY < 0 ||  curY >= maze.length || curX < 0 || curX >= maze[0].length) {
                return;
            }
            
            maze[curY][curX] = FieldType.PLAIN;
        }
    }

    /**
     * Generate FieldType [ ] [ ].
     *
     * @param curX the cur x
     * @param curY the cur y
     * @param maze the maze
     * @return the map as FieldType [] []
     */
    private FieldType[][] generate(int curX, int curY, FieldType[][] maze) {
        maze[curY][curX] = FieldType.PLAIN;
        int[] validPositions = new int[offsets.length];
        int validPositionCount = offsets.length;
        for (int i = 0; i < offsets.length; ++i) {
                validPositions[i] = i;
        }
        boolean deadEnd = true;
        while (validPositionCount != 0) {
            int newPosIndex = r.nextInt(validPositionCount);
            int[] newPosOffset = offsets[validPositions[newPosIndex]];
            int newX = curX + HALLWAY_OFFSET * newPosOffset[1];
            int newY = curY + HALLWAY_OFFSET * newPosOffset[0];

            if (validNewPosition(maze, curX, curY, newPosOffset)) {
                deadEnd = false;
                buildHallway(maze, curX, curY, newPosOffset, HALLWAY_OFFSET);
                generate(newX, newY, maze);
            }

            validPositions = ArrayHelpers.delete(validPositions, newPosIndex);
            validPositionCount--;
            
        }
        if (!goalSet && deadEnd) {
            goalSet = true;
            maze[curY][curX] = FieldType.GOAL;
        }
        return maze;
    }

}
