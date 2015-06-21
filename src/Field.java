/**
 * die Klasse Feld
 */
public class Field {

    /**
     * Feldtyp
     */
    private final FieldType fieldType;

    /**
     * Konstruktor
     * @param fieldType Typ des Feldes
     */
    public Field(FieldType fieldType) {
        this.fieldType = fieldType;
    }

    /**
     * Gettermethode
     * @return Typ des Feldes
     */
    public FieldType getFieldType() {
        return fieldType;
    }

    @Override
    public String toString() {
        return String.valueOf(getFieldType().getRepresentation());
    }

    /**
     * löst Feldaktionen aus
     * @param player wenn Spieler auf dieses Feld landet.
     */
    public void action(Player player) {
        getFieldType().action(player);
    }
}
