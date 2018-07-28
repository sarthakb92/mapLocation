package com.honeywell.fileread;

import com.jayway.restassured.path.json.JsonPath;
import groovy.json.JsonSlurper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Created by Hackathon on 7/28/2018.
 */
@Component
public class ReadFromJsonFile {

    public List<Map> readFromJson(String username, Long time){
        ClassLoader classLoader = getClass().getClassLoader();
        String fileName = username+".json";
        //File file = new File("C:\\Users\\Hackathon\\Desktop\\New folder\\Script1.json");
        File file = new File(classLoader.getResource(fileName).getFile());
        //List<String> categories = JsonPath.from(file).get("locations.timestampMs");
       // List<String> categories1 = JsonPath.from(file).get("$.locations.timestampMs[*]['longitudeE7','latitudeE7']");
        //$.A.AB[*]['ABB', 'ABA']
        //List<String> cate = JsonPath.from(file).get("store.book");
        List<Map> books = JsonPath.from(file).param("from", time).get("locations.findAll {timestampMs=from}");
        //List<Map> book1 = JsonPath.from(file).get("locations.findAll {longitudeE7=776867045 && latitudeE7=289763479}");
        //List<Map> books = JsonPath.from(file).get("store.book.findCategory { book -> book.price >= 5 && book.price <= 15 }");
        //Map books2 = JsonPath.from(file).get("locations.findAll {longitudeE7 = 776867045}");
        //it.position == \"Centre-Back\" }.find { it.nationality == \"Argentina\"
        /*for(Map c : book1){
            System.out.println(c);
        }*/
        return books;
    }

    public Map readLocation(String username,String longitutde,String latitude){
        ClassLoader classLoader = getClass().getClassLoader();
        String fileName = username+".json";
        //File file = new File("C:\\Users\\Hackathon\\Desktop\\New folder\\Script1.json");
        File file = new File(classLoader.getResource(fileName).getFile());
        Map book1 = JsonPath.from(file).param("longi",longitutde).param("lati",latitude).get("locations.findAll {longitudeE7=longi}.find{latitudeE7=lati}");
        return book1;
    }

    /*public static void main(String args[]) throws IOException {
        new ReadFromJsonFile().readFromJson();
    }*/


    private void test(){
        JsonSlurper jsonSlurper = new JsonSlurper();
        Object object = jsonSlurper.parseText("{\"locations\": [{\n" +
                "\t\t\"timestampMs\": \"1487490245304\",\n" +
                "\t\t\"latitudeE7\": 289763479,\n" +
                "\t\t\"longitudeE7\": 776867045,\n" +
                "\t\t\"accuracy\": 43\n" +
                "\t\t}]\n" +
                "\t\t}");

        //object.locations.find{it.accuracy = 43};
    }
}
