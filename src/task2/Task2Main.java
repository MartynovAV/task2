package task2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Task2Main {
    public static void main(String...args) {
        //output files:
        String stats_dates=args[0];
        String stats_offices=args[1];
        //number of input files:
        int N_input=args.length-2;
        //input files:
        String [] inputFile=new String[N_input];
        String [] filePath=new String[N_input];

        HashMap<String, Double> map1=new HashMap<>(); // Date, Sum
        HashMap<String, Double> map2=new HashMap<>();  // Office Number, Sum

        DecimalFormat df = new DecimalFormat("0.00");
        df.setRoundingMode(RoundingMode.UP);
        String separator=File.separator;

        for (int i = 0; i <N_input ; i++) {
            inputFile[i]=args[2+i];
            filePath[i]="C:"+separator+"task2"+separator+inputFile[i];
            File file=new File(filePath[i]);
            try {
                Scanner sc=new Scanner(file);
                sc.useDelimiter("\t| |\n");
                while (sc.hasNext()){
                    String date=sc.next(); sc.next();//Date
                    String officeN=sc.next(); sc.next();  //office number
                    String check=sc.next();
                    Double checkD=Double.parseDouble(check);//Sum
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
        }
        //sorting  Date - Sum by Date:
        LinkedHashMap<String, Double> map1Sorted= map1.entrySet()
                .stream()
                .sorted((Map.Entry.<String, Double>comparingByKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        // writing Date - Sum output file
        try {
            String outfile1="C:"+separator+"task2"+separator+stats_dates;
            FileWriter writer1 = new FileWriter(outfile1);
            map1Sorted.forEach((k,v) -> {
                try {
                    writer1.write(k + " - " + df.format(v)+"\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
                writer1.close();
                System.out.println(stats_dates+" has been generated.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //sorting  Office Number - Sum by Sum value:
        LinkedHashMap<String, Double> map2Sorted= map2.entrySet()
                .stream()
                .sorted((Map.Entry.<String, Double>comparingByValue().reversed()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        // writing Office Number - Sum output file
        try {
            String outfile2="C:"+separator+"task2"+separator+stats_offices;
            FileWriter writer2 = new FileWriter(outfile2);
            map2Sorted.forEach((k,v) -> {
                try {
                    writer2.write(k + " - " + df.format(v)+"\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
                writer2.close();
                System.out.println(stats_offices+" has been generated.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
