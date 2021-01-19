package com.shan.yellowpages.base.service;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * 任务service
 *
 * @author bruce
 */
public interface IKhTaskService {

    /**
     * 执行runnable的线程任务
     * 需要注意，在runnable执行时，必须要将外层的khContext传入
     * @param runnable 依赖倒转 实现runnable接口的子类
     */
    void executeTask(Runnable runnable);

    /**
     * 执行FutureTask的线程任务
     * @param futureTask 多线程任务
     */
    <T> void executeFutureTask(FutureTask<T> futureTask);

    /**
     * 执行callable的线程任务
     * 需要注意，在callable执行时，必须要将外层的khContext传入
     * @param callable 依赖倒转 实现callable接口的子类 可以有返回值
     */
    <T> Future<T> executeCallable(Callable<T> callable);

}
