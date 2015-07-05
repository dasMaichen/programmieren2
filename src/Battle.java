import java.util.Scanner;

/**
 * Battle Klasse.
 */

public class Battle {

    public static void start(Player p) {
        Creature m = randomMonster();

        Scanner sc = new Scanner(System.in);

        System.out.println("                 Kampf Start                    ");
        System.out.print(p);
        System.out.print(m);

        while (true) {

            System.out.println("Monster greift an!");
            int monsterDamage = m.attack(p);
            if (monsterDamage == -1) {
                System.out.println("Monster verfehlt!");
            } else if (monsterDamage == -2) {
                System.out.println("Monster tut nichts.");
            } else {
                System.out.printf("Monster trifft und macht %d Schaden!%n", monsterDamage);
            }

            if (p.isDefeated()) {
                System.out.println("Game Over!");
                System.exit(0);
            }

            p.regenerateAp();

            System.out.print(p);
            System.out.print(m);
        }
    }

    /**
     * Random monster.
     *
     * @return the monster
     */
    private static Monster randomMonster() {
        Monster[] monsterFarm = {
                new Monster(),
                new ResistantMonster(),
                new WaitingMonster()
        };

        double bucketSize = 1.0 / monsterFarm.length;
        double bucket = Math.random() / bucketSize;
        int selectedMonster = (int) Math.floor(bucket);
        return monsterFarm[selectedMonster];
    }
}
