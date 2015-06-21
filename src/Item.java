import java.io.Serializable;

/**
 * Created by dasMaichen on 14.05.15.
 * die Klasse Item
 */
public class Item implements Comparable<Item>, Serializable {


    /**
     * Name des Items
     */
    private String name;
    /**
     * Verkaufswert des Items
     */
    private Integer verkaufswert;
    /**
     * Gewicht des Items
     */
    private Integer gewicht;


    /**
     * Ein sinnvoller Konstruktor.
     *
     * @param name Name des Items
     * @param verkaufswert Verkaufswert des Items
     * @param gewicht Gewicht des Items
     */
    public Item(String name, int verkaufswert, int gewicht) {
        this.name = name;
        this.verkaufswert = verkaufswert;
        this.gewicht = gewicht;
    }


    //Ein nicht ganz so sinnvoller Konstruktor.

//    public Item() {
//
//        Random generator = new Random();
//
//        this.name = "";
//
//        //hier wird über eine for-Schleife zufällige Buchstaben aneinander gereiht.
//        for (int i = 0; i < 7; i++) {
//            this.name += (char) ('a' + generator.nextInt('z' - 'a'));
//        }
//
//        //Random Verkaufswert und Gewicht werden gesetzt.
//        this.verkaufswert = generator.nextInt(101);
//        this.gewicht = generator.nextInt(10001);
//    }


    @Override
    public String toString() {
        return (this.name + " - Verkaufswert: " + this.verkaufswert + " Gold - Gewicht: " + this.gewicht + " g");
    }


    //Vergleicht zuerst Name, dann Verkaufswert und dann Gewicht.
    @Override
    public int compareTo(Item anderesItem) {

        int ergebnis = this.name.compareTo(anderesItem.name);

        if (ergebnis != 0) {
            return ergebnis;
        }

        ergebnis = -this.verkaufswert.compareTo(anderesItem.verkaufswert);

        if (ergebnis != 0) {
            return ergebnis;
        }

        ergebnis = -this.gewicht.compareTo(anderesItem.gewicht);

        return ergebnis;
    }


    //Guckt ob das Object ein Instanz von Item ist
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Item)) {
            return false;
        }

        //wenn ja, dann vergleicht.
        int vergleichsergebnis = this.compareTo((Item) obj);
        if (vergleichsergebnis == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Gettermethode für Verkaufswert
     * @return den Verkaufswert
     */
    public int getVerkaufswert() {
        return this.verkaufswert;
    }

    /**
     * Gettermethode für Name
     * @return den Namen
     */
    public String getName() {
        return this.name;
    }


}


