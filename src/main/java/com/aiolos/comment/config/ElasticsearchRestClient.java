package com.aiolos.comment.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Aiolos
 * @date 2019-12-19 19:19
 */
@Configuration
public class ElasticsearchRestClient {

    @Value("${elasticsearch.ip}")
    private String ipAddress;

    @Bean("highLevelClient")
    public RestHighLevelClient highLevelClient() {

        String[] address = ipAddress.split(":");
        String ip = address[0];
        int port = Integer.parseInt(address[1]);
        HttpHost httpHost = new HttpHost(ip, port, "http");
        return new RestHighLevelClient(RestClient.builder(httpHost));
    }
}
