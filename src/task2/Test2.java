package task2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeMap;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Test2 {

    public static void main(String[] args) {
        String filePath="C:\\task2\\ops3.txt";
        File file=new File(filePath);
        TreeMap<String, Double> map1=new TreeMap<>();
        TreeMap<String, Double> map2=new TreeMap<>();

        DecimalFormat df = new DecimalFormat("0.00");
        df.setRoundingMode(RoundingMode.UP);
        try {
            Scanner sc=new Scanner(file);
            sc.useDelimiter("\t| |\n");

            while (sc.hasNext()){
                String date=sc.next(); sc.next();
                String officeN=sc.next(); sc.next();
                String check=sc.next();
                Double checkD=Double.parseDouble(check);
                // Date, Sum
                if(map1.containsKey(date)){
                    map1.put(date,map1.get(date)+checkD);
                } else{
                    map1.put(date,checkD) ;
                }
                // Office Number, Sum
                if(map2.containsKey(officeN)){
                    map2.put(officeN,map2.get(officeN)+checkD);
                } else{
                    map2.put(officeN,checkD) ;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

         //map1.forEach((k,v) -> System.out.println(k + " - " +  df.format(v)));

        try {
            FileWriter writer = new FileWriter("C:\\task2\\RES1.txt");
            map1.forEach((k,v) -> {
                try {
                    writer.write(k + " - " + df.format(v)+"\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileWriter writer2 = new FileWriter("C:\\task2\\RES2.txt");
            map2.forEach((k,v) -> {
                try {
                    writer2.write(k + " - " + df.format(v)+"\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            writer2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
