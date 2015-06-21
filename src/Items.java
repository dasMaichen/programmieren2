import java.io.IOException;

/**
 * Created by mai on 05.06.15.
 */
public class Items {

    /**
     * Verfügbare Items, die werden vom csv-Datein gelesen.
     */
    public static final DoppeltVerketteteListe<Item> VERFUEGBARE_ITEMS = itemsEinlesen();

    /**
     * Liest Items ein
     * @return eine doppelverkettete Liste von Items
     */
    private static DoppeltVerketteteListe<Item> itemsEinlesen() {

        CsvReader<Item> reader = new CsvReader<Item>(Item.class);

        try {
            DoppeltVerketteteListe<Item> listeVonItems = reader.einlesen("item.csv");

            return listeVonItems;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
