package com.example.hng8_task_two


import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton


class MainActivity : AppCompatActivity() {
    var mBehavior: BottomSheetBehavior<*>? = null
    var mBottomSheetDialog: BottomSheetDialog? = null
    var bottom_sheet: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //This gets the views from the layout file and set them to a variable
        //Edit Text To Collect User Input
        val content: EditText = findViewById<EditText>(R.id.edit_text)

        //Button To Display The Entered Text On The Bottom Sheet
        val display: MaterialButton = findViewById<MaterialButton>(R.id.display_btn)

        //This Listens for changes on the text entered and disables/enables the button based on if it is empty or not
        content.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                display.isEnabled = !s.toString().isEmpty()
            }
        })

        //This Brings up the bottom sheet when the button is clicked
        display.setOnClickListener { v: View? ->
            showTextBottomSheet(
                content.text.toString()
            )
        }
        bottom_sheet = findViewById<View>(R.id.bottom_sheet)
        val bottomsheet = null
        mBehavior = bottomsheet?.let { BottomSheetBehavior.from(it) }
    }

    //This Method Takes The Entered Text, Sets up The Bottom Sheet, Then Displays The Text
    fun showTextBottomSheet(text: String?) {
        if (this.mBehavior!!.state == BottomSheetBehavior.STATE_EXPANDED) {
            mBehavior!!.state = BottomSheetBehavior.STATE_COLLAPSED
        }
        @SuppressLint("InflateParams") val view: View =
            getLayoutInflater().inflate(R.layout.display_view_content, null)
        val content = view.findViewById<TextView>(R.id.content)
        val dismiss: MaterialButton = view.findViewById(R.id.dismiss)
        content.text = text
        //Dismissal of The Bottom Sheet
        dismiss.setOnClickListener { mBottomSheetDialog!!.dismiss() }
        mBottomSheetDialog = BottomSheetDialog(this)
        mBottomSheetDialog!!.setContentView(view)
        mBottomSheetDialog!!.dismissWithAnimation = true
        mBottomSheetDialog!!.window!!.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        //this will Show The Bottom Sheet
        mBottomSheetDialog!!.show()
    }


}


