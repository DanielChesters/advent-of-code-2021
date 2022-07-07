package xyz.coincoin.adventofcode2021

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class Day05Test: ShouldSpec({
    val day05 = Day05()
    val input = readInput("Day05_test")

    should("give 5 for part 1") {
        day05.part1(input) shouldBe 5
    }

    should("give 12 for part 2") {
        day05.part2(input) shouldBe 12
    }
})