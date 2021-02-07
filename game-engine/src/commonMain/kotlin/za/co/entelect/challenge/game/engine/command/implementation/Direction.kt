package za.co.entelect.challenge.game.engine.command.implementation

import za.co.entelect.challenge.game.engine.map.Point

enum class Direction(val shortCardinal: String, x: Int, y: Int) {

    UP("N", 0, -1),
    UP_RIGHT("NE", 1, -1),
    RIGHT("E", 1, 0),
    DOWN_RIGHT("SE", 1, 1),
    DOWN("S", 0, 1),
    DOWN_LEFT("SW", -1, 1),
    LEFT("W", -1, 0),
    UP_LEFT("NW", -1, -1);

    val vector = Point(x, y)

    companion object {

        /**
         * Resolves a Direction from the shortVertical version
         * @param shortened Shortened form of a direction. Should correspond to the `shortVertical` field of a direction
         *
         * @throws NoSuchElementException If no Direction with the short form exists
         */
        fun fromShortened(shortened: String): Direction = values().first {
            val upperCase = shortened.toUpperCase()
            it.shortCardinal == upperCase
        }

        /**
         * Checks if a Direction with the shortVertical version exists
         * @param shortened Shortened form of a direction. Should correspond to the `shortVertical` field of a direction
         * @return true if s matching direction exists, false otherwise
         */
        fun containsShortened(shortened: String): Boolean = values().any {
            val upperCase = shortened.toUpperCase()
          it.shortCardinal == upperCase
        }
    }
}