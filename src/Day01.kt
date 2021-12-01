fun main() {

    fun sumOfDescends(input: List<Int>): Int {

        var result = 0

        for (i in 0 until input.lastIndex) {
            if (input[i] < input[i + 1]) {
                result++
            }
        }

        return result
    }

    fun part1(input: List<String>): Int {

        return sumOfDescends(input.map { it.toInt() })
    }

    fun part2(input: List<String>): Int {

        return sumOfDescends(input.map { it.toInt() }
            .windowed(3, 1)
            .map { it.sum() })
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 5)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
