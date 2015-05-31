public enum Direction {
    EAST('o'),
    NORTH('n'),
    SOUTH('s'),
    WEST('w');

    private final char representation;

    private Direction(char representation) {
        this.representation = representation;
    }

    public static Direction fromChar(char representation) {
        for (Direction direction : values()) {
            if (direction.representation == representation) {
                return direction;
            }
        }

        throw new IllegalArgumentException("Ungültige Richtung!");
    }
}
