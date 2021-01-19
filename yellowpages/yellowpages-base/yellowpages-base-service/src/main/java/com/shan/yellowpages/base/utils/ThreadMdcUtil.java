//package com.shan.yellowpages.base.utils;
//
//import java.util.Map;
//import java.util.concurrent.BlockingQueue;
//import java.util.concurrent.Callable;
//import java.util.concurrent.ForkJoinPool;
//import java.util.concurrent.ForkJoinTask;
//import java.util.concurrent.Future;
//import java.util.concurrent.RejectedExecutionHandler;
//import java.util.concurrent.ThreadFactory;
//import java.util.concurrent.ThreadPoolExecutor;
//import java.util.concurrent.TimeUnit;
//
//import org.slf4j.MDC;
//import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
//import org.springframework.util.concurrent.ListenableFuture;
//
//import com.temujin.khaan.constants.ConstConfig;
//
///**
// * @author xuejw
// * @version 1.0
// * @className ThreadMdcUtil
// * @description TODO
// * @date 2019-05-30 15:27
// */
//public class ThreadMdcUtil {
//
//	/**
//	 * 设置跟踪ID,如果不存在，设置默认id
//	 */
//	public static void setTraceIdIfAbsent() {
//		if (MDC.get(ConstConfig.LOG_TRACE_ID) == null) {
//			setTraceId();
//		}
//	}
//
//	/**
//	 * 获取跟踪ID,如果不存在，构造默认id
//	 */
//	public static String getTraceIdIfAbsent() {
//		String trancId = MDC.get(ConstConfig.LOG_TRACE_ID);
//		if (trancId == null) {
//			return TraceLogUtils.getTraceId();
//		}
//		return trancId;
//	}
//
//	/**
//	 * 默认设置traceId
//	 *
//	 */
//	public static void setTraceId() {
//		setTraceId(TraceLogUtils.getTraceId());
//	}
//
//	/**
//	 * 移除设置的traceId
//	 *
//	 */
//	public static void removeTraceId() {
//		MDC.remove(ConstConfig.LOG_TRACE_ID);
//	}
//
//	/**
//	 * 自定义设置traceId
//	 *
//	 * @param traceId 追踪id
//	 */
//	public static void setTraceId(String traceId) {
//		MDC.put(ConstConfig.LOG_TRACE_ID, traceId);
//	}
//
//	public static <T> Callable<T> wrap(final Callable<T> callable, final Map<String, String> context) {
//		return () -> {
//			if (context == null) {
//				MDC.clear();
//			} else {
//				MDC.setContextMap(context);
//			}
//			setTraceIdIfAbsent();
//			try {
//				return callable.call();
//			} finally {
//				MDC.clear();
//			}
//		};
//	}
//
//	public static Runnable wrap(final Runnable runnable, final Map<String, String> context) {
//		return () -> {
//			if (context == null) {
//				MDC.clear();
//			} else {
//				MDC.setContextMap(context);
//			}
//			setTraceIdIfAbsent();
//			try {
//				runnable.run();
//			} finally {
//				MDC.clear();
//			}
//		};
//	}
//
//	public static class ThreadPoolTaskExecutorMdcWrapper extends ThreadPoolTaskExecutor {
//		@Override
//		public void execute(Runnable task) {
//			super.execute(ThreadMdcUtil.wrap(task, MDC.getCopyOfContextMap()));
//		}
//
//		@Override
//		public void execute(Runnable task, long startTimeout) {
//			super.execute(ThreadMdcUtil.wrap(task, MDC.getCopyOfContextMap()), startTimeout);
//		}
//
//		@Override
//		public <T> Future<T> submit(Callable<T> task) {
//			return super.submit(ThreadMdcUtil.wrap(task, MDC.getCopyOfContextMap()));
//		}
//
//		@Override
//		public Future<?> submit(Runnable task) {
//			return super.submit(ThreadMdcUtil.wrap(task, MDC.getCopyOfContextMap()));
//		}
//
//		@Override
//		public ListenableFuture<?> submitListenable(Runnable task) {
//			return super.submitListenable(ThreadMdcUtil.wrap(task, MDC.getCopyOfContextMap()));
//		}
//
//		@Override
//		public <T> ListenableFuture<T> submitListenable(Callable<T> task) {
//			return super.submitListenable(ThreadMdcUtil.wrap(task, MDC.getCopyOfContextMap()));
//		}
//	}
//
//	public static class ThreadPoolExecutorMdcWrapper extends ThreadPoolExecutor {
//		public ThreadPoolExecutorMdcWrapper(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
//				BlockingQueue<Runnable> workQueue) {
//			super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
//		}
//
//		public ThreadPoolExecutorMdcWrapper(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
//				BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
//			super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
//		}
//
//		public ThreadPoolExecutorMdcWrapper(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
//				BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
//			super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
//		}
//
//		public ThreadPoolExecutorMdcWrapper(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
//				BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
//			super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
//		}
//
//		@Override
//		public void execute(Runnable task) {
//			super.execute(ThreadMdcUtil.wrap(task, MDC.getCopyOfContextMap()));
//		}
//
//		@Override
//		public <T> Future<T> submit(Runnable task, T result) {
//			return super.submit(ThreadMdcUtil.wrap(task, MDC.getCopyOfContextMap()), result);
//		}
//
//		@Override
//		public <T> Future<T> submit(Callable<T> task) {
//			return super.submit(ThreadMdcUtil.wrap(task, MDC.getCopyOfContextMap()));
//		}
//
//		@Override
//		public Future<?> submit(Runnable task) {
//			return super.submit(ThreadMdcUtil.wrap(task, MDC.getCopyOfContextMap()));
//		}
//	}
//
//	public static class ForkJoinPoolMdcWrapper extends ForkJoinPool {
//		public ForkJoinPoolMdcWrapper() {
//			super();
//		}
//
//		public ForkJoinPoolMdcWrapper(int parallelism) {
//			super(parallelism);
//		}
//
//		public ForkJoinPoolMdcWrapper(int parallelism, ForkJoinWorkerThreadFactory factory,
//				Thread.UncaughtExceptionHandler handler, boolean asyncMode) {
//			super(parallelism, factory, handler, asyncMode);
//		}
//
//		@Override
//		public void execute(Runnable task) {
//			super.execute(ThreadMdcUtil.wrap(task, MDC.getCopyOfContextMap()));
//		}
//
//		@Override
//		public <T> ForkJoinTask<T> submit(Runnable task, T result) {
//			return super.submit(ThreadMdcUtil.wrap(task, MDC.getCopyOfContextMap()), result);
//		}
//
//		@Override
//		public <T> ForkJoinTask<T> submit(Callable<T> task) {
//			return super.submit(ThreadMdcUtil.wrap(task, MDC.getCopyOfContextMap()));
//		}
//	}
//}
