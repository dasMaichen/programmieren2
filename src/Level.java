import java.util.Scanner;

/**
 * The type Level.
 */
public class Level {
    /**
     * The constant ATKBONUS.
     */
    private static final int ATKBONUS = 10;
    /**
     * The Map data.
     */
    private char[][] mapData;
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
    public Level(char[][] mapData) {
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
                if (mapData[y][x] == FieldType.START.getRepresentation()) {
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
     * @param c the direction
     * @return true, wenn die Richtung möglich ist
     */
    public boolean canMove(char c) {
        switch (c) {
            case 'n':
                return canMoveUp();
            case 's':
                return canMoveDown();
            case 'o':
                return canMoveRight();
            case 'w':
                return canMoveLeft();
            default:
                return false;
        }
    }

    /**
     * Move void.
     *
     * @param c the direction
     */
    public void move(char c) {
        switch (c) {
            case 'n':
                moveUp();
                break;
            case 's':
                moveDown();
                break;
            case 'o':
                moveRight();
                break;
            case 'w':
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
        return (y >= 0) && (x >=0) && (y < mapData.length) && (x < mapData[0].length) 
            && (mapData[y][x] == FieldType.PLAIN.getRepresentation() || mapData[y][x] == FieldType.FOUNTAIN.getRepresentation() || mapData[y][x] == FieldType.SMITHY.getRepresentation()
                || mapData[y][x] == FieldType.BATTLE.getRepresentation() || mapData[y][x] == FieldType.GOAL.getRepresentation() || mapData[y][x] == FieldType.START.getRepresentation());
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
            System.out.println("[n] Norden");
        }
        if (canMoveDown()) {
            System.out.println("[s] Süden");
        }
        if (canMoveRight()) {
            System.out.println("[o] Osten");
        }
        if (canMoveLeft()) {
            System.out.println("[w] Westen");
        }
        System.out.println("------------------------------");

    //Inventar ist hinzugekommen.
        System.out.println("[i] Inventar ansehen");

    }

    /**
     * Gets field.
     *
     * @return the field
     */
    private char getField() {
        return mapData[playerY][playerX];
    }

    /**
     * Clear field.
     */
    private void clearField() {
        char field = getField();
        if (field == FieldType.SMITHY.getRepresentation() || field == FieldType.FOUNTAIN.getRepresentation() || field == FieldType.BATTLE.getRepresentation()) {
            mapData[playerY][playerX] = FieldType.PLAIN.getRepresentation();
        }
    }

    /**
     * Handle current field event.
     *
     * @param p the player
     */
    public void handleCurrentFieldEvent(Player p) {
        char field = getField();
        if (field == FieldType.SMITHY.getRepresentation()) {
            p.setAtk(p.getAtk() + ATKBONUS);
            System.out.printf("Die ATK des Spielers wurde um %d erhöht.%n", ATKBONUS);
        } else if (field == FieldType.FOUNTAIN.getRepresentation()) {
            p.setHp(p.getMaxHp());
            System.out.println("Spieler wurde vollständig geheilt!");
        } else if(field == FieldType.BATTLE.getRepresentation()) {
            startBattle(p);
        } else if(field == FieldType.GOAL.getRepresentation()) {
            System.out.println("Herzlichen Glückwunsch! Sie haben gewonnen!");
            System.exit(0);
        }
        clearField();
    }

    /**
     * Random monster.
     *
     * @return the monster
     */
    private static Monster randomMonster() {
        Monster[] monsterFarm = {
                new Monster(),
                new ResistantMonster(),
                new WaitingMonster()
        };

        double bucketSize = 1.0 / monsterFarm.length;
        double bucket = Math.random() / bucketSize;
        int selectedMonster = (int) Math.floor(bucket);
        return monsterFarm[selectedMonster];
    }

    /**
     * Start battle.
     *
     * @param p the p
     */
    public void startBattle(Player p) {
        Creature m = randomMonster();

        Scanner sc = new Scanner(System.in);
        
        System.out.println("                 Kampf Start                    ");
        System.out.print(p);
        System.out.print(m);

        while(true) {
            System.out.println("------------------------------------------------");
            System.out.println("Mögliche Aktionen:");
            System.out.println("1 -> Angriff");
            System.out.printf("2 -> Item (%d verbleibend)%n", p.getRemainingItemUses());
            System.out.printf("3 -> Harter Schlag (%d AP, %d%% Selbstschaden)%n", Player.HARD_HIT_COST, Player.HARD_HIT_SELF_DAMAGE_PERCENT);
            System.out.printf("4 -> Feuerball (%d AP)%n", Player.FIREBALL_COST);
            System.out.printf("5 -> ATK auswürfeln (%d AP)%n", Player.REROLL_COST);
            System.out.println("Welche Aktion?: ");
            System.out.println("------------------------------------------------");
            String aktion = sc.nextLine();
            int playerDamage;
            switch (aktion) {
                case "1":
                    playerDamage = p.attack(m);
                    if (playerDamage == -1) {
                        System.out.println("Spieler verfehlt!");
                    } else {
                        System.out.printf("Spieler trifft und macht %d Schaden!%n", playerDamage);
                    }
                    break;
                case "2":
                    if (p.heal()) {
                        System.out.println("Spieler heilt sich!");
                    } else {
                        System.out.println("Nicht genügend Heiltränke!");
                    }
                    break;
                case "3":
                    playerDamage = p.hardHit(m);
                    if (playerDamage != -1) {
                        System.out.println("Spieler schlägt hart zu!");
                        System.out.printf("Spieler verursacht %d Schaden!%n", playerDamage);
                        System.out.printf("Spieler verursacht %d Selbstschaden!%n", (int) (Player.HARD_HIT_SELF_DAMAGE_PERCENT / 100.0 * playerDamage));
                    } else {
                        System.out.println("Nicht genügend AP!");
                    }
                    break;
                case "4":
                    playerDamage = p.fireball(m);
                    if (playerDamage != -1) {
                        System.out.println("Spieler schießt einen Feuerball!");
                        System.out.printf("Spieler verursacht %d Schaden!%n", playerDamage);
                    } else {
                        System.out.println("Nicht genügend AP!");
                    }
                    break;
                case "5":
                    if (p.reroll()) {
                        System.out.println("ATK neu ausgewürfelt!");
                        System.out.print("Neue Statuswerte: ");
                        System.out.print(p);
                    } else {
                        System.out.println("Nicht genügend AP!");
                    }
                    break;
                default:
                    System.out.println("Fehlerhafte Aktion!");
                    continue;
            }

            if (p.isDefeated()) {
                System.out.println("Game Over!");
                System.exit(0);
            } else if (m.isDefeated()) {
                System.out.println("Spieler gewinnt!");



                //Monsteritem werden dem Spieler übertragen.
                int anzahlMonsterItem = m.inventar.size();

                for (int i = 0; i < anzahlMonsterItem; i++) {
                    Item monsterItem = m.inventar.get(i);
                    p.inventar.add(monsterItem);
                }

                //Mostergold werden dem Spieler übertragen.
                p.gold = p.gold + m.getGold();

                break;
            }

            System.out.print(p);
            System.out.print(m);

            System.out.println("Monster greift an!");
            int monsterDamage = m.attack(p);
            if (monsterDamage == -1) {
                System.out.println("Monster verfehlt!");
            } else if (monsterDamage == -2) {
                System.out.println("Monster tut nichts.");
            } else {
                System.out.printf("Monster trifft und macht %d Schaden!%n", monsterDamage);
            }

            if (p.isDefeated()) {
                System.out.println("Game Over!");
                System.exit(0);
            }

            p.regenerateAp();

            System.out.print(p);
            System.out.print(m);
        }
    }

}
