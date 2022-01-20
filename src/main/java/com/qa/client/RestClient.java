package com.qa.client;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;


public class RestClient {

    //1 Get Method
    public CloseableHttpResponse get(String url) throws IOException {

        CloseableHttpClient httpClient =  HttpClients.createDefault();
        HttpGet httpget = new HttpGet(url);
        CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpget); //Hit the url to get response

        return closeableHttpResponse;

    }
}
