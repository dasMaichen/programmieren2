/**
 * Created by mai on 03.06.15.
 */
public class Questknoten {


    private final Quest quest;
    private Questknoten nachfolger;


    /**
     *
     * Konstruktor erzeugt neue Instanzen von Questknoten.
     *
     */
    public Questknoten(Quest quest){
        this.quest = quest;
    }


    public void setNachfolger(Questknoten nachfolgerKnoten){

        this.nachfolger = nachfolgerKnoten;
    }


    public Questknoten getNachfolger() {
        return this.nachfolger;
    }

    public Quest getQuest() {
        return this.quest;
    }
}
