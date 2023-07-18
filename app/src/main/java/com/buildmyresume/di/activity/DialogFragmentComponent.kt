package com.buildmyresume.di.activity


import com.buildmyresume.di.application.DialogFragmentModule
import com.buildmyresume.di.fragment.PerFragment
import dagger.Subcomponent


@PerFragment
@Subcomponent(modules = [(DialogFragmentModule::class)])
interface DialogFragmentComponent
