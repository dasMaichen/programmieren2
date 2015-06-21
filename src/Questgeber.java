import java.io.Serializable;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by mai on 03.06.15.
 * Klasse Questgeber
 */
public class Questgeber implements Serializable {

    /**
     * Macht neue Instanz von Questgeber
     */
    private static Questgeber instance = new Questgeber();
    /**
     * Inventar.
     */
    private Questlog questlog = new Questlog();

    /**
     * Questgeber Konstruktor. Questgeber bekommt Quests in seinem Log.
     */
    public Questgeber() {

        Random generator = new Random();

        for (int i = 0; i < Quests.VERFUEGBARE_QUESTS.size() - 1; i++) {

            this.questlog.add(Quests.VERFUEGBARE_QUESTS.get(i));

        }
    }

    /**
     * Getter
     *
     * @return Instanz
     */
    public static Questgeber getInstance() {
        return instance;
    }

    /**
     * Setter
     *
     * @param questgeber der Questgeber
     */
    public static void setInstance(Questgeber questgeber) {
        instance = questgeber;
    }

    /**
     * Get-Methode
     *
     * @return Questlog
     */
    public Questlog getQuestlog() {
        return questlog;
    }

    /**
     * Methode zum Quest anbieten
     *
     * @param anzubietendeQuest anzubietende Quests
     * @param player            der Spieler
     */
    public void questAnbieten(Quest anzubietendeQuest, Player player) {

        if (anzubietendeQuest.getVorquest().equals("")) {

            player.getQuestlog().add(anzubietendeQuest);

            this.questlog.remove(anzubietendeQuest);

        } else {
            System.out.println("Questmaster: Du kannst das noch nicht machen.");
        }
    }

    /**
     * guckt ob Zielvorrausetzung erfüllt
     *
     * @return wahr, wenn erfüllt
     */
    public boolean zielVoraussetzungErfuellt() {

        int anzahlErfuellterAuftraege = 0;

        for (int i = 0; i < questlog.size(); i++) {

            if (questlog.get(i).getStatus() == true) {
                anzahlErfuellterAuftraege = anzahlErfuellterAuftraege + 1;
            }
        }

        if (anzahlErfuellterAuftraege == questlog.size()) {
            return true;
        }

        return false;
    }

    /**
     * Methode zum Quest abschließen
     *
     * @param player                der Spieler
     * @param abzuschliessendeQuest der abzuschließende Quest
     * @return wahr, wenn Quest abgeschlossen
     */
    public boolean questAbschliessen(Player player, Quest abzuschliessendeQuest) {

        int anzahlQuestgegenstand = 0;

        boolean questgegenstand;

        for (int i = 0; i < player.getInventar().size(); i++) {
            questgegenstand = player.getInventar().get(i).getName().equals(abzuschliessendeQuest.getItem());

            if (questgegenstand) {
                anzahlQuestgegenstand = anzahlQuestgegenstand + 1;
            }
        }

        if (anzahlQuestgegenstand == abzuschliessendeQuest.getAnzahl()) {

            for (int j = 0; j < player.getInventar().size(); j++) {

                questgegenstand = player.getInventar().get(j).getName().equals(abzuschliessendeQuest.getItem());

                if (questgegenstand) {
                    player.getInventar().remove(j);
                }
            }

            this.questlog.add(abzuschliessendeQuest);
            abzuschliessendeQuest.setAbgeschlossen();
            player.getQuestlog().remove(abzuschliessendeQuest);

            return true;
        }
        return false;
    }

    /**
     * Methode zum Quest verwalten
     *
     * @param player der Spieler
     */
    public void questsVerwalten(Player player) {

        boolean ende = false;

        do {
            System.out.println("Questmaster: Hallo. Was möchtest du?");

            Scanner scanner = new Scanner(System.in);

            System.out.println("------------------------------------------------");
            System.out.println("[q] - Mehr Quests!");
            System.out.println("[a] - Quest abschließen");
            System.out.println("[s] - Queststatus abfragen");
            System.out.println("[n] - Nichts");
            System.out.println("------------------------------------------------");

            String aktion = scanner.nextLine();
            switch (aktion) {
                case "q":

                    questAnnehmen(player, scanner);

                    break;

                case "a":

                    int anzahlSpielerQuests = player.getQuestlog().size();

                    int questindex = 0;

                    System.out.println("Questmaster: Welche möchtest du abschließen?");

                    for (questindex = 0; questindex < anzahlSpielerQuests; questindex++) {

                        if (questlog.get(questindex).getStatus() == false) {
                            System.out.println(questindex + ": " + player.getQuestlog().get(questindex));
                        }
                    }

                    System.out.println("[n] Ich: Nein, ich hab noch nichts geschafft.");

                    System.out.println("------------------------------------------------");

                    String auswahlStringQuest = scanner.nextLine();

                    int auswahlIntegerQuest = Integer.parseInt(auswahlStringQuest);

                    if (auswahlIntegerQuest < anzahlSpielerQuests) {

                        Quest abzuschliessendeQuest = player.getQuestlog().get(auswahlIntegerQuest);

                        if (questAbschliessen(player, abzuschliessendeQuest)) {

                            System.out.println("Questmaster: Du hast " + abzuschliessendeQuest.getName()
                                    + " erfolgreich abgeschlossen!");
                        } else {
                            System.out.println("Questmaster: Nicht so voreilig, da fehlt noch was.");
                        }

                    } else {
                        System.out.println("Questmaster: Tut mir leid." + "Ich weiß nicht, was du von mir willst.");
                    }
                    break;

                case "s":

                    System.out.println("------------------------------------------------");
                    System.out.println("Abgeschlossene Quests:");

                    for (int i = 0; i < questlog.size(); i++) {
                        boolean status = questlog.get(i).getStatus();

                        if (status) {
                            System.out.println(i + ": " + questlog.get(i).getName());
                        }
                    }

                    System.out.println("------------------------------------------------");
                    System.out.println("Offene Quests:");
                    for (int j = 0; j < questlog.size(); j++) {
                        boolean status = questlog.get(j).getStatus();
                        if (status == false) {
                            System.out.println(j + ": " + questlog.get(j).getName());
                        }
                    }

                    System.out.println("------------------------------------------------");
                    System.out.println("Angenommene Quests:");
                    for (int k = 0; k < player.getQuestlog().size(); k++) {
                        System.out.println(k + ": " + player.getQuestlog().get(k).getName());

                    }

                    System.out.println("------------------------------------------------");
                    break;


                case "n":
                    System.out.println("Questmaster: Na gut. Auf wiedersehen!");
                    ende = true;

                    break;

                default:
                    System.out.println("Questmaster: Was? Was hast du gesagt?");
            }
        } while (ende == false);
    }


    /**
     * Methode zum Quest annehmen
     *
     * @param player  der Spieler
     * @param scanner Scanner
     */
    private void questAnnehmen(Player player, Scanner scanner) {
        System.out.println("Welche Quests möchtest du annehmen?");


        for (int j = 0; j < questlog.size(); j++) {

            String vorquestName = questlog.get(j).getVorquest();


            int vorquestIndex = questlog.indexOf(new Quest(vorquestName, "", null, 0, false, false));

            if (vorquestIndex == -1) {
                System.out.println("Questmaster: Ich habe nichts mehr für dich.");
            }

            if (vorquestName.equals("") || questlog.get(vorquestIndex).getStatus()) {
                System.out.println(j + ": " + questlog.get(j));
            }

        }

        System.out.println("[n] Ich: Nee... die sind mir zu gefährlich.");

        System.out.println("------------------------------------------------");

        String auswahlString = scanner.nextLine();

        if (auswahlString.equals("n")) {
            return;
        }


        int auswahl = Integer.parseInt(auswahlString);

        if (auswahl < questlog.size()) {
            for (int j = 0; j < questlog.size(); j++) {

                if (auswahl == j) {
                    questAnbieten(questlog.get(j), player);
                }
            }

            System.out.println("Questmaster: Bitte schön. Und viel Erfolg!");
        } else {
            System.out.println("Questmaster: Tut mir leid." + "Ich weiß nicht, was du von mir willst.");
        }
    }
}
