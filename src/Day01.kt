fun main() {
    fun part1(input: List<String>): Int {
        val inputInt = input.map(String::toInt)
            return (1 until input.size).map {
                inputInt[it-1] < inputInt[it]
            }.count { it }
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 7)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
