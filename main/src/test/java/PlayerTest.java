import org.junit.jupiter.api.Test;
import ru.drudakov.enemies.Goblin;
import ru.drudakov.game.Player;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    @Test
    public void given_whenCreatePlayer_thenHp100Damage10IsBlockingFalse() {
        int hp = 100;
        int damage = 10;

        Player player = new Player();

        assertEquals(hp, player.getHp());
        assertEquals(damage, player.getDamage());
        assertFalse(player.isBlocking());
    }

    @Test
    public void givenHp10_whenCreatePlayerWithHp_thenHp10() {
        int hp = 100;

        Player player = new Player(hp);

        assertEquals(hp, player.getHp());
    }

    @Test
    public void givenNegativeHp_whenCreatePlayer_thenThrowsIllegalArgumentException() {
        int negativeHp = -10;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->
                new Player(negativeHp)
        );

        assertEquals("Can't create player with zero or less hp", thrown.getMessage());
    }

    @Test
    public void givenPlayer_whenBlock_thenIsBlockingTrue() {
        Player player = new Player();

        player.block();

        assertTrue(player.isBlocking());
    }


    @Test
    public void givenPlayer_whenUnblock_thenIsBlockingFalse() {
        Player player = new Player();

        player.unblock();

        assertFalse(player.isBlocking());
    }

    @Test
    public void givenPlayer_whenHeal10Hp_thenHpIncreasesBy10() {
        Player player = new Player();
        int baseHp = player.getHp();
        int hpToHeal = 10;

        player.heal(hpToHeal);

        assertEquals(baseHp + hpToHeal, player.getHp());
    }

    @Test
    public void givenPlayerAndGoblin_whenPlayerAttackGoblin_thenGoblinLoses10Hp() {
        Player player = new Player();
        Goblin goblin = new Goblin(0);
        int goblinBaseHp = goblin.getHp();

        player.attack(goblin);

        assertEquals(goblinBaseHp - player.getDamage(), goblin.getHp());
    }

    @Test
    public void givenPlayer_whenPlayerTake5Damage_thenPlayerLoses5Hp() {
        Player player = new Player();
        int playerBaseHp = player.getHp();
        int takeHp = 5;

        player.takeDamage(takeHp);

        assertEquals(playerBaseHp - takeHp, player.getHp());
    }

    @Test
    public void givenPlayerBlocking_whenPlayerTake5Damage_thenPlayerBlockIncomingDamage() {
        Player player = new Player();
        player.block();
        int playerBaseHp = player.getHp();
        int takeHp = 5;

        player.takeDamage(takeHp);

        assertEquals(playerBaseHp, player.getHp());
    }

    @Test
    public void givenPlayer_whenPlayerTake5Damage_thenPlayerLoses5HpAndPlayerIsNotDead() {
        Player player = new Player();
        int playerBaseHp = player.getHp();
        int takeHp = 5;

        player.takeDamage(takeHp);

        assertEquals(playerBaseHp - takeHp, player.getHp());
        assertFalse(player.isDead());
    }

    @Test
    public void givenDefaultPlayer_whenPlayerTake100Damage_thenPlayerLoses100HpAndPlayerIsDead() {
        Player player = new Player();
        int playerBaseHp = player.getHp();
        int takeHp = 100;

        player.takeDamage(takeHp);

        assertEquals(playerBaseHp - takeHp, player.getHp());
        assertTrue(player.isDead());
    }

}
