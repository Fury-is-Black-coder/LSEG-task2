package org.example;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class Download implements Runnable{

    Document doc = (Document) Jsoup.connect("https://www.ote-cr.cz/en/statistics/electricity-imbalances").get();
    Element link_tmp = doc.select("a[href*=/pubweb/]").first();
    String absHref_tmp = link_tmp.attr("abs:href");
    String href_tmp = link_tmp.attr("href");
    String file_name_tmp = href_tmp.substring(href_tmp.lastIndexOf("/")+1);
    String date_tmp = file_name_tmp.substring(file_name_tmp.indexOf("_")+1,file_name_tmp.lastIndexOf("_")-3).replace("_",".");


    String link;
    File out;

    public Download(String link, File out) throws IOException {
        this.link = link;
        this.out = out;
    }


    @Override
    public void run() {
        try{
            URL url = new URL(link);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            double fileSize = (double) http.getContentLengthLong();
            BufferedInputStream in = new BufferedInputStream(http.getInputStream());
            FileOutputStream fos = new FileOutputStream(this.out);
            BufferedOutputStream bout = new BufferedOutputStream(fos, 1024);
            byte[] buffer = new byte[1024];
            double downloaded = 0.00;
            int read = 0;
            double percentDownloaded = 0.00;
            while ((read = in.read(buffer, 0, 1024))>=0){
                bout.write(buffer, 0, read);
                downloaded += read;
                percentDownloaded = (downloaded*100)/fileSize;
                String percent = String.format("%.4f", percentDownloaded);
                System.out.println("Downloaded "+percent+"% of a file");
            }
            bout.close();
            in.close();
            System.out.println("Download complete.");

            System.out.println("Date of base: "+date_tmp);
            for (int i=1;i<=14;i++){
                ExcelUtility.getMapData(file_name_tmp,date_tmp,i);
            }

        }
        catch (IOException ex){
            ex.printStackTrace();
        }

    }
}
