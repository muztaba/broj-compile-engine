package com.muztaba.service.compiler;

import com.muztaba.model.Submission;
import com.muztaba.model.Verdict;


/**
 * Created by seal on 8/21/16.
 */
public interface Compiler {

    Verdict submit(Submission submission);

}
