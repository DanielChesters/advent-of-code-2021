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

    fun part2(input: List<String>): Int {
        return input.size
    }

    val testInput = readInput("Day03_test")
    val input = readInput("Day03")
    check(part1(testInput) == 198)
    println(part1(input))

    check(part2(testInput) == 0)
    println(part2(input))
}
