import java.util.Objects;

/**
 * Created by mai on 03.06.15.
 */
public class Quest implements Comparable<Quest> {

    private String name;
    private String  vorquest;
    private String item;
    private  int anzahl;
    private boolean fertig;
    private boolean status;


    public Quest(String name, String vorquest, String item, int anzahl,boolean fertig, boolean status){
        this.name = name;
        this.vorquest = vorquest;
        this.item = item;
        this.anzahl= anzahl;
        this.fertig = fertig;
        this.status = status;

    }

    public Quest(){
    }

    public String toString(){
        return ("Quest: "+this.name+"\n "
                + "Vorquest: "+this.vorquest+"\n"
                + "benötigter Questgegenstand: "+this.item+" Anzahl: "+this.anzahl+"\n"
                + "Status: "+this.status);
    }


    public boolean istErfüllt(Item questgegenstand, int anzahl){

        if (this.item.equals(questgegenstand.getName())){

        };
        return true;

    }


    public String getName(){return name;}

    public boolean getErfuellbarkeit(){
        return fertig;
    }

    public boolean getStatus(){
        return status;
    }

    public String getVorquest(){
        return vorquest;
    }

    public void setAbgeschlossen(){
        status = true;
    }


    @Override
    public int compareTo(Quest andereQuest) {
        int ergebnis = this.name.compareTo(andereQuest.name);
        return ergebnis;
    }


    @Override
    public boolean equals(Object obj) {
        int gleichheit=1;
        if(obj instanceof Quest){
            gleichheit = ((Quest) obj).compareTo(this);
        }
        if(gleichheit==0){
            return true;
        }return false;
    }
}
