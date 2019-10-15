package com.example.floatbuttonapp

import android.annotation.SuppressLint
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues

class SQLiteHelper(context: Context) : SQLiteOpenHelper(context, "app.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        val contactsTable = (
                "CREATE TABLE recipe (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "name VARCHAR," +
                        "description VARCHAR" + ")")
        db?.execSQL(contactsTable)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }

    fun addRecipe(recipe: Recipe) {
        val db = writableDatabase
        val values = ContentValues()
        values.put("name", recipe.name)
        values.put("description", recipe.description)
        db.insert("recipe", null, values)
        db.close()
    }

    @SuppressLint("Recycle")
    fun getRecipes(): MutableList<Recipe> {
        val recipes: MutableList<Recipe> = mutableListOf()
        val db = readableDatabase

        val selectQuery = "SELECT  * FROM recipe"

        val cursor = db.rawQuery(selectQuery, null)

        if (cursor.moveToFirst()) {
            do {
                val recipe = Recipe(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    null,
                    cursor.getString(2)

                )
                recipes.add(recipe)
            } while (cursor.moveToNext())
        }
        return recipes
    }

    @SuppressLint("Recycle")
    fun getRecipeById(id: Int):Recipe? {
//        val recipe:Recipe
        val db = readableDatabase

        val selectQuery = "SELECT  * FROM recipe WHERE id =?"
        val cursor = db.rawQuery(selectQuery, arrayOf(id.toString()))
        return if (cursor.moveToFirst()) {
            val recipe = Recipe(
                Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                null,
                cursor.getString(2)
            )
            recipe
        } else {
            null
        }
    }

    fun deleteRecipeById(id: Int) {
        val db = writableDatabase
        db.delete("recipe", "id = ?", arrayOf(id.toString()))
        db.close()
    }

    fun editRecipe(recipe: Recipe,id:Int,name:String,description:String) {
        val db = writableDatabase

            val recipe = ContentValues()
            recipe.put("name", name)
            recipe.put("description", description)
            db.update("recipe", recipe,  "id =?", arrayOf(id.toString()))
           db.close()

    }


//    fun updateTask(tasks: Tasks): Boolean {
//        val db = this.writableDatabase
//        val values = ContentValues()
//        values.put(NAME, tasks.name)
//        values.put(DESC, tasks.desc)
//        values.put(COMPLETED, tasks.completed)
//        val _success = db.update(TABLE_NAME, values, ID + "=?", arrayOf(tasks.id.toString())).toLong()
//        db.close()
//        return Integer.parseInt("$_success") != -1
//    }

    fun deleteAll() {
        val db = writableDatabase
        val deleteTable = ("DELETE FROM recipe")
        db.execSQL(deleteTable)
        db.close()
    }

}