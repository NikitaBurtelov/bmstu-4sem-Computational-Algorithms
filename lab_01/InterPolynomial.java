package com.company;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.io.*;


public class InterPolynomial {
    int arrLen, start, end;
    BigDecimal[] arrDataFile =  new BigDecimal[arrLen];
    BigDecimal[] arrAbscissa = new BigDecimal[arrLen];

    void findIndex(int n, float x) {
        int gap = 0;

        for (int i = 0; i < arrLen; i++) {
            if (x == 0f) {
                start = 0;
                end = n + 1;
                break;
            }
            else if (arrAbscissa[i].compareTo(BigDecimal.valueOf(x)) >= 0){
                if (n % 2 == 1)
                    gap = (n + 1) / 2;
                else
                    gap = n / 2;

                start = i - gap; //?????
                end = i + gap - 1;
                break;
            }
        };

        if (start < 0) {
            start = 0;
            end = n;
        }
        else if (end > arrLen) {
            end = arrLen - 1;
            start = end - n;
        }

        System.out.println(start + " SS " + end + " Value: " + arrAbscissa[start] + " " + arrAbscissa[end]);
    }

    BigDecimal sumPolynomial(int n, float x, BigDecimal value) {
        BigDecimal abscissaSum = new BigDecimal(1);
        BigDecimal sumPolynomial = new BigDecimal(0);
        BigDecimal tmp;

        for (int i = 0; i < n; i++) {
            tmp = getPolynomial(start, end,i + 1);
            abscissaSum = abscissaSum.multiply(BigDecimal.valueOf(x).subtract(arrAbscissa[start + i]));
            sumPolynomial = sumPolynomial.add(tmp.multiply(abscissaSum));
            //System.out.println("abscissaSum " + abscissaSum + "XXX:  " + x + " - " + arrAbscissa[start + i]);
            //System.out.println(sumPolynomial + "\n");
        }

        return sumPolynomial.add(value);
    }

    BigDecimal getPolynomial(int dif, int start, int end) {
        for (int i = start; i <= end; i++) {
            arrDataFile[i] = (arrDataFile[i].subtract(arrDataFile[i + dif]))
                    .divide(arrAbscissa[i].subtract(arrAbscissa[i + dif]), RoundingMode.DOWN);
        }

        return arrDataFile[start];
        /*
        while (n >= 0) {
            for (int i = start, k = 0; i <= end; i++, k++) {
                for (int j = 0; j < k + dif; j++) {
                    abscissaSum = abscissaSum.multiply(BigDecimal.valueOf(x).subtract(arrAbscissa[start + j]));
                    System.out.println("Sum x_i " + arrAbscissa[start + j] + " " + abscissaSum + "   " +
                            BigDecimal.valueOf(x).subtract(arrAbscissa[start + j]));
                }
                System.out.println();

                arrDataFile[i] = (arrDataFile[i].subtract(arrDataFile[i + dif]))
                        .divide(arrAbscissa[i].subtract(arrAbscissa[i + dif]), RoundingMode.DOWN);
            }
            sumPolynomial = sumPolynomial.add(abscissaSum.multiply(arrDataFile[start]));

            dif++;
            n--;
        }*/
    }
}
