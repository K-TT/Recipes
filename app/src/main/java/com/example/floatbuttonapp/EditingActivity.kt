package com.example.floatbuttonapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.adding_activity.*
import kotlinx.android.synthetic.main.adding_activity.itemDescription
import kotlinx.android.synthetic.main.adding_activity.itemName
import kotlinx.android.synthetic.main.editing_activity.*
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.widget.Toast


class EditingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.editing_activity)

        val id = intent.getIntExtra("id", 0)

        val db = SQLiteHelper(this)
  //      Toast.makeText(this, "$id", Toast.LENGTH_SHORT).show()

       val recipe = db.getRecipeById(id)
 //       val recipes = db.getRecipes()
//        val recipe = Recipe (recipes[id].id,recipes[id].name,recipes[id].description)
        Toast.makeText(this, "${recipe?.id}", Toast.LENGTH_SHORT).show()
        oldName.text = (recipe?.name)
        oldDescription.text = (recipe?.description)

        edit.setOnClickListener{
        val name= itemName.text.toString()
        val description = itemDescription.text.toString()
            if(recipe!=null){
            db.editRecipe(recipe,id,name,description)
       // val newRecipe=Recipe (0,name,description)
            finish()
            }
        }
    }
}