package com.sk.ar.web.test.utils;

import java.util.ArrayList;
import java.util.List;

public class EventUtils {

    public static String getEventAttendRandomCode(int size) {
        if(size > 0) {
            char[] tmp = new char[size];
            for(int i=0; i<tmp.length; i++) {
                int div = (int) Math.floor( Math.random() * 2 );

                if(div == 0) { // 0이면 숫자로
                    tmp[i] = (char) (Math.random() * 10 + '0') ;
                }else { //1이면 알파벳
                    tmp[i] = (char) (Math.random() * 26 + 'A') ;
                }
            }
            return new String(tmp);
        }
        return "ERROR : Size is required.";
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (int i=0; i<100000; i++) {
            String code = getEventAttendRandomCode(8);
            System.out.println(code);
            list.add(code);
        }
        boolean isBl = list.stream()
                .distinct()
                .count() != list.size();

        System.out.println("is >>" + isBl);
    }
}
