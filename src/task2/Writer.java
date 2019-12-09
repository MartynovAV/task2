package task2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Map;

public class Writer {
    public static void writeMaptoFile(Map map, String outputFile){
        String separator= File.separator;
        DecimalFormat df = new DecimalFormat("0.00");
        df.setRoundingMode(RoundingMode.UP);
        try {
            String outfile="C:"+separator+"task2"+separator+outputFile;
            FileWriter writer = new FileWriter(outfile);
            map.forEach((k,v) -> {
                try {
                    writer.write(k + " - " + df.format(v)+"\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            writer.close();
            System.out.println(outputFile+" has been generated.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
