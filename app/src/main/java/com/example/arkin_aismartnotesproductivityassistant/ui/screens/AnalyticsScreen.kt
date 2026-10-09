package com.example.arkin_aismartnotesproductivityassistant.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
fun AnalyticsScreen(navController: NavController, viewModel: AppViewModel) {
    val tasks by viewModel.allTasks.collectAsState()
    val completedTasks = tasks.count { it.isCompleted }

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
            Text(text = "Analytics", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = ArkinText)
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(10.dp, RoundedCornerShape(24.dp), spotColor = Color.Black.copy(alpha = 0.05f))
                .clip(RoundedCornerShape(24.dp))
                .background(Color.White)
                .padding(24.dp)
        ) {
            Column {
                Text(text = "Study Time This Week", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = ArkinText)
                Spacer(modifier = Modifier.height(16.dp))
                
                Row(
                    modifier = Modifier.fillMaxWidth().height(120.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    val heights = listOf(0.4f, 0.7f, 0.3f, 0.9f, 0.6f, 0.2f, 0.0f)
                    heights.forEach { h ->
                        Box(
                            modifier = Modifier
                                .width(20.dp)
                                .fillMaxHeight(h)
                                .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                                .background(ArkinPrimary.copy(alpha = 0.2f))
                        ) {
                            // Can add hover/click interactions for tooltip here if needed
                        }
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    listOf("M", "T", "W", "T", "F", "S", "S").forEach { day ->
                        Text(text = day, fontSize = 12.sp, color = ArkinTextMuted)
                    }
                }
            }
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp), modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .shadow(10.dp, RoundedCornerShape(24.dp), spotColor = Color.Black.copy(alpha = 0.05f))
                    .clip(RoundedCornerShape(24.dp))
                    .background(IconBgPurple)
                    .padding(20.dp)
            ) {
                Column {
                    Icon(PhosphorIcons.Fill.Fire, contentDescription = null, tint = IconColorPurple, modifier = Modifier.size(24.dp))
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "5 Days", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = ArkinText)
                    Text(text = "Current Streak", fontSize = 12.sp, fontWeight = FontWeight.Medium, color = ArkinTextMuted)
                }
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .shadow(10.dp, RoundedCornerShape(24.dp), spotColor = Color.Black.copy(alpha = 0.05f))
                    .clip(RoundedCornerShape(24.dp))
                    .background(IconBgTeal)
                    .padding(20.dp)
            ) {
                Column {
                    Icon(PhosphorIcons.Fill.CheckCircle, contentDescription = null, tint = IconColorTeal, modifier = Modifier.size(24.dp))
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "$completedTasks", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = ArkinText)
                    Text(text = "Tasks Done", fontSize = 12.sp, fontWeight = FontWeight.Medium, color = ArkinTextMuted)
                }
            }
        }
    }
}
