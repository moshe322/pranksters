package com.littlejohnny.commons.database.jdbc.utills;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class StringParser {

    public static Map<String, String> stringToMap(String str) {
        Map<String, String> map = new HashMap<String, String>();
        try {
            Pattern pattern = Pattern.compile("-_-");
            String[] words = pattern.split(str);
            for (String temp : words) {
                pattern = Pattern.compile(":");
                String[] values = pattern.split(temp);
                map.put(values[0], values[1]);
            }
            return map;
        } catch (Exception e) {
            return new HashMap<String, String>();
        }
    }

    public static String mapToString (Map<String, String> map) {
        try {
            StringBuilder builder = new StringBuilder();
            for (Map.Entry<String, String> temp : map.entrySet()) {
                builder.append(temp.getKey());
                builder.append(":");
                builder.append(temp.getValue());
                builder.append("-_-");
            }
            return builder.toString();
        } catch (Exception e) {
            return "";
        }
    }
}
