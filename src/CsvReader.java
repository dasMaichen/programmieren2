import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by mai on 03.06.15.
 */
public class CsvReader {

    public <T> T[] einlesen(String dateiname, Class<T> ausgabetyp) throws IOException {

        BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(dateiname),
                Charset.defaultCharset());

        String line = null;

        int arrayGroesse = 0;

        while (bufferedReader.readLine() != null) {

            arrayGroesse = arrayGroesse+1;
        }

        bufferedReader.close();


        bufferedReader = Files.newBufferedReader(Paths.get(dateiname),
                Charset.defaultCharset());

        bufferedReader.readLine();

        int i = 0;
        T[] arrayMitDingen = (T[]) Array.newInstance(ausgabetyp, arrayGroesse - 1);

        while ((line = bufferedReader.readLine()) != null) {

            String[] werte = line.split(", ");


            if(ausgabetyp.equals(Item.class)){
                int verkaufswert = Integer.parseInt(werte[1]);

                int gewicht = Integer.parseInt(werte[2]);

                Item item = new Item(werte[0],verkaufswert,gewicht);

                arrayMitDingen[i]= (T) item;
                i++;


            }else if (ausgabetyp.equals(Quest.class)){

                int anzahl = Integer.parseInt(werte[3]);

                Quest quest = new Quest(werte[0], werte[1], werte[2], anzahl, false);

                arrayMitDingen[i]= (T) quest;
                i++;
            }

        }return arrayMitDingen;
    }

}
