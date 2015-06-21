import java.util.Scanner;

/**
 * The type Crawler.
 */
public class Crawler {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        Player p = Player.getInstance();
        MazeGenerator mg = new RecursiveBacktracker();
        Level level = new Level(mg.generate(8, 16));

        Scanner sc = new Scanner(System.in);

        System.out.println("----------------------------------");
        System.out.println("Was möchtest du tun?");
        System.out.println("[n] Neues Spiel starten.");
        System.out.println("[l] Spielstand laden.");
        System.out.println("----------------------------------");

        String aktion = sc.nextLine();

        switch (aktion) {
            case "l":
                if (Spielstand.istSpielstandVorhanden()) {
                    Spielstand.laden();
                } else {
                    System.out.println("Du hast noch keinen Spielstand!");
                }
                // fallthru
            case "n":

                p = Player.getInstance();

                while (!p.isDefeated()) {
                    System.out.println(level);
                    level.showPrompt();
                    String input = sc.nextLine();
                    if (input.isEmpty()) {
                        System.out.println("Leere Eingabe.");


                        //Als Option kann nun Inventar ausgewählt werden.
                    } else if (input.equals("i")) {

                        System.out.println("Gold: " + p.getGold());

                        if (p.getInventar().isEmpty()) {
                            System.out.println("Das Inventar ist leer.");
                        }

                        int anzahlSpielerItem = p.getInventar().size();

                        for (int i = 0; i < anzahlSpielerItem; i++) {
                            System.out.println(i + ": " + p.getInventar().get(i));
                        }
                        System.out.println("----------------------------------");

                    } else if (input.equals("q")) {
                        System.out.println("Deine Quest:");

                        if (p.getQuestlog().isEmpty()) {
                            System.out.println("Du hast in Moment keine Quests.");
                        }

                        int anzahlSpielerQuests = p.getQuestlog().size();

                        for (int i = 0; i < anzahlSpielerQuests; i++) {
                            System.out.println(i + ": " + p.getQuestlog().get(i));
                        }
                        System.out.println("----------------------------------");

                    } else if (input.equals("s")) {
                        Spielstand.speichern();

                    } else if (input.equals("e")) {
                        System.out.println("Das Spiel wurde beendet.");
                        System.exit(0);
                    } else {
                        try {
                            Direction direction = Direction.fromChar(input.charAt(0));
                            if (!level.canMove(direction)) {
                                System.out.println("Ungültige Richtung");
                            } else {
                                level.move(direction);
                                level.getField().action(p);
                                level.clearField();
                            }
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                    }

                }
                break;

            default:
                System.out.println("Keine gültige Eingabe.");
        }


    }
}
