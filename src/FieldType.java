/**
 * Knstanten für Feldtypen.
 */

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
    FOUNTAIN('O') {
        @Override
        void action(Player player) {
            player.setHp(player.getMaxHp());
            System.out.println("Spieler wurde vollständig geheilt!");
        }
    },
    /**
     * The constant SMITHY.
     */
    SMITHY('T') {
        /**
         * The constant ATKBONUS.
         */
        private static final int ATKBONUS = 10;

        @Override
        void action(Player player) {
            player.setAtk(player.getAtk() + ATKBONUS);
            System.out.printf("Die ATK des Spielers wurde um %d erhöht.%n", ATKBONUS);
        }
    },
    /**
     * The constant BATTLE.
     */
    BATTLE('B') {
        @Override
        void action(Player player) {
            Kampf kampf = new Kampf();
            kampf.setVisible(true);
            Sync.waitForBattleEnd();
        }
    },
    /**
     * The constant GOAL.
     */
    GOAL('Z') {
        @Override
        void action(Player player) {

            boolean fertig = Questgeber.getInstance().zielVoraussetzungErfuellt();

            if (fertig) {
                System.out.println("Hurra! Du hast gewonnen!");
                System.exit(0);
            } else {
                System.out.println("Nee, nee, nee. Dir fehlen noch Quests!");
            }
        }
    },
    /**
     * The constant START.
     */
    START('S'),
    /**
     * The WALLCHAR.
     */
    WALL('#') {
        @Override
        public boolean isWalkable() {
            return false;
        }
    },
    /**
     * Der Questmaster
     */
    QUESTGEBER('Q') {
        @Override
        void action(Player player) {

            Questgeber.getInstance().questsVerwalten(player);
        }
    },
    /**
     * Konstante Haendler.
     */
    HAENDLER('H') {
        @Override
        public void action(Player player) {
            throw new UnsupportedOperationException();
        }
    };

    /**
     * the representation
     */
    private final char representation;

    /**
     * Konstruktor
     * @param representation representation
     */
    private FieldType(char representation) {
        this.representation = representation;
    }

    /**
     * Aktion
     * @param player der Spieler
     */
    void action(Player player) {

    }

    /**
     * Getter
     * @return representation
     */
    public char getRepresentation() {
        return representation;
    }


    /**
     * ob begehbar
     * @return wahr, wenn begehbar.
     */
    public boolean isWalkable() {
        return true;
    }
}
