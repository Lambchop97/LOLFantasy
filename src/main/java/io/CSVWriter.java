package io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

class CSVWriter {

    static void writeCSVFile(CSVFile file){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file.getPath()));

            StringBuilder sb;

            int k = 0;
            for(String[] s: file.data){
                sb = new StringBuilder();
                int i = 0;
                for(String e: s){
                    if(e != null) sb.append(e);
                    if(i++ < s.length - 1) sb.append(",");
                }
                writer.write(sb.toString());
                if(k++ < file.data.length - 1) writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
