fun main() {

    fun getMostCommonBit(input: List<String>, index: Int): Char {
        val significantBitCount = input.count { it[index] == '1' }
        if (significantBitCount >= (input.size / 2.0)) {
            return '1'
        }
        return '0'
    }

    fun getLeastCommonBit(input: List<String>, index: Int): Char {
        val significantBitCount = input.count { it[index] == '0' }
        if (significantBitCount <= (input.size / 2.0)) {
            return '0'
        }
        return '1'
    }

    fun getMostCommonBits(input: List<String>): String {
        val result = StringBuilder()
        for (index in 0 until input[0].length) {
            result.append(getMostCommonBit(input, index))
        }
        return result.toString()
    }

    fun getLeastCommonBits(input: List<String>): String {
        val result = StringBuilder()
        for (index in 0 until input[0].length) {
            result.append(getLeastCommonBit(input, index))
        }
        return result.toString()
    }

    fun filterMostCommonBits(input: List<String>): Int {
        var iterationList = input
        var prefix = ""
        var index = 0
        while (iterationList.size > 1) {
            prefix += getMostCommonBit(iterationList, index)
            iterationList = iterationList.filter { it.startsWith(prefix) }.toList()
            index++
        }
        return iterationList[0].toInt(2)
    }

    fun filterLeastCommonBits(input: List<String>): Int {
        var iterationList = input
        var prefix = ""
        var index = 0
        while (iterationList.size > 1) {
            prefix += getLeastCommonBit(iterationList, index)
            iterationList = iterationList.filter { it.startsWith(prefix) }.toList()
            index++
        }
        return iterationList[0].toInt(2)
    }

    fun part1(input: List<String>): Int {
        var gammaRate = getMostCommonBits(input).toInt(2)
        var epsilonRate = getLeastCommonBits(input).toInt(2)

        return gammaRate * epsilonRate
    }

    fun part2(input: List<String>): Int {
        val oxygenGeneratorRating = filterMostCommonBits(input)
        val co2ScrubberRating = filterLeastCommonBits(input)

        return oxygenGeneratorRating * co2ScrubberRating
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 198)
    check(part2(testInput) == 230)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
