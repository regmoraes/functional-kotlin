/*
 *  Lamda with receiver allows you to pass a function as parameter where the context of the passed function is the
 *  receiver. By doing this, we can implicit access all variables of the receiver directly from the function block
 */

/*
    Lets write our own apply function (from Kotlin). An apply function must receive some value and a function block as
    parameter, apply the block to the value and return the original value back without modification. Using a simple
    higher order functions we can create the apply function like this:
 */

fun <T> customApply(source: T, block: (T) -> Unit): T {
    block(source)
    return source
}

// And we can call it for Strings

val text = "I'm a string"
customApply(text) {
    println("Printing this before printing")
    println(it)
}

// Int and etc.
val number = 10
println(
    customApply(number) {
        it * 20
    }
)

// We can make it cleaner by transforming the function into an extension function over some type T

fun <T> T.customApplyExtension(block: (T) -> T): T {
    return block(this)
}

/*
    Note that every time we want to access the parameter we must refer to it using 'it'. However, we can get rid of it
    by using lambda with receiver. By adding moving the T outside the parenthesis

    block: T.() -> T

    we modify the block function, transforming it into an extension function of the type T. Since it is an extension
    function in T, the context of the block is T and can be referenced implicitly ( or directly with 'this')
*/

data class Person(val name: String, val age: Int)

fun <T> T.customApplyWithReceiver(block: T.() -> Unit): T {
    this.block()
    return this
}

val person = Person("João", 34)

person.customApplyWithReceiver {
    println(name)
    println(age)
}