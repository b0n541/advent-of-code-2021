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

            println(String.format("After $day day(s): $population"))
        }
        println(population.size)
        return population.size
    }

    fun part2(input: List<String>): Long {

        val population = input.map { it.split(",") }
            .flatten()
            .map { Pair(it.toInt(), 1L) }
            .toMutableList()

        println("Initial state: $population")

        for (day in 1..256) {
            var newBorns = 0L

            for (index in population.indices) {
                val fish = population[index]
                if (fish.first == 0) {
                    population[index] = Pair(6, fish.second)
                    newBorns += fish.second
                } else {
                    population[index] = Pair(fish.first - 1, fish.second)
                }
            }

            if (newBorns > 0) {
                population.add(Pair(8, newBorns))
            }

            //println(String.format("After $day day(s): $population"))
        }

        return population.map { it.second }.sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    check(part1(testInput) == 5934)
    check(part2(testInput) == 26984457539)

    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}
