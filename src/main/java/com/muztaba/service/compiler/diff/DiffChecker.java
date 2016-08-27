package com.muztaba.service.compiler.diff;


import com.muztaba.service.compiler.util.CompileStatus;

/**
 * Created by seal on 8/3/16.
 */
public interface DiffChecker {

    CompileStatus check(String codeOutput, String output);
}
