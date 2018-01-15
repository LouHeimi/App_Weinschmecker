package com.example.louis.weinschmeckeroffenburg.Fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class WinestyleFrag extends WebViewFragment {
    private ProgressDialog progress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Anlegen eienr Webview
        WebView webView = (WebView) super.onCreateView(inflater, container, savedInstanceState);
        if( !rotated() )
        {
            webView.setWebViewClient(new WebViewClient() {
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    return false;
                }
            });

            webView.loadUrl("http://wein-schmecker.com");
            //Dialog der anzeigt, dass die Website im Ladeprozess ist
            progress = ProgressDialog.show(this.getContext(), "Kommt sofort...",
                    "Der Weinstyle-Bereich wird für Dich geladen", true);
            webView.setWebViewClient(new WebViewClient() {

                public void onPageFinished(WebView view, String url) {
                    if (progress != null)
                        progress.dismiss();
                }
            });




        }

        return webView;
    }


}