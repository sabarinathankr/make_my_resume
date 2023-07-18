package com.buildmyresume.di

interface HasComponent<out C> {
    val component: C
}