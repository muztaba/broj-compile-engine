package com.muztaba.service.compiler.util;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

/**
 * Created by seal on 8/21/16.
 */
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class DTO {
    private final String lang;
    private final String srcPath;
    private final String inputPath;
    private final String outputFile;
    private final String resultFile;
    private final String workingDir;

    public String getLang() {
        return lang.toLowerCase();
    }

    public String getSrcPath() {
        return workingDir + "/" + srcPath;
    }

    public String getInputPath() {
        return workingDir + "/" + inputPath;
    }

    public String getOutputFile() {
        return workingDir + "/" + outputFile;
    }

    public String getWorkingDir() {
        return workingDir;
    }

    public String getResultFilePath() {
        return workingDir +"/" +resultFile;
    }

    public String getSrcName() {
        return srcPath;
    }
}
