package com.honeywell.filewrite;

import org.springframework.stereotype.Component;

import java.io.*;

/**
 * Created by Hackathon on 7/28/2018.
 */
@Component
public class WriteToNewJsonFile {

    private String newFileLocation="C:\\Users\\Hackathon\\IdeaProjects\\codetest\\src\\main\\resources\\";

    public void writingAFile(String username, String filepath) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        //File file = new File(classLoader.getResource(filepath).getFile());
        BufferedReader bufferedReader=null;
        BufferedWriter bufferedWriter=null;
        try {
            bufferedReader = new BufferedReader(new FileReader(filepath));
            String newLocation = newFileLocation+username+".json";
            bufferedWriter = new BufferedWriter(new FileWriter(newLocation));
            String c="";
            while((c=bufferedReader.readLine())!=null){
                //outputStreamWriter.write(c);
                bufferedWriter.write(c);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {

            try {

                if (bufferedReader != null)
                    bufferedReader.close();

                if (bufferedWriter != null)
                    bufferedWriter.close();

            } catch (IOException ex) {

                ex.printStackTrace();

            }

        }
    }

    /*public static void main(String args[]) throws IOException {
        new WriteToNewJsonFile().writingAFile();
    }*/
}
