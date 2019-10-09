// Method that takes a function as an argument
fun executeIfEven(x: Int, f: (Int) -> Int): Int {
    return if (x % 2 == 0) f(x) else -1
}

val double = { x: Int -> 2 * x }
println(
    executeIfEven(4, double)
)

// Or
println(
    executeIfEven(4) { it * 5 } // Same as executeIfEven(4, { it * 5 })
)

// Method that returns a function
fun doubleAndPrintSomething(x: Int): (String) -> Unit {
    return {
        println("I was $x, now I'm ${x * 2}")
        println("And I will say $it")
    }
}

val returnedFunction = doubleAndPrintSomething(3) // will return (String) -> Unit
returnedFunction("Hello") // Calling ("Hello") -> Unit
