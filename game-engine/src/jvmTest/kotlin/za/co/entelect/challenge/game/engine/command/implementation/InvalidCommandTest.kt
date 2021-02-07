package za.co.entelect.challenge.game.engine.command.implementation

import za.co.entelect.challenge.game.delegate.factory.TEST_CONFIG
import za.co.entelect.challenge.game.engine.factory.TestMapFactory.buildMapWithCellType
import za.co.entelect.challenge.game.engine.map.CellType
import za.co.entelect.challenge.game.engine.player.CommandoWorm
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class InvalidCommandTest {

    private val config = TEST_CONFIG

    val worm = CommandoWorm.build(0, config)
    val map = buildMapWithCellType(emptyList(), 4, CellType.AIR)

    @Test
    fun test_validation() {
        val command = InvalidCommand("Cannot parse command")
        assertFalse(command.validate(map, worm).isValid)

        assertTrue(command.toString().startsWith("invalid command"))
    }

    @Test(expected = NotImplementedError::class)
    fun testExecution() {
        val command = InvalidCommand("Cannot parse command")
        command.execute(map, worm)
    }
}
