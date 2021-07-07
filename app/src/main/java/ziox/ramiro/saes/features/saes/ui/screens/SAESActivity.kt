package ziox.ramiro.saes.features.saes.ui.screens

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ziox.ramiro.saes.data.AuthWebViewRepository
import ziox.ramiro.saes.data.models.viewModelFactory
import ziox.ramiro.saes.features.saes.features.grades.ui.screens.Grades
import ziox.ramiro.saes.features.saes.features.home.ui.screens.Home
import ziox.ramiro.saes.features.saes.features.profile.ui.screens.Profile
import ziox.ramiro.saes.features.saes.features.schedule.ui.screens.Schedule
import ziox.ramiro.saes.features.saes.ui.components.BottomAppBar
import ziox.ramiro.saes.ui.screens.MainActivity
import ziox.ramiro.saes.ui.theme.SAESParaAlumnosTheme
import ziox.ramiro.saes.view_models.AuthEvent
import ziox.ramiro.saes.view_models.AuthViewModel

class SAESActivity : ComponentActivity() {
    private val authViewModel: AuthViewModel by viewModels {
        viewModelFactory { AuthViewModel(AuthWebViewRepository(this)) }
    }

    @ExperimentalAnimationApi
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        listenToAuthEvents()

        setContent {
            val selectedMenuItem = rememberSaveable {
                mutableStateOf(MenuSection.SCHEDULE)
            }

            SAESParaAlumnosTheme {
                Scaffold(
                    bottomBar = {
                        BottomAppBar(selectedMenuItem)
                    }
                ) {
                    PageController(selectedMenuItem)
                }
            }
        }
    }

    private fun listenToAuthEvents() = lifecycleScope.launch {
        authViewModel.events.collect {
            if(it is AuthEvent.LogoutSuccess){
                startActivity(Intent(this@SAESActivity, MainActivity::class.java))
                finish()
            }
        }
    }
}

enum class MenuSection{
    HOME,
    SCHEDULE,
    GRADES,
    PROFILE
}


@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun PageController(
    selectedItemMenu: MutableState<MenuSection> = mutableStateOf(MenuSection.HOME)
) = Crossfade(targetState = selectedItemMenu.value) {
    when(it){
        MenuSection.HOME -> Home()
        MenuSection.GRADES -> Grades()
        MenuSection.SCHEDULE -> Schedule()
        MenuSection.PROFILE -> Profile()
        else -> {
            Box{}
        }
    }
}