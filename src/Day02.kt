fun main() {
    fun part1(input: List<String>): Int {
        val instructions = input.map{
            val split = it.split(" ")
            Pair(split[0], split[1].toInt())
        }

        val forward = instructions
            .filter { (direction, _) -> direction == "forward" }
            .sumOf { (_, count) -> count }

        val up = instructions
            .filter { (direction, _) -> direction == "up" }
            .sumOf { (_, count) -> count }

        val down = instructions
            .filter { (direction, _) -> direction == "down" }
            .sumOf { (_, count) -> count }

       return forward * (down - up)
    }

    fun part2(input: List<String>): Int {
        val instructions = input.map{
            val split = it.split(" ")
            Pair(split[0], split[1].toInt())
        }
        var horizontalPosition = 0
        var aim = 0
        var depth = 0

        instructions.forEach { (direction, count) ->
            when(direction) {
                "forward" -> {
                    horizontalPosition += count
                    depth += aim * count
                }
                "down" -> {
                    aim += count
                }
                "up" -> {
                    aim -= count
                }
            }
        }

        return horizontalPosition * depth
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    val input = readInput("Day02")
    check(part1(testInput) == 150)
    println(part1(input))

    check(part2(testInput) == 900)
    println(part2(input))
}
