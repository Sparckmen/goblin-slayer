package enemies;

import java.util.HashMap;
import java.util.Map;

public enum EnemyType {
    Goblin(0), GoblinBrute(1);

    private final int orderNumber;

    private static final Map<Integer, EnemyType> map = new HashMap<>(values().length);

    static {
        for (EnemyType enemyType : values()) map.put(enemyType.orderNumber, enemyType);
    }

    EnemyType(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public static EnemyType of(int orderNumber) {
        EnemyType result = map.get(orderNumber);
        if (result == null) {
            throw new IllegalArgumentException("Invalid order number: " + orderNumber);
        }
        return result;
    }
}
