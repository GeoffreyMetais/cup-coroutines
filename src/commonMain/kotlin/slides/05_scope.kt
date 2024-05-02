package slides

import SlideTitle
import TitleSlide
import codeSnippet
import lightCodeSnippet
import net.kodein.cup.Slide
import net.kodein.cup.Slides
import net.kodein.cup.sa.rememberSourceCode
import org.jetbrains.compose.resources.ExperimentalResourceApi

private val title_scope by TitleSlide("Coroutines scope")

@OptIn(ExperimentalResourceApi::class)
private val scope by Slide(stepCount = 3) { step ->

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

@OptIn(ExperimentalResourceApi::class)
private val scope2 by Slide(stepCount = 3) {

    val sourceCode = rememberSourceCode("kotlin") {
        val cancel by marker(highlighted(1))
        ensureStep(2)
        //language=kotlin
        """
        open class ScopedViewModel : ViewModel() {

            protected val scope = ContextScope(
                Dispatchers.Main.immediate + SupervisorJob()
            )

            override fun onCleared() {
                super.onCleared()
                ${cancel}scope.cancel()${X}
            }
        }
        """.trimIndent()
    }

    SlideTitle(text = "Coroutines scope")
    codeSnippet(sourceCode, it)
}

private val scope3 by Slide {
    val code = """
    public interface CoroutineScope {
    /**
        * The context of this scope.
        * [blablabla]
        * By convention, should contain an instance of a [job][Job]
        * to enforce structured concurrency.
        */
    public val coroutineContext: CoroutineContext
}
        """.trimIndent()
    lightCodeSnippet(code)
}

val scopeSlides = Slides(title_scope, scope, scope2, scope3)