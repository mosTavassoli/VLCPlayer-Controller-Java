package com.HSYCO.mostafa.http_request;

import com.HSYCO.mostafa.VlcController;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;


public class RequestHttp extends RequestsHttpFunction {

    private static final Logger logger = LoggerFactory.getLogger(RequestHttp.class);

    public static void getInfo() {
        logger.info("Video Information");
        String response = sendRequest("");

        if (response != null) {
            JSONObject json = XML.toJSONObject(response);
            String state = json.getJSONObject("root").getString("state");
            if (!Objects.equals(state, "stopped")) {
                JSONArray categoryArray = json
                        .getJSONObject("root")
                        .getJSONObject("information")
                        .getJSONArray("category");
                JSONArray infoArray = categoryArray
                        .getJSONObject(0)
                        .getJSONArray("info");
                for (int i = 0; i < infoArray.length(); i++) {
                    if (Objects.equals(infoArray.getJSONObject(i).getString("name"), "filename")) {
                        logger.info("The Video Title is: " + infoArray
                                .getJSONObject(i)
                                .getString("content"));
                    }
                }
            } else {
                logger.info("The Video is Stopped, Play the video to get the information !!!");
            }
        }
    }

    public static void playVideo() {
        logger.info("Video Plays");
        sendRequest("?command=pl_play");
    }

    public static void pauseVideo() {
        logger.info("Video Pauses");
        sendRequest("?command=pl_pause");
    }

    public static void stopVideo() {
        logger.info("Video Stops");
        sendRequest("?command=pl_stop");
    }
}


//        Send the Async Request
//        Void response = httpClient.sendAsync(request, HttpResponse.
//                BodyHandlers.ofString()).thenApply(HttpResponse::body).
//                thenAccept(System.out::println).join();
