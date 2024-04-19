import org.junit.jupiter.api.Test;
import ru.drudakov.enemies.Goblin;
import ru.drudakov.game.Player;

import static org.junit.jupiter.api.Assertions.*;

public class GoblinTest {
    @Test
    public void givenNegativeHpLowerBaseHp_whenCreateGoblin_thenThrowIllegalArgumentException() {
        int negativeHp = -35;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->
                new Goblin(negativeHp)
        );

        assertEquals("Can't create enemy with zero or less hp", thrown.getMessage());
    }

    @Test
    public void givenPositiveHp_whenCreateGoblin_thenSuccessful() {
        int hp = 5;
        int goblinBaseHp = 30;

        Goblin goblin = new Goblin(hp);

        assertEquals(hp + goblinBaseHp, goblin.getHp());
        assertEquals("Goblin", goblin.getName());
    }

    @Test
    public void givenGoblin_whenTake5Damage_thenLoses5Hp() {
        Goblin goblin = new Goblin(0);
        int damage = 5;
        int baseHp = goblin.getHp();

        goblin.takeDamage(damage);

        assertEquals(baseHp - damage, goblin.getHp());
    }

    @Test
    public void givenDefaultGoblin_whenTake10DamageAndCheckIsDead_thenLoses10HpAndIsNotDead() {
        Goblin goblin = new Goblin(0);
        int damage = 10;
        int baseHp = goblin.getHp();

        goblin.takeDamage(damage);

        assertEquals(baseHp - damage, goblin.getHp());
        assertFalse(goblin.isDead());
    }

    @Test
    public void givenDefaultGoblin_whenTake30DamageAndCheckIsDead_thenLoses30HpAndIsDead() {
        Goblin goblin = new Goblin(0);
        int damage = 30;
        int baseHp = goblin.getHp();

        goblin.takeDamage(damage);

        assertEquals(baseHp - damage, goblin.getHp());
        assertTrue(goblin.isDead());
    }

    @Test
    public void givenGoblinAndPlayer_whenGoblinAttacksPlayer_thenPlayerLoses10Hp() {
        Goblin goblin = new Goblin(0);
        Player player = new Player();
        int playerBaseHp = player.getHp();

        goblin.attack(player);

        assertEquals(playerBaseHp - goblin.getDamage(), player.getHp());
    }
}
