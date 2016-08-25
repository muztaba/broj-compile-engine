package com.muztaba;

import com.muztaba.config.AppConfig;
import com.muztaba.entity.Submission;
import com.muztaba.entity.Verdict;
import com.muztaba.service.entity.VerdictService;
import com.muztaba.service.compiler.Compiler;
import com.muztaba.service.queue.QueueService;
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

    @Autowired
    QueueService<Submission> queue;

    @Autowired
    Compiler compiler;

    @Autowired
    VerdictService verdictService;

    public static void main( String[] args ) {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        App app = context.getBean(App.class);
        app.task();
    }

    private void task() {
        while (true) {
            if (!queue.isEmpty()) {
                Submission submission = queue.get();
                Verdict verdict = compiler.submit(submission);
                verdictService.post(verdict);
            }
        }
    }

/*//    @Autowired
    ProblemService problemService;
    private  void fileInject() {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        problemService = (ProblemService) context.getBean("problemImpl");
        String input = "/home/seal/test/input.txt";
        String res = "/home/seal/test/output.txt";
        for (int i = 0; i < 5; i++) {
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
    }*/

}
