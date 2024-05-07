package slides

import TitleSlide
import codeSnippet
import greyedOut
import net.kodein.cup.Slide
import net.kodein.cup.Slides
import net.kodein.cup.sa.SourceCode
import net.kodein.cup.sa.rememberSourceCode

private val title by TitleSlide("Creating a coroutine")

private val suspendCoroutine by Slide(stepCount = 3) { step ->
    val code: SourceCode = rememberSourceCode("kotlin") {
        val hide by marker(greyedOut(1..2))
        val cancellation by marker(hidden(0..1))
        val switchName by marker(hidden(2))
        //language=kotlin
        """
        ${hide}suspend${X} fun <T> ${cancellation}suspendCancellableCoroutine${X}${switchName}${hide}suspendCoroutine${X}${X}(request: () -> Call<T>
        ${hide}) : T =${X} suspendCancellableCoroutine { continuation ->
            ${hide}val call = request()${X}
            call.enqueue${hide}(object : Callback<T> {${X}
                ${hide}fun${X} onResponse${hide}(call: Call<T>, response: Response<T>) {${X}
                    continuation.resume(response.body())
            ${hide}    }${X}
                ${hide}fun${X} onFailure${hide}(call: Call<T>, t: Throwable) {${X}
                    continuation.resumeWithException(t)
        ${hide}        }
            })${X}
            ${cancellation}continuation.invokeOnCancellation { call.cancel() }${X}
        }
        """.trimIndent()
    }
    codeSnippet(code, step)
}

val coroutineSlides = Slides(title, suspendCoroutine)
