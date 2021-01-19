package com.shan.yellowpages.base.service.impl;

import com.shan.yellowpages.base.service.IKhTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

/**
 * 任务service
 * 需要注意，在runnable执行时，必须要将外层的khContext传入
 *
 * @author bruce
 */
@Service("khTaskService")
public class KhTaskServiceImpl implements IKhTaskService {

    private static Logger logger = LoggerFactory.getLogger(KhTaskServiceImpl.class);

    /**
     * 线程池
     */
    private ExecutorService executorService = Executors.newCachedThreadPool();

    /**
     * 执行runnable的线程任务
     * 需要注意，在runnable执行时，必须要将外层的khContext传入
     */
    @Override
    public void executeTask(Runnable runnable) {
        logger.debug("[KhTaskServiceImpl] [executeTask] entering");

        executorService.execute(runnable);
    }

    /**
     * 执行FutureTask的线程任务
     * 需要注意，在callable执行时，必须要将外层的khContext传入
     */
    @Override
    public <T> void executeFutureTask(FutureTask<T> futureTask) {
        logger.debug("[KhTaskServiceImpl] [executeCallable] entering");

        executorService.submit(futureTask);
    }

    /**
     * 执行callable的线程任务
     * 需要注意，在callable执行时，必须要将外层的khContext传入
     */
    @Override
    public <T> Future<T> executeCallable(Callable<T> callable) {
        logger.debug("[KhTaskServiceImpl] [executeCallable] entering");

        return executorService.submit(callable);
    }

}
