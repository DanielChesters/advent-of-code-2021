package xyz.coincoin.adventofcode2021

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class Day04Test: ShouldSpec({
    val day04 = Day04()
    val input = readInput("Day04_test")

    should("give 4512 for part 1") {
        val result = day04.part1(input)

        result shouldBe 4512
    }

    should("give xxx for part 2") {
        val result = day04.part2(input)

        result shouldBe 1924
    }
})
