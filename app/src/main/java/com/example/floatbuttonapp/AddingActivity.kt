package com.example.floatbuttonapp

import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.adding_activity.*
import java.util.concurrent.TimeUnit


class AddingActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.adding_activity)

        create.setOnClickListener {
            val name = itemName.text.toString()
            val description = itemDescription.text.toString()
            val newRecipe = Recipe(0, name,null, description)
            MyTask().execute(newRecipe)

        }
    }

    inner class MyTask :
        AsyncTask<Recipe, Unit, Unit>() { //<входной параметр для doInBackground, OnProgressUpdate, выходной параметр для doInBackground в onPostExecute  >

        private val db = SQLiteHelper(applicationContext)

        override fun onPreExecute() {
            super.onPreExecute()
            progressBar.visibility = View.VISIBLE
        }

        //        override fun onProgressUpdate(vararg values: Void?) {
//            super.onProgressUpdate(*values)
//        }
        override fun doInBackground(vararg params: Recipe) {
            try {
                db.addRecipe(params[0])
                TimeUnit.SECONDS.sleep(2)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }


        }

        override fun onPostExecute(result: Unit) {
            super.onPostExecute(result)
            progressBar.visibility = View.INVISIBLE
            finish()
            //startActivity(intent)
        }
    }
}
