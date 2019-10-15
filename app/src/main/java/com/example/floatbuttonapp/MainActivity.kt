package com.example.floatbuttonapp

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle

import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback


class MainActivity : AppCompatActivity(), OnItemClick {

    private val adapter = RecyclerAdapter(this)
    private lateinit var db:SQLiteHelper



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


         db = SQLiteHelper(this)


        val restService = RestService()
        val call = restService.getApiClient().getRecipes()

        call.enqueue(object : retrofit2.Callback<ResponseModel>{
            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
            println("Error")
            }
            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                println("Success")
                if(response.body()!=null){
                    val recipes = response.body()!!.results
                    adapter.updateRecipes(recipes)
                }

            }
        })



        val layoutManager = GridLayoutManager(this, 2)
        val divider = DividerItemDecoration(this, layoutManager.orientation)

        recyclerView.layoutManager = layoutManager
        recyclerView.addItemDecoration(divider)
        recyclerView.adapter = adapter

        buttonGetAll.setOnClickListener {

           getRecipes()


        }
        buttonDeleteAll.setOnClickListener {
            db.deleteAll()
            getRecipes()
        }


        fab.setOnClickListener {
            val intent1 = Intent(this, AddingActivity::class.java)
            //intent1.putExtra("key",)
            startActivity(intent1)
        }
    }


    override fun onClick(recipe: Recipe) {
        val db = SQLiteHelper(this)
        //Toast.makeText(this, "${recipe.id}", Toast.LENGTH_SHORT).show()
        AlertDialog.Builder(this)
            .setMessage("Recipe ${recipe.id}")
            .setNegativeButton("Delete") { dialog, which ->
                db.deleteRecipeById(recipe.id)
                getRecipes()
            }
            .setPositiveButton("Edit") { dialog, which ->
                val intent2 = Intent(this, EditingActivity::class.java)
                intent2.putExtra("id", recipe.id)
                startActivity(intent2)
            }

            .create().show()
    }
    private fun getRecipes(){

        val recipes = db.getRecipes()
        adapter.updateRecipes(recipes)
    }

}
