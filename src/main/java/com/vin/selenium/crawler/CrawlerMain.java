package com.vin.selenium.crawler;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class CrawlerMain {
    public static void main(String args[]) {
        WebDriver driver = new ChromeDriver();
        String word = "profession";
        driver.get("https://www.oxfordlearnersdictionaries.com/definition/english/"+word);
        System.out.println(driver.findElement(By.className("phon")).getText());
        System.out.println(driver.findElement(By.className("sound")).getAttribute("data-src-mp3"));
        System.out.println(driver.findElement(By.className("def")).getText());
        System.out.println(driver.findElement(By.className("x-gs")).getText());

        //synonyms
        //collocations
        //images

        WebDriver driver1 = new ChromeDriver();
        driver1.get("https://www.google.com/search?tbm=isch&q="+word);

        List<WebElement> allImgs = driver1.findElements(By.tagName("img"));
        for (WebElement img : allImgs) {
            String imgName = img.getAttribute("name");
            if (imgName != null && !imgName.equals("")) {
//                System.out.println("Name: " + imgName);
                WebDriver driver2 = new ChromeDriver();
                //https://www.google.com/search?tbm=isch&q=profession#imgrc=JhxISpnwgGJ2xM:
                driver2.get("https://www.google.com/search?tbm=isch&q="+word+"#imgrc="+imgName);

                System.out.println("img: "+driver2.findElement(By.className("irc_mi")).getAttribute("src"));

                driver2.close();
            }
        }
        driver1.close();
        driver.close();
    }
}
