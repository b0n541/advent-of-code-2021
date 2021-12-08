import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {

        val initialPositions = input.map { it.split(",") }
            .flatten()
            .map { it.toInt() }

        val minPosition = initialPositions.minOrNull()!!
        val maxPosition = initialPositions.maxOrNull()!!

        var bestFuelConsumption = Int.MAX_VALUE
        for (position in minPosition..maxPosition) {
            val fuelConsumption = initialPositions.sumOf { abs(it - position) }
            if (fuelConsumption < bestFuelConsumption) {
                bestFuelConsumption = fuelConsumption
            }
        }

        return bestFuelConsumption
    }

    fun part2(input: List<String>): Int {

        val initialPositions = input.map { it.split(",") }
            .flatten()
            .map { it.toInt() }

        val minPosition = initialPositions.minOrNull()!!
        val maxPosition = initialPositions.maxOrNull()!!

        var bestFuelConsumption = Int.MAX_VALUE
        for (position in minPosition..maxPosition) {
            val fuelConsumption = initialPositions.sumOf {
                // see https://en.wikipedia.org/wiki/Triangular_number
                val distance = abs(it - position)
                (distance * (distance + 1)) / 2
            }

            if (fuelConsumption < bestFuelConsumption) {
                bestFuelConsumption = fuelConsumption
            }
        }

        return bestFuelConsumption
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_test")
    check(part1(testInput) == 37)
    check(part2(testInput) == 168)

    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}
