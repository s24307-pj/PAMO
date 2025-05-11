package com.example.bmicalculator

import kotlin.math.PI
import kotlin.random.Random

/**
 * Complete the code to make the program print "Mary is 20 years old" to standard output:
 * fun main() {
 * val name = "Mary"
 * val age = 20
 * // Write your code here
 * }
 */
fun exercise1() {
    val name = "Mary"
    val age = 20
    println("$name is $age years old")
}

/**
 * Explicitly declare the correct type for each variable:
 * fun main() {
 * val a: Int = 1000
 * val b = "log message"
 * val c = 3.14
 * val d = 100_000_000_000_000
 * val e = false
 * val f = '\n'
 * }
 */
fun exercise2() {
    val a: Int = 1000
    val b: String = "log message"
    val c: Double = 3.14
    val d: Long = 100_000_000_000_000
    val e: Boolean = false
    val f: Char = '\n'
    println("$a, $b, $c, $d, $e, $f")
}

/**
 * You have a list of “green” numbers and a list of “red” numbers. Complete the code to print how many numbers there are in total.
 * fun main() {
 * val greenNumbers = listOf(1, 4, 23)
 * val redNumbers = listOf(17, 2)
 * // Write your code here
 * }
 */
fun exercise3() {
    val greenNumbers = listOf(1, 4, 23)
    val redNumbers = listOf(17, 2)
    val totalCount = greenNumbers.count() + redNumbers.count()
    println(totalCount)
}

/**
 * You have a set of protocols supported by your server. A user requests to use a particular protocol. Complete the program to check whether the requested protocol
 * is supported or not (isSupported must be a Boolean value).
 * fun main() {
 * val SUPPORTED = setOf("HTTP", "HTTPS", "FTP")
 * val requested = "smtp"
 * val isSupported = // Write your code here
 * println("Support for $requested: $isSupported")
 *}
 */
fun exercise4() {
    val SUPPORTED = setOf("HTTP", "HTTPS", "FTP")
    val requested = "smtp"
    val isSupported = requested.uppercase() in SUPPORTED
    println("Support for $requested: $isSupported")
}

/**
 * Define a map that relates integer numbers from 1 to 3 to their corresponding spelling. Use this map to spell the given number.
 * fun main() {
 * val number2word = // Write your code here
 * val n = 2
 * println("$n is spelt as '${<Write your code here >}'")
 * }
 */
fun exercise5() {
    val number2word = mapOf(1 to "one", 2 to "two", 3 to "three")
    val n = 2
    println("$n is spelt as '${number2word[n]}'")
}

/**
 * Create a simple game where you win if throwing two dice results in the same number. Use if to print You win :) if the dice match or You lose :( otherwise.
 * import kotlin.random.Random
 * fun main() {
 * val firstResult = Random.nextInt(6)
 * val secondResult = Random.nextInt(6)
 * // Write your code here
 * }
 *
 */
fun exercise6() {
    val firstResult = Random.nextInt(6)
    val secondResult = Random.nextInt(6)
    if (firstResult == secondResult)
        println("You win :)")
    else
        println("You lose :(")
}

/**
 * Using a when expression, update the following program so that it prints the corresponding actions when you input the names of game console buttons.
 * fun main() {
 * val button = "A"
 * println(
 * // Write your code here
 * )
 * }
 */
fun exercise7() {
    val button = "A"
    println(
        when (button) {
            "A" -> "Yes"
            "B" -> "No"
            "X" -> "Menu"
            "Y" -> "Nothing"
            else -> "There is no such button"
        }
    )
}

/**
 * You have a program that counts pizza slices until there’s a whole pizza with 8 slices. Refactor this program in two ways:
 * Use a while loop.
 * Use a do-while loop.
 *fun main() {
 * var pizzaSlices = 0
 * // Start refactoring here
 * pizzaSlices++
 * println("There's only $pizzaSlices slice/s of pizza :(")
 * pizzaSlices++
 * println("There's only $pizzaSlices slice/s of pizza :(")
 * pizzaSlices++
 * println("There's only $pizzaSlices slice/s of pizza :(")
 * pizzaSlices++
 * println("There's only $pizzaSlices slice/s of pizza :(")
 * pizzaSlices++
 * println("There's only $pizzaSlices slice/s of pizza :(")
 * pizzaSlices++
 * println("There's only $pizzaSlices slice/s of pizza :(")
 * pizzaSlices++
 * println("There's only $pizzaSlices slice/s of pizza :(")
 * pizzaSlices++
 * // End refactoring here
 * println("There are $pizzaSlices slices of pizza. Hooray! We have a whole pizza! :D")
 * }
 */
fun exercise8() {
    var pizzaSlices = 0
    while ( pizzaSlices < 7 ) {
        pizzaSlices++
        println("There's only $pizzaSlices slice/s of pizza :(")
    }
    pizzaSlices++
    println("There are $pizzaSlices slices of pizza. Hooray! We have a whole pizza! :D")

    var pizzaSlicesSecond = 0
    pizzaSlicesSecond++
    do {
        println("There's only $pizzaSlicesSecond slice/s of pizza :(")
        pizzaSlicesSecond++
    } while ( pizzaSlicesSecond < 8 )
    println("There are $pizzaSlicesSecond slices of pizza. Hooray! We have a whole pizza! :D")

}

/**
 * Write a program that simulates the Fizz buzz game. Your task is to print numbers from 1 to 100 incrementally, replacing any number divisible by three with the word
 * "fizz", and any number divisible by five with the word "buzz". Any number divisible by both 3 and 5 must be replaced with the word "fizzbuzz".
 * Hint 1
 * Use a for loop to count numbers and a when expression to decide what to print at each step.
 * Hint 2
 * Use the modulo operator (%) to return the remainder of a number being divided. Use the equality operator (==) to check if the remainder equals zero.
 */
fun exercise9() {
    for (number in 1..100) {
        println(
            when {
                number % 15 == 0 -> "fizzbuzz"
                number % 3 == 0 -> "fizz"
                number % 5 == 0 -> "buzz"
                else -> "$number"
            }
        )
    }
}

/**
 * You have a list of words. Use for and if to print only the words that start with the letter l.
 * Hint
 * Use the .startsWith() function for String type.
 * fun main() {
 * val words = listOf("dinosaur", "limousine", "magazine", "language")
 * // Write your code here
 * }
 */
fun exercise10() {
    val words = listOf("dinosaur", "limousine", "magazine", "language")
    for (w in words) {
        if (w.startsWith("l"))
            println(w)
    }
}

/**
 * Write a function called circleArea that takes the radius of a circle in integer format as a parameter and outputs the area of that circle.
 * import kotlin.math.PI
 * // Write your code here
 * fun main() {
 * println(circleArea(2))
 * }
 */
fun exercise11() {
    println(circleArea(2))

}

fun circleArea(radius: Int): Double {
    return PI * radius * radius
}

/**
 * Rewrite the circleArea function from the previous exercise as a single-expression function.
 * import kotlin.math.PI
 * // Write your code here
 * fun main() {
 * println(circleArea(2))
 * }
 */
fun exercise12() {
    println(circleAreaSecond(2)) // 12.566370614359172
}

fun circleAreaSecond(radius: Int): Double = PI * radius * radius


fun main() {
    println("=== Exercise 1 ===")
    exercise1()
    
    println("\n=== Exercise 2 ===")
    exercise2()
    
    println("\n=== Exercise 3 ===")
    exercise3()
    
    println("\n=== Exercise 4 ===")
    exercise4()
    
    println("\n=== Exercise 5 ===")
    exercise5()
    
    println("\n=== Exercise 6 ===")
    exercise6()
    
    println("\n=== Exercise 7 ===")
    exercise7()
    
    println("\n=== Exercise 8 ===")
    exercise8()
    
    println("\n=== Exercise 9 ===")
    exercise9()
    
    println("\n=== Exercise 10 ===")
    exercise10()
    
    println("\n=== Exercise 11 ===")
    exercise11()
    
    println("\n=== Exercise 12 ===")
    exercise12()
} 