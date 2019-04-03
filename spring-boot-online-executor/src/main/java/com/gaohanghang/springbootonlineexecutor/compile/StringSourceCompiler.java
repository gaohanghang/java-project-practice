package com.gaohanghang.springbootonlineexecutor.compile;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaFileObject;

public class StringSourceCompiler {
    public static byte[] compile(String source, DiagnosticCollector<JavaFileObject> compileController) {
        return new byte[0];
    }
}
