package com.easy.geoWarden.ui.screen.home




import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.easy.geoWarden.ui.Theme.theme.GeoWardenTheme
import com.easy.geoWarden.ui.Theme.theme.GradientColorBlueToBlack
import com.easy.geoWarden.ui.Theme.theme.MarineBlue
import com.easy.geoWarden.ui.screen.home.components.CurrentLocationCard
import com.easy.geoWarden.ui.screen.home.components.LocalGrid
import com.easy.geoWarden.ui.screen.home.components.TopBar
import com.easy.geoWarden.utils.CurrentLocation


@OptIn(ExperimentalMaterial3Api::class)
@Composable
 fun HomeView( onNavigate:() -> Unit) {
     val viewModel: HomeViewModel = viewModel()


  GeoWardenTheme {
    Box(Modifier
        .fillMaxSize()
        .background(brush = GradientColorBlueToBlack)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(850.dp)
                .padding(16.dp,36.dp,16.dp,10.dp)
                .background(color = Color(MarineBlue.value),
            shape = RoundedCornerShape(24.dp))
        ) {

            Column(modifier = Modifier){
                //Barra do topo da Aplicação
                Box(modifier = Modifier
                    .fillMaxWidth(1f)
                    .height(85.dp)
                ) { TopBar(onNavigate = onNavigate) }


                //Box da localização atual
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(32.dp)
                    ,contentAlignment = Alignment.BottomCenter){

                    Text(text = CurrentLocation,color = Color.White, fontSize = 20.sp)
                }


                //Box Com card da localização atual
                Box(modifier = Modifier.fillMaxWidth()){ CurrentLocationCard(location = null) }


                //Criador de Coleção de Locais
                Box(modifier = Modifier.fillMaxWidth(),
                           contentAlignment = Alignment.Center){
                    Text(text = "Localizações Favoritas", color = Color.White, fontSize = 20.sp)}
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(320.dp)
                    .padding(10.dp)
                    .background(brush = GradientColorBlueToBlack
                        , shape = RoundedCornerShape(22.dp))
                    //Valor fornecido pela ViewModel
                ){LocalGrid(6)}
              }
            }
        }
    }
  }


@Preview(showBackground = true)
@Composable
fun HomeViewPreview(){
    HomeView(onNavigate = {})
}
