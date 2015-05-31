public class Field {

    private final FieldType fieldType;

    public Field(FieldType fieldType) {
        this.fieldType = fieldType;
    }

    public FieldType getFieldType() {
        return fieldType;
    }

    @Override
    public String toString() {
        return String.valueOf(getFieldType().getRepresentation());
    }

    public void action(Player player) {
        getFieldType().action(player);
    }
}
