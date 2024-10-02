package com.springcloud.msvc_cursos.services;

import com.springcloud.msvc_cursos.models.Usuario;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Test {


    public static void main(String[] args) {
    List<String> words = new ArrayList<>();
    words.add("aaaaa");words.add("ba");words.add("cv");words.add("asd");words.add("asdf");words.add("asdfg");
    String value = "aaaaa ba cv asd asdf asdfg";

        System.out.println("Result is: "+getValue(words,value));

    }

    public static String getValue(List<String> words, String message) {
        StringBuilder result = new StringBuilder();
        String[] messages = message.split(" ");
       for (String s: messages) {
           for (String ss: words) {
               if (result.toString().isEmpty() && ss.equals(s)) {
                   continue;
               } else if (String.valueOf(s.charAt(0)).equals(String.valueOf(ss.charAt(0)))) {
                   if (String.valueOf(s.charAt(s.length()-1)).equals(String.valueOf(ss.charAt(ss.length()-1)))) {
                       result.append(ss).append(" ");
                   }
               }
           }
       }
        return result.toString().trim();
    }


}
