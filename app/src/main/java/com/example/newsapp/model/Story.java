package com.example.newsapp.model;

public class Story {

    private String storyTitle;
    private String storyTopic;
    private String storyDate;
    private String storyUrl;
    private String storyAuthor;

    public Story(String storyTitle, String storyTopic, String storyDate, String storyUrl, String storyAuthor) {
        this.storyTitle = storyTitle;
        this.storyTopic = storyTopic;
        this.storyDate = storyDate;
        this.storyUrl = storyUrl;
        this.storyAuthor = storyAuthor;
    }

    public String getStoryTitle() {
        return storyTitle;
    }

    public void setStoryTitle(String storyTitle) {
        this.storyTitle = storyTitle;
    }

    public String getStoryTopic() {
        return storyTopic;
    }

    public void setStoryTopic(String storyTopic) {
        this.storyTopic = storyTopic;
    }

    public String getStoryDate() {
        return storyDate;
    }

    public void setStoryDate(String storyDate) {
        this.storyDate = storyDate;
    }

    public String getStoryUrl() {
        return storyUrl;
    }

    public void setStoryUrl(String storyUrl) {
        this.storyUrl = storyUrl;
    }

    public String getStoryAuthor() {
        return storyAuthor;
    }

    public void setStoryAuthor(String storyAuthor) {
        this.storyAuthor = storyAuthor;
    }
}
