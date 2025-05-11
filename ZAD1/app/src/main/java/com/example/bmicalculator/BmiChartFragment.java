package com.example.bmicalculator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class BmiChartFragment extends Fragment {

    private WebView webViewBmiChart;

    public static BmiChartFragment newInstance() {
        return new BmiChartFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bmi_chart, container, false);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        webViewBmiChart = view.findViewById(R.id.webViewBmiChart);

        WebSettings webSettings = webViewBmiChart.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);

        webViewBmiChart.setWebViewClient(new WebViewClient());

        webViewBmiChart.loadUrl("file:///android_asset/bmi_chart.html");
    }

    @Override
    public void onDestroyView() {
        if (webViewBmiChart != null) {
            webViewBmiChart.destroy();
        }
        super.onDestroyView();
    }
} 