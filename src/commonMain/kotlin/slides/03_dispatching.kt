package slides

import SlideTitle
import TitleSlide
import codeSnippet
import net.kodein.cup.Slide
import net.kodein.cup.Slides
import net.kodein.cup.sa.rememberSourceCode
import org.jetbrains.compose.resources.ExperimentalResourceApi

private val title_dispatching by TitleSlide("Dispatching")

@OptIn(ExperimentalResourceApi::class)
private val dispatching by Slide(stepCount = 3) { step ->

    val sourceCode = rememberSourceCode("java") {
        val launch by marker(hidden(0))
        val implem by marker(onlyShown(2))
        """
        public final void runOnUiThread(Runnable action) {
            if (Thread.currentThread() != mUiThread) {
                mHandler.post(action); // Dispatch
            } else {
                action.run(); // Immediate execution
            }
        }
        ${launch}
        launch(Dispatchers.Main.immediate) {...}
        ${X}
        ${implem}
        // (old) implementation:
        val Main = HandlerContext(
            Handler(Looper.getMainLooper()),
            "UI")
        ${X}
        """.trimIndent()
    }
    
    SlideTitle(text = "Dispatch vs Immediate")
    codeSnippet(sourceCode, step)
}

val dispatchingSlides = Slides(title_dispatching, dispatching)