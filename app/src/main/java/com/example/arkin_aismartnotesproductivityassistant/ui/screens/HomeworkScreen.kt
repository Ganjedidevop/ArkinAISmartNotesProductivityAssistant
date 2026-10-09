package com.example.arkin_aismartnotesproductivityassistant.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
fun HomeworkScreen(navController: NavController, viewModel: AppViewModel) {
    val homeworkList = viewModel.homeworkList

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ArkinBg)
            .padding(24.dp)
            .padding(bottom = 80.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
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
                Text(text = "Homework", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = ArkinText)
            }
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .shadow(10.dp, CircleShape, spotColor = Color.Black.copy(alpha = 0.05f))
                    .clip(CircleShape)
                    .background(IconBgOrange)
                    .clickable { /* Add Homework */ },
                contentAlignment = Alignment.Center
            ) {
                Icon(PhosphorIcons.Regular.Plus, contentDescription = "Add", modifier = Modifier.size(20.dp), tint = IconColorOrange)
            }
        }

        LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            items(homeworkList) { hw ->
                val (bgColor, textColor) = when (hw.color) {
                    "purple" -> IconBgPurple to IconColorPurple
                    "orange" -> IconBgOrange to IconColorOrange
                    else -> IconBgPurple to IconColorPurple
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(10.dp, RoundedCornerShape(24.dp), spotColor = Color.Black.copy(alpha = 0.05f))
                        .clip(RoundedCornerShape(24.dp))
                        .background(Color.White)
                        .padding(20.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        Column {
                            Box(
                                modifier = Modifier.clip(RoundedCornerShape(8.dp)).background(bgColor).padding(horizontal = 10.dp, vertical = 4.dp)
                            ) {
                                Text(text = hw.subject.uppercase(), fontSize = 10.sp, fontWeight = FontWeight.Bold, color = textColor, letterSpacing = 1.sp)
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(text = hw.title, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = ArkinText)
                            Spacer(modifier = Modifier.height(4.dp))
                            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                                Icon(PhosphorIcons.Regular.Clock, contentDescription = null, tint = Color.Red, modifier = Modifier.size(14.dp))
                                Text(text = "Due ${hw.due}", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = Color.Red)
                            }
                        }
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .border(2.dp, Color(0xFFE5E7EB), CircleShape)
                                .clickable { /* Mark done */ },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(PhosphorIcons.Bold.Check, contentDescription = null, tint = Color(0xFFD1D5DB), modifier = Modifier.size(16.dp))
                        }
                    }
                }
            }
        }
    }
}
