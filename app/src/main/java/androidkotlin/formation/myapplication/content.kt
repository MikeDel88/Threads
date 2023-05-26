package androidkotlin.formation.myapplication

private const val readSize = 100

private val content = """
    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer ornare mattis ligula at faucibus. Nunc iaculis id orci id ornare. Vivamus condimentum metus in leo sodales, ut tincidunt leo gravida. Suspendisse vel orci aliquet, maximus nunc nec, mattis ipsum. Morbi ac ultricies magna. Cras facilisis, massa non vestibulum volutpat, est tellus dictum ligula, vitae lobortis orci tortor a erat. Suspendisse rhoncus quis augue eget mollis.

    Phasellus pellentesque, quam eu placerat varius, ipsum ante sollicitudin lectus, nec mollis magna justo ac felis. Fusce iaculis lacus eu semper dapibus. Integer sodales commodo nibh, sed ornare magna vulputate eu. Sed velit quam, rhoncus quis lorem nec, accumsan consequat libero. Fusce ipsum tellus, tristique at eros eu, faucibus accumsan dolor. Vestibulum molestie nec sapien eget pellentesque. Suspendisse imperdiet mauris nec odio tristique blandit. Cras velit purus, dignissim id maximus a, congue laoreet sem. Donec lacus massa, efficitur eu rhoncus vitae, viverra id libero.

    Maecenas molestie cursus lacus, non posuere erat finibus quis. Aliquam dictum purus id mi vulputate, eu efficitur mi gravida. Proin vehicula ultricies nunc, non auctor mauris mollis in. Fusce sed diam at mi viverra euismod. In et ante ac nibh sollicitudin dapibus. Praesent sagittis accumsan gravida. Sed ornare suscipit lacus id molestie. Mauris elit orci, efficitur sed enim varius, gravida feugiat nulla. Ut fringilla enim sed ante luctus tincidunt. Nam eget lacus risus. Morbi urna magna, venenatis id ultricies sed, porttitor quis ex. Aliquam erat volutpat. Suspendisse semper laoreet turpis, id ullamcorper tellus tincidunt ac. Curabitur molestie metus ante, in cursus tellus ullamcorper nec. Donec pharetra urna vitae arcu facilisis, eget efficitur risus malesuada. Duis pellentesque orci velit, id vestibulum nisi rhoncus id.

    Nulla porttitor pellentesque libero, rutrum cursus est porttitor non. Suspendisse nulla sapien, congue eget mauris nec, ullamcorper posuere lacus. Curabitur at ex magna. Aliquam quis lobortis diam, at tempus tellus. Nulla fermentum pretium mauris id interdum. Etiam in libero eu turpis dictum dictum. Quisque molestie porttitor accumsan. Cras rutrum nisi vitae maximus ornare.

    Aliquam consequat metus ut velit mattis, a congue lectus dictum. Morbi a nibh a tellus iaculis viverra. Maecenas id odio in lacus sollicitudin laoreet ut ut est. Aenean ultricies est porta tincidunt luctus. Donec ac fringilla magna. Phasellus ac augue elit. Donec vel facilisis nibh, sit amet pretium dolor.

    Sed lacinia, metus vel volutpat tristique, nisi tortor venenatis urna, in condimentum risus magna ut turpis. Aliquam quis lorem vitae lacus pulvinar placerat a eget neque. Nulla ac imperdiet ipsum. Praesent orci orci, condimentum id lectus ac, cursus condimentum quam. Aliquam erat volutpat. Aenean nisi ipsum, aliquet efficitur ante non, cursus vehicula purus. Vivamus aliquam, libero eget fermentum vehicula, sem urna finibus sem, interdum tincidunt ipsum felis ac nisi.

    Suspendisse laoreet elementum tellus at rutrum. Fusce ut tellus lacinia, commodo nibh sit amet, faucibus elit. Aenean metus nunc, hendrerit at mattis et, sollicitudin vitae magna. Suspendisse a imperdiet nulla. Vestibulum sed tortor volutpat, ornare sem ac, ultrices est. Proin venenatis non ligula nec congue. Curabitur scelerisque sollicitudin sapien vel suscipit. Maecenas efficitur vitae sem in ornare. Sed et nisl sed ligula iaculis ornare eu eu justo. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Ut non nisl mattis, consequat ante et, consequat odio. Aliquam arcu tellus, laoreet sit amet lobortis at, scelerisque a nisi. Phasellus in faucibus ipsum. Nam et dui ultricies metus vestibulum laoreet.

    In hac habitasse platea dictumst. Morbi quam urna, ornare vitae quam et, placerat congue risus. Phasellus vel risus eget lectus facilisis tempor. Ut faucibus in felis vitae porta. Aliquam id nisl libero. Quisque consectetur nibh mauris. Sed tristique massa non urna hendrerit sollicitudin. In a orci sed turpis venenatis pretium ut id mauris. Nulla congue commodo sem. Duis ac eros justo. Pellentesque tortor est, rhoncus non laoreet et, fermentum nec nunc. Vivamus venenatis est diam, in tristique mi elementum et. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos.

    Vestibulum cursus orci et turpis interdum lacinia. Nulla ligula arcu, interdum id tellus eu, dignissim commodo augue. Curabitur tempor sit amet orci at venenatis. Suspendisse ac leo neque. Mauris quis hendrerit orci. Praesent laoreet lectus nulla, vitae congue dui porta et. Vivamus tincidunt dui dui, non consequat lectus dapibus a. Maecenas est nulla, viverra eget cursus sit amet, elementum tristique ligula. Integer cursus, elit a egestas congue, neque ipsum vehicula mauris, nec pulvinar ipsum lectus ut tortor. Aliquam erat volutpat. Sed feugiat purus vel diam congue porta.

    Pellentesque faucibus, magna et finibus hendrerit, nulla ante ultrices purus, in hendrerit ligula nisl ac turpis. Donec vitae eros congue magna porttitor blandit. Nullam at molestie elit. In cursus varius tincidunt. Donec tincidunt congue mollis. Maecenas nec sem vitae risus ullamcorper fringilla. Duis sed lorem libero. Vestibulum lectus ipsum, molestie eu dui nec, ultrices interdum sapien. Etiam ultrices quis nibh eget vulputate. 
""".trimIndent()

private var readIndex = 0

fun contentSize()  = content.length

fun readContent() : String {
    if (readIndex > contentSize()) {
        return ""
    }
    val read = content.substring(readIndex, readIndex + readSize)
    readIndex += readSize

    return read
}