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
        //language=java
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

private val dispathing2 by Slide(stepCount = 6) { step ->
    val code_log = rememberSourceCode("kotlin") {
        val create by marker(onlyShown(1..3))
        val start by marker(onlyShown(2..3))
        val coroutine by marker(onlyShown(3))
        ensureStep(3)
        val immediate by marker(onlyShown(4..5))
        val immediatehl by marker(highlighted(4))
        val secondRun by marker(onlyShown(5))
        //language=kotlin
        """
        override fun onCreate(savedInstanceState: Bundle?) {
            Log.d(TAG, "onCreate")
            launch(Dispatchers.Main${immediate}${immediatehl}.immediate${X}${X}) {
                Log.d(TAG, "coroutine!")
            }
            Log.d(TAG, "onCreate end")
        }
        override fun onStart() {
            Log.d(TAG, "onStart")
        }
        //prints
        ${create}onCreate
        onCreate end${X}
        ${start}onStart${X}
        ${coroutine}coroutine!${X}
        ${secondRun}onCreate
        coroutine!
        onCreate end
        onStart${X}

        """.trimIndent()
    }
    codeSnippet(code_log, step)
}


val dispatchingSlides = Slides(title_dispatching, dispatching, dispathing2)