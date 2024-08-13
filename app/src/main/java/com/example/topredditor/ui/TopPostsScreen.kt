package com.example.topredditor.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.topredditor.R
import com.example.topredditor.model.Post
import com.example.topredditor.ui.theme.TopRedditorTheme
import java.util.concurrent.TimeUnit

@Composable
fun TopPostsScreen(
    modifier: Modifier = Modifier,
    viewModel: TopPostsViewModel = viewModel(),
) {
    val uiState by viewModel.uiStateFlow.collectAsStateWithLifecycle()
    TopPostsScreenStateless(
        modifier = modifier,
        posts = uiState.posts,
    )
}

@Composable
private fun TopPostsScreenStateless(
    modifier: Modifier = Modifier,
    posts: List<Post>,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Box(
                modifier =
                Modifier
                    .statusBarsPadding()
                    .height(64.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.titleLarge,
                )
            }
        },
    ) { innerPadding ->
        LazyColumn(
            modifier = modifier.padding(innerPadding),
            contentPadding =
                PaddingValues(
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 16.dp,
                ),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(
                items = posts,
                key = { it.id },
            ) { post ->
                PostWidget(post = post)
            }
        }
    }
}

@Composable
private fun PostWidget(
    modifier: Modifier = Modifier,
    post: Post,
) {
    Row(modifier = modifier.height(IntrinsicSize.Max)) {
        val labelStyle = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Normal)

        Column(
            modifier =
            Modifier
                .weight(1F)
                .aspectRatio(3 / 4F),
        ) {
            PostThumbnail(
                modifier =
                Modifier
                    .fillMaxWidth()
                    .weight(1F),
            )
            Spacer(modifier = Modifier.height(4.dp))

            val postTime = post.creationTimeSecondsUtc * 1000
            val currentTime = System.currentTimeMillis()
            val hoursAgo = TimeUnit.MILLISECONDS.toHours(currentTime - postTime)
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = stringResource(R.string.hours_ago, hoursAgo),
                style = labelStyle,
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier =
                Modifier
                    .fillMaxHeight()
                    .weight(2F),
        ) {
            Text(
                text = post.author,
                style = MaterialTheme.typography.labelSmall,
                maxLines = 1,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = post.title,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Surface(
                tonalElevation = 2.dp,
                shape = RoundedCornerShape(30),
            ) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = stringResource(R.string.comments_count, post.commentsCount),
                    style = labelStyle,
                )
            }
        }
    }
}

@Composable
private fun PostThumbnail(modifier: Modifier = Modifier) {
    Box(
        modifier =
            modifier.background(
                color = MaterialTheme.colorScheme.surfaceColorAtElevation(2.dp),
            ),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            modifier = Modifier.size(36.dp),
            painter = painterResource(R.drawable.outline_image_24),
            contentDescription = null,
        )
    }
}

val SAMPLE_POST =
    Post(
        id = "t3_1epl10n",
        title =
            "Restaurant framed a hole someone punched in the men’s bathroom wall " +
                "n the men’s bathroom wall n the men’s bathroom wall",
        author = "Hot_Mess_Express",
        creationTimeSecondsUtc = 1723384846L,
        thumbnailLink = null,
        previewLink = null,
        commentsCount = 2500,
    )

@Preview
@Composable
private fun PostPreview() {
    TopRedditorTheme {
        Surface(modifier = Modifier.width(300.dp)) {
            PostWidget(post = SAMPLE_POST)
        }
    }
}
