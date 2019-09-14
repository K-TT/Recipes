package com.example.floatbuttonapp

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager

import kotlinx.android.synthetic.main.activity_main.*
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T


class MainActivity : AppCompatActivity(), OnItemClick {

    private val adapter = RecyclerAdapter(this)
    private lateinit var db:SQLiteHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         db = SQLiteHelper(this)


        recyclerView.layoutManager = LinearLayoutManager(this)
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
