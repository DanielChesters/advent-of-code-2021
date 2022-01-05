package xyz.coincoin.adventofcode2021

class Day01 : Day {
    override fun part1(input: List<String>): Int {
        val inputInt = input.map(String::toInt)

        return (1 until input.size).map { i ->
            inputInt[i - 1] < inputInt[i]
        }.count { it }
    }

    override fun part2(input: List<String>): Int {
        val inputInt = input.map(String::toInt)

        val inputThreeSlidingWindows = (1 until input.size - 1).map { i ->
            inputInt[i - 1] + inputInt[i] + inputInt[i + 1]
        }

        return (1 until inputThreeSlidingWindows.size).map { i ->
            inputThreeSlidingWindows[i - 1] < inputThreeSlidingWindows[i]
        }.count { it }
    }
}

fun main() {
    val day01 = Day01()
    val input = readInput("Day01")
    println(day01.part1(input))
    println(day01.part2(input))
}
