package com.example.android.newsapp.utils;


public class NewsDetail {
    private String section;
    private String title;
    private String webUrl;
    private String publishedDate;

    public NewsDetail(String section, String title, String webUrl, String publishedDate) {
        this.setSection(section);
        this.setPublishedDate(publishedDate);
        this.setTitle(title);
        this.setWebUrl(webUrl);
    }

    public String getSection() {
        return section;
    }

    public String getTitle() {
        return title;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    private void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    private void setSection(String section) {
        this.section = section;
    }

    private void setTitle(String title) {
        this.title = title;
    }

    private void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }
}
