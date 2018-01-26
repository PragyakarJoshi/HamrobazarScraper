/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pragyakar.jobscrapper.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author Pragyakar
 */
public class Grabber {

    public String grab(String link, String param) throws IOException {
        
        /**
         * STEP 1: URL
         */
        URL url = new URL(link);

        /**
         * STEP 2: URL Connection
         */
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        if (param != null) {
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.getOutputStream().write(param.getBytes());
        }

        /**
         * STEP 3: Content Reading
         */
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line = "";
        StringBuilder content = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            content.append(line).append("\n");
        }
        reader.close();
        return content.toString();
       
    }
}
