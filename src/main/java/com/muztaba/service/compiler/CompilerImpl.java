package com.muztaba.service.compiler;

import com.muztaba.entity.Submission;
import com.muztaba.entity.Verdict;
import com.muztaba.service.compiler.diff.DiffChecker;
import com.muztaba.service.compiler.util.CompileStatus;
import com.muztaba.service.compiler.util.DTO;
import com.muztaba.service.compiler.util.Language;
import com.muztaba.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by seal on 8/21/16.
 */
@Service
public class CompilerImpl implements Compiler {

    private static final Logger logger = LoggerFactory.getLogger(CompilerImpl.class);

    @Value("${working.dir}")
    private String WORKING_DIR;
    @Value("${file.name.cpp}")
    private String FILE_NAME_CPP;
    @Value("${file.name.java}")
    private String FILE_NAME_JAVA;
    @Value("${file.name.input}")
    private String INPUT_FILE;
    @Value("${file.name.output}")
    private String OUTPUT_FILE;
    @Value("${file.name.result}")
    private String RESULT_FILE;

    @Autowired
    Engine engine;

    @Autowired
    DiffChecker diffChecker;

    private Submission submission;

    private DTO dto;

    @Override
    public Verdict submit(Submission submission) {
        this.submission = submission;
        init();
        boolean flow = true;
        CompileStatus status;
        ProcessBuilder compile = ProcessBuilderFactory.getProcessBuilder(dto);
        ProcessBuilder execute = ProcessBuilderFactory.getExecutionProcessBuilder(dto);

        status = engine.compile(compile);
        logger.info("Compile Status{}", status);
        if (status != CompileStatus.COMPILE_SUCCESS)
            flow = false;

        if (flow) {
            status = engine.execute(execute, dto);
            logger.info("Execution Status {}", status);
            if (status != CompileStatus.EXECUTION_SUCCESS)
                flow = false;
        }

        if (flow) {
            status = diffCheck();
            logger.info("Diff checker, {}", status);
        }

        Verdict verdict = new Verdict();
        verdict.setCompileStatus(status);
        verdict.setSubmission(submission);
        verdict.setExecutionMemory(0l);
        verdict.setExecutionTime(0l);
        return verdict;
    }

    private void init() {
        makeDTO();
        FileUtil.batchCreate(submission, dto);
    }

    private void makeDTO() {
        String fileName = Language.langCheck(submission.getLang()).equals(Language.CPP) ? FILE_NAME_CPP : FILE_NAME_JAVA;
        this.dto = new DTO(
                submission.getLang().toLowerCase(),
                fileName,
                INPUT_FILE,
                OUTPUT_FILE,
                RESULT_FILE,
                WORKING_DIR + submission.getId(),
                submission.getProblem().getTimeLimit(),
                submission.getProblem().getMemoryLimit()
        );
    }

    private CompileStatus diffCheck() {
        String codeResult = FileUtil.fileToString(dto.getResultFilePath());
        String judgeOutput = FileUtil.fileToString(dto.getOutputFilePath());
        return diffChecker.check(codeResult, judgeOutput);
    }

}
