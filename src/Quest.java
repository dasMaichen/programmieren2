/**
 * Created by mai on 03.06.15.
 */
public class Quest {

    private String name;
    private String  prequest;
    private String item;
    private  int quantity;
    private boolean status;

    public Quest(String name, String prequest, String item, int quantity,boolean status){
        this.name = name;
        this.prequest = prequest;
        this.item = item;
        this.quantity = quantity;
        this.status = status;

    }

    public Quest(){
        this("EineQuest","","Töpfe",3,false);
    }

    public String toString(){
        return ("Quest: "+this.name+"/n"
                + "Vorquest: "+this.prequest+"/n"
                + "benötigter Questgegenstand: "+this.item+" Anzahl: "+this.quantity+"/n"
                + "Status: "+this.status);
    }


    public boolean istQuestAbgeschlossen(Item questgegenstand, int anzahl){

        if (this.item.equals(questgegenstand.getName())){
        };
        return true;

    }



}
