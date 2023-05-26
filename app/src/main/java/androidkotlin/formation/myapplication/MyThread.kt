package androidkotlin.formation.myapplication

import android.util.Log

class MyThread: Thread() {

    override fun run() {
        Log.d("MyThread", "Thread : id=${currentThread().id}, name= ${currentThread().name}")
        var sum = 0L
        for (i in 1..4000000000) {
            sum += i
        }
        Log.d("MyThread", "sum=$sum")
    }
}