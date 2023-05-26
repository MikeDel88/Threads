package androidkotlin.formation.myapplication

import android.graphics.Color
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar

val lock1 = Object()
val lock2 = Object()

class Thread1 : Thread() {
    private val TAG = "Thread1"
    override fun run() {
        synchronized(lock1) {
            Log.d(TAG, "Holding lock1")
            try {
                sleep(10)
            } catch (e: InterruptedException) {}
            Log.d(TAG, "Waiting for lock2")
            synchronized(lock2) {
                Log.d(TAG, "Holding lock1 and lock2")
            }
        }
        Log.d(TAG, "Finished")

    }

}

class Thread2 : Thread() {
    private val TAG = "Thread2"
    override fun run() {
        synchronized(lock2) {
            Log.d(TAG, "Holding lock2")
            try {
                sleep(10)
            } catch (e: InterruptedException) {}
            Log.d(TAG, "Waiting for lock1")
            synchronized(lock1) {
                Log.d(TAG, "Holding lock2 and lock1")
            }
        }
        Log.d(TAG, "Finished")

    }

}


const val CODE_DELETE = 1
const val CODE_CANCEL_DELETE = 2

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("MainActivity", "onCreate() => begin")

//        formationThread()
//        formationExoThread()
//        formationThreadPause()
//        formationThreadAttendreFin()
//        formationThreadStop()
//        formationThreadRaceCondition()
//        formationThreadDeadLock()
//        formationThreadHandler()
//        formationThreadHandlerEnvoiDonnees()
//        formationThreadHandlerDecalage()
//        exerciceThread()

        Log.d("MainActivity", "onCreate() => End")
    }

    private fun formationThread() {

        val runnable = MyRunnable()
        val thread = Thread(runnable, "ThreadKotlin")

        thread.start()
    }

    private fun formationExoThread() {
        val strings = listOf("Kotlin", "Android", "Formation")

        for((i, string) in strings.withIndex()) {
            runThread(string, "Reversed #${i + 1}")
        }
    }
    private fun runThread(chaine: String, nomThread: String) {
        val runnable = StringReverser(chaine)
        Thread(runnable, nomThread).start()
    }

    private fun formationThreadPause() {
        val runnable = SleepRunnable()
        val thread = Thread(runnable)

        findViewById<Button>(R.id.sleepButton).setOnClickListener {
            runnable.sleep()
        }

        thread.start()
    }

    private fun formationThreadAttendreFin() {
        val runnable = MyRunnable()
        val thread = Thread(runnable, "ThreadKotlin")
        Log.d("MainActivity", "Start de mon Thread")
        thread.start()

        Log.d("MainActivity", "Thread lancée")

        // Attente de la fin du thread pour exécuter le reste.
        thread.join()

        Log.d("MainActivity", "Fin de mon Thread")
    }

    private fun formationThreadStop() {
        // Approche souple
        val runnable = SleepRunnable()
        val thread = Thread(runnable)

        findViewById<Button>(R.id.stopButton).setOnClickListener {
            runnable.stop()

            // va bloquer pendant 100 milliseconde en attendant que le thread s'arrête
            thread.join(100)

            // Arrêt brutale.
            thread.interrupt()
        }

        thread.start()
    }

    private fun formationThreadRaceCondition() {
        val runnable = CounterRunnable()
        val t1= Thread(runnable)
        val t2= Thread(runnable)
        val t3= Thread(runnable)

        t1.start()
        t2.start()
        t3.start()
    }

    private fun formationThreadDeadLock() {
        val t1 = Thread1()
        val t2 = Thread2()

        t1.start()
        t2.start()

        // Il faut modifier l'ordre de lock dans le Thread2 pour faire correspondre au Thread1
    }

    class Worker(private val handler: Handler, private val input: Int) : Runnable {
        companion object {
            const val CODE_RESULT = 1
            const val CODE_FAILURE = 2
        }
        override fun run() {
            Log.d("Worker", "Begin")

            if(input < 0) {
                handler.sendEmptyMessage(CODE_FAILURE)
            }

            Thread.sleep(2000)

            val result = input * input

            handler.sendMessage(handler.obtainMessage(CODE_RESULT, result, 0))

            Log.d("Worker", "End")
        }
    }

    // Execute un thread et modifier l'ui en conséquence.
    private fun formationThreadHandler() {

        val handler = object : Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message) {
                when(msg.what) {
                    Worker.CODE_RESULT -> {
                        val textView = findViewById<TextView>(R.id.content)
                        with(textView) {
                            setTextColor(Color.GREEN)
                            text = "The result is ${msg.arg1}"
                        }
                    }
                    Worker.CODE_FAILURE -> {
                        val textView = findViewById<TextView>(R.id.content)
                        with(textView) {
                            setTextColor(Color.RED)
                            text = "Invalid input"
                        }
                    }
                }
                Log.d("MainActivity", "got message ${msg.what}")
            }
        }
        val worker = Worker(handler, 4)
        val thread = Thread(worker)
        thread.start()
    }

    private fun formationThreadHandlerEnvoiDonnees() {

        val url = "https://kotlinlang.org"
        val statusTextview = findViewById<TextView>(R.id.status)
        val contentTextView = findViewById<TextView>(R.id.content)

        val handler = Handler(Looper.getMainLooper()) { message ->

            when(message.what) {
                GetUrlRunnable.CODE_STARTED -> {
                    statusTextview.text = "Started request on $url"
                }
                GetUrlRunnable.CODE_FINISHED -> {
                    val result = message.obj as UrlResult
                    statusTextview.text = "Finished request"
                    contentTextView.text = """
                        request duration : ${result.requestDuration} ms
                        content : ${result.content}
                    """.trimIndent()
                }
            }
            true
        }

        val thread = Thread(GetUrlRunnable(handler, url))
        thread.start()
    }

    private fun formationThreadHandlerDecalage() {
        val deleteButton = findViewById<Button>(R.id.deleteButton)
        val itemTextView = findViewById<TextView>(R.id.content)
        val root = findViewById<ConstraintLayout>(R.id.root)

        val handler = object : Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message) {
                when(msg.what) {
                    CODE_DELETE -> itemTextView.visibility = View.INVISIBLE
                    CODE_CANCEL_DELETE -> {
                        itemTextView.paintFlags = 0
                        removeMessages(CODE_DELETE)
                    }
                }
            }
        }

        deleteButton.setOnClickListener {
            itemTextView.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG

            handler.sendEmptyMessageDelayed(CODE_DELETE, 3000)

            val snackbar = Snackbar.make(root, "Deleted item", Snackbar.LENGTH_LONG).setAction("Cancel") {
                    handler.sendEmptyMessage(CODE_CANCEL_DELETE)
                }
                snackbar.show()
        }
    }

    private fun exerciceThread() {
        val status = findViewById<TextView>(R.id.statusTextView)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val progressBarText = findViewById<TextView>(R.id.progressTextview)
        val content = findViewById<TextView>(R.id.contentTextView)

        val handler = Handler(Looper.getMainLooper()) { message ->
            when(message.what) {
                ReaderRunnable.CODE_STARTED -> {
                    status.text = "Started"
                    progressBar.progress = 0
                    progressBarText.text = "0 %"
                }
                ReaderRunnable.CODE_PROGRESS -> {
                    val obj = message.obj as ReadPart
                    status.text = "In Progress"
                    progressBar.progress = obj.progress
                    progressBarText.text = "${obj.progress} %"
                    content.text = obj.content
                }
                ReaderRunnable.CODE_FINISHED -> {
                    status.text = "Finished"
                    progressBar.progress = 100
                    progressBarText.text = "100 %"
                }
            }
            true
        }

        val thread = Thread(ReaderRunnable(handler))
        thread.start()
    }

}