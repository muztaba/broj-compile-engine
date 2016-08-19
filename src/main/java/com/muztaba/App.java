package com.muztaba;

import com.muztaba.config.AppConfig;
import com.muztaba.model.Problem;
import com.muztaba.service.ProblemService;
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

    @Autowired
    ProblemService problemService;

    public static void main( String[] args ) {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
    }

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
