package org.example;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NasaImage {

    @Test
    public void testRandom() throws IOException {
        NasaImage nasaImage = new NasaImage();
        String randomImage = nasaImage.getRandomImage("clouds");

        assertNotNull(randomImage);
        assertTrue(randomImage.startsWith("https://images-assets.nasa.gov/image/"));
        assertTrue(randomImage.endsWith("jpg"));

    }

    @Test
    public void testRandomSun() throws IOException {
        NasaImage nasaImage = new NasaImage();
        String randomImage = nasaImage.getRandomImage("sun");

        assertNotNull(randomImage);
        assertTrue(randomImage.startsWith("https://images-assets.nasa.gov/image/"));
        assertTrue(randomImage.endsWith("jpg"));

    }

    @Test
    public void testRandomINSA() throws IOException {
        NasaImage nasaImage = new NasaImage();
        String randomImage = nasaImage.getRandomImage("INSA");

        fail("INSA is not a valid keyword");
        /*
        assertNotNull(randomImage);
        assertTrue(randomImage.startsWith("https://images-assets.nasa.gov/image/"));
        assertTrue(randomImage.endsWith("jpg"));*/

    }

    /*
    public static void main(String[] args) throws Exception {

        NasaImage nasaImage = new NasaImage();
        String randomImage = nasaImage.getRandomImage("sun");
        // display an image from an URL
        // img = ImageIO.read(new URL(randomImage));
        //nasaImage.displayImage(img);
    }
    
     */




    public String getRandomImage(String keywordSearch) throws IOException {

        String uri = "https://images-api.nasa.gov/search?q=" + keywordSearch;
        List<String> hrefs = new ArrayList<>();

        try {
            // make the GET request
            URLConnection request = new URL(uri).openConnection();
            request.connect();
            InputStreamReader inputStreamReader = new InputStreamReader((InputStream) request.getContent());

            // map to GSON objects
            JsonElement root = new JsonParser().parse(inputStreamReader);

            // traverse the JSON data
            JsonArray items = root
                    .getAsJsonObject()
                    .get("collection").getAsJsonObject()
                    .get("items").getAsJsonArray();

            // flatten nested arrays
            JsonArray links = new JsonArray();
            items.forEach(item -> links.addAll(item
                    .getAsJsonObject()
                    .get("links")
                    .getAsJsonArray()));

            // filter links with "href" properties
            links.forEach(link -> {
                JsonObject linkObject = link.getAsJsonObject();
                String relString = linkObject.get("rel").getAsString();
                if ("preview".equals(relString)) {
                    hrefs.add(linkObject.get("href").getAsString());
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }

        // print hrefs;
        System.out.println(hrefs);
        // pick randomly an element in a list
        int randomIndex = (int) (Math.random() * hrefs.size());
        String randomImage = hrefs.get(randomIndex);
        return randomImage;

    }

    public void displayImage(BufferedImage image) {

        JFrame frame = new JFrame("Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new JScrollPane(new JLabel(new ImageIcon(image))));
        frame.pack();
        frame.setVisible(true);
    }


}
