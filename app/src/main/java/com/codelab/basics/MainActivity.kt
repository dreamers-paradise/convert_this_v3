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
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codelab.basics.ui.BasicsCodelabTheme

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
       Mass - grams
       Time - seconds
       Temperature - Celcius
       Data Storage - byte
       Length - meter
       Speed - meters per second
       Volume - liter
       *** Area - meter
     */
    fun toGrams(fromThis: String, convertMe: Double): Double {
        var conversion = convertMe
        when (fromThis) {
            "carat (ct)(metric)" -> conversion *= 0.2
            "point (pt)(metric)" -> conversion *= 0.002
            "dram (dr)(avdp)" -> conversion *= 1.771845195
            "dram (dr)(troy)" -> conversion *= 3.8879346
            "grain (gr)(metric)" -> conversion *= 0.05
            "grain (gr)(troy)" -> conversion *= 0.06479891
            "gram (g)" -> conversion *= 1
            "hundredweight (hwt)(long)" -> conversion *= 50802.34544
            "hundredweight (hwt)(short)" -> conversion *= 45359.237
            "kilogram (kg)" -> conversion *= 1000
            "megagram (Mg)" -> conversion *= 1000000
            "milligram (mg)" -> conversion *= 0.001
            "microgram (&mu;g)" -> conversion *= 0.001
            "ounce (oz)(avdp)" -> conversion *= 28.34952313
            "ounce (oz)(troy)" -> conversion *= 31.1034768
            "pennyweight (dwt)" -> conversion *= 1.55517384
            "pound (avdp)" -> conversion *= 453.59237
            "pound (metric)" -> conversion *= 500
            "pound (troy)" -> conversion *= 373.2417216
            "slug" -> conversion *= 14593.903
            "stone (st)" -> conversion *= 6350.29318
            "ton-assay (I AT)(long)" -> conversion *= 32.666667
            "ton-assay (sh AT)(short)" -> conversion *= 29.166667
            "ton (I tn)(long)" -> conversion *= 1016046.909
            "ton (sh tn)(short)" -> conversion *= 907184.74
            "ton-metric (t)" -> conversion *= 1000000
            "tonne (t)(U.S. metric ton)" -> conversion *= 1000000
        }
        return conversion
    }
    fun fromGrams(toThat: String, convertMe: Double): Double {
        var conversion = convertMe
        when (toThat) {
            "carat (ct)(metric)" -> conversion /= 0.2
            "point (pt)(metric)" -> conversion /= 0.002
            "dram (dr)(avdp)" -> conversion /= 1.771845195
            "dram (dr)(troy)" -> conversion /= 3.8879346
            "grain (gr)(metric)" -> conversion /= 0.05
            "grain (gr)(troy)" -> conversion /= 0.06479891
            "gram (g)" -> conversion /= 1
            "hundredweight (hwt)(long)" -> conversion /= 50802.34544
            "hundredweight (hwt)(short)" -> conversion /= 45359.237
            "kilogram (kg)" -> conversion /= 1000
            "megagram (Mg)" -> conversion /= 1000000
            "milligram (mg)" -> conversion /= 0.001
            "microgram (&mu;g)" -> conversion /= 0.001
            "ounce (oz)(avdp)" -> conversion /= 28.34952313
            "ounce (oz)(troy)" -> conversion /= 31.1034768
            "pennyweight (dwt)" -> conversion /= 1.55517384
            "pound (avdp)" -> conversion /= 453.59237
            "pound (metric)" -> conversion /= 500
            "pound (troy)" -> conversion /= 373.2417216
            "slug" -> conversion /= 14593.903
            "stone (st)" -> conversion /= 6350.29318
            "ton-assay (l AT)(long)" -> conversion /= 32.666667
            "ton-assay (sh AT)(short)" -> conversion /= 29.166667
            "ton (l tn)(long)" -> conversion /= 1016046.909
            "ton (sh tn)(short)" -> conversion /= 907184.74
            "ton-metric (t)" -> conversion /= 1000000
            "tonne (t)(U.S. metric ton)" -> conversion /= 1000000
        }
        return conversion
    }
    fun findMassConversion(fromThis: String, toThat: String, convertMe: Double): Double {
        val conversion = toGrams(fromThis, convertMe)
        return fromGrams(toThat, conversion)
    }
//    TODO Chris build out test cases for the find_conversion functions
//    TODO Ray add conversion math for all units
//    /* Conversion Units: [second, minute, hour, day, week, month, year, century, millennium] */
//    fun toSeconds(fromThis: String, convertMe: Double): Double {return convertMe}
//    fun fromSeconds(toThat: String, convertMe: Double): Double {return convertMe}
//    fun findTimeConversion(fromThis: String, toThat: String, convertMe: Double): Double {return convertMe}
//
//    /* Conversion Units: [celcius, fahrenheit, newton, reaumur] */
//    fun toCelcius(fromThis: String, convertMe: Double): Double {return convertMe}
//    fun fromCelcius(toThat: String, convertMe: Double): Double {return convertMe}
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
    var shouldShowOnboarding by rememberSaveable {mutableStateOf(true)}

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
//                .padding(12.dp)
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
//                    .padding(12.dp)
              )
            {
            Row(
                    modifier=Modifier
                        .padding(12.dp)
                        .animateContentSize(
                                animationSpec=spring(
                                        dampingRatio=Spring.DampingRatioMediumBouncy,
                                        stiffness=Spring.StiffnessLow
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
                            ConversionArea(name = name)
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
                            .padding(horizontal = 8.dp)
                            .offset( x = -24.dp )
                    )
                }
                "Mass" -> {
                    Image(
                        painter = painterResource(id = R.drawable.mass_icon),
                        contentDescription = "Contact profile picture",
                        modifier = Modifier
                            .size(70.dp)
                            .padding(horizontal = 8.dp)
                            .offset( x = -24.dp )
                    )
                }
                "Time" -> {
                    Image(
                        painter = painterResource(id = R.drawable.time_icon),
                        contentDescription = "Contact profile picture",
                        modifier = Modifier
                            .size(70.dp)
                            .padding(horizontal = 8.dp)
                            .offset( x = -24.dp )
                    )
                }
                "Temperature" -> {
                    Image(
                        painter = painterResource(id = R.drawable.temperature_icon),
                        contentDescription = "Contact profile picture",
                        modifier = Modifier
                            .size(70.dp)
                            .padding(horizontal = 8.dp)
                            .offset( x = -24.dp )
                    )
                }
                "Length" -> {
                    Image(
                        painter = painterResource(id = R.drawable.length_icon),
                        contentDescription = "Contact profile picture",
                        modifier = Modifier
                            .size(70.dp)
                            .padding(horizontal = 8.dp)
                            .offset( x = -24.dp )
                    )
                }
                "Volume" -> {
                    Image(
                        painter = painterResource(id = R.drawable.volume_icon),
                        contentDescription = "Contact profile picture",
                        modifier = Modifier
                            .size(70.dp)
                            .padding(horizontal = 8.dp)
                            .offset( x = -24.dp )
                    )
                }
                "Speed" -> {
                    Image(
                        painter = painterResource(id = R.drawable.speed_icon),
                        contentDescription = "Contact profile picture",
                        modifier = Modifier
                            .size(70.dp)
                            .padding(horizontal = 8.dp)
                            .offset( x = -24.dp )
                    )
                }
            }
        }
    }

@Composable
fun ConversionArea(name:String) {
    Column(
            modifier =Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.surface),
            horizontalAlignment = Alignment.CenterHorizontally
          ) {
        SimpleOutlinedTextFieldSample(name=name)
        Text(
                text = "first unit",
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.primary
            )
        Text(
                text = "second unit",
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.primary
            )
        Text(
                text = "third unit",
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.primary
            )
        Text(
                text = "fourth unit",
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.primary
            )
        Text(
                text = "fifth unit",
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.primary
            )
        Text(
                text = "sixth unit",
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.primary
            )
    }
}

@Composable
fun SimpleOutlinedTextFieldSample(name:String) {
    var text by remember { mutableStateOf("") }
    OutlinedTextField(
        value=text,
        onValueChange={text=it},
        label={Text("Text area for $name")}
    )
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
