package com.mission1.mission1;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mission1.mission1.db.Wifi;
import com.mission1.mission1.db.WifiService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class SeoulAPI {
    private final String KEY = "615377575277776e34306256524265";

    public int getData() {
        int rowCnt = 0;
        int totalCnt = -1;
        WifiService wifiService = new WifiService();
        wifiService.dbCreate();
        Gson gson = new Gson();
        try {
            int startIdx = 1;
            int endIdx = 1000;
            String SERVICE = "TbPublicWifiInfo";
            String TYPE = "json";
            String DOMAIN = "http://openapi.seoul.go.kr:8088";
            String url0 = DOMAIN + "/" + KEY + "/" + TYPE + "/" + SERVICE + "/";
            String url = url0 + startIdx + "/" + endIdx;
            OkHttpClient client = new OkHttpClient();
            Request.Builder builder = new Request.Builder().url(url).get();
            Request request = builder.build();

            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                ResponseBody body = response.body();
                if (body != null) {
                    JsonObject json = gson.fromJson(body.string(), JsonObject.class);
                    JsonObject jsonService = gson.fromJson(json.get(SERVICE), JsonObject.class);
                    totalCnt = jsonService.get("list_total_count").getAsInt();
                    JsonArray jsonArray = jsonService.getAsJsonArray("row");
                    for (JsonElement elt : jsonArray) {
                        Wifi wifi = gson.fromJson(elt, Wifi.class);
                        wifiService.dbInsert(wifi);
                        rowCnt++;
                    }
                }
            }
            while (endIdx < totalCnt) {
                int SIZE = 1000;
                startIdx += SIZE;
                endIdx += SIZE;
                url = url0 + startIdx + "/" + endIdx;
                builder = new Request.Builder().url(url).get();
                request = builder.build();
                response = client.newCall(request).execute();
                if (response.isSuccessful()) {
                    ResponseBody body = response.body();
                    if (body != null) {
                        JsonObject json = gson.fromJson(body.string(), JsonObject.class);
                        JsonObject jsonService = gson.fromJson(json.get(SERVICE), JsonObject.class);
                        JsonArray jsonArray = jsonService.getAsJsonArray("row");
                        for (JsonElement elt : jsonArray) {
                            Wifi wifi = gson.fromJson(elt, Wifi.class);
                            wifiService.dbInsert(wifi);
                            rowCnt++;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("list_total_count:" + totalCnt);
        System.out.println("rowCnt:" + rowCnt);
        return rowCnt;
    }
}
