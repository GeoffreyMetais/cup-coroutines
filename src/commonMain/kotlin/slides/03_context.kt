package slides

import SlideTitle
import TitleSlide
import codeSnippet
import net.kodein.cup.Slide
import net.kodein.cup.Slides
import net.kodein.cup.sa.rememberSourceCode
import org.jetbrains.compose.resources.ExperimentalResourceApi

private val title_context by TitleSlide(
    "Coroutines context",
    "dispatcher + parent job + name + exception handler"
)

@OptIn(ExperimentalResourceApi::class)
private val context by Slide {

    val sourceCode = rememberSourceCode("kotlin") {
        //language=kotlin
        """
        val job = Job()
        val exceptionHandler = CoroutineExceptionHandler {
            coroutineContext, throwable -> whatever(throwable)
        }
        val dispatcher = Dispatchers.Default
        val name = "image processing context"
        
        scope.launch(dispatcher+exceptionHandler+job+name) { ... }
        """.trimIndent()
    }

    SlideTitle(text = "Coroutines context")
    codeSnippet(sourceCode)
}

val contextSlides = Slides(title_context, context)
