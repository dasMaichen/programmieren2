
public enum FieldType {
    /**
     * The constant PLAIN.
     */
    PLAIN('.'),
    /**
     * The constant PLAYER_CHAR.
     */
    PLAYER_CHAR('P'),
    /**
     * The constant FOUNTAIN.
     */
    FOUNTAIN('O'),
    /**
     * The constant SMITHY.
     */
    SMITHY('T'),
    /**
     * The constant BATTLE.
     */
    BATTLE('B'),
    /**
     * The constant GOAL.
     */
    GOAL('Z'),
    /**
     * The constant START.
     */
    START('S'),
    /**
     * The WALLCHAR.
     */
    WALL('#');

    private final char representation;

    private FieldType(char representation) {
        this.representation = representation;
    }

    public char getRepresentation() {
        return representation;
    }
}
