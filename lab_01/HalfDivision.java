package com.company;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class HalfDivision {
    int arrLen, start = 0, end = 99;;
    BigDecimal eps = new BigDecimal(0.1);
    BigDecimal[] arrDataFile;
    BigDecimal[] arrAbscissa;

    BigDecimal getHalfDivision() {
        BigDecimal mid = new BigDecimal(0);
        InterPolynomial tmp = new InterPolynomial();

        mid = getMid(mid);
        getPolynomial(1, start, end - 1);

        while (arrAbscissa[start].subtract(arrAbscissa[end])
                .compareTo(eps) > 0) {

            if ((arrDataFile[start].multiply(arrDataFile[end]))
                    .compareTo(BigDecimal.valueOf(0)) < 0) {
                end = mid.intValueExact();
            }
            else {
                start = mid.intValueExact();
            }
            mid = getMid(mid);
        }

        return mid;
    }

    BigDecimal getMid(BigDecimal mid) {
        mid = (arrAbscissa[start].subtract(arrAbscissa[end]))
                .divide(BigDecimal.valueOf(2), RoundingMode.DOWN);
        return mid;
    }

    BigDecimal[] getPolynomial(int dif, int start, int end) {
        for (int i = start; i <= end; i++) {
            arrDataFile[i] = (arrDataFile[i].subtract(arrDataFile[i + dif]))
                    .divide(arrAbscissa[i].subtract(arrAbscissa[i + dif]), RoundingMode.DOWN);
        }

        return arrDataFile;
    }
}
