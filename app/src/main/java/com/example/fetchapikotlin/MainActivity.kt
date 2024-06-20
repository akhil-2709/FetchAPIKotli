package com.example.fetchapikotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.fetchapikotlin.ui.theme.FetchAPIKotlinTheme
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchapikotlin.adapter.ItemAdapter
import com.example.fetchapikotlin.model.Item
import com.example.fetchapikotlin.network.ApiService
import com.example.fetchapikotlin.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var itemAdapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        fetchItems()
    }

    private fun fetchItems() {
        val apiService = RetrofitClient.instance.create(ApiService::class.java)
        val call = apiService.getItems()

        call.enqueue(object : Callback<List<Item>> {
            override fun onResponse(call: Call<List<Item>>, response: Response<List<Item>>) {
                if (response.isSuccessful && response.body() != null) {
                    val items = response.body()!!
                        .filter { !it.name.isNullOrBlank() }
                        .sortedWith(compareBy({ it.listId }, { it.name }))

                    itemAdapter = ItemAdapter(items)
                    recyclerView.adapter = itemAdapter
                    Log.d("MainActivity", "Items loaded: ${items.size}")
                } else {
                    Toast.makeText(this@MainActivity, "Failed to retrieve data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Item>>, t: Throwable) {
                Log.e("MainActivity", "Error fetching data", t)
                Toast.makeText(this@MainActivity, "Error fetching data", Toast.LENGTH_SHORT).show()
            }
        })
    }
}

//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    FetchAPIKotlinTheme {
//        Greeting("Android")
//    }
//}