package com.muztaba;

import com.muztaba.config.AppConfig;
import com.muztaba.model.Problem;
import com.muztaba.model.Submission;
import com.muztaba.service.ProblemService;
import com.muztaba.service.compiler.Compiler;
import com.muztaba.service.compiler.CompilerImpl;
import com.muztaba.service.processor.QueueImpl;
import com.muztaba.service.processor.QueueService;
import com.muztaba.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Hello world!
 *
 */
@Component
public class App {

    QueueService<Submission> queue;

    Compiler compiler;

    public static void main( String[] args ) {
        new App().task();
    }

    private void task() {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        queue =  context.getBean(QueueImpl.class);
        compiler = context.getBean(CompilerImpl.class);
        while (true) {
            if (!queue.isEmpty()) {
                Submission submission = queue.get();
                compiler.submit(submission);
            }
        }
    }


/*    private  void fileInject() {
        String input = "/home/seal/test/in.txt";
        String res = "/home/seal/test/out.txt";
        for (int i = 0; i < 5; i++) {
            int id = i + 1;
            Problem problem = problemService.get(id);
            problem.setInputFile(FileUtil.readFileAsByte(input));
            problem.setResultFile(FileUtil.readFileAsByte(res));
            problemService.post(problem);
        }
    }*/

}
