package com.lixlim.practice;

import java.io.IOException;
import java.net.URISyntaxException;

public interface ApiClient {

    /**
     * Api call as GET
     *
     * @param url
     * @param accessToken
     * @param queryString
     * @return
     * @throws URISyntaxException
     * @throws IOException
     * @throws InterruptedException
     */
    public String apiCallGet(String url, String accessToken, String queryString)
            throws URISyntaxException, IOException, InterruptedException;

    /**
     * Api call as POST
     *
     * @param url
     * @param accessToken
     * @param jsonString
     * @return
     * @throws URISyntaxException
     * @throws IOException
     * @throws InterruptedException
     */
    public String apiCallPost(String url, String accessToken, String jsonString)
            throws URISyntaxException, IOException, InterruptedException;
}
