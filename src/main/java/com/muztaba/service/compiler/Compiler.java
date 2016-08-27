package com.muztaba.service.compiler;

import com.muztaba.entity.Submission;
import com.muztaba.entity.Verdict;


/**
 * Created by seal on 8/21/16.
 */
public interface Compiler {

    Verdict submit(Submission submission);

}
