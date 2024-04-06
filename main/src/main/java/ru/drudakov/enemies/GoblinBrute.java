package ru.drudakov.enemies;

import ru.drudakov.game.Player;
import ru.drudakov.Randomizer;

public class GoblinBrute extends Enemy {
    private static final double BREAK_BLOCK_CHANCE = 0.2;
    public static final int BASE_HP = 50;

    public GoblinBrute(int hpModify) {
        super(BASE_HP + hpModify, 20, EnemyType.GoblinBrute,"Goblin-Brute");
    }

    @Override
    public void attack(Player player) {
        int damageModifier = Randomizer.getInstance().nextIntMinMax(-10, 5);
        int playerDamage = damage + damageModifier;
        System.out.print(getName() + " attacks. ");

        double chanceToBreakBlock = Randomizer.getInstance().nextDouble();
        if (chanceToBreakBlock <= BREAK_BLOCK_CHANCE && player.isBlocking()) {
            System.out.print(getName() + " breaks player block. ");
            player.unblock();
        }
        player.takeDamage(playerDamage);
    }
}