package com.buildmyresume.manager


interface Passable<in T> {

    fun passData(t: T)

}
