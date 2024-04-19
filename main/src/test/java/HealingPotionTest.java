import org.junit.jupiter.api.Test;
import ru.drudakov.game.Player;
import ru.drudakov.items.HealingPotion;

import static org.junit.jupiter.api.Assertions.*;

public class HealingPotionTest {
    @Test
    public void givenDefaultHealingPotion_whenHeal_thenIncreasePlayerHpBy10() {
        Player player = new Player();
        HealingPotion potion = new HealingPotion();
        int playerInitialHp = player.getHp();

        potion.use(player);

        assertEquals(playerInitialHp, player.getHp() - 10);
    }

    @Test
    public void givenHealingPotionWith20Heal_whenHeal_thenIncreasePlayerHpBy20() {
        int hpToHeal = 20;
        Player player = new Player();
        HealingPotion potion = new HealingPotion(hpToHeal);
        int playerInitialHp = player.getHp();

        potion.use(player);

        assertEquals(playerInitialHp, player.getHp() - hpToHeal);
    }

    @Test
    public void givenHealingPotionWithNegativeValue_whenInvokeConstructor_thenThrowIllegalArgumentException() {
        int negativeHp = -5;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->
                new HealingPotion(negativeHp)
        );

        assertEquals("You can't provide a negative argument", thrown.getMessage());
    }
}
