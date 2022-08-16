package ru.rsreu.autoauthor.jsp;

import java.util.List;

public class Yourclass {
    public static boolean contains(List list, Object o) {
        if (list!=null){
            return list.contains(o);
        }
        return false;
    }
}
