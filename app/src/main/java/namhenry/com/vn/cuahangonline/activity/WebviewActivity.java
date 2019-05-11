package namhenry.com.vn.cuahangonline.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import namhenry.com.vn.cuahangonline.R;

public class WebviewActivity extends AppCompatActivity {
WebView webView;
Toolbar toolbarwebview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        webView=findViewById(R.id.webview);
        toolbarwebview=findViewById(R.id.toolbarwebview);
        webView.loadUrl("https://www.facebook.com/chuatong.nam.5");
        webView.setWebViewClient(new WebViewClient());
        setSupportActionBar(toolbarwebview);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarwebview.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
