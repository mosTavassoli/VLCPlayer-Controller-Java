package com.HSYCO.mostafa.http_request;

import com.HSYCO.mostafa.VlcController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.concurrent.CompletableFuture;

public class RequestsHttpFunction {
    private static final Logger logger = LoggerFactory.getLogger(RequestsHttpFunction.class);
    private static final String BASE_URL = "http://127.0.0.1:8080/requests/status.xml";
    private static final String ERROR_MSG = "Seems that the VLC PLAYER is not running !!!";
    private static final HttpClient httpClient = HttpClient.newBuilder()
            .authenticator(new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("", "123456".toCharArray());
                }
            })
            .version(HttpClient.Version.HTTP_2)
            .build();

    public static String sendRequest(String QueryParam) {
        try {
//            String valueToEncode = "" + ":" + "123456";
//            String encodedAuth = Base64.getEncoder()
//                    .encodeToString(valueToEncode.getBytes(StandardCharsets.UTF_8));

            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(BASE_URL + QueryParam))
//                    .header("Authorization", "Basic " + encodedAuth)
                    .build();

            CompletableFuture<String> thenApplyAsync = httpClient
                    .sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApplyAsync((resp) -> {
                        int status = resp.statusCode();
                        if (status != 200) {
                            System.err.println("Error: " + resp.statusCode());
                            return "NOT VALID";
                        }
                        return resp.body();
                    });
            thenApplyAsync.join();
            return thenApplyAsync.get();
        } catch (Exception ex) {
            logger.info(ERROR_MSG);
        }
        return null;
    }
}


// SYNC way of getting info
//             httpClient.send(request, HttpResponse.BodyHandlers.ofString());



//    ArrayList<String> myList = new ArrayList<>(Arrays.asList("Mostafa", "Ahmad", "Akbar"));
//            myList.stream().filter(item -> item.startsWith("M")).forEach(System.out::println);