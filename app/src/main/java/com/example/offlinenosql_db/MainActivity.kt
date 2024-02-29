package com.example.offlinenosql_db

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.offlinenosql_db.data.local.entities.User
import com.example.offlinenosql_db.ui.theme.OfflineNoSQLDBTheme
import com.example.offlinenosql_db.utils.ObjectBox

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OfflineNoSQLDBTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {


                    val userBox = ObjectBox.store.boxFor(User::class.java)
                    val insertUsers = listOf(
                        User(name = "Tina"),
                        User(name = "Dina"),
                        User(name = "Wina")
                    )

                    userBox.put(insertUsers)
                    val users = userBox.all.toList()
                    LazyColumn() {
                        item {
                            Greeting("Android")
                        }
                        items(users) { user ->
                            Text(text = user.name ?: "")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    OfflineNoSQLDBTheme {
        Greeting("Android")
    }
}