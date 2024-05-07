package slides

import SlideTitle
import TitleSlide
import codeSnippet
import lightCodeSnippet
import net.kodein.cup.Slide
import net.kodein.cup.Slides
import net.kodein.cup.sa.rememberSourceCode
import org.jetbrains.compose.resources.ExperimentalResourceApi

private val title_suspend by TitleSlide("Suspending vs blocking")

@OptIn(ExperimentalResourceApi::class)
private val running_coroutines by Slide(stepCount = 3) { step ->

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
            // stop if Job is cancelled
            ensureActive()
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

val launchingCoroutinesSlides = Slides(title_suspend, running_coroutines)
