package com.example.mygithubuser.theme

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.example.mygithubuser.databinding.ActivityThemeBinding
import com.example.mygithubuser.datastore.Preferences


private val Context.dataStore: DataStore<androidx.datastore.preferences.core.Preferences> by preferencesDataStore("setting")

class ThemeActivity : AppCompatActivity() {


    private lateinit var binding : ActivityThemeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThemeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val pref = Preferences.getInstance(dataStore)
        val viewModel =  ViewModelProvider(this, ThemeViewModel.Factory(pref)).get(ThemeViewModel::class.java)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel.getTheme().observe(this, {
            isDark ->
            if (isDark){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                binding.swDark.isChecked = true
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                binding.swDark.isChecked = false
            }
        })
        binding.swDark.setOnCheckedChangeListener{ _: CompoundButton, isCheck: Boolean ->
            viewModel.saveTheme(isCheck)
        }
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}