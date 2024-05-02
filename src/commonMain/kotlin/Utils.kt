import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import net.kodein.cup.Slide
import net.kodein.cup.sa.SAStyle
import net.kodein.cup.sa.SourceCode
import net.kodein.cup.sa.SourceCodeBuilder
import net.kodein.cup.sa.rememberSourceCode

internal fun TitleSlide(title: String, subTitle: String? = null) = Slide(if (subTitle == null) 1 else 2) { step ->
    Title(title)
    if (subTitle != null) {
        AnimatedVisibility(visible = step >= 1) {
            Title2(subTitle)
        }
    }
}

@Composable
internal fun codeSnippet(sourceCode: SourceCode, step: Int = 0) {
    SourceCode(
            step = step,
            sourceCode = sourceCode,
                style = TextStyle(fontFamily = KodeinTheme.Fonts.JetBrainsMono),
                theme = KodeinTheme.SourceCodeTheme,
                modifier = Modifier
                    .background(Color.Black, RoundedCornerShape(2.dp))
                    .padding(8.dp)
        )
}

@Composable
internal fun lightCodeSnippet(snippet: String, language: String = "kotlin") {
    val sourceCode = rememberSourceCode(language) { snippet }
    codeSnippet(sourceCode)
}

@Composable
internal fun Title(text: String) = Text(
    text = text,
    style = MaterialTheme.typography.h1
)

@Composable
internal fun Title2(text: String) = Text(
    text = text,
    style = MaterialTheme.typography.h2,
    modifier = Modifier.padding(top = 16.dp)
)

@Composable
internal fun SlideTitle(text: String) = Text(
    text = text,
        style = MaterialTheme.typography.h6,
        modifier = Modifier.padding(bottom = 8.dp)
)

@Composable
fun Link(
    text: String,
    url: String
) {
    val uriHandler = LocalUriHandler.current
    Text(
        text = text,
        color = MaterialTheme.colors.primary,
        textDecoration = TextDecoration.Underline,
        modifier = Modifier
            .pointerHoverIcon(PointerIcon.Hand)
            .clickable {
                uriHandler.openUri(url)
            }
    )
}

private val greyedOutStyle = SAStyle(SpanStyle(color = Color.DarkGray))
fun SourceCodeBuilder.greyedOut(vararg steps: Int): SourceCodeBuilder.State = styled(greyedOutStyle, *steps)
fun SourceCodeBuilder.greyedOut(vararg steps: IntRange): SourceCodeBuilder.State = styled(greyedOutStyle, *steps)
