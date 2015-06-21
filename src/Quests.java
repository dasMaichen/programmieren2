import java.io.IOException;

/**
 * Created by mai on 05.06.15.
 */
public class Quests {
    /**
     * Verfügbare Quests (werden eingelsen)
     */
    public static final DoppeltVerketteteListe<Quest> VERFUEGBARE_QUESTS = questsEinlesen();

    /**
     * liest Quests ein
     * @return eine LIste von Quests
     */
    private static DoppeltVerketteteListe<Quest> questsEinlesen() {

        CsvReader<Quest> reader = new CsvReader<Quest>(Quest.class);

        try {
            DoppeltVerketteteListe<Quest> listeVonQuests = reader.einlesen("quest.csv");

            return listeVonQuests;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
