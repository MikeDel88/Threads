package androidkotlin.formation.myapplication

import android.util.Log

class MyRunnable: Runnable {
    override fun run() {
        Log.d("MyRunnable", "Thread : id=${Thread.currentThread().id}, name= ${Thread.currentThread().name}")
        var sum = 0L
        for (i in 1..4000000000) {
            sum += i
        }
        Log.d("MyRunnable", "sum=$sum")
    }
}