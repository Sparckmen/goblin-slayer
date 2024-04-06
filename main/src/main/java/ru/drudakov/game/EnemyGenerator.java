package ru.drudakov.game;

import ru.drudakov.enemies.Enemy;
import ru.drudakov.enemies.EnemyType;
import ru.drudakov.enemies.Goblin;
import ru.drudakov.enemies.GoblinBrute;
import ru.drudakov.Randomizer;

import java.util.ArrayList;
import java.util.List;

public class EnemyGenerator {
    private final static int ENEMIES_AMOUNT = 2;
    private final static int HP_VARIOUS = 10;

    public static List<Enemy> generateEnemies() {
        List<Enemy> enemies = new ArrayList<>(ENEMIES_AMOUNT);
        var randomizer = Randomizer.getInstance();

        for (int i = 0; i < ENEMIES_AMOUNT; i++) {
            var enemyType = EnemyType.of(randomizer.nextInt(EnemyType.values().length));

            var randomizedHpModifier = randomizer.nextIntMinMax(-HP_VARIOUS, HP_VARIOUS);
            if (enemyType == EnemyType.Goblin) {
                enemies.add(new Goblin(randomizedHpModifier));
            } else if (enemyType == EnemyType.GoblinBrute) {
                enemies.add(new GoblinBrute(randomizedHpModifier));
            }
        }

        return enemies;
    }
}
