package song.sam.cozytrain.ui.component

import android.annotation.SuppressLint
import android.os.Build.VERSION.SDK_INT
import android.util.Log
import android.webkit.JavascriptInterface
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.health.connect.client.records.Record
import androidx.health.connect.client.records.StepsRecord
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.web.AccompanistWebChromeClient
import com.google.accompanist.web.AccompanistWebViewClient
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState
import retrofit2.Call
import retrofit2.Response
import song.sam.cozytrain.BuildConfig
import song.sam.cozytrain.data.healthconnect.types.SleepSessionData
import song.sam.cozytrain.model.Health
import song.sam.cozytrain.model.HealthDataResponse
import song.sam.cozytrain.model.SleepStage
import song.sam.cozytrain.model.convertSleepStageToSleepStages
import song.sam.cozytrain.network.RetrofitService
import song.sam.cozytrain.ui.healthconnect.UiState
import song.sam.cozytrain.ui.healthconnect.viewmodel.SleepSessionViewModel
import song.sam.cozytrain.ui.healthconnect.viewmodel.StepsViewModel
import song.sam.cozytrain.utils.UserDataViewModel
import java.time.Duration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.width
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import song.sam.cozytrain.R

@SuppressLint("JavascriptInterface")
@Composable
fun DrawHealthConnectSubscreen(
    viewModelData1: ViewModelData<out Record>,
    viewModelData2: ViewModelData<out Record>,
    userModelData: UserDataViewModel,
) {

    val isPermission by userModelData.permissionFlow.collectAsState(initial = false)

    LaunchedEffect(viewModelData1.uiState,viewModelData2.uiState) {
        if (viewModelData1.uiState == UiState.Success && viewModelData2.uiState == UiState.Success ) {
            userModelData.savePermission(true)
        }
    }

    val stepViewModel: StepsViewModel = hiltViewModel()
    val stepVMD: ViewModelData<StepsRecord> = stepViewModel.getViewModelData()

    val sleepsessionViewModel: SleepSessionViewModel = hiltViewModel()
    val sleepsessionVMD: ViewModelData<SleepSessionData> = sleepsessionViewModel.getViewModelData()

    Log.d("걸음수 ㅋㅋ", "${stepVMD} ${stepVMD.data}")
    Log.d("수면 단계 ㅋㅋ", "${sleepsessionVMD}   ${sleepsessionVMD.data}")

    if (isPermission == true ) { /*|| viewModelData2.uiState == UiState.Success */
        Log.d("성공", "인데 왜 화면이 안 뜨지")

        var steps = stepVMD.data[0].count.toInt()
        var sleepDuration = parseDuration(sleepsessionVMD.data[0].duration.toString())

        var sleepStages = convertSleepStageToSleepStages(sleepsessionVMD.data[0].stages)

        val webViewState =
            rememberWebViewState(
                url = BuildConfig.COZY_TRAIN_URL,
            )
        var webViewClient = AccompanistWebViewClient()
        var webChromeClient = AccompanistWebChromeClient()
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = androidx.compose.ui.graphics.Color.Black)
        ){
            WebView(
                state = webViewState,
                client = webViewClient,
                chromeClient = webChromeClient,
                onCreated = { webView ->
                    with(webView) {
                        settings.run {
                            javaScriptEnabled = true
                            domStorageEnabled = true
                            javaScriptCanOpenWindowsAutomatically = false
                        }
                    }
                    webView.addJavascriptInterface(AndroidBridge {
//                                                                 postDreamData(dreamContent, dreamType)
                        postHealthData(sleepDuration, sleepStages, steps)
                    }, "AndroidBridge");
                }
            )
        }
    }
    else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){

            val imageLoader = ImageLoader.Builder(LocalContext.current)
                .components {
                    if (SDK_INT >= 28) {
                        add(ImageDecoderDecoder.Factory())
                    } else {
                        add(GifDecoder.Factory())
                    }
                }
                .build()

            Image(
                painter = rememberAsyncImagePainter(R.drawable.low, imageLoader),
                contentDescription = null,
            )

            Text(
                text = "서비스를 이용하기 위해서는\n수면 및 걸음수 정보가 필요합니다.\n" +
                        "버튼을 눌러 권한을 허용해주세요.",
                modifier = Modifier
                    .padding(top = 8.dp)
                    .width(IntrinsicSize.Max),
                fontSize = 22.sp,
                lineHeight = 32.sp

            )
            TextButton(
                onClick = {
                    viewModelData1.onRequestPermissions(viewModelData1.permissions)
                    viewModelData2.onRequestPermissions(viewModelData2.permissions)
//                    viewModelData2.onRequestPermissions(viewModelData1.permissions + viewModelData2.permissions)
                },
                modifier = Modifier.padding(top = 24.dp)
//                    .background(color = androidx.compose.ui.graphics.Color.Magenta),

            ) {
                Text(text="권한 허용",
                    fontSize = 20.sp)
            }
        }
    }
}

fun postHealthData(sleepDuration: Int, sleepStages: List<SleepStage>, steps: Int) {

    val health = Health(
        sleepDuration = sleepDuration,
        sleepStages = sleepStages,
        steps = steps,
        stressLevel = 0
    )

    /* Retrofit을 통한 수면 시간 저장 구현 */
    val requestParams = health

    Log.d("요청 ㅋㅋ ", requestParams.toString())

    RetrofitService.instance.postHealthData(requestParams)
        .enqueue(object : retrofit2.Callback<HealthDataResponse> {
            override fun onResponse(
                call: Call<HealthDataResponse>,
                response: Response<HealthDataResponse>
            ) {
                Log.d("ㅋㅋ 성공", response.toString())
            }

            override fun onFailure(call: Call<HealthDataResponse>, t: Throwable) {
                Log.d("ㅋㅋ 실패", t.message.toString())
            }

        })
}

class AndroidBridge (
    private val onAuthTokenSet: () -> Unit
){
    @JavascriptInterface
    fun onLoginSuccess(accessToken: String) {
        Log.d("ㅋㅋ 내가 만든 쿠키", "AccessToken: $accessToken")
        RetrofitService.setAuthToken(accessToken)
        onAuthTokenSet.invoke() // 토큰이 설정되면 실행되어야할 작업 호출
    }
}

// 시간 가져오기
fun parseDuration(durationString: String): Int {
    val duration = Duration.parse(durationString)
    val minutes = duration.toMinutes().toInt()
    return minutes
}
