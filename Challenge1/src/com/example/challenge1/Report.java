package com.example.challenge1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

public class Report extends Activity {
	BufferedReader br = null;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.report);

		WebView webview = (WebView) findViewById(R.id.webView1);
		String path = "raw/Data.txt";
		File text = new File(path);
		Scanner scan;

		String data = "";

		// InputStream is = getResources().openRawResource(R.raw.data);

		try {
			this.br = new BufferedReader(new FileReader(text));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		data = "['Happy',"+ConnectionService.happy+"],['Sad',"+ConnectionService.sad+",['Excited',"+ConnectionService.excited+"],['Angry',"+ConnectionService.angry+"]";
		String content = "<html>"
				+ "  <head>"
				+ "    <script type=\"text/javascript\" src=\"jsapi.js\"></script>"
				+ "    <script type=\"text/javascript\">"
				+ "      google.load(\"visualization\", \"1\", {packages:[\"corechart\"]});"
				+ "      google.setOnLoadCallback(drawChart);"
				+ "      function drawChart() {"
				+ "        var data = google.visualization.arrayToDataTable(["

				+ "          ['Sentiment', 'Percentage'],"
				+ data
				/*
				 * + "          ['06AM',  5,      2,	0]," +
				 * "          ['09AM',  4,	5,	10]," +
				 * "          ['12PM',  6,	7,	5]," +
				 * "          ['03PM',  3,	10,	5]," +
				 * "          ['06PM',  10,	8,	4]," +
				 * "          ['09PM',  5,	8,	10]"
				 */+ "        ]);"
				+ "        var options = {"

				+ "          title: 'Sentiment Analysis',"
				+ "hAxis: {title: 'Sentiment', titleTextStyle: {color: 'red'}}"

				+ "        };"
				+ "    var options1 = {"

				+ "          title: 'Sentiment Analysis',"
				+ "hAxis: {title: 'Sentiment', titleTextStyle: {color: 'red'}}"

				+ "        };"
				+ "        var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));"
				+ "var chart1 = new google.visualization.PieChart(document.getElementById('piechart'));"
				+ "        chart.draw(data, options);"
				+ "chart1.draw(data,options1);"
				+ "      }"
				+ "    </script>"
				+ "  </head>"
				+ "  <body>"
				+ "    <div id=\"chart_div\" style=\"width: 1200px; height: 700px;\"></div>"
				+ "    <div id=\"piechart\" style=\"width: 1200px; height: 700px;\"></div>"
				+ "  </body>" + "</html>";

		WebSettings webSettings = webview.getSettings();
		webSettings.setJavaScriptEnabled(true);
		webview.getSettings().setLoadWithOverviewMode(true);
		webview.getSettings().setUseWideViewPort(true);
		webview.getSettings().setBuiltInZoomControls(true);
		webview.requestFocusFromTouch();

		webview.setLayerType(View.LAYER_TYPE_SOFTWARE, null);

		webview.loadDataWithBaseURL("file:///android_asset/", content,
				"text/html", "utf-8", null);
		// webview.loadUrl("file:///android_asset/Code.html"); // Can be used in
		// this way too.

	}

}
