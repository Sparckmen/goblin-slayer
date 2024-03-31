package enemies;

public class Goblin extends Enemy {
    public static final int BASE_HP = 30;

    public Goblin(int hpModify) {
        super(BASE_HP + hpModify,  10, EnemyType.Goblin,"Goblin");
    }
}
