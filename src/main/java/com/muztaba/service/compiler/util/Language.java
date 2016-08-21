package com.muztaba.service.compiler.util;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by seal on 8/9/16.
 */
public class Language {
    public static final String JAVA = "java";
    public static final String CPP = "cpp";
    public static final String C = "c";

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
}
