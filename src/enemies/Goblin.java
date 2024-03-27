package enemies;

public class Goblin extends Enemy {
    public static final int BASE_HP = 30;

    public Goblin() {
        this(BASE_HP);
    }

    public Goblin(int hpModify) {
        super(BASE_HP + hpModify, EnemyType.Goblin, 10);
    }

    public void takeDamage(int damage) {
        hp -= damage;
    }
}
