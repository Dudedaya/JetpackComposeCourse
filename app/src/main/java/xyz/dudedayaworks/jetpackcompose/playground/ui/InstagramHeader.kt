package xyz.dudedayaworks.jetpackcompose.playground.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import xyz.dudedayaworks.jetpackcompose.playground.R

@Composable
fun InstagramHeader(viewModel: MainViewModel) {
    val isFollowed by viewModel.isFollowing.collectAsState()
    Card(
        modifier = Modifier.padding(16.dp),
        shape = RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp),
        border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.onBackground),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    modifier = Modifier
                        .size(48.dp)
                        .background(color = Color.White, shape = CircleShape)
                        .padding(6.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(
                            Brush.linearGradient(
                                colors = listOf(
                                    Color.Magenta,
                                    Color(0xFF7F00FF)
                                )
                            )
                        )
                        .padding(4.dp)
                        .border(
                            width = 3.dp,
                            color = Color.White,
                            shape = RoundedCornerShape(9.dp)
                        ),
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = stringResource(id = R.string.app_name),
                    colorFilter = ColorFilter.tint(color = Color.White),
                    contentScale = ContentScale.Fit,
                )
                CounterWithText(count = "555", text = "posts")
                CounterWithText(count = "12M", text = "followers")
                CounterWithText(count = "12345", text = "following")
            }
            Text(
                text = "Instagram",
                fontSize = 28.sp,
                fontFamily = FontFamily.Cursive,
                modifier = Modifier.padding(top = 8.dp)
            )
            Text(
                text = "#YoursToMake",
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 4.dp)
            )
            Text(
                text = "www.facebook.com/emotional_health",
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 4.dp)
            )
            // since we use isFollowed by delegate - it would unbox to a value here
            // and recomposition would trigger for both InstagramHeader and FollowButton
            // when the state changes.
            // In that case we can pass the state itself
            FollowButton(isFollowed) {
                viewModel.toggleFollowing()
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun FollowButton(
    isFollowed: Boolean,
    onClick: () -> Unit,
) {
    Button(
        modifier = Modifier.padding(top = 4.dp),
        onClick = onClick,
        shape = RoundedCornerShape(4.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isFollowed) {
                MaterialTheme.colorScheme.primary.copy(alpha = .5f)
            } else {
                MaterialTheme.colorScheme.primary
            },
        ),
    ) {
        val text = if (isFollowed) {
            "Unfollow"
        } else {
            "Follow"
        }
        Text(
            text = text,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
        )
    }
}

@Composable
private fun CounterWithText(count: String, text: String) {
    Column(
        modifier = Modifier
            .height(64.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = count,
            fontSize = 22.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily.Serif,
        )
        Text(
            text = text,
            fontFamily = FontFamily.Monospace,
            fontSize = 14.sp,
        )
    }
}