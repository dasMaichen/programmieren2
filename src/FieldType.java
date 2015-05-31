
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
            Battle.start(player);
        }
    },
    /**
     * The constant GOAL.
     */
    GOAL('Z') {
        @Override
        void action(Player player) {
            System.out.println("Herzlichen Glückwunsch! Sie haben gewonnen!");
            System.exit(0);
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
    };

    private final char representation;

    private FieldType(char representation) {
        this.representation = representation;
    }

    void action(Player player) {

    }

    public char getRepresentation() {
        return representation;
    }

    public boolean isWalkable() {
        return true;
    }
}
