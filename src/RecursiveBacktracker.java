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
     * erzeugt neuen Händlerfeld
     * @return Feld mit Händler
     */
    private static Field erzeugeHaendlerFeld() {
        return new Field(FieldType.HAENDLER) {
            /**
             * eine neue Händlerinstanz
             */
            private Haendler haendler = new Haendler();

            @Override
            public void action(Player player) {
                Haendler.handeln(player, haendler);
            }
        };
    }

    /**
     * Valid new position.
     *
     * @param maze   the maze
     * @param x      the x
     * @param y      the y
     * @param offset the offset
     * @return the boolean
     */
    private boolean validNewPosition(Field[][] maze, int x, int y, int[] offset) {
        int newX = x + HALLWAY_OFFSET * offset[1];
        int newY = y + HALLWAY_OFFSET * offset[0];
        if (newY < 0 || newY >= maze.length || newX < 0 || newX >= maze[0].length) {
            return false;
        }
        return maze[newY][newX].getFieldType() == FieldType.WALL;

    }

    /**
     * Init maze.
     *
     * @param height the height
     * @param width  the width
     * @return the map as Field[][]
     */
    private Field[][] initMaze(int height, int width) {
        assert height % 2 == 0 && width % 2 == 0;
        Field[][] map = new Field[height][width];
        for (int i = 0; i < map.length; ++i) {
            for (int j = 0; j < map[i].length; ++j) {
                map[i][j] = new Field(FieldType.WALL);
            }
        }
        return map;
    }

    /**
     * In maze.
     *
     * @param maze the maze
     * @param x    the x
     * @param y    the y
     * @return true, wenn sich die Koordinatem im Spielfeld befinden
     */
    private boolean inMaze(Field[][] maze, int x, int y) {
        return !(y < 0 || y >= maze.length || x < 0 || x >= maze[0].length);
    }

    /**
     * Count visitable neighbors.
     *
     * @param maze the maze
     * @param x    the x
     * @param y    the y
     * @return the neighbors
     */
    private int countVisitableNeighbors(Field[][] maze, int x, int y) {
        int n = 0;
        for (int[] offset : offsets) {
            int newX = x + offset[1];
            int newY = y + offset[0];
            if (inMaze(maze, newX, newY) && maze[newY][newX].getFieldType() != FieldType.WALL) {
                n++;
            }
        }
        return n;
    }

    /**
     * Place special fields.
     * Jetzt neu: statt chars Instanzen von Fieldtype.
     *
     * @param maze the maze
     */
    private void placeSpecialFields(Field[][] maze) {
        for (int i = 0; i < maze.length; ++i) {
            for (int j = 0; j < maze[0].length; ++j) {
                if (maze[i][j].getFieldType() == FieldType.PLAIN) {
                    int neighbors = countVisitableNeighbors(maze, j, i);
                    if (neighbors >= 3) {
                        maze[i][j] = new Field(FieldType.BATTLE);
                    } else if (neighbors == 1) {
                        if (r.nextDouble() < 0.3) {
                            maze[i][j] = new Field(FieldType.FOUNTAIN);
                        } else if (r.nextDouble() >= 0.3 && r.nextDouble() < 0.6) {
                            maze[i][j] = new Field(FieldType.SMITHY);
                        } else {
                            maze[i][j] = erzeugeHaendlerFeld();
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
     * @param width  the width
     * @return the map as FieldType[][]
     */
    @Override
    public Field[][] generate(int height, int width) {
        if (height < 2 || width < 2) {
            throw new IllegalArgumentException("Non-valid maze dimensions");
        }
        goalSet = false;
        Field[][] maze = initMaze(height, width);
        int startx = 2 * r.nextInt(width / 2) + 1;
        int starty = 2 * r.nextInt(height / 2) + 1;
        maze = generate(startx, starty, maze);
        maze[starty][startx] = new Field(FieldType.START);


        //Setze Questgeber.
        int questgeberx = 2 * r.nextInt(width / 2) + 1;
        int questgebery = 2 * r.nextInt(height / 2) + 1;
        maze = generate(questgeberx, questgebery, maze);
        maze[questgebery][questgeberx] = new Field(FieldType.QUESTGEBER);
        //maze = border(maze);
        placeSpecialFields(maze);


        int hx = 2 * r.nextInt(width / 2) + 1;
        int hy = 2 * r.nextInt(height / 2) + 1;
        maze = generate(hx, hy, maze);
        maze[hy][hx] = erzeugeHaendlerFeld();
        //maze = border(maze);
        placeSpecialFields(maze);


        int hx2 = 2 * r.nextInt(width / 2) + 1;
        int hy2 = 2 * r.nextInt(height / 2) + 1;
        maze = generate(hx2, hy2, maze);
        maze[hy2][hx2] = erzeugeHaendlerFeld();
        //maze = border(maze);
        placeSpecialFields(maze);


        return maze;


    }

    /**
     * Build hallway.
     *
     * @param maze   the maze
     * @param curX   the cur x
     * @param curY   the cur y
     * @param offset the offset
     * @param length the length
     */
    private void buildHallway(Field[][] maze, int curX, int curY, int[] offset, int length) {

        for (int i = 1; i <= length; ++i) {

            curX += offset[1];
            curY += offset[0];

            if (curY < 0 || curY >= maze.length || curX < 0 || curX >= maze[0].length) {
                return;
            }

            maze[curY][curX] = new Field(FieldType.PLAIN);
        }
    }

    /**
     * Generate Field [ ] [ ].
     *
     * @param curX the cur x
     * @param curY the cur y
     * @param maze the maze
     * @return the map as Field [] []
     */
    private Field[][] generate(int curX, int curY, Field[][] maze) {
        maze[curY][curX] = new Field(FieldType.PLAIN);
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
            maze[curY][curX] = new Field(FieldType.GOAL);
        }
        return maze;
    }

}
