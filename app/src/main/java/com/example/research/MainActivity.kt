package com.example.research

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.example.research.ui.theme.ResearchTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ResearchTheme {
                App()
            }
        }
    }
}

@Composable
fun App() {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val articles = articlesList
    // Set a breakpoint for wide vs narrow screens (600dp is commonly used)
    val isWideScreen = screenWidth >= 600

    if (isWideScreen) {
        // Grid layout for wider screens (like tablets)
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(8.dp)
        ) {
            items(articles.size) { index ->
                ArticleItem(articles[index])
            }
        }
    } else {
        // Single-column layout for narrow screens (like phones)
        LazyColumn(
            modifier = Modifier.padding(8.dp)
        ) {
            items(articles.size) { index ->
                ArticleItem(articles[index])
            }
        }
    }
}

@Composable
fun ArticleItem(article: Article) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        Text(
            text = article.title,
            modifier = Modifier.padding(16.dp)
        )
    }
}

data class Article(val title: String)

val articlesList = listOf(
    Article("Article 1"),
    Article("Article 2"),
    Article("Article 3"),
    Article("Article 4"),
    Article("Article 5"),
    Article("Article 6")
)