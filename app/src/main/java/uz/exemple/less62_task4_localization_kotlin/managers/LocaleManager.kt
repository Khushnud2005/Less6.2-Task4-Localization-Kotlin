package uz.exemple.less62_task4_localization_kotlin.managers

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.os.LocaleList
import android.preference.PreferenceManager
import android.util.Log
import androidx.annotation.RequiresApi
import java.util.*
import kotlin.collections.LinkedHashSet

class LocaleManager(context: Context) {
    private var prefs: PrefsManager

    companion object {
        const val LANGUAGE_ENGLISH = "en"
        const val LANGUAGE_RUSSIAN = "ru"
        const val LANGUAGE_UZBEK = "uz"
        private const val LANGUAGE_KEY = "language_key"
        fun getLocale(res: Resources): Locale {
            val config = res.configuration
            return if (isAtLeastVersion(Build.VERSION_CODES.N)) config.locales[0] else config.locale
        }

        fun isAtLeastVersion(version: Int): Boolean {
            return Build.VERSION.SDK_INT >= version
        }
    }

    init {
        prefs = PrefsManager.getInstance(context)
    }

    fun setLocale(c: Context): Context {
        Log.d("@@@language ",getLanguage().toString())
        return update(c, getLanguage())
    }

    fun setNewLocale(c: Context, language: String) {
        persistLanguage(language)
        update(c, language)
    }

    private fun update(c: Context, language: String): Context {
        updateResources(c, language)
        val appContext = c.applicationContext
        if (c !== appContext) {
            updateResources(appContext, language)
        }
        return appContext
    }

    fun getLanguage(): String{
       val language = prefs.loadData(LANGUAGE_KEY)!!
        return language
    }


    @SuppressLint("ApplySharedPref")
    private fun persistLanguage(language: String) {
        // use commit() instead of apply(), because sometimes we kill the application process
        // immediately that prevents apply() from finishing
        prefs.saveData(LANGUAGE_KEY, language)
    }

    private fun updateResources(context: Context, language: String) {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val res = context.resources
        val config = Configuration(res.configuration)
        if (isAtLeastVersion(Build.VERSION_CODES.N)) {

            setLocaleForApi24(config, locale)
        } else if (isAtLeastVersion(Build.VERSION_CODES.JELLY_BEAN_MR1)) {

            config.setLocale(locale)
        } else {
            Log.d("@@@","VERSION_CODES>>")
            config.locale = locale
        }
        res.updateConfiguration(config, res.displayMetrics)
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private fun setLocaleForApi24(config: Configuration, target: Locale) {
        val set: MutableSet<Locale> = LinkedHashSet()
        // bring the target locale to the front of the list
        set.add(target)
        val all = LocaleList.getDefault()
        for (i in 0 until all.size()) {
            // append other locales supported by the user
            set.add(all[i])
        }
        val locales = set.toTypedArray()
        config.setLocales(LocaleList(*locales))
    }


}