package com.example.russianroulette

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.russianroulette.rule_screen.RuleScreen
import com.example.russianroulette.ui.theme.GreenBg
import com.example.russianroulette.ui.theme.RussianRouletteTheme
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.io.StreamCorruptedException

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)


        setContent {
            RussianRouletteTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = GreenBg
                ) {
                    RuleScreen()
                }
            }
        }


    }


//    fun saveData(res: Int) {
//        val editor = pref?.edit()
//        editor?.putInt("counter", res)
//        editor?.apply()
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        saveData(counter)
//    }

}