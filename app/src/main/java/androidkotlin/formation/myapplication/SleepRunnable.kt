package androidkotlin.formation.myapplication

import android.util.Log

class SleepRunnable: Runnable {

    private var shouldSleep = false
    private var running = false

    override fun run() {
        Log.d("SleepRunnable", "Begin")

        var sum = 0L
        running = true

        for(i in 0..10000000000) {
            sum += i

            try {
                if(shouldSleep) {
                    shouldSleep = false
                    Thread.sleep(4000)
                }
                if(i % 10L == 0L) {
                    Thread.sleep(5000)

                } else
                    Thread.sleep(5)

            } catch (e: InterruptedException) {
                Log.e("SleepRunnable", "Arrêt Forcé du Thread")
            }

            if(!running) {
                break;
            }

            Log.d("SleepRunnable", "sum=$sum")
        }

        Log.d("SleepRunnable", "End")
    }

    fun sleep() {
        shouldSleep = true
    }

    fun stop() {
        Log.d("SleepRunnable", "Stopping runnable")
        running = false
    }
}