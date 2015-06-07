import java.util.Scanner;

public class Battle {
    public static void start(Player p) {
        Creature m = randomMonster();

        Scanner sc = new Scanner(System.in);

        System.out.println("                 Kampf Start                    ");
        System.out.print(p);
        System.out.print(m);

        while (true) {
            System.out.println("------------------------------------------------");
            System.out.println("Mögliche Aktionen:");
            System.out.println("1 -> Angriff");
            System.out.printf("2 -> Item (%d verbleibend)%n", p.getRemainingItemUses());
            System.out.printf("3 -> Harter Schlag (%d AP, %d%% Selbstschaden)%n", Player.HARD_HIT_COST, Player.HARD_HIT_SELF_DAMAGE_PERCENT);
            System.out.printf("4 -> Feuerball (%d AP)%n", Player.FIREBALL_COST);
            System.out.printf("5 -> ATK auswürfeln (%d AP)%n", Player.REROLL_COST);
            System.out.println("Welche Aktion?: ");
            System.out.println("------------------------------------------------");
            String aktion = sc.nextLine();
            int playerDamage;
            switch (aktion) {
                case "1":
                    playerDamage = p.attack(m);
                    if (playerDamage == -1) {
                        System.out.println("Spieler verfehlt!");
                    } else {
                        System.out.printf("Spieler trifft und macht %d Schaden!%n", playerDamage);
                    }
                    break;
                case "2":
                    if (p.heal()) {
                        System.out.println("Spieler heilt sich!");
                    } else {
                        System.out.println("Nicht genügend Heiltränke!");
                    }
                    break;
                case "3":
                    playerDamage = p.hardHit(m);
                    if (playerDamage != -1) {
                        System.out.println("Spieler schlägt hart zu!");
                        System.out.printf("Spieler verursacht %d Schaden!%n", playerDamage);
                        System.out.printf("Spieler verursacht %d Selbstschaden!%n", (int) (Player.HARD_HIT_SELF_DAMAGE_PERCENT / 100.0 * playerDamage));
                    } else {
                        System.out.println("Nicht genügend AP!");
                    }
                    break;
                case "4":
                    playerDamage = p.fireball(m);
                    if (playerDamage != -1) {
                        System.out.println("Spieler schießt einen Feuerball!");
                        System.out.printf("Spieler verursacht %d Schaden!%n", playerDamage);
                    } else {
                        System.out.println("Nicht genügend AP!");
                    }
                    break;
                case "5":
                    if (p.reroll()) {
                        System.out.println("ATK neu ausgewürfelt!");
                        System.out.print("Neue Statuswerte: ");
                        System.out.print(p);
                    } else {
                        System.out.println("Nicht genügend AP!");
                    }
                    break;
                default:
                    System.out.println("Fehlerhafte Aktion!");
                    continue;
            }

            if (p.isDefeated()) {
                System.out.println("Game Over!");
                System.exit(0);
            } else if (m.isDefeated()) {
                System.out.println("Spieler gewinnt!");


                //Monsteritem werden dem Spieler übertragen.
                int anzahlMonsterItem = m.inventar.size();

                for (int i = 0; i < anzahlMonsterItem; i++) {
                    Item monsterItem = (Item) m.inventar.get(i);
                    p.inventar.add(monsterItem);
                }

                //Mostergold werden dem Spieler übertragen.
                p.setGold(p.getGold() + m.getGold());

                break;
            }

            System.out.print(p);
            System.out.print(m);

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
