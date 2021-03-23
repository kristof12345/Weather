package com.kristof.weather.presenters

abstract class Presenter<Screen> {
    protected var screen: Screen? = null

    open fun attachScreen(screen: Screen) {
        this.screen = screen
    }

    open fun detachScreen() {
        this.screen = null
    }
}