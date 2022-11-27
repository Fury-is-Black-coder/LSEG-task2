package org.example;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;

import static java.rmi.server.LogStream.log;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Task 2");

        //Getting the current date value
        LocalDate currentdate = LocalDate.now();
        //Getting the current day
        int currentDay = currentdate.getDayOfMonth();
        System.out.println("Current day: "+currentDay);
        //Getting the current month
        Month currentMonth = currentdate.getMonth();
        System.out.println("Current month: "+currentMonth);
        //getting the current year
        int currentYear = currentdate.getYear();
        System.out.println("Current month: "+currentYear);
        //getting the month number
        int currentMonthNumber = LocalDate.now().getMonthValue();
        System.out.println("Current month number: "+currentMonthNumber);

//        String curent_url = "https://www.ote-cr.cz/pubweb/attachments/05_09_12/"+currentYear+"/month"+
//                currentMonthNumber+"/day"+currentDay+"/Imbalances_"+currentDay+"_"+currentMonthNumber+"_"+
//                currentYear+"_V0_EN.xls";

//        File out_curent = new File("Imbalances_"+currentDay+"_"+currentMonthNumber+"_"+
//                currentYear+"_V0_EN.xls");
//        new Thread(new Download(curent_url, out_curent)).start();

//        File input = new File("/tmp/temp.html");
//        Document doc = (Document) Jsoup.parse("https://www.ote-cr.cz/en/statistics/electricity-imbalances");

        Document doc = (Document) Jsoup.connect("https://www.ote-cr.cz/en/statistics/electricity-imbalances").get();

        Element link = doc.select("a[href*=/pubweb/]").first();
        String absHref = link.attr("abs:href");
        String href = link.attr("href");
        System.out.println(absHref);
        System.out.println(href);

        String file_name = href.substring(href.lastIndexOf("/")+1);

        File out = new File(file_name);
        new Thread(new Download(absHref, out)).start();







    }
}
