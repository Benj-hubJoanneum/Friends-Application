package com.example.friends_dasfreundebuch;

public class Message {
    private String content;
    private String iconUrl;

    public Message(String content, String iconUrl) {
        this.content = content;
        this.iconUrl = iconUrl;
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
