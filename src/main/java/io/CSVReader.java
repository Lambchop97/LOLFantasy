package io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public  class CSVReader {

    static void readCSVFile(CSVFile file){
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(file.getPath()));

            StringBuilder sb = new StringBuilder();

            String line;
            int w = 0;
            while((line = fileReader.readLine()) != null){
                sb.append(line);
                sb.append(";");
                int i = line.split(",").length;
                if(w < i) w = i;
            }

            //TODO: check for empty file
            String[] lines = sb.toString().split(";");

            String[][] data = new String[lines.length][w];

            int i = 0;
            for(String s: lines){
                String[] d = new String[w];
                int k = 0;
                for(String e: s.split(",")){
                     d[k++] = e;
                }
                data[i++] = d;
            }

            file.data = data;
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
