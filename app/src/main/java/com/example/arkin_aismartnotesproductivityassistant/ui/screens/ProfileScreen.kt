package com.example.arkin_aismartnotesproductivityassistant.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
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
import androidx.compose.ui.platform.LocalContext
import android.widget.Toast
import com.example.arkin_aismartnotesproductivityassistant.viewmodel.AppViewModel

@Composable
fun ProfileScreen(navController: NavController, viewModel: AppViewModel) {
    val context = LocalContext.current
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
            Text(text = "Profile", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = ArkinText)
        }
        
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(96.dp)
                    .clip(CircleShape)
                    .background(Brush.linearGradient(listOf(Color(0xFFC084FC), Color(0xFF60A5FA))))
                    .padding(4.dp),
                contentAlignment = Alignment.Center
            ) {
                Box(modifier = Modifier.fillMaxSize().clip(CircleShape).background(Color.White), contentAlignment = Alignment.Center) {
                    Text("R", color = ArkinPrimary, fontWeight = FontWeight.Bold, fontSize = 40.sp)
                }
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(ArkinPrimary)
                        .align(Alignment.BottomEnd)
                        .clickable { 
                            Toast.makeText(context, "Change Photo clicked", Toast.LENGTH_SHORT).show()
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(PhosphorIcons.Fill.Camera, contentDescription = null, tint = Color.White, modifier = Modifier.size(16.dp))
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = "Rohan Sharma", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = ArkinText)
            Text(text = "Computer Science, 2nd Year", fontSize = 14.sp, color = ArkinTextMuted)
        }
        
        Spacer(modifier = Modifier.height(32.dp))
        
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(10.dp, RoundedCornerShape(24.dp), spotColor = Color.Black.copy(alpha = 0.05f))
                .clip(RoundedCornerShape(24.dp))
                .background(Color.White)
        ) {
            ProfileMenuItem(
                icon = PhosphorIcons.Fill.User,
                label = "Account Details",
                bgColor = IconBgBlue,
                iconColor = IconColorBlue,
                onClick = { Toast.makeText(context, "Account Details clicked", Toast.LENGTH_SHORT).show() }
            )
            HorizontalDivider(color = Color(0xFFF3F4F6))
            ProfileMenuItem(
                icon = PhosphorIcons.Fill.Moon,
                label = "Dark Mode (Demo)",
                bgColor = IconBgPurple,
                iconColor = IconColorPurple,
                trailingIcon = PhosphorIcons.Regular.ToggleLeft,
                onClick = { Toast.makeText(context, "Dark Mode toggled", Toast.LENGTH_SHORT).show() }
            )
            HorizontalDivider(color = Color(0xFFF3F4F6))
            ProfileMenuItem(
                icon = PhosphorIcons.Fill.Database,
                label = "Clear Cache",
                bgColor = IconBgOrange,
                iconColor = IconColorOrange,
                onClick = { Toast.makeText(context, "Cache cleared", Toast.LENGTH_SHORT).show() }
            )
            HorizontalDivider(color = Color(0xFFF3F4F6))
            ProfileMenuItem(
                icon = PhosphorIcons.Fill.SignOut,
                label = "Logout",
                bgColor = Color(0xFFFEE2E2),
                iconColor = Color(0xFFEF4444),
                textColor = Color(0xFFEF4444),
                trailingIcon = null,
                onClick = { Toast.makeText(context, "Logged out", Toast.LENGTH_SHORT).show() }
            )
        }
    }
}

@Composable
fun ProfileMenuItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    bgColor: Color,
    iconColor: Color,
    textColor: Color = ArkinText,
    trailingIcon: androidx.compose.ui.graphics.vector.ImageVector? = PhosphorIcons.Regular.CaretRight,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Box(
                modifier = Modifier.size(40.dp).clip(RoundedCornerShape(12.dp)).background(bgColor),
                contentAlignment = Alignment.Center
            ) {
                Icon(imageVector = icon, contentDescription = null, tint = iconColor, modifier = Modifier.size(20.dp))
            }
            Text(text = label, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = textColor)
        }
        if (trailingIcon != null) {
            Icon(imageVector = trailingIcon, contentDescription = null, tint = Color(0xFF9CA3AF), modifier = Modifier.size(if (trailingIcon == PhosphorIcons.Regular.ToggleLeft) 24.dp else 16.dp))
        }
    }
}
