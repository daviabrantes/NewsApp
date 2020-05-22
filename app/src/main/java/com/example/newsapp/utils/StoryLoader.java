package com.example.newsapp.utils;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import com.example.newsapp.model.Story;

import java.util.List;

public class StoryLoader extends AsyncTaskLoader<List<Story>> {

    private String url;

    public StoryLoader(@NonNull Context context, String url) {
        super(context);
        this.url = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Nullable
    @Override
    public List<Story> loadInBackground() {
        if (url == null){
            return null;
        }

        List<Story> stories = QueryUtils.fetchStoryData(url);
        return stories;
    }
}
