package nvc.studyroom.common.utils;

import java.util.Random;

public class TempKey {
    private static final int SIZE = 6;

    public static String generate() {
        Random ran = new Random();
        StringBuffer sb = new StringBuffer();
        int num  = 0;
        do {
            num = ran.nextInt(10) + '0';
            sb.append((char) num);
        } while (sb.length() < SIZE);

        return sb.toString();
    }
}
