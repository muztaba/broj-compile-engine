package com.muztaba;

import com.muztaba.config.AppConfig;
import com.muztaba.model.Submission;
import com.muztaba.model.Verdict;
import com.muztaba.service.VerdictService;
import com.muztaba.service.VerdictServiceImpl;
import com.muztaba.service.compiler.Compiler;
import com.muztaba.service.compiler.CompilerImpl;
import com.muztaba.service.processor.QueueImpl;
import com.muztaba.service.processor.QueueService;
import com.muztaba.service.producer.MyTask;
import com.muztaba.service.producer.MyTaskImpl;
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
//        verdictService = context.getBean(VerdictServiceImpl.class);
        verdictService = (VerdictService) context.getBean("verdictServiceImpl");
//        verdictService = (VerdictService) context.getBean(VerdictServiceImpl.class.getName());
        while (true) {
            if (!queue.isEmpty()) {
                Submission submission = queue.get();
                Verdict verdict = compiler.submit(submission);
                verdictService.post(verdict);
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
