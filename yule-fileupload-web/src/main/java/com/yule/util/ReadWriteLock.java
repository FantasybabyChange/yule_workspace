package com.yule.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ReadWriteLock {
	
	private static Log logger = LogFactory.getLog(ReadWriteLock.class);
	// 读状态
	private boolean isRead;

	// 写状态
	private boolean isWrite;
	
	public synchronized void readLock() {
		// 有写入时读取线程停止
		while (isWrite) {
			try {
				System.out.println("有线程在进行写入,读取线程停止,进入等待状态");
				wait();
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}

		logger.info("设定锁为读取状态");
		isRead = true;
	}

	public synchronized void writeLock() {
		// 有读取时读取线程停止
		while (isRead) {
			try {
				logger.info("有线程在进行读取,写入线程停止,进入等待状态");
				wait();
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}

		// 有写入时写入线程也一样要停止
		while (isWrite) {
			try {
				logger.info("有线程在进行写入,写入线程停止,进入等待状态");
				wait();
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}

		logger.info("设定锁为写入状态");
		isWrite = true;
	}

	public synchronized void readUnlock() {
		logger.info("解除读取锁");
		isRead = false;
		notifyAll();
	}
	
	public synchronized void writeUnlock() {
		logger.info("解除写入锁");
		isWrite = false;
		notifyAll();
	}
}
