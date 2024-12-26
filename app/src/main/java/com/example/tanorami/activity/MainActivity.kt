package com.example.tanorami.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import com.example.domain.data_store.AppDataStore
import com.example.tanorami.App
import com.example.tanorami.navigation.AppNavigation
import com.example.ui.theme.AppTheme
import kotlinx.coroutines.delay
import javax.inject.Inject
import kotlin.random.Random

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var viewModel: MainViewModel

    @Inject
    lateinit var dataStore: AppDataStore

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        (application as App).appComponent.inject(this)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                Box {
                    AppNavigation(
                        viewModelFactory = viewModelFactory,
                    )
                    if (viewModel.showNewYearAnimation.collectAsState().value) {
                        SnowfallAnimation(viewModel.countSnowflakes.collectAsState().value)
                    }
                }
            }
        }
    }
}

@Composable
fun SnowfallAnimation(countSnowflake: Int = 150) {
    // Получаем размеры экрана
    val density = LocalDensity.current
    val configuration = LocalConfiguration.current
    val screenWidth =
        with(density) { configuration.screenWidthDp.dp.roundToPx() } // ширина экрана в пикселях
    val screenHeight =
        with(density) { configuration.screenHeightDp.dp.roundToPx() }// высота экрана в пикселях

    // Создаём снежинки с учётом размеров экрана
    val snowflakes = remember {
        List(countSnowflake) {
            SnowflakeState(
                xStart = Random.nextFloat() * screenWidth,
                yStart = Random.nextFloat() * screenHeight
            )
        }
    }

    LaunchedEffect(Unit) {
        while (true) {
            snowflakes.forEach { snowflake ->
                snowflake.animateSnowflake(screenWidth.toFloat(), screenHeight.toFloat())
            }
            delay(16L) // Обновление каждые 16 мс
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .graphicsLayer {
                alpha = 0.9f // Уменьшение прозрачности слоя
            }
            .drawBehind {
                snowflakes.forEach { snowflake ->
                    drawCircle(
                        color = Color.White,
                        radius = snowflake.size,
                        center = Offset(snowflake.x.value, snowflake.y.value)
                    )
                }
            }
    )
}

class SnowflakeState(xStart: Float, yStart: Float) {
    var size = Random.nextFloat() * 8 + 2

    // Инициализация координат
    val x = Animatable(xStart)
    val y = Animatable(yStart)

    suspend fun animateSnowflake(screenWidth: Float, screenHeight: Float) {
        // Обновляем координаты снежинки
        y.snapTo(y.value + Random.nextFloat() * 5 + 2) // Скорость падения
        if (y.value > screenHeight) { // Если снежинка выходит за нижний край
            y.snapTo(-size) // Переносим наверх
            x.snapTo(Random.nextFloat() * screenWidth) // Генерируем новую X
        }
    }
}