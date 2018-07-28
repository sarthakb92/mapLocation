package com.honeywell.fileread;

import org.springframework.stereotype.Component;

import java.io.File;

/**
 * Created by Hackathon on 7/28/2018.
 */
@Component
public class ReadFile {

    public Boolean checkFileExists(String username){
        String fileName = username+".json";
        try{
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource(fileName).getFile());
            if(file!=null){
                return true;
            }
            else{
                return false;
            }
        }catch (Exception e){
            return false;
        }
    }
}
