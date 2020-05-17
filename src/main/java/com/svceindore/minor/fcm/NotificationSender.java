package com.svceindore.minor.fcm;

import com.svceindore.minor.exception.FCMKeyNotFoundException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.annotation.Bean;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

public class NotificationSender {
    private String SERVER_KEY;
    private final JSONObject root;

    public NotificationSender() throws Exception {
        this.root = new JSONObject();
        Properties properties = new Properties();
        File file = new File("project.properties");
        if (!file.exists())file.createNewFile();
        properties.load(new FileInputStream(file));
        this.SERVER_KEY=properties.getProperty("SERVER_KEY");
        if (this.SERVER_KEY==null)throw new FCMKeyNotFoundException();
    }

    public NotificationSender send(JSONObject message){
        this.root.put("data", message);
        return this;
    }

    public String toTopic(String topic) throws Exception {
        System.out.println("Send to Topic : "+topic);
        root.put("condition", "'" + topic + "' in topics");
        return sendPushNotification(true);
    }

    public String toGroup(JSONArray mobileTokens) throws Exception {
        root.put("registration_ids", mobileTokens);
        return sendPushNotification(false);
    }

    public String toToken(String token) throws Exception {
        root.put("to", token);
        return sendPushNotification(false);
    }


    private String sendPushNotification(boolean toTopic) throws Exception {
        String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";
        URL url = new URL(API_URL_FCM);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setUseCaches(false);
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestProperty("Authorization", "key=" + SERVER_KEY);

        try {
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(root.toString());
            wr.flush();

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            String output;
            StringBuilder builder = new StringBuilder();
            while ((output = br.readLine()) != null) {
                builder.append(output);
            }
            System.out.println(builder);
            String result = builder.toString();

            JSONObject obj = new JSONObject(result);

            if (toTopic) {
                if (obj.has("message_id")) {
                    return "SUCCESS";
                }
            } else {
                int success = Integer.parseInt(obj.getString("success"));
                if (success > 0) {
                    return "SUCCESS";
                }
            }

            return builder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }

    }
}