package uz.exemple.less62_task4_localization_kotlin.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import uz.exemple.less62_task4_localization_kotlin.MyApplication
import uz.exemple.less62_task4_localization_kotlin.R
import uz.exemple.less62_task4_localization_kotlin.managers.LocaleManager

class LanguageActivity : AppCompatActivity() {
    lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language)
        initViews()
    }

    fun initViews() {
        context = this
        val b_english = findViewById<Button>(R.id.b_english)
        b_english.setOnClickListener {
            MyApplication.localManager.setNewLocale(context, LocaleManager.LANGUAGE_ENGLISH)

            finish()
        }
        val b_russian = findViewById<Button>(R.id.b_russian)
        b_russian.setOnClickListener {
            MyApplication.localManager.setNewLocale(context,LocaleManager.LANGUAGE_RUSSIAN)

            finish()
        }
        val b_uzbek = findViewById<Button>(R.id.b_uzbek)
        b_uzbek.setOnClickListener {
            MyApplication.localManager.setNewLocale(context,LocaleManager.LANGUAGE_UZBEK)

            finish()
        }


        val one = resources.getQuantityString(R.plurals.my_cats, 1, 1)

        val few = resources.getQuantityString(R.plurals.my_cats, 2, 3)

        val many = resources.getQuantityString(R.plurals.my_cats, 5, 10)

        Log.d("@@@one ", one)
        Log.d("@@@few ", few)
        Log.d("@@@many ", many)

    }
}