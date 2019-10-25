package com.example.floatbuttonapp

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.view.Window
import kotlinx.android.synthetic.main.dialog_edit.*


class EditingDialog(activity: Activity): Dialog(activity){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_edit)

        edit.setOnClickListener{
            dismiss()
        }

    }

}
