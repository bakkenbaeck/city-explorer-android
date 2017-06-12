package explorer.city.com.cityexplorer

import org.junit.Test


/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */

class ExampleInstrumentedTestKotlin {

    @Test
    @Throws(Exception::class)
    fun useAppContext() {
        val reference = "my string"
        val testStrings = arrayOf(
                reference,
                " $reference ",
                " $reference  ",
                " \t $reference \t ",
                " \n  $reference \n ",
                "\tmy \n string\t"
        )

    }
}
