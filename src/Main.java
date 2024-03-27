import enemies.Enemy;
import game.EnemyGenerator;
import game.Player;

import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    private static final int ACTIONS_NUM = 2;

    /*
     *  Суть приложения состоит в текстовом сражении между игроком и врагами.
     *  Игрок начинает со 100 единиц HP (здоровья) и должен победить всех врагов. Каждый противник имеет тип, от
     *  которого зависит его урон и показатель HP. Игрок может атаковать или блокировать. Если после хода игрока
     *  противник не был убит, то он атакует игрока. Игра продолжается, пока игрок или все враги не будут побеждены.
     */
    public static void main(String[] args) {

        Player player = new Player();
        System.out.println("Player's HP: " + player.getHp());

        List<Enemy> enemies = EnemyGenerator.generateEnemies();
        System.out.println("Welcome player. You must defeat " + enemies.size() + " enemies. Good Luck!");

        for (Enemy enemy : enemies) {
            System.out.printf("\n=== Enemy %s. HP: %d ===\n", enemy.getEnemyType(), enemy.getHp());

            while (enemy.getHp() > 0) {
                if (player.isBlocking()) {
                    player.unblock();
                }

                System.out.println("Choose your action:");
                System.out.println("1. Attack");
                System.out.println("2. Block");

                int choice = inputInt(ACTIONS_NUM);
                switch (choice) {
                    case 1:
                        player.attack(enemy);
                        break;
                    case 2:
                        player.block();
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }

                if (enemy.isDead()) {
                    System.out.printf("%s defeated!", enemy.getEnemyType());
                    continue;
                }

                enemy.attack(player);

                if (player.isDead()) {
                    System.out.println("You have been defeated. Try again.");
                    break;
                }
            }
        }

        if (!player.isDead()) {
            System.out.println("\nAll enemies defeated! You win!");
        }
    }

    public static int inputInt(int max) {
        int choice = scanner.nextInt();
        while (choice < 0 && choice > max) {
            choice = scanner.nextInt();
        }
        return choice;
    }
}