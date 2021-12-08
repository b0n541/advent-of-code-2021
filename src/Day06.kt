fun main() {
    fun part1(input: List<String>): Int {

        val population = input.map { it.split(",") }
            .flatten()
            .map { it.toInt() }
            .toMutableList()

        println("Initial state: $population")

        for (day in 1..80) {
            val newBorns = mutableListOf<Int>()

            for (index in population.indices) {
                if (population[index] == 0) {
                    population[index] = 6
                    newBorns.add(8)
                } else {
                    population[index]--
                }
            }

            population.addAll(newBorns)

            //println(String.format("After $day day(s): $population"))
        }
        println(population.size)
        return population.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    check(part1(testInput) == 5934)
    //check(part2(testInput) == 12)

    val input = readInput("Day06")
    println(part1(input))
    //println(part2(input))
}
