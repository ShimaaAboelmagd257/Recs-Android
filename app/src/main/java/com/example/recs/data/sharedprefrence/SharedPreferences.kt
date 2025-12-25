package com.example.recs.data.sharedprefrence
import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class SharedPreferences @Inject constructor(private val context: Context) : SharedPreference {

    private val sharedPreferences: SharedPreferences by lazy {
        context.getSharedPreferences("com.example.recs.data.sharedPreference.SharedPreferences", Context.MODE_PRIVATE)
    }
    fun saveUrl(key: String , url: String) {
        sharedPreferences.edit().putString(key, url).apply()
    }
    override fun addString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }
    override fun addFloat(key: String, value: Float) {
        sharedPreferences.edit().putFloat(key, value).apply()
    }
    override fun getFloat(key: String, value: Float) :Float {
        return sharedPreferences.getFloat(key, value)
    }

    override  fun getString(key: String, defaultValue: String): String {
        return sharedPreferences.getString(key, defaultValue)!!
    }

    override fun addBoolean(key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    override fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, defaultValue)
    }

    override fun deleteData(key: String) {
        sharedPreferences.edit().remove(key).apply()
    }
}