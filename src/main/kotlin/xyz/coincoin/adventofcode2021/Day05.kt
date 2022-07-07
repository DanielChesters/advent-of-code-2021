package xyz.coincoin.adventofcode2021

data class Position(val x: Int, val y: Int)
data class Line(val from: Position, val to: Position) {
    val minX = minOf(from.x, to.x)
    val minY = minOf(from.y, to.y)
    val maxX = maxOf(from.x, to.x)
    val maxY = maxOf(from.y, to.y)
}
typealias Lines = List<Line>
typealias Table = Array<IntArray>

class Day05 : Day {
    override fun part1(input: List<String>): Int {
        val lines = convertInput(input)
        val table = createTable(lines)

        val updatedTable = updateTable(table, lines)

        return getScore(updatedTable)
    }

    override fun part2(input: List<String>): Int {
        val lines = convertInput(input)
        val table = createTable(lines)

        val updatedTable = updateTableWithDiagonalLines(table, lines)

        return getScore(updatedTable)
    }

    private fun getScore(table: Table): Int = table.sumOf { row ->
        row.count { position ->
            position > 1
        }
    }

    private fun updateTable(table: Table, lines: Lines): Table {
        val filterLines = lines.filter { line ->
            line.from.x == line.to.x || line.from.y == line.to.y
        }
        return markTable(table, filterLines)
    }
    private fun updateTableWithDiagonalLines(table: Table, lines: Lines): Table {
        val filterLines = lines.filter { line ->
            line.from.x == line.to.x
                    || line.from.y == line.to.y
                    || (line.from.x == line.from.y && line.to.x == line.to.y)
        }
        return markTable(table, filterLines)
    }

    private fun markTable(table: Table, lines: Lines): Table {
        val currentLine = lines.first()

        val updatedTable = table.mapIndexed { y, row ->
            row.mapIndexed { x, value ->
                if (y in currentLine.minY..currentLine.maxY && x in currentLine.minX..currentLine.maxX) value + 1
                else value
            }.toIntArray()
        }.toTypedArray()

        return if (lines.size == 1) updatedTable
        else markTable(updatedTable, lines.subList(1, lines.size))
    }

    private fun createTable(lines: Lines): Table {
        val maxX = lines.maxOf { line ->
            maxOf(line.from.x, line.to.x)
        }
        val maxY = lines.maxOf { line ->
            maxOf(line.from.y, line.to.y)
        }

        return Array(maxY + 1) {
            IntArray(maxX + 1) { 0 }
        }
    }

    private fun convertInput(input: List<String>): Lines = input.map {
        convertLine(it)
    }

    private fun convertLine(s: String): Line {
        val (s1, _, s2) = s.trim().split("\\s+".toRegex())
        val position1 = convertPosition(s1)
        val position2 = convertPosition(s2)

        return Line(position1, position2)
    }

    private fun convertPosition(s: String): Position {
        val (x, y) = s.split(",")
        return Position(x.toInt(), y.toInt())
    }

}

fun main() {
    val day05 = Day05()
    val input = readInput("Day05")
    println(day05.part1(input))
    println(day05.part2(input))
}