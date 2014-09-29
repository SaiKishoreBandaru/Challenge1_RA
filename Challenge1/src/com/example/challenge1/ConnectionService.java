package com.example.challenge1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ConnectionService extends Activity {
	static String res;
	static int angry;
	static int sad;
	static int excited;
	static int happy;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.connection_service);
		TextView myTV = (TextView) findViewById(R.id.tview);Double d = 0.0;
		/*String root = Environment.getExternalStorageDirectory().toString();
		File myDir = new File(root + "/Data");
		File fn = new File(myDir, "NFL.txt");
		String line = "";
 
		if (!fn.exists()) {
			myTV.setText("no file");
		}
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(fn));

			while ((line = br.readLine()) != null) {
				d = d + 1.0;
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		myTV.setText(d.toString(d) + " tweets made in USA");*/
		
		new Thread(new Runnable()
		{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			//String url ="http://134.193.136.127:8983/solr/collection1_shard1_replica1/select?q=state_s%3AAL&rows=100&wt=json&indent=true";
			String url = "http://134.193.136.114:8181/HBaseWS/jaxrs/generic/hbaseRetrieveAll/HBaseChallenge";
			
			BackgroundTask bt=new BackgroundTask();
			res=bt.doInBackground(url);					
		}
		
		}).start();
		String[] tmp=res.split("\n");
		d=(double) tmp.length;
		myTV.setText(d.toString(d) + " tweets made in USA");
		Button b2 = (Button) findViewById(R.id.button2);
		b2.setOnClickListener(new View.OnClickListener() {
			// myTV.setText();

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// c.startScan();

				Intent intent = new Intent(ConnectionService.this, Report.class);
				startActivity(intent);

			}
		});
	}
	
	


public class BackgroundTask extends AsyncTask<String, String, String>{

	
	@Override
    protected String doInBackground(String... params) {		   
         String command=params[0]; // URL to call			                 
          String response="";
          String emotion="";
     
         try {   	  
         response = executeCommand(command); 			         
        
        	
        	
        	 res=response;
         		         
             
         }catch (Exception ex) {
             System.out.println("error!!");
             Log.i("url response", "IN Catch");
             ex.printStackTrace();
         }
        
         return response; 
    }
	
	private String executeCommand(String command) {
	   	 
		StringBuffer output = new StringBuffer();
		String output1="";
		String line="";
		try {
			
			 URL url = new URL(command);						
			 HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();         
	         BufferedReader br = new BufferedReader(new InputStreamReader((urlConnection.getInputStream())));
	         while ((line = br.readLine())!= null) {
	        	 String s[]=line.split(",");
	        	 s[2]=s[2].toLowerCase();
					output.append(s[2] + "\n");	
					
					if(s[2].contains("happy"))
					{
						 if(s[2].contains("not happy"))
							sad=sad+1;
						 else
							happy=happy+1;
					}
					else if(s[2].contains("sad"))
						sad=sad+1;
					else if(s[2].contains("angry"))
						angry=angry+1;
					else if(s[2].contains("excited"))
						excited=excited+1;
					else if(s[2].contains("not excited"))
						sad=sad+1;
				
					else if(s[2].contains("fabulous"))
						excited=excited+1;
					else if(s[2].contains("irritated"))
						angry=angry+1;
					else if(s[2].contains("fuck"))
						angry=angry+1;
					else if(s[2].contains("insane"))
						angry=angry+1;
					else if(s[2].contains("pathetic"))
						angry=angry+1;
					else if(s[2].contains("romantic"))
						happy=happy+1;
					else if(s[2].contains("hurray"))
						happy=happy+1;
					else if(s[2].contains("worried"))
						sad=sad+1;
					else if(s[2].contains("lame"))
						sad=sad+1;
					else if(s[2].contains("jubilliant"))
						excited=excited+1;
					else if(s[2].contains("fight"))
						angry=angry+1;
					else if(s[2].contains("romantic fight"))
						happy=happy+1;
					else if(s[2].contains("funny"))
						happy=happy+1;
					else if(s[2].contains("suspense"))
						excited=excited+1;
					else if(s[2].contains("whoa"))
					{
						 if(s[2].contains("happy"))
							happy=happy+1;
						 else
						angry=angry+1;
					}
					else if(s[2].contains("hi"))
						happy=happy+1;
					else if(s[2].contains("dangerous"))
						angry=angry+1;
					else if(s[2].contains("kind"))
						happy=happy+1;
					else if(s[2].contains("serious"))
						angry=angry+1;
					else if(s[2].contains("hackathon"))
						excited=excited+1;
					else if(s[2].contains("relax"))
						happy=happy+1;
					else if(s[2].contains("booz"))
						excited=excited+1;
					else if(s[2].contains("walk"))
						happy=happy+1;
					
					
					
				}		
			
 
		} catch (Exception e) {
			e.printStackTrace();
		}
 
		return output1+output.toString();
 
	}
	
	
}

}
