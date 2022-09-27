package uz.exemple.less62_task4_localization_kotlin.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import uz.exemple.less62_task4_localization_kotlin.R

class MainActivity : AppCompatActivity() {
    lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    fun initViews() {
        val b_home = findViewById<Button>(R.id.b_home)
        context = this
        b_home.setOnClickListener {
            callLanguageActivity()
        }
    }

    fun callLanguageActivity() {
        val intent = Intent(context, LanguageActivity::class.java)
        startActivity(intent)
    }
}