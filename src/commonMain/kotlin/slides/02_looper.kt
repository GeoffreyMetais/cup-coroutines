package slides

import SlideTitle
import lightCodeSnippet
import net.kodein.cup.Slide

val code = """
        function main
            initialize()
            while message != quit
                message := get_next_message()
                process_message(message)
            end while
        end function
        """.trimIndent()

val looper by Slide {
    SlideTitle("Application looper")
    lightCodeSnippet(code, language = "C")
}
