package za.co.entelect.challenge.game.engine.player

import za.co.entelect.challenge.game.delegate.factory.TEST_CONFIG
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class WormsPlayerTest {

    val config = TEST_CONFIG

    @Test
    fun test_playerBuild() {
        val player = WormsPlayer.build(0, config)

        val allWormsCount = config.commandoWorms.count + config.agentWorms.count + config.technologistWorms.count
        assertEquals(allWormsCount, player.worms.size)
        assertEquals(
                config.commandoWorms.initialHp * config.commandoWorms.count +
                        config.agentWorms.initialHp * config.agentWorms.count +
                        config.technologistWorms.initialHp * config.technologistWorms.count,
                player.health)
        assertFalse(player.dead)
        assertEquals(player.worms[0], player.currentWorm)

        player.selectNextWorm()

        assertEquals(player.worms[1], player.currentWorm)
    }

    @Test
    fun test_player_wormSelection() {
        val player = WormsPlayer.build(0, config)
        player.worms[1].health = 0

        val allWormsCount = config.commandoWorms.count + config.agentWorms.count + config.technologistWorms.count
        assertEquals(allWormsCount, player.worms.size)
        assertEquals(config.commandoWorms.count * config.commandoWorms.initialHp
                + (config.agentWorms.count - 1) * config.agentWorms.initialHp
                + config.technologistWorms.count * config.technologistWorms.initialHp,
                player.health)
        assertFalse(player.dead)

        player.selectNextWorm()
        assertEquals(player.worms[2], player.currentWorm)

        player.selectNextWorm()
        assertEquals(player.worms[0], player.currentWorm)
    }

    @Test
    fun test_player_wormSelection_All() {
        val player = WormsPlayer.build(2, config)
        assertEquals(1, player.currentWorm.id)

        player.selectNextWorm()
        assertEquals(player.worms[1], player.currentWorm)
        assertEquals(2, player.currentWorm.id)

        player.selectNextWorm()
        assertEquals(player.worms[2], player.currentWorm)
        assertEquals(3, player.currentWorm.id)

        player.selectNextWorm()
        assertEquals(player.worms[0], player.currentWorm)
        assertEquals(1, player.currentWorm.id)

        player.selectNextWorm()
        assertEquals(player.worms[1], player.currentWorm)
        assertEquals(2, player.currentWorm.id)

        player.selectNextWorm()
        assertEquals(player.worms[2], player.currentWorm)
        assertEquals(3, player.currentWorm.id)
    }

    @Test
    fun test_player_wormSelection_currentWormDead() {
        val player = WormsPlayer.build(2, config)

        player.updateCurrentWorm(player.worms[1])
        player.worms[1].health = 0;

        assertEquals(2, player.currentWorm.id);

        player.selectNextWorm();

        assertEquals(3, player.currentWorm.id)
    }

    @Test
    fun test_player_dead() {
        val player = WormsPlayer.build(0, config)
        player.worms.forEachIndexed { i, worm -> worm.health = -i }

        val allWormsCount = config.commandoWorms.count + config.agentWorms.count + config.technologistWorms.count
        assertEquals(allWormsCount, player.worms.size)
        assertEquals(0, player.health)
        assertTrue(player.dead)

        assertEquals(player.worms[0], player.currentWorm)
        player.selectNextWorm()
        assertEquals(player.worms[0], player.currentWorm)
        assertEquals(0, player.totalScore)
    }

}
