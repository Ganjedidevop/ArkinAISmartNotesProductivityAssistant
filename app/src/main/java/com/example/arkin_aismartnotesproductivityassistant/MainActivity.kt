package com.example.arkin_aismartnotesproductivityassistant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.example.arkin_aismartnotesproductivityassistant.data.AppDatabase
import com.example.arkin_aismartnotesproductivityassistant.data.AppRepository
import com.example.arkin_aismartnotesproductivityassistant.ui.MainScreen
import com.example.arkin_aismartnotesproductivityassistant.ui.theme.ArkinTheme
import com.example.arkin_aismartnotesproductivityassistant.viewmodel.AppViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        val database = AppDatabase.getDatabase(this)
        val repository = AppRepository(database.appDao())
        val viewModelFactory = AppViewModel.Factory(repository)
        val viewModel = ViewModelProvider(this, viewModelFactory)[AppViewModel::class.java]

        setContent {
            ArkinTheme {
                MainScreen(viewModel = viewModel)
            }
        }
    }
}
