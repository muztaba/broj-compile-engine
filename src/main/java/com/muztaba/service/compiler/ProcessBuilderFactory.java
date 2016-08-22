package com.muztaba.service.compiler;


import com.muztaba.service.compiler.util.DTO;
import com.muztaba.service.compiler.util.Language;

import java.io.File;

/**
 * Created by seal on 8/9/16.
 */

public class ProcessBuilderFactory {

    public static ProcessBuilder getProcessBuilder(DTO dto) {
        ProcessBuilder processBuilder = null;
        switch ("CPP".toLowerCase()) {
            case Language.CPP :
                processBuilder = new ProcessBuilder("g++", dto.getSrcName(true), "-o", dto.getSrcName(false));
                break;
            case Language.JAVA :
                processBuilder = new ProcessBuilder("javac", dto.getSrcName(true));
                break;
        }
        processBuilder.directory(new File(dto.getWorkingDir()));
        return processBuilder;
    }

    public static ProcessBuilder getExecutionProcessBuilder(DTO dto) {
        ProcessBuilder processBuilder = null;

        switch ("CPP".toLowerCase()) {
            case Language.CPP :
                processBuilder = new ProcessBuilder("./" + dto.getSrcName(false));
                break;
            case Language.JAVA :
                processBuilder = new ProcessBuilder("java", "-cp", ".", dto.getSrcName(false));
                break;
        }

        processBuilder.directory(new File(dto.getWorkingDir()));
        processBuilder.redirectInput(new File(dto.getInputFilePath()));
        processBuilder.redirectOutput(new File(dto.getResultFilePath()));

        return processBuilder;
    }

}
