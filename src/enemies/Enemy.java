package enemies;

import game.Player;
import game.Randomizer;

public abstract class Enemy {
    private final String name;
    private final EnemyType enemyType;

    protected int hp;
    protected int damage;

    public Enemy(int hp, int damage, EnemyType enemyType, String name) {
        if (hp <= 0) {
            throw new IllegalArgumentException("Can't create enemy with zero or less hp");
        }

        this.hp = hp;
        this.name = name;
        this.damage = damage;
        this.enemyType = enemyType;
    }

    public void takeDamage(int damage) {
        hp -= damage;
    }

    public void attack(Player player) {
        int damageModifier = Randomizer.getInstance().nextIntMinMax(-5, 5);
        int playerDamage = damage + damageModifier;
        System.out.print(getName() + " attacks. ");
        player.takeDamage(playerDamage);
    }

    public int getHp() {
        return hp;
    }

    public boolean isDead() {
        return hp <= 0;
    }

    public String getName() {
        return name;
    }
}
