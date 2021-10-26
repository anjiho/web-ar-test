package com.sk.ar.web.test.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
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

    public static List<String> getEventAttendRandomCode(int strSize, int intSize, int limitSize) {
        List<String> arrList = new ArrayList<>();
        if(strSize > 0) {
            for (int j=0; j<limitSize; j++) {

                char[] tmp = new char[strSize];
                for(int i=0; i<tmp.length; i++) {
                    tmp[i] = (char) (Math.random() * 26 + 'A') ;
                }

                String ran = new String(tmp);
                String str = ran.substring(0, 2);
                String str2 = ran.substring(2, 4);
                String code = str + getNumberRan(intSize) + str2;

                if (!arrList.contains(code)) {
                    arrList.add(code);
                } else if (arrList.contains(code)) {
                    j--;
                }
            }
        }
        return arrList;
    }

    public static String getNumberRan(int len) {
        String ranStr = "";
        int n[] = new int[len];
        int index = 0;
        for(int i = 0; i<n.length;i++) {
            do {
                index = (int)(Math.random()*10);
            }while(exists(n,index));
            n[i] = index;
        }
        for(int i = 0; i<len; i++) {
            ranStr += String.valueOf(n[i]);
        }
        return ranStr;
    }

    private static boolean exists(int n[], int index) {
        for (int i = 0; i < n.length; i++) {
            if(n[i] == index)
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        long beforeTime = System.currentTimeMillis();

        List<String> arrList = getEventAttendRandomCode(4, 4, 10000);

        boolean isBl = arrList.stream()
                .distinct()
                .count() != arrList.size();
        long isCount2 = arrList.stream().count();
        long isCount = arrList.stream()
                .distinct()
                .count();

        System.out.println("cnt >>" + isCount);
        System.out.println("cnt2 >>" + isCount2);
        System.out.println("is >>" + isBl);

        long afterTime = System.currentTimeMillis();
        long secDiffTime = (afterTime - beforeTime)/1000;
        System.out.println("시간차이(m) : "+secDiffTime);

    }
}
