import java.util.EnumSet;

/**
 * The type Level.
 */
public class Level {
    /**
     * The Map data.
     */
    private Field[][] mapData;
    /**
     * The Player x coordinate.
     */
    private int playerX;
    /**
     * The Player y coordinate.
     */
    private int playerY;

    /**
     * Instantiates a new Level.
     *
     * @param mapData the map data
     */
    public Level(Field[][] mapData) {
        if (mapData.length < 3 || mapData[0].length < 3) {
            throw new IllegalArgumentException("Invalid Map Data");
        }
        this.mapData = mapData;
        if (!findStart()) {
            throw new IllegalArgumentException("Invalid Map Data: No starting position");
        }
    }

    /**
     * Find start.
     *
     * @return true, wenn die Startposition gefunden wuerde
     */
    private boolean findStart() {
        for (int y = 0; y < mapData.length; y++) {
            for (int x = 0; x < mapData[0].length; x++) {
                if (mapData[y][x].getFieldType() == FieldType.START) {
                    playerX = x;
                    playerY = y;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * To string.
     *
     * @return the string
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < mapData.length; ++y) {
            for (int x = 0; x < mapData[0].length; ++x) {
                if (y == playerY && x == playerX) {
                    sb.append(FieldType.PLAYER_CHAR.getRepresentation());
                } else {
                    sb.append(mapData[y][x]);
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Can move.
     *
     * @param direction the direction
     * @return true, wenn die Richtung möglich ist
     */
    public boolean canMove(Direction direction) {
        switch (direction) {
            case NORTH:
                return canMoveUp();
            case SOUTH:
                return canMoveDown();
            case EAST:
                return canMoveRight();
            case WEST:
                return canMoveLeft();
            default:
                return false;
        }
    }

    /**
     * Move void.
     *
     * @param direction the direction
     */
    public void move(Direction direction) {
        switch (direction) {
            case NORTH:
                moveUp();
                break;
            case SOUTH:
                moveDown();
                break;
            case EAST:
                moveRight();
                break;
            case WEST:
                moveLeft();
                break;
        }
    }


    /**
     * Is walkable position.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @return true, wenn das Feld x,y begehbar ist
     */
    public boolean isWalkablePosition(int x, int y) {
        return (y >= 0) && (x >= 0) && (y < mapData.length) && (x < mapData[0].length)
                && mapData[y][x].getFieldType().isWalkable();
    }

    /**
     * Can move up.
     *
     * @return true, wenn mögliche Bewegung
     */
    public boolean canMoveUp() {
        return isWalkablePosition(playerX, playerY - 1);
    }

    /**
     * Can move down.
     *
     * @return true, wenn mögliche Bewegung
     */
    public boolean canMoveDown() {
        return isWalkablePosition(playerX, playerY + 1);
    }

    /**
     * Can move left.
     *
     * @return true, wenn mögliche Bewegung
     */
    public boolean canMoveLeft() {
        return isWalkablePosition(playerX - 1, playerY);
    }

    /**
     * Can move right.
     *
     * @return true, wenn mögliche Bewegung
     */
    public boolean canMoveRight() {
        return isWalkablePosition(playerX + 1, playerY);
    }

    /**
     * Move up.
     */
    public void moveUp() {
        if (canMoveUp()) {
            playerY--;
        }
    }

    /**
     * Move down.
     */
    public void moveDown() {
        if (canMoveDown()) {
            playerY++;
        }
    }

    /**
     * Move left.
     */
    public void moveLeft() {
        if (canMoveLeft()) {
            playerX--;
        }
    }

    /**
     * Move right.
     */
    public void moveRight() {
        if (canMoveRight()) {
            playerX++;
        }
    }

    /**
     * Show prompt.
     */
    public void showPrompt() {
        System.out.println("------------------------------");
        System.out.println("Richtung? ");
        if (canMoveUp()) {
            System.out.println("[o] Norden");
        }
        if (canMoveDown()) {
            System.out.println("[u] Süden");
        }
        if (canMoveRight()) {
            System.out.println("[l] Osten");
        }
        if (canMoveLeft()) {
            System.out.println("[r] Westen");
        }
        System.out.println("------------------------------");

        //Inventar ist hinzugekommen.
        System.out.println("[i] Inventar ansehen");
        System.out.println("[q] Questlog ansehen");
        System.out.println("");
        System.out.println("[s] Spielstand speichern");
        System.out.println("[e] Spiel beenden");

    }

    /**
     * Gets field.
     *
     * @return the field
     */
    public Field getField() {
        return mapData[playerY][playerX];
    }

    /**
     * Clear field.
     */
    public void clearField() {
        Field field = getField();
        if (EnumSet.of(FieldType.SMITHY).contains(field.getFieldType())) {
            mapData[playerY][playerX] = new Field(FieldType.PLAIN);
        }
    }

}
