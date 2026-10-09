package com.example.arkin_aismartnotesproductivityassistant.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
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
import com.example.arkin_aismartnotesproductivityassistant.navigation.AppNavigation
import com.example.arkin_aismartnotesproductivityassistant.ui.theme.*
import com.example.arkin_aismartnotesproductivityassistant.viewmodel.AppViewModel
import com.example.arkin_aismartnotesproductivityassistant.ui.screens.NoteModal

@Composable
fun MainScreen(viewModel: AppViewModel) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    
    var showAddNoteModal by remember { mutableStateOf(false) }

    Scaffold(
        bottomBar = {
            if (currentRoute in listOf("dashboard", "notes", "todo", "profile")) {
                BottomNavigationBar(
                    navController = navController,
                    currentRoute = currentRoute,
                    onAddClick = { showAddNoteModal = true }
                )
            }
        },
        containerColor = ArkinBg
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            AppNavigation(navController = navController, viewModel = viewModel)
        }
        
        if (showAddNoteModal) {
            NoteModal(
                onDismiss = { showAddNoteModal = false },
                onSave = { title, content, tag, color ->
                    viewModel.addNote(title, content, tag, color, false)
                    showAddNoteModal = false
                }
            )
        }
    }
}

@Composable
fun BottomNavigationBar(
    navController: NavController,
    currentRoute: String?,
    onAddClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(elevation = 30.dp, shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)),
        color = Color.White,
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            NavItem(
                icon = PhosphorIcons.Fill.House,
                label = "Home",
                isSelected = currentRoute == "dashboard",
                onClick = {
                    if (currentRoute != "dashboard") {
                        navController.navigate("dashboard") {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
            NavItem(
                icon = PhosphorIcons.Regular.FileText,
                label = "Notes",
                isSelected = currentRoute == "notes",
                onClick = {
                    if (currentRoute != "notes") {
                        navController.navigate("notes") {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
            
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .offset(y = (-8).dp)
                    .clip(CircleShape)
                    .background(ArkinPrimary)
                    .clickable { onAddClick() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = PhosphorIcons.Regular.Plus,
                    contentDescription = "Add Note",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }
            
            NavItem(
                icon = PhosphorIcons.Regular.ListChecks,
                label = "To-Do",
                isSelected = currentRoute == "todo",
                onClick = {
                    if (currentRoute != "todo") {
                        navController.navigate("todo") {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
            NavItem(
                icon = PhosphorIcons.Regular.User,
                label = "Profile",
                isSelected = currentRoute == "profile",
                onClick = {
                    if (currentRoute != "profile") {
                        navController.navigate("profile") {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun NavItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val color = if (isSelected) ArkinPrimary else ArkinTextMuted
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
            .width(48.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = color,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            fontSize = 10.sp,
            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Medium,
            color = color
        )
    }
}
