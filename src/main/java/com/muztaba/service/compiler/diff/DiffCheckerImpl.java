package com.muztaba.service.compiler.diff;

import com.muztaba.service.compiler.util.CompileStatus;
import org.bitbucket.cowwoc.diffmatchpatch.DiffMatchPatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by seal on 8/3/16.
 */
@Service
public class DiffCheckerImpl implements DiffChecker {

    private static final Logger logger = LoggerFactory.getLogger(DiffCheckerImpl.class);

    private static final String REGRX = "(?m)(^\\s+|[\\t\\f ](?=[\\t\\f ])|[\\t\\f ]$|\\s+\\z)";

    @Override
    public CompileStatus check(String codeOutput, String output) {
        codeOutput = codeOutput.replaceAll(REGRX, "").trim();
        output = output.trim();
        DiffMatchPatch dmp = new DiffMatchPatch();
        CompileStatus status;

        List<DiffMatchPatch.Diff> list = dmp.diffMain(codeOutput, output);
        if (list.size() == 1 && list.get(0).operation == DiffMatchPatch.Operation.EQUAL)
            status = CompileStatus.ACCEPTED;
        else
            status = CompileStatus.WRONG;

        return status;
    }
}
