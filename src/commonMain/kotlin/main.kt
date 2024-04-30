import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import net.kodein.cup.*
import net.kodein.cup.laser.laser
import net.kodein.cup.speaker.speakerWindow
import net.kodein.cup.ui.cupScaleDown
import org.kodein.emoji.compose.EmojiService
import slides.*


fun main() = cupApplication(
    title = "Coroutines basics"
) {
    remember {
        // https://github.com/kosi-libs/Emoji.kt?tab=readme-ov-file#initializing-the-emoji-service
        EmojiService.initialize()
    }

    MaterialTheme(
        colors = darkColors(),
        typography = MaterialTheme.typography.cupScaleDown()
    ) {
        Presentation(
            slides = presentationSlides,
            configuration = {
                speakerWindow()
                laser()
                defaultSlideSpecs = SlideSpecs(
                    size = SLIDE_SIZE_16_10,
                    startTransitions = TransitionSet.fade,
                    endTransitions = TransitionSet.fade
                )
            },
            backgroundColor = MaterialTheme.colors.background
        ) { slidesContent ->
            CompositionLocalProvider(
                LocalContentColor provides MaterialTheme.colors.onBackground
            ) {
                slidesContent()
            }
        }
    }
}

val presentationSlides = Slides(
    intro,
    title_suspend,
    running_coroutines,
    title_dispatching,
    looper,
    dispatching,
    title_context,
    context,
    title_scope,
    scope,
    scope2,
)
