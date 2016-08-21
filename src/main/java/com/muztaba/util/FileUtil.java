package com.muztaba.util;

import com.muztaba.model.Submission;
import com.muztaba.service.compiler.util.DTO;

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

    public static void createDirectory(String path) {
        try {
            Files.createDirectory(Paths.get(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void batchCreate(Submission submission, DTO dto) {

        FileUtil.createDirectory(dto.getWorkingDir());
        FileUtil.writeByteToFile(submission.getSrcFile(), dto.getSrcPath());
        FileUtil.writeByteToFile(submission.getProblem().getInputFile(), dto.getInputPath());
        FileUtil.writeByteToFile(submission.getProblem().getResultFile(), dto.getOutputFile());
    }
}
