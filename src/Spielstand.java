import java.io.*;

/**
 * Created by mai on 20.06.15.
 */
public class Spielstand {


    /**
     * DATEINNAME Konstante für den Spielstand.
     */
    private static final String DATEINAME = "spielstand";


    /**
     * Speichert Player und Questgeber.
     */
    public static void speichern() {

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(DATEINAME);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(Player.getInstance());
            objectOutputStream.writeObject(Questgeber.getInstance());

            objectOutputStream.close();

            System.out.println("Dein Spielstand wurde gespeichert.");
        } catch (IOException e) {
            System.err.println("Das Speichern hat nicht funktioniert. Schade.");
        }

    }

    /**
     * Laedt Spieler und Questgeber.
     */
    public static void laden() {

        try {
            FileInputStream fileInputStream = new FileInputStream(DATEINAME);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            Player player = (Player) objectInputStream.readObject();
            Player.setInstance(player);

            Questgeber questgeber = (Questgeber) objectInputStream.readObject();
            Questgeber.setInstance(questgeber);

            objectInputStream.close();
        } catch (ClassNotFoundException e) {
            System.err.println("Konnte Instanz nicht deserialisieren.");

        } catch (IOException e) {
            System.err.println("Spielstand konnte nicht geladen werden.");

        }
    }

    /**
     * Guckt, ob schon ein Spielstand existiert.
     *
     * @return true, wenn Spielstand schon vorhanden.
     */
    public static boolean istSpielstandVorhanden() {
        return new File(DATEINAME).exists();
    }


}
