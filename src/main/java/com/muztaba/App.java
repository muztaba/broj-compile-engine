package com.muztaba;

import com.muztaba.config.AppConfig;
import com.muztaba.entity.Submission;
import com.muztaba.entity.Verdict;
import com.muztaba.service.entity.VerdictService;
import com.muztaba.service.compiler.Compiler;
import com.muztaba.service.producer.DummyProblem;
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

    @Autowired
    DummyProblem dummyProblem; //false data insert to problem table

    public static void main( String[] args ) {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        App app = context.getBean(App.class);
//        app.task();
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


}
