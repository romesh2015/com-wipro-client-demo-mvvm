package com.wipro.assignment.mvvm.utility
import android.content.Context
import android.content.SharedPreferences
import com.wipro.assignment.mvvm.DemoApplication
/**
 * In this class we are storing the app session if required in app.
 */
class SharedPrefsHelper private constructor() {
    private val sharedPreferences: SharedPreferences
    fun delete(key: String?): Boolean {
        var result = false
        if (sharedPreferences.contains(key)) {
            result = editor.remove(key).commit()
        }
        return result
    }
    fun save(key: String?, value: Any?) {
        val editor = editor
        if (value is Boolean) {
            editor.putBoolean(key, (value as Boolean?)!!)
        } else if (value is Int) {
            editor.putInt(key, value)
        } else if (value is Float) {
            editor.putFloat(key, (value as Float?)!!)
        } else if (value is Long) {
            editor.putLong(key, (value as Long?)!!)
        } else if (value is String) {
            editor.putString(key, value as String?)
        } else if (value is Enum<*>) {
            editor.putString(key, value.toString())
        } else if (value != null) {
            throw RuntimeException("Attempting to save non-supported preference")
        }
        editor.commit()
    }
    operator fun <T> get(key: String?): T? {
        return sharedPreferences.all[key] as T?
    }
    operator fun <T> get(key: String?, defValue: T): T {
        val returnValue = sharedPreferences.all[key] as T?
        return returnValue ?: defValue
    }
    fun has(key: String?): Boolean {
        return sharedPreferences.contains(key)
    }
    private val editor: SharedPreferences.Editor
        private get() = sharedPreferences.edit()
    companion object {
        private const val TAG = "SharedPrefsHelper"
        private const val SHARED_PREFS_NAME = "TestData"
        private var instance: SharedPrefsHelper? = null

        @JvmStatic
        @Synchronized
        fun getInstance(): SharedPrefsHelper? {
            if (instance == null) {
                instance = SharedPrefsHelper()
            }
            return instance
        }
    }
    init {
        instance = this
        sharedPreferences = DemoApplication.getsAppContext()!!.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)
    }
}