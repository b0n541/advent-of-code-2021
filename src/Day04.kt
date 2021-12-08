fun main() {
    fun part1(input: List<String>): Int {
        var drawnNumbers = input[0].split(",").map { it.toInt() }
        println(drawnNumbers)

        var bingoBoards = getBingoBoards(input)

        for (index in drawnNumbers.indices) {//drawnNumbers.indices) {
            val nextNumber = drawnNumbers[index]
            println("Next number $nextNumber")
            bingoBoards.forEach {
                val isBingo = it.markNumber(nextNumber)
                if (isBingo) {
                    println("BINGO!")
                    println(it)
                    return it.sumOfUnmarkedNumbers() * nextNumber
                }
            }
        }

        return -1
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    //check(part1(testInput) == 4512)
    check(part2(testInput) == 1924)

    val input = readInput("Day04")
    //println(part1(input))
    println(part2(input))
}

fun getBingoBoards(input: List<String>): MutableList<BingoBoard> {

    var result = mutableListOf<BingoBoard>()

    for (line in 2..input.size step 6) {
        result.add(BingoBoard(input.subList(line, line + 5)))
    }

    return result
}

class BingoBoard(private val numbers: List<MutableList<Int>>) {

    fun markNumber(number: Int): Boolean {
        for (row in numbers.indices) {
            for (column in numbers[row].indices) {
                if (numbers[row][column] == number) {
                    numbers[row][column] = -1
                    return checkBingo(row, column)
                }
            }
        }
        return false
    }

    private fun checkBingo(row: Int, column: Int): Boolean {
        return numbers[row].all { it == -1 }
                || numbers.map { it[column] }.all { it == -1 }
    }

    fun sumOfUnmarkedNumbers(): Int {
        return numbers.flatten().filter { it > -1 }.sum()
    }

    override fun toString(): String {
        return numbers.toString()
    }
}

fun BingoBoard(input: List<String>): BingoBoard {
    val numbers = input.map {
        it.trim()
            .replace("  ", " ")
            .split(" ")
            .map { it.toInt() }
            .toMutableList()
    }
    return BingoBoard(numbers)
}