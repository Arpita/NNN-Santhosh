package com.example.kvsan.restapi;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.kvsan.restapi.datamodel.DataModel;
import com.example.kvsan.restapi.homepagerecycler.RecyclerViewAdapterCategories;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HomeScreen extends AppCompatActivity {
    final List<DataModel> dataModelList = new ArrayList<>();
    private RecyclerView categoriesRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        categoriesRecyclerView = findViewById(R.id.recyclerviewid);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        categoriesRecyclerView.setLayoutManager(layoutManager);

        final DownloadTask downloadTask = new DownloadTask();
        downloadTask.execute("https://api.vimeo.com/categories");


    }

    public class DownloadTask extends AsyncTask<String, Void, String> {
        StringBuilder result = new StringBuilder();

        @Override
        protected String doInBackground(String... urls) {
            try {
                final URL url = new URL(urls[0]);
                final HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.addRequestProperty("Authorization", "bearer b2918607b4301812914f5a5141c4d7da");
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setRequestProperty("charset", "utf-8");
                final InputStream inputStream = httpURLConnection.getInputStream();
                final InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                int data = inputStreamReader.read();
                while (data != -1) {
                    char currentCharacter = (char) data;
                    result.append(currentCharacter);
                    data = inputStreamReader.read();
                }

            } catch (MalformedURLException e) {
                Toast.makeText(HomeScreen.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                Toast.makeText(HomeScreen.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            return String.valueOf(result);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            try {
                final JSONObject jsonObject = new JSONObject(result);
                final JSONArray dataArray = jsonObject.getJSONArray("data");
                for (int i = 0; i < dataArray.length(); i++) {
                    JSONObject jsonobject = dataArray.getJSONObject(i);
                    final DataModel dataModel = new DataModel();
                    dataModel.setCategoryName(jsonobject.getString("name"));
                    dataModel.setLink(jsonobject.getString("link"));
                    dataModel.setUri(jsonobject.getString("uri"));
                    dataModelList.add(dataModel);
                }
                RecyclerView.Adapter categoryAdapter = new RecyclerViewAdapterCategories(HomeScreen.this, dataModelList);
                categoriesRecyclerView.setAdapter(categoryAdapter);

            } catch (JSONException e) {
                Toast.makeText(HomeScreen.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
