package xyz.coincoin.adventofcode2021

typealias Boards = List<Board>

typealias BingoNumber = Pair<Int, Boolean>

typealias Row = List<BingoNumber>

typealias Board = List<Row>

class Day04 : Day {
    override fun part1(input: List<String>): Int {
        val drawNumbers = getDrawNumbers(input)

        val boards = getBoards(input)

        val (markedBoard, currentDraw) = markBoards(boards, drawNumbers)

        val winningBoard = getWinner(markedBoard)
        val sumNotMarkInt = sumNotMarkInt(winningBoard)

        return sumNotMarkInt * currentDraw
    }

    private fun getBoards(input: List<String>) = input.subList(2, input.size)
        .filter { it != "" }
        .windowed(size = 5, step = 5, transform = { list ->
            list.map { s ->
                s.trim().split("\\s+".toRegex())
                    .map { i ->
                        BingoNumber(i.toInt(), false)
                    }

            }
        })

    private fun getDrawNumbers(input: List<String>) = input[0].split(',')
        .map(String::toInt).toIntArray()

    private fun markBoards(boards: Boards, drawNumbers: IntArray): Pair<Boards, Int> {
        val currentDraw = drawNumbers[0]
        val markedBoards = boards.map { board ->
            board.map { row ->
                row.map { (number, mark) ->
                    if (currentDraw == number)
                        BingoNumber(number, true)
                    else
                        BingoNumber(number, mark)
                }
            }
        }

        return if (hasAWinner(markedBoards))
            Pair(markedBoards, currentDraw)
        else
            markBoards(markedBoards, drawNumbers.sliceArray(1 until drawNumbers.size))
    }

    private fun hasAWinner(boards: Boards): Boolean = boards.any(::checkIfBoardIsWinner)

    private fun getWinner(boards: Boards): Board =
        boards.first(::checkIfBoardIsWinner)

    private fun sumNotMarkInt(board: Board): Int = board.sumOf { row ->
        row.sumOf { (number, mark) ->
            if (mark) 0 else number
        }
    }

    private fun checkIfBoardIsWinner(board: Board) =
        board.any { row ->
            row.all { (_, mark) -> mark }
        } || (0..4).any { col ->
            board.map { it[col] }
                .all { (_, mark) -> mark }
        }

    override fun part2(input: List<String>): Int {
        return 0
    }
}

fun main() {
    val day04 = Day04()
    val input = readInput("Day04")
    println(day04.part1(input))
    println(day04.part2(input))
}