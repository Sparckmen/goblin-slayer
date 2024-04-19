import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import ru.drudakov.Randomizer;
import ru.drudakov.enemies.GoblinBrute;
import ru.drudakov.game.Player;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

public class GoblinBruteTest {
    @Test
    public void givenNegativeHpLowerBaseHp_whenCreateGoblinBrute_thenThrowIllegalArgumentException() {
        int negativeHp = -55;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->
                new GoblinBrute(negativeHp)
        );

        assertEquals("Can't create enemy with zero or less hp", thrown.getMessage());
    }

    @Test
    public void givenPositiveHp_whenCreateGoblinBrute_thenSuccessful() {
        int hp = 5;
        int baseHp = 50;

        GoblinBrute goblinBrute = new GoblinBrute(hp);

        assertEquals(hp + baseHp, goblinBrute.getHp());
        assertEquals("Goblin-Brute", goblinBrute.getName());
    }

    @Test
    public void givenGoblinBrute_whenTake5Damage_thenLoses5Hp() {
        GoblinBrute goblinBrute = new GoblinBrute(0);
        int damage = 5;
        int baseHp = goblinBrute.getHp();

        goblinBrute.takeDamage(damage);

        assertEquals(baseHp - damage, goblinBrute.getHp());
    }

    @Test
    public void givenDefaultGoblinBrute_whenTake10DamageAndCheckIsDead_thenLoses10HpAndIsNotDead() {
        GoblinBrute goblinBrute = new GoblinBrute(0);
        int damage = 10;
        int baseHp = goblinBrute.getHp();

        goblinBrute.takeDamage(damage);

        assertEquals(baseHp - damage, goblinBrute.getHp());
        assertFalse(goblinBrute.isDead());
    }

    @Test
    public void givenDefaultGoblinBrute_whenTake50DamageAndCheckIsDead_thenLoses30HpAndIsDead() {
        GoblinBrute goblinBrute = new GoblinBrute(0);
        int damage = 50;
        int baseHp = goblinBrute.getHp();

        goblinBrute.takeDamage(damage);

        assertEquals(baseHp - damage, goblinBrute.getHp());
        assertTrue(goblinBrute.isDead());
    }

    @Test
    public void givenGoblinBruteAndPlayer_whenGoblinBruteAttacksPlayer_thenPlayerLoses20Hp() {
        GoblinBrute goblinBrute = new GoblinBrute(0);
        Player player = new Player();
        int playerBaseHp = player.getHp();

        goblinBrute.attack(player);

        assertEquals(playerBaseHp - goblinBrute.getDamage(), player.getHp());
    }

    @Test
    public void givenGoblinBruteAndPlayer_whenGoblinBruteAttacksPlayerPlayerIsBlockingAndGoblinBruteTriggerBlockBreak_thenPlayerIsNotBlockingAndLoses20Hp() {
        Randomizer randomizer = Mockito.mock(Randomizer.class);
        try (MockedStatic<Randomizer> mock = mockStatic(Randomizer.class)) {
            mock.when(Randomizer::getInstance).thenReturn(randomizer);
            when(randomizer.nextDouble()).thenReturn(0.1);

            GoblinBrute goblinBrute = new GoblinBrute(0);
            Player player = new Player();
            player.block();
            int playerBaseHp = player.getHp();

            goblinBrute.attack(player);

            assertFalse(player.isBlocking());
            assertEquals(playerBaseHp - goblinBrute.getDamage(), player.getHp());
        }
    }
}
