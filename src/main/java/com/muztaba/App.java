package com.muztaba;

import com.muztaba.config.AppConfig;
import com.muztaba.entity.Submission;
import com.muztaba.entity.Verdict;
import com.muztaba.service.entity.VerdictService;
import com.muztaba.service.compiler.Compiler;
import com.muztaba.service.producer.DummyProblem;
import com.muztaba.service.queue.QueueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.Future;

/**
 * Hello world!
 */
@Component
public class App {

    private static final Logger logger = LoggerFactory.getLogger(App.class);

    @Autowired
    QueueService<Submission> queue;

    @Autowired
    Compiler compiler;

    @Autowired
    VerdictService verdictService;

    @Autowired
    DummyProblem dummyProblem; //false data insert to problem table

    @Autowired
    CompletionService<Verdict> completionService;

    public static void main(String[] args) {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        App app = context.getBean(App.class);
        app.task();
    }

    boolean first = true;

    private void task() {
        int num = Runtime.getRuntime().availableProcessors() + 1;
        while (true) {

            while (first) {
                List<Submission> list = queue.getList(num);
                if (list.size() != 0) {
                    first = false;
                    for (Submission s : list) {
                        completionService.submit(new Callable<Verdict>() {
                            @Override
                            public Verdict call() throws Exception {
                                return compiler.submit(s);
                            }
                        });
                    }
                }
            }

            if (!first & !queue.isEmpty()) {
                Submission submission = queue.get();
                logger.info("Add to thread, {}", submission);
                completionService.submit(new Callable<Verdict>() {
                    @Override
                    public Verdict call() throws Exception {
                        return compiler.submit(submission);
                    }
                });
            }

            try {
                Future<Verdict> futureVerdict = completionService.take();
                Verdict verdict = futureVerdict.get();
                if (verdict != null) {
                    logger.info("Verdict Save {}", verdict);
                    verdictService.post(verdict);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
