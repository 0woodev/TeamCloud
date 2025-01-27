package com.woodev.teamcloud.util;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
    public static String formatString(String template, Map<String, Object> params) {
        Pattern pattern = Pattern.compile("\\{(\\w+)}");
        Matcher matcher = pattern.matcher(template);

        StringBuffer buffer = new StringBuffer();
        while (matcher.find()) {
            String key = matcher.group(1); // {key}에서 key만 추출
            String value = params.getOrDefault(key, "").toString(); // 키에 해당하는 값 가져오기
            matcher.appendReplacement(buffer, value);
        }
        matcher.appendTail(buffer);

        return buffer.toString();
    }
}
