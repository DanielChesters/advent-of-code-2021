fun main() {
    fun part1(input: List<String>): Int {
        val inputInt = input.map(String::toInt)

        return (1 until input.size).map { i ->
            inputInt[i - 1] < inputInt[i]
        }.count { it }
    }

    fun part2(input: List<String>): Int {
        val inputInt = input.map(String::toInt)

        val inputThreeSlidingWindows = (1 until input.size - 1).map { i ->
            inputInt[i - 1] + inputInt[i] + inputInt[i + 1]
        }

        return (1 until inputThreeSlidingWindows.size).map { i ->
            inputThreeSlidingWindows[i - 1] < inputThreeSlidingWindows[i]
        }.count { it }
    }

    val testInput = readInput("Day01_test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 5)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
