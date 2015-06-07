import java.util.Random;
import java.util.Scanner;

/**
 * Created by mai on 03.06.15.
 */
public class Questgeber {

    /**
     * Inventar.
     */
    public Questlog questlog = new Questlog();

    /**
     * Get-Methode
     *
     * @return Questlog
     */
    public Questlog getQuestlog() {
        return questlog;
    }

    /**
     * Questgeber Konstruktor. Questgeber bekommt Quests in seinem Log.
     */
    public Questgeber() {

        Random generator = new Random();

        for (int i = 0; i < Quests.VERFUEGBARE_QUESTS.size()-1; i++) {

            this.questlog.add(Quests.VERFUEGBARE_QUESTS.get(i));

            }
    }

    public void questAnbieten(Quest anzubietendeQuest, Player player) {

        if (anzubietendeQuest.getVorquest().equals("")) {

            player.questlog.add(anzubietendeQuest);

            //this.questlog.remove(anzubietendeQuest);

            System.out.println("Questmaster: Viel Erfolg!");

        } else {
            System.out.println("Questmaster: Du kannst das noch nicht machen.");
        }
    }




    public static void questsVerwalten(Player player, Questgeber questgeber) {

        boolean ende = false;

        do {
            System.out.println("Questmaster: Hallo. Was möchtest du?");

            Scanner scanner = new Scanner(System.in);

            System.out.println("------------------------------------------------");
            System.out.println("[q] - Mehr Quests!");
            System.out.println("[a] - Quest abschließen");
            System.out.println("[n] - Nichts");
            System.out.println("------------------------------------------------");

            String aktion = scanner.nextLine();
            switch (aktion) {
                case "q":

                    int anzahlQuests = questgeber.questlog.size();

                    System.out.println("Welche Quests möchtest du annehmen?");


                    for (int j = 0; j < questgeber.questlog.size(); j++) {

                        String vorquestName = questgeber.questlog.get(j).getVorquest();


                        int vorquestIndex = questgeber.questlog.indexOf(new Quest(vorquestName, "", null,
                                0, false, false));

                        if(questgeber.questlog.get(vorquestIndex).getStatus()||vorquestName.equals("")){
                            System.out.println(j+": "+questgeber.questlog.get(j));
                        }

                    }

                    System.out.println("[n] Ich: Nee... die sind mir zu gefährlich.");

                    System.out.println("------------------------------------------------");

                    String auswahlString = scanner.nextLine();

                    if (auswahlString.equals("n")) {
                        break;
                    }


                    int auswahl = Integer.parseInt(auswahlString);

                    if (auswahl < anzahlQuests) {
                        for (int j = 0; j < anzahlQuests; j++) {

                            if (auswahl == j) {
                                questgeber.questAnbieten((Quest) questgeber.questlog.get(j), player);
                            }
                        }

                        System.out.println("Questmaster: Bitte schön. Und viel Erfolg!");
                    } else {
                        System.out.println("Questmaster: Tut mir leid." +
                                "Ich weiß nicht, was du von mir willst.");
                    }

                    break;

                case "a":

                    int anzahlSpielerQuests = player.questlog.size();

                    int questindex = 0;

                    System.out.println("Questmaster: Welche möchtest du abschließen?");

                    for (questindex = 0; questindex < anzahlSpielerQuests; questindex++) {
                        System.out.println(questindex + ": " + player.questlog.get(questindex));
                    }

                    System.out.println("[n] Ich: Nein, ich hab noch nichts geschafft.");

                    System.out.println("------------------------------------------------");

                    String auswahlStringQuest = scanner.nextLine();

                    if (auswahlStringQuest.equals("n")) {
                        break;
                    }

                    int auswahlIntegerQuest = Integer.parseInt(auswahlStringQuest);

                    if (auswahlIntegerQuest < anzahlSpielerQuests) {

                        Quest abzuschliessendeQuest = questgeber.questlog.get(auswahlIntegerQuest);


                        if(abzuschliessendeQuest.getErfuellbarkeit()){
                            abzuschliessendeQuest.setAbgeschlossen();
                        }

                    } else {
                        System.out.println("Questmaster: Tut mir leid." +
                                "Ich weiß nicht, was du von mir willst.");
                    }
                    break;

                case "n":
                    System.out.println("Questmaster: Na gut. Auf wiedersehen!");
                    ende = true;
            }



        }while (ende=!true);
    }
}