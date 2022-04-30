package com.example.sharedpreferencestest

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sharedpreferencestest.databinding.ActivityMainBinding

const val APP_PREFERENCES = " app preferences"
const val PREFERENCES_TEXT_KEY_VALUE = "key value"
class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private lateinit var preferences: SharedPreferences

    private val preferencesListener = SharedPreferences.OnSharedPreferenceChangeListener{preferences, key ->
        if (key == PREFERENCES_TEXT_KEY_VALUE){
            binding.savedSharedPreferencesTextView.text = preferences.getString(key, "")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        val currentValue = preferences.getString(PREFERENCES_TEXT_KEY_VALUE, "")
        binding.editTextSharedPreferences.setText(currentValue)
        binding.savedSharedPreferencesTextView.text = currentValue

        binding.saveFromEditTextButton.setOnClickListener {
            val value = binding.editTextSharedPreferences.text.toString()
            preferences.edit()
                .putString(PREFERENCES_TEXT_KEY_VALUE, value)
                .apply()
        }
        binding.clearButton.setOnClickListener {
            binding.editTextSharedPreferences.setText("")
        }

        preferences.registerOnSharedPreferenceChangeListener(preferencesListener)
    }

    override fun onDestroy() {
        super.onDestroy()
        preferences.unregisterOnSharedPreferenceChangeListener(preferencesListener)
    }
}