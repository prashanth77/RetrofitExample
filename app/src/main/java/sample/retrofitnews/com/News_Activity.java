package sample.retrofitnews.com;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class News_Activity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news);

        try {
            WebView webView=(WebView)findViewById(R.id.webView_news);
            WebSettings webSettings = webView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            Bundle gt=getIntent().getExtras();
            String str=gt.getString("news");
            Log.e("url", str);
            webView.loadUrl(str);

        }
        catch (Exception r){

        }



    }
}
