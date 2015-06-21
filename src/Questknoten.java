import java.io.Serializable;

/**
 * Created by mai on 03.06.15.
 */
public class Questknoten implements Serializable {

    /**
     * ein Quest
     */
    private final Quest quest;
    /**
     * Nachfolgender Questknoten
     */
    private Questknoten nachfolger;


    /**
     * Konstruktor erzeugt neue Instanzen von Questknoten.
     * @param quest ein Quest
     */
    public Questknoten(Quest quest) {
        this.quest = quest;
    }

    /**
     * Getter
     * @return Nachfolger
     */
    public Questknoten getNachfolger() {
        return this.nachfolger;
    }

    /**
     * Setter
     * @param nachfolgerKnoten setzt Nachfolger
     */
    public void setNachfolger(Questknoten nachfolgerKnoten) {

        this.nachfolger = nachfolgerKnoten;
    }

    /**
     * Getter
     * @return Quest
     */
    public Quest getQuest() {
        return this.quest;
    }
}
