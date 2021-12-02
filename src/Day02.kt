fun main() {

    fun part1(input: List<String>): Int {

        var depth = 0
        var position = 0

        input.map { Move(it) }
            .forEach {
                when (it.direction) {
                    Direction.FORWARD -> position += it.distance
                    Direction.DOWN -> depth += it.distance
                    Direction.UP -> depth -= it.distance
                }
            }

        return position * depth
    }

    fun part2(input: List<String>): Int {

        val moves = input.map { SubmarineMove(it) }
        var state = SubmarineState()

        for (index in moves.indices) {
            state = moves[index].action(state)
        }

        return state.position * state.depth
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 150)
    check(part2(testInput) == 900)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}

enum class Direction {
    FORWARD,
    DOWN,
    UP
}

data class Move(val direction: Direction, val distance: Int)

fun Move(line: String): Move {
    val (direction, distance) = line.split(" ")
    return Move(Direction.valueOf(direction.uppercase()), distance.toInt())
}

data class SubmarineState(val position: Int = 0, val depth: Int = 0, val aim: Int = 0)

sealed class SubmarineMove(val action: (SubmarineState) -> SubmarineState)

class MoveForward(private val distance: Int) :
    SubmarineMove({ SubmarineState(it.position + distance, it.depth + it.aim * distance, it.aim) })

class SteerDown(private val distance: Int) :
    SubmarineMove({ SubmarineState(it.position, it.depth, it.aim + distance) })

class SteerUp(private val distance: Int) :
    SubmarineMove({ SubmarineState(it.position, it.depth, it.aim - distance) })

fun SubmarineMove(line: String): SubmarineMove {
    val (direction, distance) = line.split(" ")

    return when (Direction.valueOf(direction.uppercase())) {
        Direction.FORWARD -> MoveForward(distance.toInt())
        Direction.DOWN -> SteerDown(distance.toInt())
        Direction.UP -> SteerUp(distance.toInt())
    }
}