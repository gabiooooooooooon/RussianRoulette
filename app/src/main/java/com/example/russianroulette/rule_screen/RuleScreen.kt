package com.example.russianroulette.rule_screen

import android.app.AlertDialog
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import com.example.russianroulette.R
import com.example.russianroulette.ui.theme.Redd
import com.example.russianroulette.utils.NumberUtil

@Composable
fun RuleScreen() {

    var rotationValue by remember {
        mutableStateOf(0f)
    }
    var numberGet by remember {
        mutableStateOf(0)
    }
    var number by remember {
        mutableStateOf(0)
    }

    var numberSort by remember {
        mutableStateOf(100)
    }

    val textStyleBody1 = MaterialTheme.typography.bodyLarge
    var textStyle by remember { mutableStateOf(textStyleBody1) }
    var readyToDraw by remember { mutableStateOf(false) }


    val angle: Float by animateFloatAsState(
        targetValue = rotationValue,
        animationSpec = tween(
            durationMillis = 2000,
            easing = LinearOutSlowInEasing
        ),
        finishedListener = {
            val index = (365f - (rotationValue % 360f)) / (360f / NumberUtil.list.size)
            Log.d("MyLog", "index: ${index}")
            number = NumberUtil.list[index.toInt()]
            if (number == 0) {
                numberSort += numberGet * 50
            } else {
                if (number == 33 ||
                    number == 20 ||
                    number == 31 ||
                    number == 22 ||
                    number == 29 ||
                    number == 28 ||
                    number == 35 ||
                    number == 26 ||
                    number == 15 ||
                    number == 4 ||
                    number == 2 ||
                    number == 17 ||
                    number == 6 ||
                    number == 13 ||
                    number == 11 ||
                    number == 8 ||
                    number == 10 ||
                    number == 24) {
                    numberSort -= numberGet * 2
                } else if
                    (number == 32 ||
                    number == 19 ||
                    number == 21 ||
                    number == 25 ||
                    number == 34 ||
                    number == 27 ||
                    number == 36 ||
                    number == 30 ||
                    number == 23 ||
                    number == 5 ||
                    number == 16 ||
                    number == 1 ||
                    number == 14 ||
                    number == 9 ||
                    number == 18 ||
                    number == 7 ||
                    number == 12 ||
                    number == 3) {
                    numberSort += numberGet * 2
                }
            }
        }
    )
    Log.d("MyLog", "angle: $angle")

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Всего очков: " + numberSort,
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .wrapContentWidth()
                .wrapContentHeight(),
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold,
            color = White,
            onTextLayout = { textLayoutResult ->
                if (textLayoutResult.didOverflowWidth) {
                    textStyle = textStyle.copy(fontSize = textStyle.fontSize * 0.9)
                } else {
                    readyToDraw = true
                }
            }
        )

        Text(
            text = number.toString(),
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .wrapContentWidth()
                .wrapContentHeight(),
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold,
            color = White
        )

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()){
            Image(
                painter = painterResource(id = R.drawable.ruleta),
                contentDescription = "Ruleta",
                modifier = Modifier
                    .fillMaxSize()
                    .rotate(angle)
            )
            Image(
                painter = painterResource(id = R.drawable.flecha),
                contentDescription = "Arrow",
                modifier = Modifier
                    .fillMaxSize()
            )
        }

        val context = LocalContext.current;


        if (numberSort < 0) {
            Toast.makeText(context,
                "Вы проиграли ;)",
                Toast.LENGTH_LONG).show();
        }
        
        val button = Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            onClick = {
                if (numberSort > 0) {
                    val builder = AlertDialog.Builder(context)
                    val editText = EditText(context)
                    builder.setView(editText)
                    builder.setTitle("Введите значение")

                    builder.setPositiveButton("OK") { dialog, which ->
                        val inputText = editText.text

                        if (inputText.isNotEmpty()) {
                            if ((inputText.isDigitsOnly())) {
                                if ((inputText.toString().toInt() <= numberSort) && (inputText.toString().toInt() > 0))  {
                                    rotationValue = ((0..360).random().toFloat() + 720) + angle
                                    numberGet = inputText.toString().toInt()
                                    numberSort -= inputText.toString().toInt()
                                }
                                else {
                                    Toast.makeText(context, "Неверное значение", Toast.LENGTH_SHORT).show()
                                }
                            }
                            else {
                                Toast.makeText(context, "Значение не валидно", Toast.LENGTH_SHORT).show()

                            }
                        } else {
                            Toast.makeText(context, "Вы не ввели значение", Toast.LENGTH_SHORT).show()
                        }
                    }

                    builder.setNegativeButton("Отмена") { dialog, which ->
                        // Здесь выполняется код, если пользователь отменил диалог
                    }

                    builder.show()
                } else if (numberSort <= 0) {
                    val builderUn = AlertDialog.Builder(context)
                    builderUn.setTitle("Перезапустить игру?")
                    builderUn.setPositiveButton("Да") { dialog, which ->
                        numberSort = 100
                    }

                    builderUn.setNegativeButton("Нет") { dialog, which ->

                    }

                    builderUn.show()
                }
            },
            colors = ButtonDefaults.buttonColors(Redd)
        ) {
            Text(
                text = "Старт",
                color = White
            )
        }
    }
}