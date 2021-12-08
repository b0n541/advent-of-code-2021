import kotlin.math.abs

fun main() {
    fun findHotSpots(input: List<String>, filterDiagonalLines: Boolean): Int {
        val lines = input.map { Line(it) }
            .filter { if (filterDiagonalLines) (it.isHorizontal() || it.isVertical()) else true }
        println(lines)

        val maxSize = lines.map { it.startPoint.toList() + it.endPoint.toList() }
            .flatten()
            .maxOrNull()

        val floorMap = FloorMap(maxSize!! + 1)

        lines.forEach {
            floorMap.addLine(it)
        }

        println(floorMap)

        return floorMap.getHotSpots()
    }

    fun part1(input: List<String>): Int {
        return findHotSpots(input, true)
    }

    fun part2(input: List<String>): Int {
        return findHotSpots(input, false)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == 5)
    check(part2(testInput) == 12)

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}

class Line(val startPoint: Pair<Int, Int>, val endPoint: Pair<Int, Int>) {
    fun isHorizontal(): Boolean {
        return startPoint.first == endPoint.first
    }

    fun isVertical(): Boolean {
        return startPoint.second == endPoint.second
    }

    override fun toString(): String {
        return "$startPoint -> $endPoint"
    }
}

fun Line(input: String): Line {
    val startEnd = input.split(" -> ")
    val startPoint = startEnd[0].split(",").map { it.toInt() }
    val startX = startPoint[0]
    val startY = startPoint[1]
    val endPoint = startEnd[1].split(",").map { it.toInt() }
    val endX = endPoint[0]
    val endY = endPoint[1]
    return Line(Pair(startX, startY), Pair(endX, endY))
}

class FloorMap(size: Int) {
    private var floor = Array(size) { Array(size) { 0 } }

    fun addLine(line: Line) {
        var currentPoint = line.startPoint
        val endPoint = line.endPoint

        val deltaX = if (currentPoint.first == endPoint.first) 0
        else (endPoint.first - currentPoint.first) / abs(endPoint.first - currentPoint.first)

        val deltaY = if (currentPoint.second == endPoint.second) 0
        else (endPoint.second - currentPoint.second) / abs(endPoint.second - currentPoint.second)

        while (true) {
            floor[currentPoint.first][currentPoint.second]++
            if (currentPoint.first != endPoint.first || currentPoint.second != endPoint.second) {
                currentPoint = Pair(currentPoint.first + deltaX, currentPoint.second + deltaY)
            } else {
                break
            }
        }
    }

    fun getHotSpots(): Int {
        return floor.flatten().filter { it > 1 }.count()
    }

    override fun toString(): String {
        return buildString {
            for (column in floor.indices) {
                for (row in floor) {
                    append(row[column])
                }
                append("\n")
            }
        }
    }
}
