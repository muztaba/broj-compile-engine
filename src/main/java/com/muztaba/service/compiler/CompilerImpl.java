package com.muztaba.service.compiler;

import com.muztaba.entity.Submission;
import com.muztaba.entity.Verdict;
import com.muztaba.service.compiler.util.CompileStatus;
import com.muztaba.service.compiler.util.DTO;
import com.muztaba.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by seal on 8/21/16.
 */
@Service
public class CompilerImpl implements Compiler {

    private static final Logger logger = LoggerFactory.getLogger(CompilerImpl.class);

    private static final String WORKING_DIR = "/home/seal/test1/";
    private static final String FILE_NAME = "A.cpp";
    private static final String INPUT_FILE = "input.txt";
    private static final String OUTPUT_FILE_PATH = "output.txt";
    private static final String RESULT_FILE = "res.txt";
    private DTO dto;

    @Override
    public Verdict submit(Submission submission) {
        Engine engine = new Engine();

        makeDTO(submission);
        FileUtil.batchCreate(submission, dto);
        ProcessBuilder compile = ProcessBuilderFactory.getProcessBuilder(dto);
        CompileStatus compileStatus = engine.compile(compile);
        logger.info("Compile Status{}", compileStatus);

        ProcessBuilder execute = ProcessBuilderFactory.getExecutionProcessBuilder(dto);
        CompileStatus status = engine.execute(execute);
        logger.info("Execution Status {}", status);

        Verdict verdict = new Verdict();
        verdict.setCompileStatus(status);
        verdict.setSubmission(submission);
        return verdict;
    }

    private void makeDTO(Submission submission) {
        this.dto = new DTO(
                submission.getLang(),
                FILE_NAME,
                INPUT_FILE,
                OUTPUT_FILE_PATH,
                RESULT_FILE,
                WORKING_DIR + submission.getId()
        );
    }
}
