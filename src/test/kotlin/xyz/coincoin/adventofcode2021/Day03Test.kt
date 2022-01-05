package xyz.coincoin.adventofcode2021

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class Day03Test : ShouldSpec({
    val day03 = Day03()
    val input = readInput("Day03_test")

    should("Give 198 for part 1") {
        val result = day03.part1(input)

        result shouldBe 198
    }

    should("Give 230 for part 2") {
        val result = day03.part2(input)

        result shouldBe 230
    }
})