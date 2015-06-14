import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by mai on 03.06.15.
 */
public class CsvReader<T> {

    private final Class<T> ausgabetyp;

    public CsvReader(Class<T> ausgabetyp){
        this.ausgabetyp = ausgabetyp;
    }

    public DoppeltVerketteteListe<T> einlesen(String dateiname) throws IOException {

        BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(dateiname),
                Charset.defaultCharset());

        String line = null;

        bufferedReader.readLine();


        DoppeltVerketteteListe<T> listeVonDingen =  new DoppeltVerketteteListe<>();

        while ((line = bufferedReader.readLine()) != null) {

            String[] werte = line.split(", ");


            if(ausgabetyp.equals(Item.class)){
                int verkaufswert = (int)Double.parseDouble(werte[1]);

                int gewicht = (int)Double.parseDouble(werte[2]);

                Item item = new Item(werte[0],verkaufswert,gewicht);

                listeVonDingen.add((T) item);


            }else if (ausgabetyp.equals(Quest.class)){

                int anzahl = Integer.parseInt(werte[3]);

                Quest quest = new Quest(werte[0], werte[1], werte[2], anzahl, false, false);

               listeVonDingen.add((T) quest);

            }

        }return listeVonDingen;
    }

}
