import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.wakeupfriend.data.datasource.Alarm

@Composable
fun AlarmListScreen(alarms: List<Alarm>, onAlarmClick: (Alarm) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "설정된 알람 목록", style = MaterialTheme.typography.headlineLarge)

        Spacer(modifier = Modifier.height(24.dp))

        // 알람 목록을 표시하는 LazyColumn
        LazyColumn {
            items(alarms) { alarm ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    elevation = CardDefaults.elevatedCardElevation(8.dp) // 수정된 부분
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(text = "전화번호: ${alarm.phoneNumber}")
                            Text(text = "메시지: ${alarm.message}")
                            Text(text = "시간: ${alarm.alarmTime}")
                        }

                        IconButton(onClick = { onAlarmClick(alarm) }) {
                            Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit Alarm")
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AlarmListScreenPreview() {
    val alarms = listOf(
        Alarm(1, "010-1234-5678", "Test message 1", "08:00"),
        Alarm(2, "010-2345-6789", "Test message 2", "09:00")
    )
    AlarmListScreen(alarms = alarms, onAlarmClick = {})
}
