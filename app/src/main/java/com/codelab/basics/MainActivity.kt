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
private fun Greetings(names:List<String> = listOf("Data Storage", "Weight", "Time", "Temperature", "Length", "Volume", "Speed")) {
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
            modifier=Modifier
                .padding(12.dp)
                .animateContentSize(
                        animationSpec=spring(
                                dampingRatio=Spring.DampingRatioMediumBouncy,
                                stiffness=Spring.StiffnessLow
                                            )
                                   )
       ) {
        Column(
                modifier=Modifier
                    .weight(1f)
                    .padding(12.dp)
              ) {
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
                Column {
                    if(expanded) {
                    ConversionArea(name=name)
                    } else {
                        Text(
                                text=name,
                                style=MaterialTheme.typography.h4.copy(
                                        fontWeight=FontWeight.ExtraBold
                                                                      )
                            )
                    }
                        IconButton(onClick={expanded=!expanded}) {
                            when(name) {
                                "Data Storage"-> {
                                    Image(
                                            painter=painterResource(id=R.drawable.data_storage_icon),
                                            contentDescription="Contact profile picture",
                                            modifier=Modifier
                                                .size(70.dp)
                                         )
                                }
                                "Weight"      -> {
                                    Image(
                                            painter=painterResource(id=R.drawable.weight_icon),
                                            contentDescription="Contact profile picture",
                                            modifier=Modifier
                                                .size(70.dp)
                                         )
                                }
                                "Time"        -> {
                                    Image(
                                            painter=painterResource(id=R.drawable.time_icon),
                                            contentDescription="Contact profile picture",
                                            modifier=Modifier
                                                .size(70.dp)
                                         )
                                }
                                "Temperature" -> {
                                    Image(
                                            painter=painterResource(id=R.drawable.temperature_icon),
                                            contentDescription="Contact profile picture",
                                            modifier=Modifier
                                                .size(70.dp)
                                         )
                                }
                                "Length" -> {
                                    Image(
                                            painter=painterResource(id=R.drawable.length_icon),
                                            contentDescription="Contact profile picture",
                                            modifier=Modifier
                                                .size(70.dp)
                                         )
                                }
                                "Volume" -> {
                                    Image(
                                            painter=painterResource(id=R.drawable.volume_icon),
                                            contentDescription="Contact profile picture",
                                            modifier=Modifier
                                                .size(70.dp)
                                         )
                                }
                                "Speed" -> {
                                    Image(
                                            painter=painterResource(id=R.drawable.speed_icon),
                                            contentDescription="Contact profile picture",
                                            modifier=Modifier
                                                .size(70.dp)
                                         )
                                }
                            }
                        }
                    }
                }
            }
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

@Composable
fun ConversionArea(name:String) {
    Column(
            modifier =Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.surface),
            horizontalAlignment = Alignment.CenterHorizontally
          ) {
        Text(
                text = name,
                style = MaterialTheme.typography.h4,
                color = MaterialTheme.colors.primary
            )
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
