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
import android.widget.ListView
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

class MainActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var itemsAdapter: ItemAdapter
    private val itemsList = ArrayList<Item>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.listView)
        itemsAdapter = ItemAdapter(this, itemsList)
        listView.adapter = itemsAdapter

        fetchData()
    }

    private fun fetchData() {
        val apiService = RetrofitClient.instance.create(ApiService::class.java)
        apiService.getItems().enqueue(object : Callback<List<Item>> {
            override fun onResponse(call: Call<List<Item>>, response: Response<List<Item>>) {
                if (response.isSuccessful) {
                    response.body()?.let { items ->
                        val filteredAndSortedItems = items
                            .filter { !it.name.isNullOrEmpty() }
                            .sortedWith(compareBy({ it.listId }, { it.name }))

                        itemsList.clear()
                        itemsList.addAll(filteredAndSortedItems)
                        itemsAdapter.notifyDataSetChanged()
                    }
                }
            }

            override fun onFailure(call: Call<List<Item>>, t: Throwable) {
                // Handle failure
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