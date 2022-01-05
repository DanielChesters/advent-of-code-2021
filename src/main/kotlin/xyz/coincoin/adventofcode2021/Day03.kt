package xyz.coincoin.adventofcode2021

class Day03 : Day {
    override fun part1(input: List<String>): Int {
        val binaryLength = input[0].length
        val gammaRate = (0 until binaryLength).map { i ->
            val bits = input.map { it[i] }
            if (bits.count { it == '1' } > bits.count { it == '0' }) '1' else '0'
        }.joinToString("").toInt(2)

        val epsilonRate = gammaRate xor "1".repeat(binaryLength).toInt(2)

        return gammaRate * epsilonRate
    }

    private fun computeOxygenGeneratorRating(input: List<String>, position: Int): Int = if (input.size == 1) {
        input[0].toInt(2)
    } else {
        val bits = input.map { it[position] }
        val bitToKeep = if (bits.count { it == '1' } >= bits.count { it == '0' }) '1' else '0'
        val newInput = input.filter {
            it[position] == bitToKeep
        }
        computeOxygenGeneratorRating(newInput, position + 1)
    }

    private fun computeCo2GeneratorRating(input: List<String>, position: Int): Int = if (input.size == 1) {
        input[0].toInt(2)
    } else {
        val bits = input.map { it[position] }
        val bitToKeep = if (bits.count { it == '1' } < bits.count { it == '0' }) '1' else '0'
        val newInput = input.filter {
            it[position] == bitToKeep
        }
        computeCo2GeneratorRating(newInput, position + 1)
    }

    override fun part2(input: List<String>): Int {
        val oxygenGeneratorRating = computeOxygenGeneratorRating(input, 0)
        val co2GeneratorRating = computeCo2GeneratorRating(input, 0)
        return oxygenGeneratorRating * co2GeneratorRating
    }
}

fun main() {
    val day03 = Day03()
    val input = readInput("Day03")
    println(day03.part1(input))
    println(day03.part2(input))
}
