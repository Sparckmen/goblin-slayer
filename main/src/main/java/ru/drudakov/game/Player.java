package ru.drudakov.game;

import ru.drudakov.enemies.Enemy;

public class Player {
    private int hp;
    private final int damage;
    private boolean isBlocking;

    public Player() {
        this(100);
    }

    public Player(int hp) {
        this(hp, 10);
    }

    public Player(int hp, int damage) {
        if (hp <= 0) {
            throw new IllegalArgumentException("Can't create player with zero or less hp");
        }

        this.hp = hp;
        this.damage = damage;
        this.isBlocking = false;
    }

    public int getHp() {
        return hp;
    }

    public boolean isBlocking() {
        return isBlocking;
    }

    public void attack(Enemy enemy) {
        enemy.takeDamage(damage);
        System.out.printf("Player attacks and deals %d damage to the %s. %s HP: %d\n", damage, enemy.getName(), enemy.getName(), enemy.getHp());
    }

    public void takeDamage(int damage) {
        if (!isBlocking) {
            hp -= damage;
            System.out.printf("Player takes %d damage. HP: %d\n", damage, hp);
        } else {
            System.out.printf("Player blocked incoming attack with %d damage.", damage);
        }
    }

    public boolean isDead() {
        return hp <= 0;
    }

    public void block() {
        isBlocking = true;
        System.out.println("Player is now blocking.");
    }

    public void unblock() {
        isBlocking = false;
        System.out.println("Player stops blocking.");
    }
}