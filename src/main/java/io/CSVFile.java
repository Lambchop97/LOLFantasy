package io;

import start.StartUp;

public class CSVFile {

    String[][] data;
    private String path;

    public CSVFile(String filename){
        this.path = StartUp.HOME_PATH + filename;
        CSVReader.readCSVFile(this);
        CSVWriter.writeCSVFile(this);
    }

    String getPath(){
        return path;
    }

    public void printData(){
        for(String[] sa: data){
            for(String s: sa){
                if(s == null || s.matches("")){
                    System.out.print("\'null\' ");
                } else {
                    System.out.print(s + " ");
                }
            }
            System.out.println();
        }
    }

    public int getWidth(){
        return data[0].length;
    }

    public int getHeight(){
        return data.length;
    }

    public void setData(String[][] newData){
        data = newData;
    }

    public void resizeData(int x, int y){
        int newX = Math.max(x, data[0].length);
        int newY = Math.max(y, data.length);

        String[][] oldData = data;
        data = new String[newY][newX];
        setRange(oldData, 0, 0);
    }

    public boolean setCell(String newData, int x, int y){
        if(data[0].length <= x || data.length <= y || 0 > x || 0 > y) return false;
        data[y][x] = newData;
        return true;
    }

    public boolean setRange(String[][] newData, int x, int y){
        if(data[0].length < x + newData[0].length || data.length < y + newData.length || 0 > x || 0 > y) return false;
        for(int k = y; k < y + newData.length; k++){
            System.arraycopy(newData[k-y], 0, data[k], x, newData[k-y].length);
        }
        return true;
    }

    public String fetchCell(int x, int y){
        if(x < 0 || y < 0 || x > data[0].length || y > data.length) return null;
        return data[y][x];
    }

    public String[][] fetchRange(int x, int y, int w, int h){
        if(x < 0 || y < 0 || x + w > data[0].length || y + h > data.length) return null;
        String[][] range = new String[h][w];
        for(int k = 0; k < h; k++){
            System.arraycopy(data[k+y], x, range[k], 0, range[k].length);
        }
        return range;
    }
}