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
import com.example.offlinenosql_db.data.local.entities.RealmPerson
import com.example.offlinenosql_db.data.local.entities.ObjectBoxPerson
import com.example.offlinenosql_db.data.local.entities.Address
import com.example.offlinenosql_db.data.local.entities.Details
import com.example.offlinenosql_db.ui.theme.OfflineNoSQLDBTheme
import com.example.offlinenosql_db.utils.ObjectBox
import com.example.offlinenosql_db.utils.RealmDB.realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.ext.toRealmList
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


                    val objectBoxPerson = ObjectBox.store.boxFor(ObjectBoxPerson::class.java)
                    val insertObjectBoxPeople = listOf(
                        ObjectBoxPerson(name = "Tina"),
                        ObjectBoxPerson(name = "Dina"),
                        ObjectBoxPerson(name = "Wina")
                    )
                    objectBoxPerson.put(insertObjectBoxPeople)
                    val objectBoxItems = objectBoxPerson.all.toList()

                    /// RealM
                    realm.writeBlocking {
                        copyToRealm(RealmPerson().apply {
                            name = "SOme item"
                            address = Address().apply {
                                street = "Rydygiera"
                                details = mutableListOf(
                                    Details().apply { description = "a" },
                                    Details().apply { description = "b" },
                                    Details().apply { description = "c" }
                                ).toRealmList()
                            }
                        })
                    }
                    // all items in the realm
                    val realmPeople: RealmResults<RealmPerson> = realm.query<RealmPerson>().find()

                    LazyColumn() {
                        item {
                            Greeting("ObjectBox")
                        }
                        items(objectBoxItems) { item ->
                            Text(text = item.name ?: "")
                        }
                        item {
                            Spacer(modifier = Modifier.fillMaxWidth().height(20.dp))
                            Greeting("Realm")
                        }
                        items(realmPeople) { item ->
                            Text(text = item.getDisplayText())
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