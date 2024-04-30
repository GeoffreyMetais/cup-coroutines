package slides

import SlideTitle
import TitleSlide
import codeSnippet
import net.kodein.cup.Slide
import net.kodein.cup.sa.rememberSourceCode
import org.jetbrains.compose.resources.ExperimentalResourceApi

val title_context by TitleSlide(
    "Coroutines context",
    "dispatcher + parent job + name + exception handler"
)

@OptIn(ExperimentalResourceApi::class)
val context by Slide {

    val sourceCode = rememberSourceCode("kotlin") {
        //language=kotlin
        """
        val job = Job()
        val exceptionHandler = CoroutineExceptionHandler {
            coroutineContext, throwable -> whatever(throwable)
        }
        val dispatcher = Dispatchers.Default
        val name = "image processing context"
        
        launch(dispatcher+exceptionHandler+job+name) { ... }
        """.trimIndent()
    }

    SlideTitle(text = "Coroutines context")
    codeSnippet(sourceCode)
}
