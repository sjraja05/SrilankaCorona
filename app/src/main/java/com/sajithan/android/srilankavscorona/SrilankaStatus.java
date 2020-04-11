package com.sajithan.android.srilankavscorona;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;

public class SrilankaStatus extends AppCompatActivity {

    private TextView mTextViewConfirmed, mTextViewRecovered, mTextViewDeath, mTextViewActive;
    private RequestQueue mRequestQueue;
    private WebView myWebView;
    private Button buttonlink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_srilanka_status);
        //initWidgets();

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Srilanka Status");
        //  mRequestQueue = Volley.newRequestQueue(this);
//         WebView myWebView = (WebView) findViewById(R.id.webview);

        //parseJson();
        Button buttonlink = (Button) findViewById(R.id.buttonlink);
        buttonlink.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent browserIntent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://docs.google.com/spreadsheets/d/1zIgPU0ZlYkiKaavYAUcHKgEP95jdaMaf9ljJgRqtog4/edit#gid=0"));
              //  myWebView.loadUrl("https://docs.google.com/spreadsheets/d/1zIgPU0ZlYkiKaavYAUcHKgEP95jdaMaf9ljJgRqtog4/edit#gid=0");
                startActivity(browserIntent);
            }
        });
    }
    /*private void initWidgets() {
        mTextViewConfirmed = findViewById(R.id.text_view_confirmed);
        mTextViewRecovered = findViewById(R.id.text_view_recovered);
        mTextViewDeath = findViewById(R.id.text_view_death);
        mTextViewActive = findViewById(R.id.text_view_active);
    }*/

 /*   private void parseJson() {

        String url = "https://docs.google.com/spreadsheets/d/1zIgPU0ZlYkiKaavYAUcHKgEP95jdaMaf9ljJgRqtog4/edit#gid=0";

        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                   JSONObject object = response.getJSONObject("data");

                   int confirmed = object.getInt("confirmed");
                   int deaths = object.getInt("deaths");
                   int recovered = object.getInt("recovered");
                   int active = object.getInt("active");

                   mTextViewConfirmed.setText("Confirmed\n\t" + confirmed);
                   mTextViewRecovered.setText("Recovered\n\t" + recovered);
                   mTextViewDeath.setText("Deaths\n\t" + deaths);
                   mTextViewActive.setText("Active\n\t" + active);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(), "Network error!! Please connect to internet", Toast.LENGTH_LONG).show();
            }
        });

        mRequestQueue.add(objectRequest);
    }*/

}
