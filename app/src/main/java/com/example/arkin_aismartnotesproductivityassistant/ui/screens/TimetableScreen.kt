package com.example.arkin_aismartnotesproductivityassistant.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.adamglin.PhosphorIcons
import com.adamglin.phosphoricons.*
import com.adamglin.phosphoricons.bold.*
import com.adamglin.phosphoricons.*
import com.adamglin.phosphoricons.fill.*
import com.adamglin.phosphoricons.*
import com.adamglin.phosphoricons.regular.*
import com.adamglin.phosphoricons.*
import com.adamglin.phosphoricons.light.*
import com.adamglin.phosphoricons.*
import com.example.arkin_aismartnotesproductivityassistant.ui.theme.*
import com.example.arkin_aismartnotesproductivityassistant.viewmodel.AppViewModel

@Composable
fun TimetableScreen(navController: NavController, viewModel: AppViewModel) {
    val timetableList = viewModel.timetableList
    val days = listOf("Mon" to "27", "Tue" to "28", "Wed" to "29", "Thu" to "30", "Fri" to "31")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ArkinBg)
            .padding(24.dp)
            .padding(bottom = 80.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(bottom = 24.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .shadow(10.dp, CircleShape, spotColor = Color.Black.copy(alpha = 0.05f))
                    .clip(CircleShape)
                    .background(Color.White)
                    .clickable { navController.popBackStack() },
                contentAlignment = Alignment.Center
            ) {
                Icon(PhosphorIcons.Regular.ArrowLeft, contentDescription = "Back", modifier = Modifier.size(20.dp), tint = ArkinText)
            }
            Text(text = "Timetable", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = ArkinText)
        }

        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.padding(bottom = 24.dp)) {
            items(days) { day ->
                val isSelected = day.first == "Mon"
                Box(
                    modifier = Modifier
                        .size(56.dp, 64.dp)
                        .shadow(if (isSelected) 10.dp else 5.dp, RoundedCornerShape(16.dp), spotColor = if (isSelected) ArkinPrimary else Color.Black.copy(alpha = 0.05f))
                        .clip(RoundedCornerShape(16.dp))
                        .background(if (isSelected) ArkinPrimary else Color.White)
                        .clickable { /* Select day */ },
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = day.first, fontSize = 12.sp, fontWeight = FontWeight.Medium, color = if (isSelected) Color.White else ArkinTextMuted)
                        Text(text = day.second, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = if (isSelected) Color.White else ArkinTextMuted)
                    }
                }
            }
        }

        Box(modifier = Modifier.fillMaxSize()) {
            Box(modifier = Modifier.padding(start = 72.dp).fillMaxHeight().width(1.dp).background(Color(0xFFE5E7EB)))

            LazyColumn(verticalArrangement = Arrangement.spacedBy(24.dp)) {
                items(timetableList) { c ->
                    val (bgColor, ringColor) = when (c.color) {
                        "orange" -> IconColorOrange to IconBgOrange
                        "purple" -> IconColorPurple to IconBgPurple
                        "teal" -> IconColorTeal to IconBgTeal
                        else -> IconColorPurple to IconBgPurple
                    }

                    Row(verticalAlignment = Alignment.Top, modifier = Modifier.fillMaxWidth()) {
                        Box(modifier = Modifier.width(60.dp).padding(top = 4.dp), contentAlignment = Alignment.TopEnd) {
                            Text(text = c.time.split(" - ")[0], fontSize = 12.sp, fontWeight = FontWeight.Bold, color = ArkinText)
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Box(modifier = Modifier.padding(top = 8.dp)) {
                            Box(modifier = Modifier.size(12.dp).clip(CircleShape).background(bgColor).border(4.dp, ringColor, CircleShape))
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .shadow(10.dp, RoundedCornerShape(16.dp), spotColor = Color.Black.copy(alpha = 0.05f))
                                .clip(RoundedCornerShape(16.dp))
                                .background(Color.White)
                                .padding(16.dp)
                        ) {
                            Column {
                                Text(text = c.subject, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = ArkinText)
                                Spacer(modifier = Modifier.height(4.dp))
                                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                                    Icon(PhosphorIcons.Regular.MapPin, contentDescription = null, tint = ArkinTextMuted, modifier = Modifier.size(14.dp))
                                    Text(text = c.room, fontSize = 12.sp, color = ArkinTextMuted)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
