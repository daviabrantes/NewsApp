package com.example.newsapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.newsapp.adapter.StoryAdapter;
import com.example.newsapp.model.Story;
import com.example.newsapp.utils.StoryLoader;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Story>>{

    private static final String GUARDIAN_REQUEST_URL = "https://content.guardianapis.com/search";
    private static final int STORY_LOADER_ID = 1;

    private TextView emptyStateTextView;
    private ListView listView;
    private StoryAdapter storyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listview);
        emptyStateTextView = findViewById(R.id.empty_view);
        listView.setEmptyView(emptyStateTextView);

        storyAdapter = new StoryAdapter(this, new ArrayList<Story>());
        listView.setAdapter(storyAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Story currentStory = storyAdapter.getItem(position);
                Uri storyUri = Uri.parse(currentStory.getStoryUrl());
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, storyUri);
                startActivity(websiteIntent);
            }
        });

        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getSupportLoaderManager();
            loaderManager.initLoader(STORY_LOADER_ID, null, this);
        } else {
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);
            emptyStateTextView.setText("No Internet Connection");
        }
    }

    @NonNull
    @Override
    public Loader<List<Story>> onCreateLoader(int id, @Nullable Bundle args) {

        Uri baseUri = Uri.parse(GUARDIAN_REQUEST_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();

        uriBuilder.appendQueryParameter("api-key", "test");
        return new StoryLoader(this, uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Story>> loader, List<Story> stories) {
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);
        emptyStateTextView.setText("No News to Show");

        if (stories != null && !stories.isEmpty()) {
            storyAdapter.clear();
            storyAdapter.addAll(stories);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Story>> loader) {
        storyAdapter.clear();
    }
}
