fun main() {
    fun part1(input: List<String>): Int {
        return playBingo(input, GameEndRule.FIRST_BOARD)
    }

    fun part2(input: List<String>): Int {
        return playBingo(input, GameEndRule.LAST_BOARD)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 4512)
    check(part2(testInput) == 1924)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}

fun getDrawnNumbers(input: List<String>) = input[0].split(",").map { it.toInt() }

fun playBingo(input: List<String>, gameEndRule: GameEndRule): Int {
    var drawnNumbers = getDrawnNumbers(input)
    println(drawnNumbers)

    var bingoBoards = getBingoBoards(input)

    for (index in drawnNumbers.indices) {
        val nextNumber = drawnNumbers[index]
        println("Next number $nextNumber")
        bingoBoards.forEach {
            val isBingo = it.markNumber(nextNumber)
            if (isBingo) {
                println("BINGO!")
                println(it)
                when (gameEndRule) {
                    GameEndRule.FIRST_BOARD -> return it.sumOfUnmarkedNumbers() * nextNumber
                    GameEndRule.LAST_BOARD -> {
                        if (bingoBoards.size == 1) {
                            return it.sumOfUnmarkedNumbers() * nextNumber
                        }
                    }
                }
            }
        }
        bingoBoards.removeIf { it.isBingo }
    }

    return -1
}

fun getBingoBoards(input: List<String>): MutableList<BingoBoard> {

    var result = mutableListOf<BingoBoard>()

    for (line in 2..input.size step 6) {
        result.add(BingoBoard(input.subList(line, line + 5)))
    }

    return result
}

class BingoBoard(private val numbers: List<MutableList<Int>>) {

    var isBingo = false

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
        if (numbers[row].all { it == -1 }
            || numbers.map { it[column] }.all { it == -1 }) {
            isBingo = true
        }
        return isBingo
    }

    fun sumOfUnmarkedNumbers(): Int {
        return numbers.flatten().filter { it > -1 }.sum()
    }

    override fun toString(): String {
        return numbers.toString()
    }
}

fun BingoBoard(input: List<String>): BingoBoard {
    val numbers = input.map { line ->
        line.trim()
            .replace("  ", " ")
            .split(" ")
            .map { it.toInt() }
            .toMutableList()
    }
    return BingoBoard(numbers)
}

enum class GameEndRule {
    FIRST_BOARD,
    LAST_BOARD
}