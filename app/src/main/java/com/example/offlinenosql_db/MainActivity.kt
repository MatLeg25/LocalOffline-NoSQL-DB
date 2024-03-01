package com.example.offlinenosql_db

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.offlinenosql_db.data.local.entities.Item
import com.example.offlinenosql_db.data.local.entities.User
import com.example.offlinenosql_db.ui.theme.OfflineNoSQLDBTheme
import com.example.offlinenosql_db.utils.ObjectBox
import com.example.offlinenosql_db.utils.RealmDB.realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.RealmResults

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

                    /// RealM
                    realm.writeBlocking {
                        copyToRealm(Item().apply {
                            summary = "Do the laundry"
                            isComplete = false
                        })
                    }
                    // all items in the realm
                    val items: RealmResults<Item> = realm.query<Item>().find()

                    LazyColumn() {
                        item {
                            Greeting("ObjectBox")
                        }
                        items(users) { user ->
                            Text(text = user.name ?: "")
                        }
                        item {
                            Spacer(modifier = Modifier.fillMaxWidth().height(20.dp))
                            Greeting("Realm")
                        }
                        items(items) { item ->
                            Text(text = item.summary ?: "")
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