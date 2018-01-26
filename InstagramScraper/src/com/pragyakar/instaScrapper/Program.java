/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pragyakar.instaScrapper;

import com.pragyakar.instaScrapper.util.Grabber;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Pragyakar
 */
public class Program {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("==========[ Instagram Photo Scrapper ]==========");
        
        String baseUrl = "https://instagram.com/";
        System.out.print("Enter username : ");
        String link = input.next();
        try {
            Grabber grabber = new Grabber();
            String regEx = "https://(.*?).jpg";

            Pattern pattern = Pattern.compile(regEx);

            Matcher matcher = pattern.matcher(grabber.getBody(baseUrl + link));

            while (matcher.find()) {
                String imgPath = (matcher.group(0));

                String path = (imgPath);
                String[] tokens = path.split("/");
                File file = new File("C://instagram");
                if (!file.isDirectory()) {
                    file.mkdir();
                }
                File file1 = new File("C://Instagram/" + link);
                if (!file1.isDirectory()) {
                    file1.mkdir();
                }

                grabber.downloadImg(path, "C://instagram/" + link + "/" + tokens[tokens.length - 1]);
            }
            System.out.println("The photos are stored in your C drive.");

        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }
    
}
