package com.example.wakeupfriend

import AlarmListScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.example.wakeupfriend.data.datasource.Alarm
import com.example.wakeupfriend.ui.screen.AlarmMainScreen
import com.example.wakeupfriend.ui.theme.WakeUpFriendTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WakeUpFriendTheme {
                val navController = rememberNavController()
                var alarms by remember { mutableStateOf(listOf<Alarm>()) }  // 알람 목록 상태

                NavHost(navController = navController, startDestination = "alarm_main") {
                    composable("alarm_main") {
                        AlarmMainScreen(navController = navController, onAlarmAdded = { alarm ->
                            // 알람이 추가되면 알람 목록에 추가
                            alarms = alarms + alarm
                            navController.navigate("alarm_list") // 알람 목록 화면으로 이동
                        })
                    }
                    composable("alarm_list") {
                        AlarmListScreen (alarms = alarms, onAlarmClick = { alarm ->
                            // 알람을 클릭했을 때의 동작을 여기에 추가
                        })
                    }
                }
            }
        }
    }
}
