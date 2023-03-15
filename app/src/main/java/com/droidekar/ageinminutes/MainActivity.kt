package com.droidekar.ageinminutes

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.droidekar.ageinminutes.ui.theme.AgeInMinutesTheme
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AgeInMinutesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Contents("Android")
                }
            }
        }
    }
}



@Composable
fun Contents(name: String) {
    // Fetching the Local Context
    val mContext = LocalContext.current

    // Declaring integer values
    // for year, month and day
    val mYear: Int
    val mMonth: Int
    val mDay: Int

    // Initializing a Calendar
    val mCalendar = Calendar.getInstance()

    // Fetching current year, month and day
    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

    mCalendar.time = Date()

    // Declaring a string value to
    // store date in string format
    val mDate = remember { mutableStateOf("") }
    var bdYear = remember { mutableStateOf(0) }
    var bdDay = remember { mutableStateOf(0) }
    var bdMonth = remember { mutableStateOf(0) }

    // Declaring DatePickerDialog and setting
    // initial values as current values (present year, month and day)
    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            mDate.value = "${mMonth+1}/$mDayOfMonth/$mYear"
            bdMonth.value = mMonth+1
            bdDay.value = mDayOfMonth
            bdYear.value = mYear
        }, mYear, mMonth, mDay
    )
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.size(20.dp))
        Button(onClick = {
        mDatePickerDialog.show()
        }) {
            Text("Select Birthday", modifier = Modifier)
        }
        Spacer(modifier = Modifier.size(20.dp))
        val minutes =
        Text(text = "Birthday: ${mDate.value}")

        val calender = Calendar.getInstance()
        val currentMillis = calender.timeInMillis

        //Text(text = "month picked: ${bdMonth.value} \n day picked: ${bdDay.value} \n yr picked: ${bdYear.value}")

        Spacer(modifier = Modifier.size(20.dp))

        if(bdYear.value > 0) {
            val bd = GregorianCalendar(bdYear.value, bdMonth.value-1, bdDay.value).time
            val bdMillis = bd.time

            Text(text = "Age in minutes: ${(currentMillis-bdMillis)/(1000*60)}")
        }


    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AgeInMinutesTheme {
        Contents("Android")
    }
}