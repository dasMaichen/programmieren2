import java.util.Random;
import java.util.Scanner;

/**
 * Created by mai on 31.05.15.
 */
public class Haendler {
    /**
     * Inventar.
     */
    private Inventar inventar = new Inventar();

    /**
     * Haendler Konstruktor. Haendler bekommt random Items in seinem Inventar.
     */
    public Haendler() {

        Random generator = new Random();

        int anzahlZufaelligerItems = 1 + generator.nextInt(7);

        for (int i = 0; i < anzahlZufaelligerItems; i++) {
            int j = generator.nextInt(Items.VERFUEGBARE_ITEMS.size() - 1);
            this.inventar.add(Items.VERFUEGBARE_ITEMS.get(j));
        }
    }

    /**
     * Die Methode handeln stellt das Verfahren beim Handeln.
     *
     * @param player   der Spieler
     * @param haendler der Haendler.
     */
    public static void handeln(Player player, Haendler haendler) {
        boolean ende = false;

        do {
            System.out.println("Händler: Hallo. Möchtest du etwas kaufen oder verkaufen?");

            Scanner scanner = new Scanner(System.in);

            System.out.println("------------------------------------------------");
            System.out.println("Du hast gerade: " + player.getGold() + " Gold");
            System.out.println("[k] - Kaufen ");
            System.out.println("[v] - Verkaufen");
            System.out.println("[n] - Nichts");
            System.out.println("------------------------------------------------");

            String aktion = scanner.nextLine();
            switch (aktion) {
                case "k":

                    int anzahlHaendlerItem = haendler.inventar.size();

                    int inventarindex = 0;

                    System.out.println("Du hast gerade: " + player.getGold() + " Gold");
                    System.out.println("Was möchtest du kaufen?");

                    for (inventarindex = 0; inventarindex < anzahlHaendlerItem; inventarindex++) {
                        System.out.println(inventarindex + ": " + haendler.inventar.get(inventarindex));
                    }

                    System.out.println("[n] Ich: (Da ist ja nur Plunder...) Äh... nichts.");

                    System.out.println("------------------------------------------------");

                    String auswahlString = scanner.nextLine();

                    if (auswahlString.equals("n")) {
                        break;

                    }
                    int auswahl = Integer.parseInt(auswahlString);

                    if (auswahl < anzahlHaendlerItem) {
                        for (int j = 0; j < anzahlHaendlerItem; j++) {

                            if (auswahl == j) {
                                haendler.verkaufen((Item) haendler.inventar.get(j), player);
                            }
                        }
                    } else {
                        System.out.println("Händler: Tut mir leid."
                                + "Ich weiß nicht, was du von mir willst.");
                    }

                    break;


                case "v":

                    int anzahlSpielerItem = player.getInventar().size();

                    inventarindex = 0;

                    System.out.println("Was möchtest du verkaufen? (Der Händler gibt dir 30% weniger "
                            + "als der eigentliche Verkaufspreis)");

                    for (inventarindex = 0; inventarindex < anzahlSpielerItem; inventarindex++) {
                        System.out.println(inventarindex + ": " + player.getInventar().get(inventarindex));
                    }

                    System.out.println("[n] Ich: Nein, ich will nichts verkaufen. Ich mag dieses Zeugs.");

                    System.out.println("------------------------------------------------");

                    String auswahlStringVerkauf = scanner.nextLine();

                    if (auswahlStringVerkauf.equals("n")) {
                        break;
                    }

                    int auswahlIntegerVerkauf = Integer.parseInt(auswahlStringVerkauf);

                    if (auswahlIntegerVerkauf < anzahlSpielerItem) {

                        for (int j = 0; j < anzahlSpielerItem; j++) {

                            if (auswahlIntegerVerkauf == j) {
                                haendler.kaufen((Item) player.getInventar().get(j), player);
                            }
                        }
                    } else {
                        System.out.println("Händler: Tut mir leid."
                                + "Ich weiß nicht, was du von mir willst.");
                    }
                    break;

                case "n":
                    System.out.println("Händler: Auf wiedersehen!");
                    ende = true;
                    break;

                default:
                    System.out.println("Händler: Entschuldige, ich habe dich nicht verstanden.");
            }
        } while (ende != true);
    }

    /**
     * Get-Methode
     *
     * @return inventar
     */
    public Inventar getInventar() {
        return inventar;
    }

    /**
     * Diese Methode lässt den Spieler vom Händler einen Gegenstand kaufen,
     * wenn genügend Gold vorhanden.
     *
     * @param zuverkaufendesItem Item, das verkauft werden soll
     * @param player             Spieler ist der Käufer.
     */
    public void verkaufen(Item zuverkaufendesItem, Player player) {

        if (player.getGold() >= zuverkaufendesItem.getVerkaufswert()) {

            player.setGold(player.getGold() - zuverkaufendesItem.getVerkaufswert());

            player.getInventar().add(zuverkaufendesItem);

            this.inventar.remove(zuverkaufendesItem);

            System.out.println("Händler: Danke für den Einkauf.");

        } else {
            System.out.println("Händler: Du kannst dir diesen Gegenstand nicht leisten.");
        }
    }

    /**
     * Diese Methode werden Items vom Spieler gekauft mit geringerem Preis als der Verkaufswert
     * des Gegenstandes.
     *
     * @param zukaufendesItem Spieler Item, das gekauft werden soll.
     * @param player          Der Spieler.
     */
    public void kaufen(Item zukaufendesItem, Player player) {

        player.setGold(player.getGold() + (int) (70 * zukaufendesItem.getVerkaufswert() / 100.0));

        this.inventar.add(zukaufendesItem);

        player.getInventar().remove(zukaufendesItem);

        System.out.println("Händler: Vielen Dank.");
    }
}
