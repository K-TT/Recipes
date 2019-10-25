package com.example.floatbuttonapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_search.*
import retrofit2.Call
import retrofit2.Response

class SearchFragment: Fragment(R.layout.fragment_search), OnItemClick{

    private val adapter = RecyclerAdapter(this)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val layoutManager = GridLayoutManager(activity, 2)
        val divider = DividerItemDecoration(activity, layoutManager.orientation)

        recyclerView.layoutManager = layoutManager
        recyclerView.addItemDecoration(divider)
        recyclerView.adapter = adapter

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
     
    }

    override fun onClick(recipe: Recipe) {
        val dialog = EditingDialog(activity!!)
        dialog.show()

    }


}
