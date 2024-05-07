package slides

import TitleSlide
import lightCodeSnippet
import net.kodein.cup.Slide
import net.kodein.cup.Slides

private val flow_title by TitleSlide("Flows")

private val flow by Slide {
    val sourceCode = """
            public interface Flow<out T> {
                // This method can be used along with SAM-conversion of FlowCollector
                public suspend fun collect(collector: FlowCollector<T>)
            }

            public fun interface FlowCollector<in T> {

                public suspend fun emit(value: T)
            }
        """.trimIndent()

    TitleSlide("What is a Flow?")
    lightCodeSnippet(sourceCode)
}

val flow_slides = Slides(flow_title, flow)
