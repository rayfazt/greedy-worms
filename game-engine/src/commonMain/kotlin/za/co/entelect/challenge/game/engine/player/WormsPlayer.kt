package za.co.entelect.challenge.game.engine.player

import za.co.entelect.challenge.game.engine.config.GameConfig
import kotlin.js.JsName

class WormsPlayer private constructor(val id: Int,
                                      val worms: List<Worm>,
                                      private val config: GameConfig) {

    var currentWorm: Worm = worms[0]
        private set

    var previousWorm: Worm = worms[0]
        private set

    init {
        this.worms.forEach { it.player = this }
    }

    val health: Int
        get() = livingWorms.sumBy { it.health }

    val dead
        get() = worms.all { it.dead }


    val livingWorms
        get() = worms.filter { !it.dead }

    var commandScore: Int = 0

    val totalScore: Int
        get() = commandScore + health / worms.size

    /**
     * Amount of consecutive rounds the player has done nothing
     */
    var consecutiveDoNothingsCount = 0

    val disqualified
        get() = consecutiveDoNothingsCount > config.maxDoNothings

    var wormSelectionTokens = config.wormSelectTokens.count

    fun selectNextWorm() {
        //Assign living worms to a local variable since it is a computed property
        val livingWorms = this.livingWorms
        if (livingWorms.isNotEmpty()) {
            val nextWorm = livingWorms.firstOrNull { it.id > currentWorm.id } ?: livingWorms.first()
            updateCurrentWorm(nextWorm)
        }
    }

    fun updateCurrentWorm(newWorm: Worm) {
        previousWorm = currentWorm
        currentWorm = newWorm
    }

    override fun toString(): String {
        return "WormsPlayer(id=$id)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is WormsPlayer) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id
    }


    companion object {

        /**
         * Build a WormsPlayer with the correct properties from the config
         * @param id A unique identifier for this player
         * @param config The game config object
         */
        @JsName("build")
        fun build(id: Int, config: GameConfig): WormsPlayer {
            val worms = listOf(Pair(CommandoWorm, config.commandoWorms),
                    Pair(AgentWorm, config.agentWorms),
                    Pair(TechnologistWorm, config.technologistWorms))
                    .flatMap { (builder, details) -> (0 until details.count).map { builder } }
                    .mapIndexed { index, builder -> builder.build(index + 1, config) }

            return WormsPlayer(id, worms, config)
        }

        @JsName("buildWithWorms")
        fun build(id: Int, worms: List<Worm>, config: GameConfig): WormsPlayer {
            return WormsPlayer(id, worms, config)
        }

    }
}
