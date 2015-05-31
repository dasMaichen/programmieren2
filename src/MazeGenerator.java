/**
 * The interface Maze generator.
 */
public interface MazeGenerator {

    /**
     * Generate FieldType [ ] [ ].
     *
     * @param height the height
     * @param width  the width
     * @return the FieldType [ ] [ ]
     */
    FieldType[][] generate(int height, int width);
}
