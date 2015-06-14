import java.io.IOException;

/**
 * Created by mai on 05.06.15.
 */
public class Items {

    public static final DoppeltVerketteteListe<Item> VERFUEGBARE_ITEMS = itemsEinlesen();

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
