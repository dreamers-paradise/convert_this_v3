/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.codelab.basics

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.codelab.basics.ui.BasicsCodelabTheme
import java.math.RoundingMode
import java.text.DecimalFormat

class MainActivity:ComponentActivity() {
    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicsCodelabTheme {
                MyApp()
            }
        }
    }
    /* Base Units
       Mass - grams - DONE
       Time - seconds - DONE
       Temperature - Celsius
       Data Storage - byte
       Length - meter
       Speed - meters per second
       Volume - liter
       *** Area - meter
     */


//    TODO Chris build out test cases for the find_conversion functions
//    TODO Ray add conversion math for all units
//    /* Conversion Units: [second, minute, hour, day, week, month, year, century, millennium] */
//    fun toSeconds(fromThis: String, convertMe: Double): Double {return convertMe}
//    fun fromSeconds(toThat: String, convertMe: Double): Double {return convertMe}
//    fun findTimeConversion(fromThis: String, toThat: String, convertMe: Double): Double {return convertMe}
//
//    /* Conversion Units: [celsius, fahrenheit, newton, reaumur] */
//    fun toCelsius(fromThis: String, convertMe: Double): Double {return convertMe}
//    fun fromCelsius(toThat: String, convertMe: Double): Double {return convertMe}
//    fun findTemperatureConversion(fromThis: String, toThat: String, convertMe: Double): Double {return convertMe}
//
//    /* Conversion Units: [bit, byte, kilobyte, megabyte, gigabyte, terabyte, petabyte, exabyte, zettabyte, yottabyte] */
//    fun toByte(fromThis: String, convertMe: Double): Double {return convertMe}
//    fun fromByte(toThat: String, convertMe: Double): Double {return convertMe}
//    fun findDataStorageConversion(fromThis: String, toThat: String, convertMe: Double): Double {return convertMe}
//
//    /* Conversion Units: [centimeter, decimeter, millimeter, meter, decameter, hectometer, kilometer, inch, foot, yard, mile,] */
//    fun toMeter(fromThis: String, convertMe: Double): Double {return convertMe}
//    fun fromMeter(toThat: String, convertMe: Double): Double {return convertMe}
//    fun findLengthConversion(fromThis: String, toThat: String, convertMe: Double): Double {return convertMe}
//
//    /* Conversion Units: [centimetersPerSecond, metersPerSecond, kilometersPerSecond, inchesPerSecond, feetPerSecond, milesPerHour, knot] */
//    fun toMetersPerSecond(fromThis: String, convertMe: Double): Double {return convertMe}
//    fun fromMetersPerSecond(toThat: String, convertMe: Double): Double {return convertMe}
//    fun findSpeedConversion(fromThis: String, toThat: String, convertMe: Double): Double {return convertMe}
//
//    /* Conversion Units: [cubicMeter, barrel, cubicFoot, cubicDecimeter, liter, gallon, pint, cubicInch, cubicCentimeter, quart, gill, fluidOunce, fluidDram ] */
//    fun toLiter(fromThis: String, convertMe: Double): Double {return convertMe}
//    fun fromLiter(toThat: String, convertMe: Double): Double {return convertMe}
//    fun findVolumeConversion(fromThis: String, toThat: String, convertMe: Double): Double {return convertMe}


}

@Composable
private fun MyApp() {
    // Turning off onboarding until we have a useful screen
    var shouldShowOnboarding by rememberSaveable {mutableStateOf(false)}

    if(shouldShowOnboarding) {
        OnboardingScreen(onContinueClicked={shouldShowOnboarding=false})
    }
    else {
        Greetings()
    }
}

@Composable
private fun OnboardingScreen(onContinueClicked:()->Unit) {
    Surface {
        Column(
                modifier=Modifier.fillMaxSize(),
                verticalArrangement=Arrangement.Center,
                horizontalAlignment=Alignment.CenterHorizontally
              ) {
            Text("Convert this is the best")
            Button(
                    modifier=Modifier.padding(vertical=24.dp),
                    onClick=onContinueClicked
                  ) {
                Text("Continue")
            }
        }
    }
}

@Composable
private fun Greetings(names:List<String> = listOf("Mass", "Time", "Temperature", "Length", "Volume", "Speed", "Data Storage")) {
    LazyColumn(modifier=Modifier.padding(vertical=4.dp)) {
        items(items=names) {name->
            Greeting(name=name)
        }
    }
}

@Composable
private fun Greeting(name:String) {
    Card(
            backgroundColor=MaterialTheme.colors.surface,
            modifier=Modifier.padding(vertical=4.dp,horizontal=8.dp)
        ) {
        CardContent(name)
    }
}

@Composable
private fun CardContent(name:String) {
    var expanded by remember {mutableStateOf(false)}
        Row(
            modifier= Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
       ) {
        Column(
                modifier=Modifier
                    .weight(1f)
              )
            {
            Row(
                    modifier= Modifier
                        .padding(12.dp)
                        .animateContentSize(
                            animationSpec = spring(
                                dampingRatio = Spring.DampingRatioMediumBouncy,
                                stiffness = Spring.StiffnessLow
                            )
                        )
               ) {
                    if(expanded) {
                        Column(verticalArrangement=Arrangement.Center,
                                horizontalAlignment=Alignment.CenterHorizontally) {
                            Row(modifier=Modifier
                                .clickable {expanded=!expanded})
                             {
                                NamePlusIcon(name = name)
                            }
                            var expandedDropDown by remember { mutableStateOf(false)}
                            val suggestions  = getKeys(name)
                            var selectedText by remember { mutableStateOf(suggestions[0]) }

                            Column(Modifier.padding(5.dp)) {

                                Box {
                                    Row(Modifier.clickable {
                                        expandedDropDown = !expandedDropDown
                                    }) {
                                        Text(text = selectedText)
                                        Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = "")

                                        DropdownMenu(
                                            expanded = expandedDropDown,
                                            onDismissRequest = { expandedDropDown = false }
                                        ) {
                                            suggestions.forEach { label ->
                                                DropdownMenuItem(onClick = {
                                                    selectedText = label
                                                    expandedDropDown = false
                                                }) {
                                                    Text(text = label)
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            ConversionArea(name = name, conversion = selectedText)
                        }
                    } else {
                        Column(verticalArrangement=Arrangement.Center,
                                horizontalAlignment=Alignment.CenterHorizontally
                            ) {
                            Row(modifier=Modifier
                                .clickable {expanded=!expanded})
                            {
                                NamePlusIcon(name = name)
                            }
                        }
                    }
                }
            }
        }
    }

@Composable
fun NamePlusIcon(name:String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                modifier=Modifier.padding(horizontal=24.dp),
                text = name,
                style = MaterialTheme.typography.h4.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
            when (name) {
                "Data Storage" -> {
                    Image(
                        painter = painterResource(id = R.drawable.data_storage_icon),
                        contentDescription = "Contact profile picture",
                        modifier = Modifier
                            .size(70.dp)
                            .padding(horizontal = 4.dp)
                            .offset(x = (-24).dp)
                    )
                }
                "Mass" -> {
                    Image(
                        painter = painterResource(id = R.drawable.mass_icon),
                        contentDescription = "Contact profile picture",
                        modifier = Modifier
                            .size(70.dp)
                            .padding(horizontal = 4.dp)
                            .offset(x = (-24).dp)
                    )
                }
                "Time" -> {
                    Image(
                        painter = painterResource(id = R.drawable.time_icon),
                        contentDescription = "Contact profile picture",
                        modifier = Modifier
                            .size(70.dp)
                            .padding(horizontal = 4.dp)
                            .offset(x = (-24).dp)
                    )
                }
                "Temperature" -> {
                    Image(
                        painter = painterResource(id = R.drawable.temperature_icon),
                        contentDescription = "Contact profile picture",
                        modifier = Modifier
                            .size(70.dp)
                            .padding(horizontal = 4.dp)
                            .offset(x = (-24).dp)
                    )
                }
                "Length" -> {
                    Image(
                        painter = painterResource(id = R.drawable.length_icon),
                        contentDescription = "Contact profile picture",
                        modifier = Modifier
                            .size(70.dp)
                            .padding(horizontal = 4.dp)
                            .offset(x = (-24).dp)
                    )
                }
                "Volume" -> {
                    Image(
                        painter = painterResource(id = R.drawable.volume_icon),
                        contentDescription = "Contact profile picture",
                        modifier = Modifier
                            .size(70.dp)
                            .padding(horizontal = 4.dp)
                            .offset(x = (-24).dp)
                    )
                }
                "Speed" -> {
                    Image(
                        painter = painterResource(id = R.drawable.speed_icon),
                        contentDescription = "Contact profile picture",
                        modifier = Modifier
                            .size(70.dp)
                            .padding(horizontal = 4.dp)
                            .offset(x = (-24).dp)
                    )
                }
            }
        }
    }

fun getKeys(name: String): List<String> {
    val massKeys = listOf(
        "Gram(g)",
        "Ounce(oz)",
        "Pound(lb)",
        "Kilogram(kg)",
        "Microgram(mcg)",
        "Stone(st)",
        "US ton(t)",
        "Imperial ton(t)",
        "Metric ton(mt)"
    )
    val timeKeys = listOf(
        "Second",
        "Minute",
        "Hour",
        "Day",
        "Week",
        "Month",
        "Year"
    )
    val speedKeys = listOf(
        "Kilometer per hour",
        "Miles per hour(mph)",
        "Foot per second",
        "Meter per second",
        "Knot"
    )
    val dataStorageKeys = listOf(
        "bit(b)",
        "byte(B)",
        "kilobyte(kB)",
        "megabyte(MB)",
        "gigabyte(GB)",
        "terabyte(TB)",
        "petabyte(PB)"
    )
    return when(name) {
        "Mass" -> massKeys
        "Time" -> timeKeys
        "Speed" -> speedKeys
        "Data Storage" -> dataStorageKeys
        else -> {
            listOf("undefined")
        }
    }
}
/* Conversion Units: [second, minute, hour, day, week, month, year, century, millennium] */
private fun toSeconds(fromThis: String, convertMe: Double): Double {
    var conversion = convertMe
    when (fromThis) {
        "Nanosecond" -> conversion *= 1000000000
        "Microsecond" -> conversion *= 1000000
        "Millisecond" -> conversion *= 1000
        "Second" -> conversion *= 1
        "Minute" -> conversion *= 60
        "Hour" -> conversion *= 3600
        "Day" -> conversion *= 86400
        "Week" -> conversion *= 604800
        "Month" -> conversion *= 2629746
        "Year" -> conversion *= 31556952
        "Decade" -> conversion *= 315569520
        "Century" -> conversion *= 3155695200
        "Millennium" -> conversion *= 31557600000
    }
    return conversion
}
private fun fromSeconds(toThat: String, convertMe: Double): Double {
    var conversion = convertMe
    when (toThat) {
//        "Nanosecond" -> conversion /= 1000000000
//        "Microsecond" -> conversion /= 1000000
//        "Millisecond" -> conversion /= 1000
        "Second" -> conversion /= 1
        "Minute" -> conversion /= 60
        "Hour" -> conversion /= 3600
        "Day" -> conversion /= 86400
        "Week" -> conversion /= 604800
        "Month" -> conversion /= 2629746
        "Year" -> conversion /= 31556952
//        "Decade" -> conversion /= 315569520
//        "Century" -> conversion /= 3155695200
//        "Millennium" -> conversion /= 31557600000
    }
    return conversion
}
fun findTimeConversion(fromThis: String, toThat: String, convertMe: String): Double {
    val conversion = toSeconds(fromThis, convertMe.toDouble())
    val converted = fromSeconds(toThat, conversion)
    return converted
}

private fun toGrams(fromThis: String, convertMe: Double): Double {
    var conversion = convertMe

    when (fromThis) {
        "Ounce(oz)" -> conversion *= 28.34952
        "Pound(lb)" -> conversion *= 453.59237
        "Gram(g)" -> conversion *= 1
        "Kilogram(kg)" -> conversion *= 1000
        "Microgram(&mu;g)" -> conversion *= 1000000
        "Stone(st)" -> conversion *= 6350.29318
        "US ton(t)" -> conversion *=  907184.74
        "Imperial ton(t)" -> conversion *=  1016046.91
        "Metric ton(mt)" -> conversion *=  1000000
    }
    return conversion
}
private fun fromGrams(toThat: String, convertMe: Double): Double {
    var conversion = convertMe
    when (toThat) {
        "Ounce(oz)" -> conversion /= 28.34952
        "Pound(lb)" -> conversion /= 453.59237
        "Gram(g)" -> conversion /= 1
        "Kilogram(kg)" -> conversion /= 1000
        "Microgram(&mu;g)" -> conversion /= 1000000
        "Stone(st)" -> conversion /= 6350.29318
        "US ton(t)" -> conversion /=  907184.74
        "Imperial ton(t)" -> conversion /=  1016046.91
        "Metric ton(mt)" -> conversion /=  1000000
    }
    return conversion
}
fun findMassConversion(fromThis: String, toThat: String, convertMe: String): Double {
    val conversion = toGrams(fromThis, convertMe.toDouble())
    val converted = fromGrams(toThat, conversion)
    if(converted > 1){
        return Math.round(converted * 10000.0) / 10000.0
    }
    return converted
}

private fun toKph(fromThis: String, convertMe: Double): Double {
    var conversion = convertMe
    when (fromThis) {
        "Kilometer per hour" -> conversion *= 1
        "Miles per hour(mph)" -> conversion *= 1.609344
        "Foot per second" -> conversion *= 1.09728
        "Meter per second" -> conversion *= 3.6
        "Knot" -> conversion *= 1.852
    }
    return conversion
}
private fun fromKph(toThat: String, convertMe: Double): Double {
    var conversion = convertMe
    when (toThat) {
        "Kilometer per hour" -> conversion /= 1
        "Miles per hour(mph)" -> conversion /= 1.609344
        "Foot per second" -> conversion *= 1.09728
        "Meter per second" -> conversion *= 1.09728
        "Knot" -> conversion *= 0.539957
    }
    return conversion
}
fun findSpeedConversion(fromThis: String, toThat: String, convertMe: String): Double {
    val conversion = toKph(fromThis, convertMe.toDouble())
    val converted = fromKph(toThat, conversion)
    return converted
}

private fun toByte(fromThis: String, convertMe: Double): Double {
    var conversion = convertMe

    when (fromThis) {
        "byte(B)" -> conversion *= 1
        "bit(b)" -> conversion /= 8
        "kilobyte(kB)" -> conversion *= 1000
        "megabyte(MB)" -> conversion *= 1000000
        "gigabyte(GB)" -> conversion *= 1000000000
        "terabyte(TB)" -> conversion *= 1000000000000
        "petabyte(PB)" -> conversion *= 1000000000000000
    }
    return conversion
}
private fun fromByte(toThat: String, convertMe: Double): Double {
    var conversion = convertMe
    when (toThat) {
        "byte(B)" -> conversion /= 1
        "bit(b)" -> conversion *= 8
        "kilobyte(kB)" -> conversion /= 1000
        "megabyte(MB)" -> conversion /= 1000000
        "gigabyte(GB)" -> conversion /= 1000000000
        "terabyte(TB)" -> conversion /= 1000000000000
        "petabyte(PB)" -> conversion /= 1000000000000000
    }
    return conversion
}
fun findDataStorageConversion(fromThis: String, toThat: String, convertMe: String): Double {
    val conversion = toByte(fromThis, convertMe.toDouble())
    val converted = fromByte(toThat, conversion)
    return converted
}

@Composable
fun ConversionArea(name:String, conversion:String) {
    Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.surface),
            horizontalAlignment = Alignment.CenterHorizontally
          ) {

        var userText by remember { mutableStateOf("") }
        OutlinedTextField(
            value=userText,
            onValueChange={ userText = it },
            label={Text("Text area for $name")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        val keysToUse = getKeys(name)

        for (unit in keysToUse){
            var converted = ""
            if(userText.length > 0){
                val df = DecimalFormat("#.###")
                df.roundingMode = RoundingMode.CEILING
                when (name) {
                    "Mass" -> converted =
                        df.format(findMassConversion(conversion, unit, userText)).toString()
                    "Time" -> converted =
                        df.format(findTimeConversion(conversion, unit, userText)).toString()
                    "Speed" -> converted =
                        df.format(findSpeedConversion(conversion, unit, userText)).toString()
                    "Data Storage" -> converted =
                        df.format(findDataStorageConversion(conversion, unit, userText)).toString()
                }
            }
            Text(
                text = unit.plus(": ").plus(converted),
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.primary
            )
        }
    }
}

@Preview(
        showBackground=true,
        widthDp=320,
        uiMode=UI_MODE_NIGHT_YES,
        name="DefaultPreviewDark"
        )
@Preview(showBackground=true,widthDp=320)
@Composable
fun DefaultPreview() {
    BasicsCodelabTheme {
        Greetings()
    }
}

@Preview(showBackground=true,widthDp=320,heightDp=320)
@Composable
fun OnboardingPreview() {
    BasicsCodelabTheme {
        OnboardingScreen(onContinueClicked={})
    }
}
