import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cup_presentation_coroutines.generated.resources.Logo
import cup_presentation_coroutines.generated.resources.Res
import net.kodein.cup.*
import net.kodein.cup.laser.laser
import net.kodein.cup.speaker.speakerWindow
import net.kodein.cup.ui.cupScaleDown
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.kodein.emoji.compose.EmojiService
import slides.*


@OptIn(ExperimentalResourceApi::class)
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
                Image(
                    painter = painterResource(Res.drawable.Logo),
                    contentDescription = "Deezer logo",
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .size(64.dp)
                    )
            }
        }
    }
}

val presentationSlides = Slides(
    intro,
    launchingCoroutinesSlides,
    dispatchingSlides,
    contextSlides,
    scopeSlides,
)
