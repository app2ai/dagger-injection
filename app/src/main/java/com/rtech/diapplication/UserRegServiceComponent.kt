package com.rtech.diapplication

import dagger.Component

@Component
interface UserRegServiceComponent {
    fun injectViewObjects(mainActivity: MainActivity)
}