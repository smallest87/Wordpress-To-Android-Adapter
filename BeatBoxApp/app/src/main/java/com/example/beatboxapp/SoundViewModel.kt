package com.example.beatboxapp

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

class SoundViewModel: BaseObservable() {

    var sound: Sound? = null
    set(sound) {
        field =sound
        notifyChange()
    }
    @get:Bindable
    val title: String?
    get() = sound?.name
}