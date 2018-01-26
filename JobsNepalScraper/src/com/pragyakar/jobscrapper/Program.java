/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pragyakar.jobscrapper;

import com.pragyakar.jobscrapper.util.Grabber;
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

        try {
            Scanner input = new Scanner(System.in);
            boolean cont = true;
            while (cont) {
                System.out.println("Enter the type of job you are looking for : ");
                String kw = input.next();

                /**
                 * STEP 1-3: URL, URL Connection & Content Reading
                 */
                Grabber grabber = new Grabber();
                String content = grabber.grab("http://www.jobsnepal.com/simple-job-search", "Keywords=" + kw);

                /**
                 * STEP 4: Setting a Pattern
                 */
                String regex = "<a class=\"job-item\"(.*?)href=\"(.*?)\"\\s>\\n.(.*?)</a>";
                Pattern pattern = Pattern.compile(regex);

                /**
                 * STEP 5: Matching
                 */
                Matcher matcher = pattern.matcher(content);
                while (matcher.find()) {
                    System.out.println("");
                    System.out.println("Title : " + matcher.group(3).trim());
                    System.out.println("Link : " + matcher.group(2));
                }
                System.out.println("");
                System.out.println("Do you want to continue[Y/N] : ");
                String choice = input.next();
                if (choice.equalsIgnoreCase("N")){
                    cont = false;
                }
                
            }
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }
}
