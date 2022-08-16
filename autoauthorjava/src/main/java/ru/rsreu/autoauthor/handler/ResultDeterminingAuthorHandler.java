package ru.rsreu.autoauthor.handler;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResultDeterminingAuthorHandler {
    public static Map<String, Double> getResultDetermingAuthor(String stringFromPython){
        HashMap<String, Double> mapResult = new HashMap<>();
        Pattern p = Pattern.compile("\\(.*?\\)");
        Pattern p2 = Pattern.compile("\\[.*?\\]");
        Matcher matcher = p.matcher(stringFromPython);
        Matcher matcher2 = p2.matcher(stringFromPython);
        while (matcher.find()&&matcher2.find()) {
            mapResult.put(stringFromPython.substring(matcher.start()+1,matcher.end()-1), Double.valueOf(stringFromPython.substring(matcher2.start()+1,matcher2.end()-1)));
        }
        return mapResult;
    }
}
