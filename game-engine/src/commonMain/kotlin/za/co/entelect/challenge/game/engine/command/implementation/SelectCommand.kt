package za.co.entelect.challenge.game.engine.command.implementation

import za.co.entelect.challenge.game.engine.command.CommandStrings
import za.co.entelect.challenge.game.engine.command.WormsCommand
import za.co.entelect.challenge.game.engine.command.feedback.CommandFeedback
import za.co.entelect.challenge.game.engine.command.feedback.CommandValidation
import za.co.entelect.challenge.game.engine.command.feedback.SelectCommandFeedback
import za.co.entelect.challenge.game.engine.command.feedback.StandardCommandFeedback
import za.co.entelect.challenge.game.engine.map.WormsMap
import za.co.entelect.challenge.game.engine.player.Worm

/**
 * Select a specific worm as active worm
 */
class SelectCommand(val wormId: Int) : WormsCommand {

    override val order: Int = -1

    override fun validate(gameMap: WormsMap, worm: Worm): CommandValidation {
        val player = worm.player
        val newWorm = player.worms.firstOrNull { it.id == wormId }

        return when {
            player.wormSelectionTokens == 0 -> CommandValidation.invalidMove("Player has no selection tokens")
            newWorm == null -> CommandValidation.invalidMove("Worm with id $wormId not found")
            newWorm.dead -> CommandValidation.invalidMove("Worm with id $wormId not alive")
            else -> CommandValidation.validMove()
        }
    }

    override fun execute(gameMap: WormsMap, worm: Worm): CommandFeedback {
        val player = worm.player
        val newWorm = player.worms.firstOrNull { it.id == wormId }

        return if (newWorm != null && !newWorm.dead) {
            player.updateCurrentWorm(newWorm)
            player.wormSelectionTokens -= 1
            SelectCommandFeedback(toString(), newWorm, 0)
        } else {
            StandardCommandFeedback(toString(), 0, player.id, false, "Invalid worm selection")
        }
    }

    override fun ignoresBeingFrozen(): Boolean {
        return true
    }

    override fun toString(): String = "${CommandStrings.SELECT.string} $wormId"

}
