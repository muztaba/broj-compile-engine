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
                processBuilder = new ProcessBuilder("g++", dto.getSrcName(), "-o", "A");
                break;
            case Language.JAVA :
//                processBuilder = new ProcessBuilder("javac", fileName);
                break;
        }
        processBuilder.directory(new File(dto.getWorkingDir()));
        return processBuilder;
    }

    public static ProcessBuilder getExecutionProcessBuilder(DTO dto) {
        ProcessBuilder processBuilder = null;

        switch ("CPP".toLowerCase()) {
            case Language.CPP :
                processBuilder = new ProcessBuilder("./" + "A");
                break;
            case Language.JAVA :
//                processBuilder = new ProcessBuilder("java", "-cp", ".", fileName);
                break;
        }

        processBuilder.directory(new File(dto.getWorkingDir()));
        processBuilder.redirectInput(new File(dto.getInputPath()));
        processBuilder.redirectOutput(new File(dto.getResultFilePath()));

        return processBuilder;
    }

}
