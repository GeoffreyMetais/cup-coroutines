package slides

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cup_presentation_coroutines.generated.resources.Res
import cup_presentation_coroutines.generated.resources.android_kotlin
import cup_presentation_coroutines.generated.resources.suspend_call
import net.kodein.cup.Slide
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource


@OptIn(ExperimentalResourceApi::class)
val intro by Slide {
    Image(
        painterResource(Res.drawable.android_kotlin),
        contentDescription = "Bugdroid holding Kotlin flags",
        modifier = Modifier
            .size(96.dp)
    )

    Text(
        text = "Coroutines basics",
        style = MaterialTheme.typography.h1,
        modifier = Modifier
            .padding(top = 8.dp)
    )
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.height(24.dp)
    ) {
        Text(
            text = "Introduction to Coroutines internals",
            style = MaterialTheme.typography.body1,
        )
        Image(
            painterResource(Res.drawable.suspend_call),
            contentDescription = "suspend icon",
            modifier = Modifier
                .padding(start = 4.dp)
                .size(16.dp)
        )
    }
}
