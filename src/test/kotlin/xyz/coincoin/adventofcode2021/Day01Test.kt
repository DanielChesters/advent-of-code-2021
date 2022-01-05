package xyz.coincoin.adventofcode2021

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class Day01Test : ShouldSpec({
    val day01 = Day01()
    val input = readInput("Day01_test")

    should("Give 7 for part 1") {
        val result = day01.part1(input)

        result shouldBe 7
    }

    should("Give 5 for part 2") {
        val result = day01.part2(input)

        result shouldBe 5
    }
})