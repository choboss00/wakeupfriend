package com.example.wakeupfriend.ui.screen

import android.app.TimePickerDialog
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.wakeupfriend.data.datasource.Alarm
import java.util.*

@Composable
fun AlarmMainScreen(navController: NavController, onAlarmAdded: (Alarm) -> Unit) {
    var phoneNumber by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }
    var isPhoneNumberValid by remember { mutableStateOf(true) }
    val context = LocalContext.current

    // 시간 선택 다이얼로그 상태
    val calendar = Calendar.getInstance()
    val timePickerDialog = remember {
        TimePickerDialog(
            context,
            { _, hourOfDay, minute ->
                // 선택된 시간으로 알람 시간 설정
                time = String.format("%02d:%02d", hourOfDay, minute)
            },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            false
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "알람 설정", style = MaterialTheme.typography.headlineLarge)

        Spacer(modifier = Modifier.height(24.dp))

        // 전화번호 입력 필드
        OutlinedTextField(
            value = phoneNumber,
            onValueChange = {
                phoneNumber = it
                isPhoneNumberValid = it.length >= 10
            },
            label = { Text("전화번호 입력") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 유효하지 않으면 경고 메시지 표시
        if (!isPhoneNumberValid) {
            Text(text = "유효한 전화번호를 입력해주세요", color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 메시지 입력 필드
        OutlinedTextField(
            value = message,
            onValueChange = { message = it },
            label = { Text("메시지 입력") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        // 알람 시간 설정 버튼
        OutlinedButton(
            onClick = { timePickerDialog.show() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = if (time.isEmpty()) "알람 시간 선택" else "설정된 시간: $time")
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 알람 설정 버튼
        Button(
            onClick = {
                if (isPhoneNumberValid) {
                    // 알람이 설정되었을 때 알람 목록에 추가
                    val id = System.currentTimeMillis().toInt()  // 고유한 id 생성
                    onAlarmAdded(Alarm(id = id, phoneNumber = phoneNumber, message = message, alarmTime = time))
                    navController.navigate("alarm_list")
                } else {
                    Toast.makeText(context, "전화번호를 확인해주세요.", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "알람 설정")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AlarmMainScreenPreview() {
    AlarmMainScreen(navController = rememberNavController(), onAlarmAdded = {})
}
