package androidkotlin.formation.myapplication

class StringReverser(private val chaine: String): Runnable {
    override fun run() {
        println("Thread=${Thread.currentThread().name}")

        val chaineConverted = chaine.reversed()

        println("""
        >Chaine originale=$chaine, 
        >Chaine renversée:$chaineConverted
        >Nombre de caractères chaine renversée: ${chaineConverted.length}
        """.trimMargin(">"))
    }

}