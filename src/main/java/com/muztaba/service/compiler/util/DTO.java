package com.muztaba.service.compiler.util;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

/**
 * Created by seal on 8/21/16.
 */
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class DTO {
    private final String lang;
    private final String srcFileName;
    private final String inputFileName;
    private final String outputFileName;
    private final String resultFile;
    private final String workingDir;
    private final long timeLimit;
    private final long memoryLimit;

    public String getLang() {
        return lang.toLowerCase();
    }

    public String getSrcPath() {
        return workingDir + "/" + srcFileName;
    }

    public String getInputFilePath() {
        return workingDir + "/" + inputFileName;
    }

    public String getOutputFilePath() {
        return workingDir + "/" + outputFileName;
    }

    public String getWorkingDir() {
        return workingDir;
    }

    public String getResultFilePath() {
        return workingDir + "/" + resultFile;
    }

    public String getSrcName(boolean var) {
        String f = null;
        if (var) {
            f = srcFileName;
        } else {
            String[] strings = srcFileName.split("\\.");
            f = strings[0];
        }
        return f;
    }

    public long getTimeLimit() {
        return timeLimit;
    }

    public long getMemoryLimit() {
        return memoryLimit;
    }
}
