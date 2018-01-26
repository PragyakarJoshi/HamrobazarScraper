package com.pragyakar.prodcutsscrapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
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
        System.out.print("Enter search keyword: ");
        String keyword = input.next();
        try {
            String address = "http://hamrobazaar.com/search.php?do_search=Search&searchword=" + keyword + "&Search.x=0&Search.y=0&catid_search=0";
            URL url = new URL(address);
            URLConnection conn = url.openConnection();

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            StringBuilder content = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n".trim());
            }

            reader.close();

            String rx = "<a\\shref=\"(.*?)\">\\s(.*?)<b>(.*?)</b>(.*?)<b>(.*?)</b>";
            Pattern pattern = Pattern.compile(rx);

            Matcher matcher = pattern.matcher(content);
            while (matcher.find()) {
                if (!matcher.group(3).equals(keyword)) {
                    System.out.println("");
                    System.out.println("Title: " + matcher.group(3));
                    System.out.println("Price: " + matcher.group(5));
                    System.out.println("Link: http://hamrobazaar.com/" + matcher.group(1));
                }
            }

        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

}
