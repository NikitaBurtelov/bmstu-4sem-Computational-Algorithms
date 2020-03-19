package com.company;
import java.io.*;
import java.io.IOError;
import java.io.Console;
import java.math.BigDecimal;
import java.util.Scanner;


public class Main {
    private static int n;
    private static float x;

    public static void dataInput() {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите значение n: ");
        n = in.nextInt();
        System.out.println("Введите значение x: ");
        x = in.nextFloat();
        in.close();
    }

    private static int readFile(BigDecimal[] arr_x, BigDecimal[] arr_y) throws IOException{
        String[] bufer;
        BigDecimal tmp;
        Reader reader = new FileReader("file.txt");
        BufferedReader buffReader = new BufferedReader(reader);
        int count = 0;

        while (buffReader.ready()) {
            bufer = buffReader.readLine().split(" ");

            tmp = new BigDecimal(bufer[0]);
            arr_x[count] = tmp;

            tmp = new BigDecimal(bufer[1]);
            arr_y[count] = tmp;
            count++;
        }

        reader.close();
        buffReader.close();

        return count;
    }

    public static void main(String args[]) throws IOException{
        ValueTable tmp = new ValueTable();
        InterPolynomial polynomial = new InterPolynomial();
        BigDecimal arr_x[] = new BigDecimal[tmp.arrLen];
        BigDecimal arr_y[] = new BigDecimal[tmp.arrLen];

        dataInput();

        int lenDataArr = readFile(arr_x, arr_y);

        polynomial.arrAbscissa = arr_x;
        polynomial.arrDataFile = arr_y;
        polynomial.arrLen = lenDataArr;

        tmp.N = n;

        tmp.initDataFile();

        polynomial.findIndex(n, x);
        System.out.println(polynomial.sumPolynomial(n, x));


    }
}
