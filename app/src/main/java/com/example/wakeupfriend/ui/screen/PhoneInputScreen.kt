package com.example.wakeupfriend.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.launch
import com.example.wakeupfriend.ui.theme.WakeUpFriendTheme

@Composable
fun PhoneInputScreen(onPhoneNumberEntered: (String) -> Unit) {
    var phoneNumber by remember { mutableStateOf("") }
    var isPhoneNumberValid by remember { mutableStateOf(true) }
    val context = LocalContext.current // Context 객체를 가져옴
    val scope = rememberCoroutineScope() // CoroutineScope를 사용하여 Toast를 호출

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "친구의 전화번호를 입력하세요", style = MaterialTheme.typography.headlineLarge)

        Spacer(modifier = Modifier.height(24.dp))

        // 전화번호 입력 필드
        OutlinedTextField(
            value = phoneNumber,
            onValueChange = {
                phoneNumber = it
                // 전화번호 길이가 10자리 이상이면 유효성 체크
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

        // 등록 버튼
        Button(
            onClick = {
                if (isPhoneNumberValid) {
                    // 전화번호 유효성 체크 후 화면 전환 혹은 다른 동작
                    onPhoneNumberEntered(phoneNumber)
                } else {
                    // CoroutineScope를 사용하여 UI 스레드에서 Toast를 호출
                    scope.launch {
                        Toast.makeText(context, "전화번호를 확인해주세요.", Toast.LENGTH_SHORT).show()
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "등록하기")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PhoneInputScreenPreview() {
    WakeUpFriendTheme {
        PhoneInputScreen(onPhoneNumberEntered = {})
    }
}
