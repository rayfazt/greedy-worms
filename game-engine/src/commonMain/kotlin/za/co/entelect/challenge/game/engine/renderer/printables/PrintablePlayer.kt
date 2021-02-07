package za.co.entelect.challenge.game.engine.renderer.printables

import za.co.entelect.challenge.game.engine.command.CommandStrings
import za.co.entelect.challenge.game.engine.command.feedback.CommandFeedback
import za.co.entelect.challenge.game.engine.map.WormsMap
import za.co.entelect.challenge.game.engine.player.WormsPlayer
import kotlin.jvm.Transient

class PrintablePlayer(player: WormsPlayer) {

    val id: Int = player.id
    val score: Int = player.totalScore
    var health: Int? = null
    val currentWormId = player.currentWorm.id
    val remainingWormSelections = player.wormSelectionTokens
    var previousCommand: String = CommandStrings.NOTHING.string
    var worms: List<PrintableWorm> = emptyList()

    @Transient
    val consoleHealth: Int = player.health

    companion object {
        /**
         * Check if @player is actually the @perspectivePlayer
         */
        fun isPerspectivePlayer(player: WormsPlayer, perspectivePlayer: WormsPlayer?) =
                (player == perspectivePlayer) || (perspectivePlayer == null)

        /**
         * Build a PrintablePlayer from @player that is modified to fit the perspective of @perspectivePlayer
         * @perspectivePlayer is not allowed to see some details from other players
         */
        fun buildForPerspectivePlayer(player: WormsPlayer, perspectivePlayer: WormsPlayer?, wormsMap: WormsMap): PrintablePlayer {
            val playerForPerspectivePlayer = PrintablePlayer(player)
            playerForPerspectivePlayer.worms = player.worms
                    .map { PrintableWorm.buildForDetailsPerspectivePlayer(it, perspectivePlayer) }
            if (isPerspectivePlayer(player, perspectivePlayer)) {
                playerForPerspectivePlayer.health = player.health
            }
            playerForPerspectivePlayer.previousCommand = getLastCommand(wormsMap, player)
            return playerForPerspectivePlayer
        }

        private fun getLastCommand(wormsMap: WormsMap, player: WormsPlayer): String {
            val feedback = wormsMap
                .getFeedback(wormsMap.currentRound - 1)
                .filter { it.playerId == player.id }
            return when (feedback.size) {
                1    -> feedback[0].command
                2    -> extractSelectCommand(feedback)
                else -> CommandStrings.NOTHING.string
            }
        }

        private fun extractSelectCommand(feedback: List<CommandFeedback>): String {
            val selectCommand = feedback
                .find { it.command.startsWith("select") }
            val otherCommand  = feedback
                .find { !it.command.startsWith("select") }
            return "${(selectCommand?.command)?:CommandStrings.NOTHING.string}; ${(otherCommand?.command)?:CommandStrings.NOTHING.string}"
        }
    }

}
