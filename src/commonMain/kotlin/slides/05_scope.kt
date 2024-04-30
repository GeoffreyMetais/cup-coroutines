package slides

import SlideTitle
import TitleSlide
import codeSnippet
import net.kodein.cup.Slide
import net.kodein.cup.sa.rememberSourceCode
import org.jetbrains.compose.resources.ExperimentalResourceApi

val title_scope by TitleSlide("Coroutines scope")

@OptIn(ExperimentalResourceApi::class)
val scope by Slide(stepCount = 3) { step ->


    val sourceCode = rememberSourceCode("kotlin") {
        val global by marker(onlyShown(1))
        ensureStep(2)
        //language=kotlin
        """
        coroutineScope { 
            val deferred1 = async(Dispatchers.Default) { getFirstValue() }
            val deferred2 = async(Dispatchers.IO) { getSecondValue() }
            useValues(deferred1.await(), deferred2.await())
        } // Waits for completion
        
        ${global}// Conveniency object for transition and special cases
        GlobalScope.launch { fireAndForget() }${X}
        """.trimIndent()
    }

    SlideTitle(text = "Coroutines scope")
    codeSnippet(sourceCode, step)
}
