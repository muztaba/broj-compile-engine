package com.muztaba.service.producer;

import com.muztaba.entity.Problem;
import com.muztaba.service.entity.ProblemService;
import com.muztaba.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by seal on 8/25/16.
 */
@Service
public class DummyProblem {
    @Autowired
    ProblemService problemService;

    public void fileInject(int upTo) {
        String input = "/home/seal/test/input.txt";
        String res = "/home/seal/test/output.txt";
        for (int i = 0; i < upTo; i++) {
            int id = i + 1;
            Problem problem = new Problem();
            problem.setName("seal " + String.valueOf(id));
            problem.setTimeLimit(2000L);
            problem.setMemoryLimit(2000L);
            problem.setTime(new Date());
            problem.setInputFile(FileUtil.readFileAsByte(input));
            problem.setResultFile(FileUtil.readFileAsByte(res));
            problemService.post(problem);
        }
    }

}
