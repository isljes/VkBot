package com.vk.bot.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
@Service
public class RequesterImp implements Requester {
    public String executeRequest(URI uri){
        HttpClient client=HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();
        HttpResponse<String> response = null;
        try {
            response=client.send(request,HttpResponse.BodyHandlers.ofString());
            return response.body();
        }  catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
