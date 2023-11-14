package song.sam.cozytrain.ui.component

import android.annotation.SuppressLint
import android.os.Build.VERSION.SDK_INT
import android.util.Log
import android.webkit.JavascriptInterface
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.health.connect.client.records.Record
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.google.accompanist.web.AccompanistWebChromeClient
import com.google.accompanist.web.AccompanistWebViewClient
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState
import song.sam.cozytrain.BuildConfig
import song.sam.cozytrain.R
import song.sam.cozytrain.ui.healthconnect.UiState
import song.sam.cozytrain.utils.UserDataViewModel

@SuppressLint("JavascriptInterface")
@Composable
fun DrawHealthConnectSubscreen(
    viewModelData1: ViewModelData<out Record>,
    viewModelData2: ViewModelData<out Record>,
    userModelData: UserDataViewModel,
) {

    val isPermission by userModelData.permissionFlow.collectAsState(initial = false)
    Log.d("isPermission", "$isPermission")

    LaunchedEffect(viewModelData1.uiState) {
        if (viewModelData1.uiState == UiState.Success) {
            userModelData.savePermission(true)
        }
    }

    if (isPermission == true || viewModelData1.uiState == UiState.Success) {
        val webViewState =
            rememberWebViewState(
                url = BuildConfig.COZY_TRAIN_URL,
            )
        val webViewClient = AccompanistWebViewClient()
        val webChromeClient = AccompanistWebChromeClient()
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = androidx.compose.ui.graphics.Color.Black)
        ) {
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
                    webView.addJavascriptInterface(AndroidBridge(), "AndroidBridge");
                    /*webView.evaluateJavascript(
                        "(function() {return window.document.cookie; })();"
                    ) { result ->
                        Log.d("ㅋㅋ 쿠키", result)
                    }*/
                }
            )
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

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
                    viewModelData1.onRequestPermissions(viewModelData1.permissions + viewModelData2.permissions)
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

class AndroidBridge {
    @JavascriptInterface
    fun onLoginSuccess(accessToken: String) {
        Log.d("ㅋㅋ 내가 만든 쿠키", "AccessToken: $accessToken")
    }
}
