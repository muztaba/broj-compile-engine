package com.muztaba.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by seal on 8/20/16.
 */
public class FileUtil {
    public static byte[] readFileAsByte(String path) {
        byte[] bytes = null;
        try {
            bytes = Files.readAllBytes(Paths.get(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return bytes;
    }

    public static void writeByteToFile(byte[] bytes, String path) {
        try {
            Files.write(Paths.get(path), bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
