package xyz.coincoin.adventofcode2021

import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = Day::class.java
    .getResourceAsStream("/$name.txt")
    ?.bufferedReader()
    ?.readLines()!!

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)
