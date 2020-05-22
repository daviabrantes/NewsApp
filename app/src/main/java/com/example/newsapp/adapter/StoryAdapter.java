package com.example.newsapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.newsapp.R;
import com.example.newsapp.model.Story;
import java.util.List;

public class StoryAdapter extends ArrayAdapter<Story> {

    public StoryAdapter(@NonNull Context context, @NonNull List<Story> stories) {
        super(context, 0, stories);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.story_adapter, parent, false);
        }

        Story currentStory = getItem(position);

        TextView textStoryTitle = listItemView.findViewById(R.id.story_title);
        String storyTitle = currentStory.getStoryTitle();
        textStoryTitle.setText(storyTitle);

        TextView textStoryDate = listItemView.findViewById(R.id.story_date);
        String storyDate = currentStory.getStoryDate();
        textStoryDate.setText(storyDate);

        TextView textStoryTopic = listItemView.findViewById(R.id.story_topic);
        String storyTopic = currentStory.getStoryTopic();
        textStoryTopic.setText(storyTopic);

        TextView textStoryAuthor = listItemView.findViewById(R.id.story_author);
        String storyAuthor = currentStory.getStoryAuthor();
        textStoryAuthor.setText(storyAuthor);

        return listItemView;
    }


}
