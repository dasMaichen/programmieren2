/**
 * The interface Maze generator.
 */
public interface MazeGenerator {

    /**
     * Generate Field [ ] [ ].
     *
     * @param height the height
     * @param width  the width
     * @return the Field [ ] [ ]
     */
    Field[][] generate(int height, int width);
}
