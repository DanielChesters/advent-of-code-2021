package xyz.coincoin.adventofcode2021

typealias Boards = List<Board>

typealias BoardsWithPosition = List<BoardWithPosition>

data class BoardWithPosition(val board: Board, val position: Int)
data class BingoNumber(val number: Int, val marked: Boolean)

typealias Row = List<BingoNumber>

typealias Board = List<Row>

class Day04 : Day {
    override fun part1(input: List<String>): Int {
        val drawNumbers = getDrawNumbers(input)

        val boards = getBoards(input)

        val (markedBoards, currentDraw) = markBoards(boards, drawNumbers)

        val winningBoard = getWinner(markedBoards)
        val sumNotMarkInt = sumNotMarkInt(winningBoard)

        return sumNotMarkInt * currentDraw
    }

    override fun part2(input: List<String>): Int {
        val drawNumbers = getDrawNumbers(input)

        val boards = getBoards(input)

        val boardsWithPosition = boards.map { board: Board ->
            BoardWithPosition(board, 0)
        }

        val (markedBoards, currentDraw, lastWinningPosition) = markBoardsUntilLastWin(boardsWithPosition, drawNumbers)

        val lastWinningBoard = getLastWinningBoard(markedBoards, lastWinningPosition)

        val sumNotMarkInt = sumNotMarkInt(lastWinningBoard)

        return sumNotMarkInt * currentDraw
    }

    private fun getLastWinningBoard(markedBoards: BoardsWithPosition, lastWinningPosition: Int): Board {
        return markedBoards.first {
            it.position == lastWinningPosition
        }.board
    }

    private fun markBoardsUntilLastWin(boards: BoardsWithPosition, drawNumbers: IntArray, currentWinningPosition: Int = 1): Triple<BoardsWithPosition, Int, Int> {
        val currentDraw = drawNumbers[0]
        val markedBoards = boards.map { boardWithWinningPosition ->
            val updatedBoard = markRow(boardWithWinningPosition.board, currentDraw)
            if (checkIfBoardIsWinner(updatedBoard) && boardWithWinningPosition.position == 0) {
                Triple(updatedBoard, currentWinningPosition, true)
            } else {
                Triple(updatedBoard, boardWithWinningPosition.position, false)
            }
        }

        return if (allBoardWinner(markedBoards.map { it.first }))
            Triple(markedBoards.map { BoardWithPosition(it.first, it.second) },
                currentDraw,
                currentWinningPosition)
        else
            markBoardsUntilLastWin(
                boards = markedBoards.map { BoardWithPosition   (it.first, it.second) },
                drawNumbers = drawNumbers.sliceArray(1 until drawNumbers.size),
                currentWinningPosition = if (markedBoards.any { it.third }) currentWinningPosition + 1 else currentWinningPosition
            )
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
            markRow(board, currentDraw)
        }

        return if (hasAWinner(markedBoards))
            Pair(markedBoards, currentDraw)
        else
            markBoards(markedBoards, drawNumbers.sliceArray(1 until drawNumbers.size))
    }

    private fun markRow(
        board: Board,
        currentDraw: Int
    ): Board = board.map { row ->
        row.map { (number, mark) ->
            if (currentDraw == number)
                BingoNumber(number, true)
            else
                BingoNumber(number, mark)
        }
    }

    private fun hasAWinner(boards: Boards): Boolean = boards.any(::checkIfBoardIsWinner)

    private fun allBoardWinner(boards: Boards): Boolean = boards.all(::checkIfBoardIsWinner)

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
}

fun main() {
    val day04 = Day04()
    val input = readInput("Day04")
    println(day04.part1(input))
    println(day04.part2(input))
}