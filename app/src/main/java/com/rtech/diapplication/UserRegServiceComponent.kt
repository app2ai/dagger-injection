package com.rtech.diapplication

import com.rtech.diapplication.modules.NotificationServiceModule
import dagger.Component

@Component(modules = [NotificationServiceModule::class])
interface UserRegServiceComponent {
    fun injectViewObjects(mainActivity: MainActivity)
}