package ru.drudakov.enemies;

import java.util.HashMap;
import java.util.Map;

public enum EnemyType {
    Goblin(0), GoblinBrute(1);

    private final int orderNumber;

    private static final Map<Integer, EnemyType> ENEMY_TYPE_TO_NUMBER_MAP = new HashMap<>(values().length);

    static {
        for (EnemyType enemyType : values()) ENEMY_TYPE_TO_NUMBER_MAP.put(enemyType.orderNumber, enemyType);
    }

    EnemyType(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public static EnemyType of(int orderNumber) {
        EnemyType result = ENEMY_TYPE_TO_NUMBER_MAP.get(orderNumber);
        if (result == null) {
            throw new IllegalArgumentException("Invalid order number: " + orderNumber);
        }
        return result;
    }
}
