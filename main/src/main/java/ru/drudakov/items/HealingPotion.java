package ru.drudakov.items;

import ru.drudakov.game.Player;

public class HealingPotion  {
    private final int hpToHeal;

    public HealingPotion() {
        this(10);
    }

    public HealingPotion(int hpToHeal) {
        if (hpToHeal <= 0) {
            throw new IllegalArgumentException("You can't provide a negative argument");
        }

        this.hpToHeal = hpToHeal;
    }

    public void use(Player player) {
        player.heal(hpToHeal);
    }
}
