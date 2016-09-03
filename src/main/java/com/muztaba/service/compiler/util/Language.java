package com.muztaba.service.compiler.util;

import org.springframework.beans.factory.annotation.Value;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by seal on 8/9/16.
 */
public class Language {
    public static final String JAVA = "java";
    public static final String CPP = "cpp";
    public static final String C = "c";

    @Value("${file.name.cpp}")
    private static String FILE_NAME_CPP;
    @Value("${file.name.c}")
    private static String FILE_NAME_C;
    @Value("${file.name.java}")
    private static String FILE_NAME_JAVA;

    private static final Set<String> langSet = new HashSet<>();

    static {
        //temporary. might be read from properties file.
        langSet.add(JAVA);
        langSet.add(CPP);
        langSet.add(C);
    }

    public static boolean isThere(String lang) {
        return langSet.contains(lang);
    }

    public static String langCheck(String lang) {
        String str = null;
        switch (lang.toLowerCase()) {
            case CPP :
                str = FILE_NAME_CPP;
                break;
            case JAVA :
                str = FILE_NAME_JAVA;
                break;
            case C :
                str = FILE_NAME_C;
                break;
        }
        return str;
    }
}
