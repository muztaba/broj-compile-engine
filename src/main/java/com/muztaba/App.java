package com.muztaba;

import com.muztaba.config.AppConfig;
import com.muztaba.entity.Problem;
import com.muztaba.entity.Submission;
import com.muztaba.entity.Verdict;
import com.muztaba.service.entity.ProblemService;
import com.muztaba.service.entity.VerdictService;
import com.muztaba.service.compiler.Compiler;
import com.muztaba.service.compiler.CompilerImpl;
import com.muztaba.service.queue.QueueImpl;
import com.muztaba.service.queue.QueueService;
import com.muztaba.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Hello world!
 *
 */
@Component
public class App {

//    @Autowired
    QueueService<Submission> queue;

//    @Autowired
    Compiler compiler;

//    @Autowired
    VerdictService verdictService;

    public static void main( String[] args ) {
        new App().task();
    }

    private void task() {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        queue =  context.getBean(QueueImpl.class);
        compiler = context.getBean(CompilerImpl.class);
        verdictService = (VerdictService) context.getBean("verdictServiceImpl");
        while (true) {
            if (!queue.isEmpty()) {
                Submission submission = queue.get();
                Verdict verdict = compiler.submit(submission);
                verdictService.post(verdict);
            }
        }
    }

    @Autowired
    ProblemService problemService;
    private  void fileInject() {
        String input = "/home/seal/test/in.txt";
        String res = "/home/seal/test/out.txt";
        for (int i = 0; i < 5; i++) {
            int id = i + 1;
            Problem problem = problemService.get(id);
            problem.setInputFile(FileUtil.readFileAsByte(input));
            problem.setResultFile(FileUtil.readFileAsByte(res));
            problemService.post(problem);
        }
    }

}
