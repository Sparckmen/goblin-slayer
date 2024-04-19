package ru.drudakov;

import ru.drudakov.enemies.Enemy;
import ru.drudakov.game.EnemyGenerator;
import ru.drudakov.game.Player;
import ru.drudakov.items.HealingPotion;

import java.util.List;
import java.util.Stack;

public class Main {

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

        Stack<HealingPotion> potions = new Stack<>();
        potions.add(new HealingPotion(-5));

        List<Enemy> enemies = EnemyGenerator.generateEnemies();
        System.out.println("Welcome player. You must defeat " + enemies.size() + " enemies. Good Luck!");

        for (Enemy enemy : enemies) {
            System.out.printf("\n=== Enemy %s. HP: %d ===\n", enemy.getName(), enemy.getHp());

            while (enemy.getHp() > 0 && !player.isDead()) {
                if (player.isBlocking()) {
                    player.unblock();
                }

                System.out.println("Choose your action:");
                System.out.println("1. Attack");
                System.out.println("2. Block");
                System.out.println("3. Heal");

                int choice = InputUtils.inputInt(ACTIONS_NUM);
                switch (choice) {
                    case 1 -> player.attack(enemy);
                    case 2 -> player.block();
                    case 3 -> {
                        if (potions.isEmpty()) {
                            System.out.println("You out of potions");
                        } else {
                            var potion = potions.pop();
                            potion.use(player);
                        }
                    }
                    default -> System.out.println("Invalid choice.");
                }

                if (enemy.isDead()) {
                    System.out.printf("%s defeated!", enemy.getName());
                    continue;
                }

                enemy.attack(player);

                if (player.isDead()) {
                    System.out.println("You have been defeated. Try again.");
                }
            }
        }

        if (!player.isDead()) {
            System.out.println("\nAll enemies have been defeated! You win!");
        }
    }
}