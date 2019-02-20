package me.inassar.slidingcontent.state

sealed class ScreenState<out T> {
    class Render<T>(val renderState: T) : ScreenState<T>()
}