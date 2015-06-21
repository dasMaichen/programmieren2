import java.io.Serializable;

/**
 * Created by mai on 03.06.15.
 */
public class Quest implements Comparable<Quest>, Serializable {

    /**
     * der Questname
     */
    private String name;
    /**
     * Name des Vorquest
     */
    private String vorquest;
    /**
     * benötigtes Item
     */
    private String item;
    /**
     * Anzahl des Items
     */
    private int anzahl;

    /**
     * ob Quest fertig
     */
    private boolean fertig;
    /**
     * ob Abgeschlossen oder nicht
     */
    private boolean status;


    /**
     * Konstruktor für Quest
     * @param name Name
     * @param vorquest Vorquest
     * @param item Item
     * @param anzahl Anzahl
     * @param fertig ob erfüllt
     * @param status ob abgeschlossen
     */
    public Quest(String name, String vorquest, String item, int anzahl, boolean fertig, boolean status) {
        this.name = name;
        this.vorquest = vorquest;
        this.item = item;
        this.anzahl = anzahl;
        this.fertig = fertig;
        this.status = status;

    }

    /**
     * parameterloser Konstruktor
     */
    public Quest() {
    }

    @Override
    public String toString() {
        return ("Quest: " + this.name + "\n "
                + "Vorquest: " + this.vorquest + "\n"
                + "benötigter Questgegenstand: " + this.item + " Anzahl: " + this.anzahl + "\n"
                + "Status: " + this.status);
    }


    /**
     * Gettermethode für Name
     * @return Name
     */
    public String getName() {
        return name;
    }

    /**
     * guckt ob Quest erfüllt
     * @return fertig
     */
    public boolean getErfuellbarkeit() {
        return fertig;
    }

    /**
     * Gettermethode
     * @return status
     */
    public boolean getStatus() {
        return status;
    }

    /**
     * Getter
     * @return Vorquest
     */
    public String getVorquest() {
        return vorquest;
    }

    /**
     * Setter setzt status
     */
    public void setAbgeschlossen() {
        status = true;
    }

    /**
     * Gettermethode
     * @return anzahl
     */
    public int getAnzahl() {
        return anzahl;
    }

    /**
     * Getter
     * @return Item
     */
    public String getItem() {
        return item;
    }

    @Override
    public int compareTo(Quest andereQuest) {
        int ergebnis = this.name.compareTo(andereQuest.name);
        return ergebnis;
    }


    @Override
    public boolean equals(Object obj) {
        int gleichheit = 1;
        if (obj instanceof Quest) {
            gleichheit = ((Quest) obj).compareTo(this);
        }
        if (gleichheit == 0) {
            return true;
        }
        return false;
    }
}
