package com.sajithan.android.srilankavscorona;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, NewsAdapter.onItemClickListner {
    Context context;
    // ArrayList<HospitalDetailModel> dataList;
    TextView newCases , totalCases , deaths, recovered;
    TextView newCasesLocal , totalCasesLocal  , deathsLocal , recoveredLocal ;
    private DrawerLayout drawer;
    private NavigationView navigationView;

    List<Data>getData=new ArrayList<>();
    List<CoronaModel>getCoronaModel=new ArrayList<>();

    Gson gS = new Gson();

    String coronaModelSender;
    String dataModelSender;

    private RecyclerView mRecyclerView;
    private RequestQueue mRequestQueue;
    private List<NewsItems> mNewsItems;
    private NewsAdapter mAdapter;

    public static final String EXTRA_IMAGE_URL = "imageUrl";
    public static final String EXTRA_TITLE = "title";
    public static final String EXTRA_DESCRIPTION = "description";

    private ImageView imageViewSrilanka, imageViewWorld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context=getApplicationContext();
        newCasesLocal=findViewById(R.id.newcasesTextDisplayLocal);
        totalCasesLocal=findViewById(R.id.totalCasesTextDisplayLocal);
        deathsLocal=findViewById(R.id.deathsTextDisplayLocal);
        recoveredLocal=findViewById(R.id.receveredTextDisplayLocal);

        //    dataList=new ArrayList<>();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Covid-19 Srilanka");

        imageViewSrilanka = findViewById(R.id.imageSrilanka);
        imageViewWorld = findViewById(R.id.imageWorld);

        imageViewSrilanka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SrilankaStatus.class));
            }
        });

        imageViewWorld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, GlobalActivity.class));
            }
        });


        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // mRecyclerView = findViewById(R.id.recycler_view_main);
//        mRecyclerView.setHasFixedSize(true);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRequestQueue = Volley.newRequestQueue(this);
        mNewsItems = new ArrayList<>();

//        parseJsonCorona();
//        parseJsonHealth();
//        parseFitness();
//        parseEntertainment();

//url Connection
        ItemApi itemapi = new ItemApi();
        SharedPreferences prefs = context.getSharedPreferences("MyPref", MODE_PRIVATE);
        final String ServerName = prefs.getString("ServerName", "");
        final String ComCdeURL = ServerName+itemapi.getCorona();

        new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {

                OkHttpClient client = new OkHttpClient();
                com.squareup.okhttp.Request request = new com.squareup.okhttp.Request.Builder().url(ComCdeURL).build();

                com.squareup.okhttp.Response response = null;

                try {
                    response = client.newCall(request).execute();
                    return response.body().string();
                } catch (IOException IOE) {
                    IOE.printStackTrace();
                }

                return null;
            }



            @Override
            protected void onPostExecute(Object result) {
                try {

                    if (result != null) {
                        String results = result.toString();

                        JSONObject resultObject = new JSONObject(results);
                        JSONObject dataObject = resultObject.getJSONObject("data");

                        JSONArray hospitalArray = dataObject.getJSONArray("hospital_data");

                        CoronaModel coronaModel = new CoronaModel(resultObject.getBoolean("success"), resultObject.getString("message"));
                        Data data = new Data(dataObject.getString("update_date_time"),dataObject.getInt("local_new_cases"),dataObject.getInt("local_total_cases"),dataObject.getInt( "local_total_number_of_individuals_in_hospitals"),dataObject.getInt("local_deaths"),dataObject.getInt("local_new_deaths"),dataObject.getInt(  "local_recovered"),dataObject.getInt( "global_new_cases"),dataObject.getInt(   "global_total_cases"),dataObject.getInt(  "global_deaths"),dataObject.getInt( "global_new_deaths"),dataObject.getInt( "global_recovered"));
                        getData.add(data);

//                        newCases.setText(String.valueOf(data.getGlobal_new_cases()));
//                        totalCases.setText(String.valueOf(data.getGlobal_total_cases()));
//                        deaths.setText(String.valueOf(data.getGlobal_new_deaths()));
//                        recovered.setText(String.valueOf(data.getGlobal_recovered()));

                        newCasesLocal.setText(String.valueOf(data.getLocal_new_cases()));
                        totalCasesLocal.setText(String.valueOf(data.getLocal_total_cases()));
                        deathsLocal.setText(String.valueOf(data.getLocal_new_deaths()));
                        recoveredLocal.setText(String.valueOf(data.getLocal_recovered()));

                        for(int i = 0; i < hospitalArray.length(); i++) {

                            JSONObject jsonObject = hospitalArray.getJSONObject(i);
                            JSONObject hospitalObject = jsonObject.getJSONObject("hospital");

//                            Hospital hospital = new Hospital(hospitalObject.getInt("id"),hospitalObject.getString("name"),hospitalObject.getString("name_si"),hospitalObject.getString("name_ta"),hospitalObject.getString("created_at"),hospitalObject.getString( "updated_at"),hospitalObject.getString(  "deleted_at"));
//                            HospitalDetailModel hospitalDetail = new HospitalDetailModel(jsonObject.getInt("id"),jsonObject.getInt( "hospital_id"),jsonObject.getInt( "cumulative_local"),jsonObject.getInt("cumulative_foreign"),jsonObject.getInt("treatment_local"),jsonObject.getInt("treatment_foreign"),jsonObject.getString("created_at"),jsonObject.getString("updated_at"),jsonObject.getString("deleted_at"),jsonObject.getInt(  "cumulative_total"),jsonObject.getInt(  "treatment_total"));
//
//                            hospitals.add(hospital);
//                            hospitalsDetils.add(hospitalDetail);
                        }

                        coronaModelSender = gS.toJson(coronaModel);
                        dataModelSender = gS.toJson(data);
//                        hospitalSender= gS.toJson(hospitals);
//                        hospitalDetailsSender= gS.toJson(hospitalsDetils);


                    } else {
                        Toast.makeText(context, "Please Check Your Internet Connection", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception ex) {

                    Toast.makeText(context, "Please Check the Entered Credentials", Toast.LENGTH_SHORT).show();

                }
            }
        }.execute(ComCdeURL);




    }

    private void parseEntertainment() {


        String url = "https://newsapi.org/v2/everything?qInTitle=%22entertainment%22&language=en&sortBy=relevancy&apiKey=c7fad055e9964a0c9ee41efbbf6a7933";
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("articles");

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject object = jsonArray.getJSONObject(i);

                        String imageUrl = object.getString("urlToImage");
                        String title = object.getString("title");
                        String content = object.getString("description");

                        mNewsItems.add(new NewsItems(imageUrl, title, content));
                    }
                    mAdapter = new NewsAdapter(mNewsItems, getApplicationContext());
                    mRecyclerView.setAdapter(mAdapter);
                    mAdapter.setOnItemClickListner(MainActivity.this);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(), "Network Connection Error!!", Toast.LENGTH_LONG).show();
            }
        });

        mRequestQueue.add(objectRequest);

    }


    private void parseFitness() {

        String url = "https://newsapi.org/v2/everything?qInTitle=%22fitness%22&language=en&sortBy=relevancy&apiKey=c7fad055e9964a0c9ee41efbbf6a7933";
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("articles");

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject object = jsonArray.getJSONObject(i);

                        String imageUrl = object.getString("urlToImage");
                        String title = object.getString("title");
                        String content = object.getString("description");

                        mNewsItems.add(new NewsItems(imageUrl, title, content));
                    }
                    mAdapter = new NewsAdapter(mNewsItems, getApplicationContext());
                    mRecyclerView.setAdapter(mAdapter);
                    mAdapter.setOnItemClickListner(MainActivity.this);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(), "Network Connection Error!!", Toast.LENGTH_LONG).show();
            }
        });

        mRequestQueue.add(objectRequest);
    }

    private void parseJsonHealth() {

        String url = "https://newsapi.org/v2/everything?qInTitle=%22health%22&language=en&sortBy=relevancy&apiKey=c7fad055e9964a0c9ee41efbbf6a7933";

        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("articles");

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject object = jsonArray.getJSONObject(i);

                        String imageUrl = object.getString("urlToImage");
                        String title = object.getString("title");
                        String content = object.getString("description");

                        mNewsItems.add(new NewsItems(imageUrl, title, content));
                    }
                    mAdapter = new NewsAdapter(mNewsItems, getApplicationContext());
                    mRecyclerView.setAdapter(mAdapter);
                    mAdapter.setOnItemClickListner(MainActivity.this);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(), "Network Connection Error!!", Toast.LENGTH_LONG).show();
            }
        });

        mRequestQueue.add(objectRequest);
    }

    private void parseJsonCorona() {

        String url = "https://newsapi.org/v2/everything?qInTitle=%22corona%22&language=en&sortBy=relevancy&apiKey=c7fad055e9964a0c9ee41efbbf6a7933";

        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("articles");

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject object = jsonArray.getJSONObject(i);

                        String imageUrl = object.getString("urlToImage");
                        String title = object.getString("title");
                        String content = object.getString("description");

                        mNewsItems.add(new NewsItems(imageUrl, title, content));
                    }
                    mAdapter = new NewsAdapter(mNewsItems, getApplicationContext());
                    mRecyclerView.setAdapter(mAdapter);
                    mAdapter.setOnItemClickListner(MainActivity.this);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(), "Network Connection Error!!", Toast.LENGTH_LONG).show();
            }
        });

        mRequestQueue.add(objectRequest);
    }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {


            case R.id.nav_emergency:
                startActivity(new Intent(MainActivity.this, EmergencyActivity.class));
                break;

            case R.id.nav_feedback:
                sendEmail();
                break;

            case R.id.nav_symptoms:
                startActivity(new Intent(MainActivity.this, SymptomsActivity.class));
                break;

            case R.id.nav_donation:
                openDialog();
                break;

            case R.id.nav_share:
                share();
                break;

            case R.id.nav_about:
                startActivity(new Intent(MainActivity.this, AboutActivity.class));
                break;
            case R.id.nav_exit:
                finish();
                break;
        }
        return true;
    }

    private void openDialog() {

        ExampleDialog dialog = new ExampleDialog();
        dialog.show(getSupportFragmentManager(), "shown succesfully");
    }

    private void share() {
        Intent intentInvite = new Intent(Intent.ACTION_SEND);
        intentInvite.setType("text/plain");
        //app link a podanum
        String body = "App link";
        String subject = "Your Subject";
        intentInvite.putExtra(Intent.EXTRA_SUBJECT, subject);
        intentInvite.putExtra(Intent.EXTRA_TEXT, body);
        startActivity(Intent.createChooser(intentInvite, "Share using"));
    }

    @SuppressLint("LongLogTag")
    private void sendEmail() {

        String[] TO = {"twelvetwingles@gmail.com"};
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:"));
        //  emailIntent.setType("application/octet-stream");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);


        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this,
                    "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onItemClick(int position) {
        Intent detailIntent = new Intent(MainActivity.this, DetailActivity.class);
        NewsItems clickedItem = mNewsItems.get(position);

        detailIntent.putExtra(EXTRA_IMAGE_URL, clickedItem.getImageUrl());
        detailIntent.putExtra(EXTRA_TITLE, clickedItem.getNewsTitle());
        detailIntent.putExtra(EXTRA_DESCRIPTION, clickedItem.getDescription());

        startActivity(detailIntent);
    }
}
