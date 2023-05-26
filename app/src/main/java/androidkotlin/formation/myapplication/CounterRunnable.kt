package androidkotlin.formation.myapplication

import android.util.Log

class CounterRunnable: Runnable {

    private var counter = 0

    override fun run() {

        synchronized(this) {
            incremente()
            Log.d("CounterRunnable", "Thread=${Thread.currentThread().name}, value after increment = ${getValue()}")

            decremente()
            Log.d("CounterRunnable", "Thread=${Thread.currentThread().name}, value after decrement = ${getValue()}")
        }
    }

    fun incremente() {
        try {
            Thread.sleep(10)
        } catch (e: InterruptedException) { }

        counter++
    }

    fun decremente() {
        counter--
    }

    fun getValue() = counter
}