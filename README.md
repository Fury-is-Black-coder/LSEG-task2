# LSEG-task2

**To avoid errors, you need to add the path to the libraries from folder "libraries" in each project. add jsoup-1.15.3.jar**

1.  We get download link to xsl file via Jsoup library.
Element link = doc.select("a[href*=/pubweb/]").first();
We have also absolute href to file.

2. We download file via Donload.Java (Utility)
3. We read xls via lib - poi from apache and use HSSFWorkbook to work with xls format. We read data step by step from rows and columns.
4. We convert time to needed format like 00:00
5. We get date of file from href and format to write in console using substring.

Finally we always get fresh data with formating to our console. 

![image](https://user-images.githubusercontent.com/57364788/204152599-844b3593-e64f-466b-a7a2-678790e721eb.png)


maven: 

  <dependency>
            <groupId>org.w3c</groupId>
            <artifactId>dom</artifactId>
            <version>2.3.0-jaxb-1.0.6</version>
        </dependency>

        <dependency>
            <groupId>org.apache.poi</groupId>
            <artifactId>poi</artifactId>
            <version>5.2.1</version>
        </dependency>

        <dependency>
            <groupId>org.apache.poi</groupId>
            <artifactId>poi-ooxml</artifactId>
            <version>5.2.1</version>
        </dependency>

        <dependency>
            <groupId>org.apache.logging.log4j</groupId>
            <artifactId>log4j-to-slf4j</artifactId>
            <version>2.8.2</version>
        </dependency>

        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-simple</artifactId>
            <version>1.7.32</version>
        </dependency>
