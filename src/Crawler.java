import java.util.Scanner;

/**
 * The type Crawler.
 */
public class Crawler {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        MazeGenerator mg = new RecursiveBacktracker();
        Level level = new Level(mg.generate(8,16 ));
        Scanner sc = new Scanner(System.in);
        Player p = new Player();
        while (!p.isDefeated()) {
            System.out.println(level);
            level.showPrompt();
            String input = sc.nextLine();
            if (input.isEmpty()) {
                System.out.println("Leere Eingabe.");



            //Als Option kann nun Inventar ausgewählt werden.
            } else if (input.equals("i")){

                System.out.println("Gold: "+p.getGold());

                if(p.inventar.isEmpty()){
                    System.out.println("Das Inventar ist leer.");
                }

                int anzahlSpielerItem = p.inventar.size();

                for (int i = 0; i < anzahlSpielerItem; i++) {
                    System.out.println(i+": "+p.inventar.get(i));
                }
                System.out.println("----------------------------------");

            } else {
                try {
                    Direction direction = Direction.fromChar(input.charAt(0));
                    if (!level.canMove(direction)) {
                        System.out.println("Ungültige Richtung");
                    } else {
                        level.move(direction);
                        level.getField().action(p);
                        level.clearField();
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }

            }
        }
    }
