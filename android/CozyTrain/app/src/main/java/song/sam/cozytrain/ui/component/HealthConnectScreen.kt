package song.sam.cozytrain.ui.component

import android.annotation.SuppressLint
import android.util.Log
import android.webkit.JavascriptInterface
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.health.connect.client.HealthConnectClient
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
import song.sam.cozytrain.data.healthconnect.HealthConnectSource
import song.sam.cozytrain.data.healthconnect.types.SleepSessionData
import song.sam.cozytrain.model.Health
import song.sam.cozytrain.model.HealthData
import song.sam.cozytrain.model.HealthDataResponse
import song.sam.cozytrain.model.SleepStage
import song.sam.cozytrain.model.convertSleepStageToSleepStages
import song.sam.cozytrain.model.dreamData
import song.sam.cozytrain.network.RetrofitService
import song.sam.cozytrain.ui.healthconnect.UiState
import song.sam.cozytrain.ui.healthconnect.viewmodel.HealthConnectViewModel
import song.sam.cozytrain.ui.healthconnect.viewmodel.SleepSessionViewModel
import song.sam.cozytrain.ui.healthconnect.viewmodel.StepsViewModel
import java.time.Duration
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@SuppressLint("JavascriptInterface")
@Composable
fun DrawHealthConnectSubscreen(
    viewModelData1: ViewModelData<out Record>,
    viewModelData2: ViewModelData<out Record>
) {
    var flag by remember { mutableStateOf(false) }

    val stepViewModel: StepsViewModel = hiltViewModel()
    val stepVMD: ViewModelData<StepsRecord> = stepViewModel.getViewModelData()

    val sleepsessionViewModel: SleepSessionViewModel = hiltViewModel()
    val sleepsessionVMD: ViewModelData<SleepSessionData> = sleepsessionViewModel.getViewModelData()

    Log.d("걸음수 ㅋㅋ", "${stepVMD} ${stepVMD.data}")
    Log.d("수면 단계 ㅋㅋ", "${sleepsessionVMD}   ${sleepsessionVMD.data}")

    if (viewModelData1.uiState == UiState.Success && viewModelData2.uiState == UiState.Success) {
        flag = false
        Log.d("성공", "인데 왜 화면이 안 뜨지")

        var steps = stepVMD.data[0].count.toInt()
        var sleepDuration = parseDuration(sleepsessionVMD.data[0].duration.toString())

        var sleepStages = convertSleepStageToSleepStages(sleepsessionVMD.data[0].stages)

        var dreamContent = "테스트 ㅋㅋ"
        var dreamType = 0

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
        flag = true
    }

    if(flag){
        Text("칙칙 포근포근 서비스를 이용하기 위해서는 삼성헬스 정보가 필요합니다. \n 권한을 허용해주세요.")
        TextButton(onClick = {
            Log.d("??","??")
            viewModelData1.onRequestPermissions(viewModelData1.permissions)
            viewModelData2.onRequestPermissions(viewModelData2.permissions)
        }) {
            Text("권한 허용")
        }
    }

}

fun postDreamData(dreamContent: String, dreamType: Int) {
    val dream = dreamData(
        dreamContent = dreamContent,
        dreamType = dreamType
    )

    val requestParams = dream
    Log.d("요청 ㅋㅋ ", requestParams.toString())

    RetrofitService.instance.postDreamData(requestParams)
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

fun postHealthData(sleepDuration: Int, sleepStages: List<SleepStage>, steps: Int) {

    val health = Health(
        sleepDuration = sleepDuration,
        sleepStages = sleepStages,
        steps = steps,
        stressLevel = 0
    )

    /* Retrofit을 통한 수면 시간 저장 구현 */
    val requestParams = HealthData(health = health)

    Log.d("요청 ㅋㅋ ", requestParams.toString())


    Log.d("요청11 ㅋㅋ ", requestParams.health.sleepDuration.toString())
    Log.d("요청22 ㅋㅋ ", requestParams.health.sleepStages.toString())
    Log.d("요청33 ㅋㅋ ", requestParams.health.steps.toString())
    Log.d("요청44 ㅋㅋ ", requestParams.health.stressLevel.toString())

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
