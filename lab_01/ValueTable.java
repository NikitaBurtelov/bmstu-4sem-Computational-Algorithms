package com.company;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.io.*;

class ValueTable{
    private static final BigDecimal Start = new BigDecimal(0.0);
    private static final BigDecimal End = new BigDecimal(10);
    private static final BigDecimal Step = new BigDecimal(0.1);
    int arrLen = 100;
    int N;

    private BigDecimal[] arr_x = new BigDecimal[arrLen];
    private BigDecimal[] arr_y = new BigDecimal[arrLen];

    void initDataFile() throws IOException{
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File("file.txt")));
        BigDecimal start = Start;
        BigDecimal end = End;

        //Запись значений х и у в файл
        for (int i = 0; i < arrLen; i++) {
            arr_x[i] = start;
            arr_y[i] = BigDecimal.valueOf(Math.pow(start.doubleValue(), 2));
            start = start.add(Step);
        }

        writerDataFile(writer);

        //gettingValues(writer);

        writer.flush();
    }

    private void writerDataFile(BufferedWriter writer) throws IOException {
        /*
        for (BigDecimal i : arr) {
            writer.write(String.valueOf(i));
            writer.write(" ");
        }
        writer.write("\r\n");
        */
        for (int i = 0; i < arr_x.length; i++) {
            writer.write(String.valueOf(arr_x[i].setScale(3, RoundingMode.DOWN)));
            writer.write(" ");
            writer.write(String.valueOf(arr_y[i].setScale(3, RoundingMode.DOWN)));
            writer.write("\r\n");
        }
    }

    private void gettingValues(BufferedWriter writer) throws IOException{
        int dif = 1;

        while (N >= 0) {
            for (int i = 0; i < arr_y.length - dif; i++) {
                arr_y[i] = (arr_y[i].subtract(arr_y[i + dif])).divide(arr_x[i].subtract(arr_x[i + dif]), RoundingMode.DOWN);
                writer.write(String.valueOf(arr_y[i]));
                writer.write(" ");
            }
            writer.write("\r\n");

            dif++;
            N--;
        }
    }
}
