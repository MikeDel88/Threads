package androidkotlin.formation.myapplication

import android.os.Handler

data class ReadPart(val progress: Int, val content: String)

class ReaderRunnable(private val handler: Handler): Runnable {

    companion object {
        const val CODE_STARTED = 1
        const val CODE_PROGRESS = 2
        const val CODE_FINISHED = 3
    }
    override fun run() {

        handler.sendEmptyMessage(CODE_STARTED)

        Thread.sleep(1000)

        val sb = java.lang.StringBuilder()
        val totalSize = contentSize()
        var currentSize = 0

        while (currentSize < totalSize) {
            sb.append(readContent())

            currentSize += sb.length
            val progress = currentSize.toDouble() / totalSize * 100.0

            handler.sendMessage(handler.obtainMessage(CODE_PROGRESS, ReadPart(
                progress.toInt().coerceAtMost(100),
                sb.toString())))

            Thread.sleep(500)
        }

        handler.sendEmptyMessage(CODE_FINISHED)

    }
}