package xyz.coincoin.adventofcode2021

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class Day02Test : ShouldSpec({
    val day02 = Day02()
    val input = readInput("Day02_test")
    should("give 150 for part 1") {
        val result = day02.part1(input)

        result shouldBe 150
    }

    should("give 900 for part 2") {
        val result = day02.part2(input)

        result shouldBe 900
    }
})