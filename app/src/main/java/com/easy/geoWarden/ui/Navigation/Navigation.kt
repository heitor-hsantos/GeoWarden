package com.easy.geoWarden.ui.Navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.easy.geoWarden.ui.screen.configuration.ConfigurationView
import com.easy.geoWarden.ui.screen.home.HomeView
import com.easy.geoWarden.ui.screen.loginScreen.LoginView
import com.easy.geoWarden.ui.screen.splashScreen.InitialScreen


@Composable
fun NavigationGraph(modifier: Modifier){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Dest.InitialScreen) {

        composable<Dest.InitialScreen> {
            InitialScreen {
                navController.navigate(Dest.HomeScreen)
                { popUpTo(Dest.InitialScreen) { inclusive = true } }
            }
        }


        composable<Dest.HomeScreen>{
            HomeView {
                navController.navigate(Dest.ConfigurationView)
            }

        }
        composable<Dest.LoginView>{
            LoginView{
                navController.navigate(Dest.HomeScreen)
            }

            Dest.HomeScreen
        }
        composable<Dest.RegisterScreen>{}

        composable<Dest.ConfigurationView>{
            // ConfigurationView já obtém seu ViewModel com hiltViewModel()
            ConfigurationView(
                onNavigateToLogin = { // Apenas a ação de navegação é passada
                    navController.navigate(Dest.LoginView)
                }
            )

        }

    }

}