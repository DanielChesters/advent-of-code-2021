fun main() {
    fun part1(input: List<String>): Int {
        val binaryLength = input[0].length
        val gammaRate = (0 until binaryLength).map { i ->
            val bits = input.map { it[i] }
            if (bits.count { it == '1' } > bits.count { it == '0' }) '1' else '0'
        }.joinToString("").toInt(2)

        val epsilonRate = gammaRate xor "1".repeat(binaryLength).toInt(2)

        return gammaRate * epsilonRate
    }

    fun computeOxygenGeneratorRating(input: List<String>, position: Int): Int = if (input.size == 1) {
        input[0].toInt(2)
    } else {
        val bits = input.map { it[position] }
        val bitToKeep = if (bits.count { it == '1' } >= bits.count { it == '0' }) '1' else '0'
        val newInput = input.filter {
            it[position] == bitToKeep
        }
        computeOxygenGeneratorRating(newInput, position + 1)
    }

    fun computeCo2GeneratorRating(input: List<String>, position: Int): Int = if (input.size == 1) {
        input[0].toInt(2)
    } else {
        val bits = input.map { it[position] }
        val bitToKeep = if (bits.count { it == '1' } < bits.count { it == '0' }) '1' else '0'
        val newInput = input.filter {
            it[position] == bitToKeep
        }
        computeCo2GeneratorRating(newInput, position + 1)
    }

    fun part2(input: List<String>): Int {
        val oxygenGeneratorRating = computeOxygenGeneratorRating(input, 0)
        val co2GeneratorRating = computeCo2GeneratorRating(input, 0)
        return oxygenGeneratorRating * co2GeneratorRating
    }

    val testInput = readInput("Day03_test")
    val input = readInput("Day03")
    check(part1(testInput) == 198)
    println(part1(input))

    check(part2(testInput) == 230)
    println(part2(input))
}
