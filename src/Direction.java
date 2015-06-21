/**
 * Enum für Richtungen.
 */
public enum Direction {
    /**
     * Osten
     */
    EAST('r'),
    /**
     * Norden
     */
    NORTH('o'),
    /**
     * Süden
     */
    SOUTH('u'),
    /**
     * Westen
     */
    WEST('l');

    /**
     * Char die die Richtung repräsentieren
     */
    private final char representation;

    /**
     *
     * @param representation die Richtungen
     */
    private Direction(char representation) {
        this.representation = representation;
    }

    /**
     * Richtung
     * @param representation die Himmelrichtungen
     * @return Bewegungsrichtung
     */
    public static Direction fromChar(char representation) {
        for (Direction direction : values()) {
            if (direction.representation == representation) {
                return direction;
            }
        }

        throw new IllegalArgumentException("Ungültige Richtung!");
    }
}
