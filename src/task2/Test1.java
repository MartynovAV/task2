package task2;

import java.util.Scanner;
import java.util.TreeMap;

public class Test1 {
    public static void main(String[] args) {
        String a="2018/04/24 17:19:35\t1849\t1\t87735.69";

        TreeMap<String, Double> map1=new TreeMap<>();
        TreeMap<String, Double> map2=new TreeMap<>();

        Scanner sc=new Scanner(a);
        sc.useDelimiter("\t| ");

        while (sc.hasNext()){
            String date=sc.next();
            sc.next();
            String officeN=sc.next();
            sc.next();
            String check=sc.next();
            Double checkD=Double.parseDouble(check);
            map1.put(date,checkD);
            map2.put(officeN,checkD);
        }
        System.out.println(map1);

        for (int i = 0; i < 3; i++) {
            if(map1.containsKey("2018/04/24")){
                map1.put("2018/04/24",map1.get("2018/04/24")+10000);
            } else{
                map1.put("2018/04/25",20000.0) ;
            }
        }

        System.out.println(map1);

    }
}
