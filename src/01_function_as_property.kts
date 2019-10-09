val double = { x: Int -> x * 2 } // No type provided need to explicit function parameter name and type

val triple: (Int) -> Int = { it * 3 } // Type provided, no need to explicit function parameter name or type

// More than one parameter, should specify names
val exp = { x: Int, y: Int ->
    var acc = 1
    for (i in 1..y) {
        acc *= x
    }
    acc
}

double(3)
triple(4)
exp(2, 10)