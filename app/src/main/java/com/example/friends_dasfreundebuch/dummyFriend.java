package com.example.friends_dasfreundebuch;

import android.media.Image;
import android.os.Parcel;
import android.os.Parcelable;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class dummyFriend implements Parcelable {

    /*public int image;*/
    public String name;
    private String imgURL;
    private ArrayList<Attribute> attributes;
    private ArrayList<Message> messages;

    public dummyFriend(String name, String imgURL) {
        this.name = name;
        this.imgURL = imgURL;
        this.attributes = new ArrayList<>();
        this.messages = new ArrayList<>();
    }

    public dummyFriend(String name, String imgURL, ArrayList<Attribute> attributes, ArrayList<Message> messages) {
        this.name = name;
        this.imgURL = imgURL;
        this.attributes = attributes;
        this.messages = messages;
    }

    public ArrayList<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(ArrayList<Attribute> attributes) {
        this.attributes = attributes;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    protected dummyFriend(Parcel in) {
        name = in.readString();
        imgURL = in.readString();
    }

    public static final Creator<dummyFriend> CREATOR = new Creator<dummyFriend>() {
        @Override
        public dummyFriend createFromParcel(Parcel in) {
            return new dummyFriend(in);
        }

        @Override
        public dummyFriend[] newArray(int size) {
            return new dummyFriend[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(imgURL);
    }
}
