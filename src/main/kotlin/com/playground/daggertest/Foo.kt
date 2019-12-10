package com.playground.daggertest

import dagger.Component
import javax.inject.Inject

class Foo @Inject constructor() {
    fun getData(): String = "hey, I am foo"
}

class Bar @Inject constructor() {

    // Example for field injection - Dagger sets this field directly when Bar is requested via Dagger
    @Inject
    lateinit var foo: Foo
    lateinit var otherFoo: Foo

    fun getData(): String = "hey, I am bar and my foo says: ${foo.getData()} and otherFoo says: ${otherFoo.getData()}"

    // Example for method injection
    // This method is identified by Dagger as holding an @Inject annotation and calls it with a foo it provides
    @Inject
    fun gimmeOtherFoo(otherFoo: Foo) {
        this.otherFoo = otherFoo
    }
}

@Component
interface MyComponent {
    fun getBar(): Bar
}

fun main() {
    println("Hello World!")
    var bar = DaggerMyComponent.builder().build().getBar()
    println(bar.getData())
}
