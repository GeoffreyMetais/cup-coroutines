package slides

import SlideTitle
import TitleSlide
import codeSnippet
import net.kodein.cup.Slide
import net.kodein.cup.sa.rememberSourceCode
import org.jetbrains.compose.resources.ExperimentalResourceApi

val title_suspend by TitleSlide("Suspending vs blocking")

@OptIn(ExperimentalResourceApi::class)
val running_coroutines by Slide(stepCount = 3) {step ->

   val sourceCode = rememberSourceCode("kotlin") {
        val job by marker(hidden(0))
        val join by marker(onlyShown(2))
       val launch by marker(hidden(2))
        //language=kotlin
        """
        ${launch}
        scope.launch() {
            // Get from IO context
            val image = withContext(ioDispatcher) { getImage() } 
            // Back on main thread
            imageView.setImageBitmap(image)
        }${X}
        
        ${job}val job = scope.launch(mainDispatcher) { 
            val value1 = async(cpuDispatcher) { getFirstValue() }
            val value2 = async(ioDispatcher) { getSecondValue() }
            useValues(value1.await(), value2.await())
        }${X}
        ${join}
        // suspends current coroutine until job is done
        job.join()
        // or cancel it
        job.cancel()
        ${X}
        """.trimIndent()
    }
    
    SlideTitle(text = "Launching coroutines")
    codeSnippet(sourceCode, step)
}