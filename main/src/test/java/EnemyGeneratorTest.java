import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import ru.drudakov.Randomizer;
import ru.drudakov.enemies.Enemy;
import ru.drudakov.enemies.EnemyType;
import ru.drudakov.game.EnemyGenerator;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

public class EnemyGeneratorTest {
    @Test
    public void givenEnemyGenerator_whenGenerateOnlyGoblins_thenReturn2LengthListWithAllGoblins() {
        Randomizer randomizer = Mockito.mock(Randomizer.class);
        try (MockedStatic<Randomizer> mock = mockStatic(Randomizer.class)) {
            mock.when(Randomizer::getInstance).thenReturn(randomizer);
            when(randomizer.nextInt(2)).thenReturn(EnemyType.Goblin.ordinal());

            List<Enemy> goblins = EnemyGenerator.generateEnemies();

            assertEquals(2, goblins.size());
            goblins.forEach(goblin -> assertEquals(goblin.getName(), "Goblin"));
        }
    }

    @Test
    public void givenEnemyGenerator_whenGenerateOnlyGoblinBrutes_thenReturn2LengthListWithAllGoblinBrutes() {
        Randomizer randomizer = Mockito.mock(Randomizer.class);
        try (MockedStatic<Randomizer> mock = mockStatic(Randomizer.class)) {
            mock.when(Randomizer::getInstance).thenReturn(randomizer);
            when(randomizer.nextInt(2)).thenReturn(EnemyType.GoblinBrute.ordinal());

            List<Enemy> goblins = EnemyGenerator.generateEnemies();

            assertEquals(2, goblins.size());
            goblins.forEach(goblin -> assertEquals(goblin.getName(), "Goblin-Brute"));
        }
    }
}
