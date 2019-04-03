package com.gaohanghang.springbootonlineexecutor.service;

import com.gaohanghang.springbootonlineexecutor.compile.StringSourceCompiler;
import com.gaohanghang.springbootonlineexecutor.execute.JavaClassExecutor;
import org.springframework.stereotype.Service;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaFileObject;
import java.util.List;
import java.util.concurrent.*;

@Service
public class ExecuteStringSourceService {
    /* 客户端发来的程序的运行时间限制 */
    private static final int RUN_TIME_LIMITED = 15;

    /* N_THREAD = N_CPU + 1，因为是 CPU 密集型的操作 */
    private static final int N_THREAD = 5;

    private static final ExecutorService poll = new ThreadPoolExecutor(N_THREAD, N_THREAD,
            60L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(N_THREAD));

    private static final String WAIT_WARNING = "服务器忙，请稍后提交";

    private static final String NO_OUTPUT = "Noting.";

    public String execute(String source) {
        DiagnosticCollector<JavaFileObject> compileController = new DiagnosticCollector<>(); // 编译结果收集器

        // 编译源代码
        byte[] classBytes = StringSourceCompiler.compile(source, compileController);

        // 编译不通过，获取并返回编译错误信息
        if (classBytes == null) {
            // 获取编译错误信息
            List<Diagnostic<? extends JavaFileObject>> compileError =  compileController.getDiagnostics(); // 获取编译错误信息的list
            StringBuilder compileErrorRes = new StringBuilder();
            for (Diagnostic diagnostic : compileError) {
                compileErrorRes.append("Compilation error at ");
                compileErrorRes.append(diagnostic.getLineNumber());
                compileErrorRes.append(".");
                compileErrorRes.append(System.lineSeparator());
            }
            return compileErrorRes.toString();
        }

        // 运行字节码的main方法
        Callable<String> runTask = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return JavaClassExecutor.execute(classBytes);
            }
        };

        Future<String> res = null;
        try {
            res = poll.submit(runTask);
        } catch (RejectedExecutionException e) {
            return WAIT_WARNING;
        }

        // 获取运行结果，处理非客户端代码错误
        String runResult;
        try {
            runResult = res.get(RUN_TIME_LIMITED, TimeUnit.SECONDS); // get 方法 用来返回和 Future 关联的任务的结果
        } catch (InterruptedException e) {
            runResult = "Program interrupted";
        } catch (ExecutionException e) {
            runResult = e.getCause().getMessage();
        } catch (TimeoutException e) {
            runResult = "Time Limit Exceeded"; // 超出时限
        } finally {
            res.cancel(true); // 该方法便可以用来（尝试）终止一个任务。
        }
        return runResult != null ? runResult : NO_OUTPUT;
    }
}
