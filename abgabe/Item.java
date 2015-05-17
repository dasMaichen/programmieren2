import java.util.Random;

/**
 * @author Mai Kuginuki 12345678 Gruppe 6b
 *
 * Created by dasMaichen on 14.05.15.
 */


/*Die Lösung ohne Einschränkung wäre so:

    public class Item implements Comparable {

    [...]

    dann müsste man bei der Methode comparable:

     public int compareTo(Object anderesObjekt) {
        Item anderesItem = (Item) anderesObjekt;

            --> ist kein Item wird Exception geworfen (ClassCastException).

     [...]

       }
     }
  */



public class Item implements Comparable<Item> {


    private String name;
    private Integer verkaufswert;
    private Integer gewicht;


    /**
     * Ein sinnvoller Konstruktor.
     *
     * @param name
     * @param verkaufswert
     * @param gewicht
     */
    public Item(String name, int verkaufswert, int gewicht) {
        this.name = name;
        this.verkaufswert = verkaufswert;
        this.gewicht = gewicht;
    }


    //Ein nicht ganz so sinnvoller Konstruktor.

    public Item() {

        Random generator = new Random();

        this.name = "";

        //hier wird über eine for-Schleife zufällige Buchstaben aneinander gereiht.
        for (int i = 0; i < 7; i++) {
            this.name += (char) ('a' + generator.nextInt('z' - 'a'));
        }

        //Random Verkaufswert und Gewicht werden gesetzt.
        this.verkaufswert = generator.nextInt(101);
        this.gewicht = generator.nextInt(10001);
    }


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
        if (vergleichsergebnis==0){
            return true;
        }else{
            return false;
        }


    }
}
