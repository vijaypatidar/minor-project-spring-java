package com.svceindore.minor.fcm;

import org.json.JSONObject;

public class Notification {
    private final JSONObject data;

    public Notification() {
        data = new JSONObject();
    }

    public JSONObject getMessageData() {
        return data;
    }

    public void setTitle(String title) {
        data.put("title", title);
    }

    public void setContentInfo(String contentInfo) {
        data.put("contentInfo", contentInfo);
    }

    public void setContentDetailInfo(String contentDetailInfo) {
        data.put("contentDetailInfo", contentDetailInfo);
    }

    public void setImageUrl(String imageUrl) {
        data.put("imageUrl", imageUrl);
    }

    public void setAction(String action) {
        data.put("action",action);
    }
}