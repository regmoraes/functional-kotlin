/*
Currying is the technique of translating the evaluation of a function that takes multiple arguments into evaluating
a sequence of functions, each with a single argument.
*/

// Two inputs, x and y, one output
val multiply = { x: Int, y: Int -> x * y }
println(multiply(3, 5))

/*
    We want to transform multiply = { x: Int, y: Int -> x * y } into two functions that takes one parameter each and
    chain them to produce the final output. The resulting function call will be like this:

    multiply(x)(y)

    This means that multiply(x) would return the function { x, y -> x * y } with x set. For example, if we call:

    multiply(3)

    it will return

    { 3, y -> 3 * y }

    Then, we can take the function { 3, y -> 3 * y } and call it passing the y argument, given something like:

    { 3, 5 -> 3 * 5 }

    So, multiply(x)(y) is equivalent to:

    { x -> { y -> x * y } }

    therefore calling multiply(3)(5) is the same as:

    { 3 -> { 5 -> 3 * 5 } }

 */

// Currying with method call
fun curryingMultiply(x: Int): (Int) -> Int {
    return { y: Int -> x * y }
}

val returnedFunction = curryingMultiply(3)  // Will return { y -> 3 * y }
println(
    returnedFunction(5) // Will execute { 5 -> 3 * 5 } == 15
)

// Currying with function parameter

/*
    curryingMultiply will first return the function { x: Int -> AnotherFunction }.
    The AnotherFunction that is returned from the first is { y: Int -> x * y }, with the x parameter initialized.
 */
val curryingMultiply = { x: Int -> { y: Int -> x * y } }

val partialFunction = curryingMultiply(3) // { y -> 3 * y }
println(
    partialFunction(5) // { 5 -> 3 * 5 } == 3 * 5
)
