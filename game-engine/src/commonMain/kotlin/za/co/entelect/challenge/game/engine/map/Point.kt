package za.co.entelect.challenge.game.engine.map

import kotlin.math.*

data class Point(val x: Int, val y: Int) {

    private fun Int.pow(i: Int): Double = this.toDouble().pow(i)
    /**
     * Movement is vertical, horizontal or diagonal so this is the maximum of x and y distance
     */
    fun movementDistance(other: Point): Int = maximumDimension(other)

    fun shootingDistance(other: Point): Double = floor(euclideanDistance(other))
    fun manhattanDistance(other: Point) = abs(x - other.x) + abs(y - other.y)
    fun euclideanDistance(other: Point) = sqrt((x - other.x).pow(2) + (y - other.y).pow(2))
    fun euclideanDistance(other: Pair<Double, Double>) = sqrt((x - other.first).pow(2) + (y - other.second).pow(2))
    fun maximumDimension(other: Point) = max(abs(x - other.x), abs(y - other.y))
    fun abs(): Point = Point(abs(x), abs(y))
    operator fun minus(other: Point): Point = Point(x - other.x, y - other.y)
    operator fun plus(other: Point): Point = Point(x + other.x, y + other.y)

    override fun toString(): String = "$x $y"

    companion object {
        fun getAllPointsOfASquare(start: Int, end: Int)
                = (start..end).flatMap { x -> (start..end).map { y -> Point(x, y) } }
    }
}


