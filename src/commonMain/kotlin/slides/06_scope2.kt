package slides

import SlideTitle
import codeSnippet
import net.kodein.cup.Slide
import net.kodein.cup.sa.rememberSourceCode
import org.jetbrains.compose.resources.ExperimentalResourceApi


@OptIn(ExperimentalResourceApi::class)
val scope2 by Slide(stepCount = 3) {

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
