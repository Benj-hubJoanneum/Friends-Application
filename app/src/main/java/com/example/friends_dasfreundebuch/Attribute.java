package com.example.friends_dasfreundebuch;

public class Attribute {
    private String title;
    private String content;
    private String iconUrl;

    public Attribute(String title, String content, String iconUrl) {
        this.title = title;
        this.content = content;
        this.iconUrl = iconUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
}
