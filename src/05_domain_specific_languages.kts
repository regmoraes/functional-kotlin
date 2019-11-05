/*
 *  A domain-specific language (DSL) is a computer language specialized to a particular application domain
 *  (from: Wikipedia)
 */

/*
    One of the greatest advantages of lambda with receivers is the possibility to create DSLs, because we can create
    scoped blocks (lambdas) for each DSL element (receivers).

    Let's create a super simple DSL to define a very simple Book. The idea is to create data classes with parameters
    that we'll fill in 'steps', like a builder, so we need to delcare them as vars.
 */

data class Author(var name: String = "")

data class Cover(var title: String = "")

data class Page(var text: String = "")

data class Book(var author: Author? = null, var cover: Cover? = null, var pages: MutableList<Page> = mutableListOf())

/*
    The root function is book, this function creates a new empty Book, executes a block of code in the context of
    the book and returns it. Within this context we can access every parameter of the book, and execute any extensions
    functions it may have. So the book function will be:
*/

fun book(block: Book.() -> Unit): Book {
    return Book().apply(block)
}

// We can then call the function like this:

book {
    author = Author("DouglasAdams")
    cover = Cover("The Hitchhiker's Guide to the Galaxy")
    pages = mutableListOf(Page(text = "Content of first page"))
}

/*
    The syntax is cool but it can be improved more. Let's create a new function to help us define the author the same
    way we defined our book. We want something like this:

    book {
       author {
            name = "A Name"
       }
    }

    Note that the author block is called inside the book block, so it must be called within the book context. To do this
    we create an author function that is an extension function for a Book. Another thing to note is that the block of
    the author function has an Actor as a context, which means the Actor is the receiver of the block:
*/

fun Book.author(block: Author.() -> Unit): Book {
    return this.apply { author = Author().apply(block) }
}

// Now we can make:

book {
    author {
        name = "Douglas Adams"
    }
    cover = Cover("The Hitchhiker's Guide to the Galaxy")
    pages = mutableListOf(Page(text = "Content of first page"))
}

// The same process can be repeated for all the other variables:

fun Book.cover(block: Cover.() -> Unit): Book {
    return this.apply { cover = Cover().apply(block) }
}

fun Book.pages(block: MutableList<Page>.() -> Unit): Book {
    return this.apply { this.pages = mutableListOf<Page>().apply(block) }
}

/*
    We create a page function so we can define single pages separately. The page must be called within a
    MutableList<Page> context because we'll modify the original list
*/
fun MutableList<Page>.page(block: Page.() -> Unit): MutableList<Page> {
    return this.apply { add(Page().apply(block)) }
}

// The final result

val theHitchhikersGuideToTheGalaxy = book {

    author {
        name = "Douglas Adams"
    }

    cover {
        title = "The Hitchhiker's Guide to the Galaxy"
    }

    pages {

        page {
            text = "Contents of first page"
        }

        page {
            text = "Contents of second page"
        }

        page {
            text = "Contents of third page"
        }
    }
}

println(theHitchhikersGuideToTheGalaxy)