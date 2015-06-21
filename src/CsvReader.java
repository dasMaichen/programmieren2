import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by mai on 03.06.15.
 * @param <T> Type von der gelesenen Datei.
 */
public class CsvReader<T> {

    /**
     * Typ der gelesenen Datensätze.
     */
    private final Class<T> ausgabetyp;

    /**
     * Knostruktor füt CSV Reader.
     * @param ausgabetyp Ausgabetyp
     */
    public CsvReader(Class<T> ausgabetyp) {
        this.ausgabetyp = ausgabetyp;
    }

    /**
     * Liest die Daten ein
     * @param dateiname Dateiname
     * @return Listen von Dingen
     * @throws IOException wenn Datei leer.
     */
    public DoppeltVerketteteListe<T> einlesen(String dateiname) throws IOException {

        BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(dateiname),
                Charset.defaultCharset());

        String line = null;

        bufferedReader.readLine();


        DoppeltVerketteteListe<T> listeVonDingen = new DoppeltVerketteteListe<>();

        while ((line = bufferedReader.readLine()) != null) {

            String[] werte = line.split(", ");


            if (ausgabetyp.equals(Item.class)) {
                int verkaufswert = (int) Double.parseDouble(werte[1]);

                int gewicht = (int) Double.parseDouble(werte[2]);

                Item item = new Item(werte[0], verkaufswert, gewicht);

                listeVonDingen.add((T) item);


            } else if (ausgabetyp.equals(Quest.class)) {

                int anzahl = Integer.parseInt(werte[3]);

                Quest quest = new Quest(werte[0], werte[1], werte[2], anzahl, false, false);

                listeVonDingen.add((T) quest);

            }

        }
        return listeVonDingen;
    }

}
