package com.shan.yellowpages.base.ssl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;

/**
 * JksSslContextFactory的工厂类
 * 通常用于websocket
 * 参考了： https://www.cnblogs.com/guogangj/p/5209330.html
 * 
 * @author bruce
 *
 */
public final class JksSslContextFactory implements FactoryBean<SSLContext>{
	
	private final Logger logger = LoggerFactory.getLogger(JksSslContextFactory.class);
	
	private String jksFilePath;
	
	private String keyPassword;//密钥的访问密码
	
	private String storePassword;//密钥库的访问密码

	@Override
	public SSLContext getObject() throws Exception {
		logger.debug("[JksSslContextFactory][getObject]entering, keyPassword:{}, storePassword:{}, jksFilePath:{}", keyPassword, storePassword, jksFilePath);
		
		KeyStore ks = KeyStore.getInstance("JKS");
		InputStream ksInputStream = new FileInputStream(new File(jksFilePath));
		ks.load(ksInputStream, keyPassword.toCharArray());
		KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
		kmf.init(ks, storePassword.toCharArray());
		
		SSLContext sslContext = SSLContext.getInstance("TLS");
		sslContext.init(kmf.getKeyManagers(), null, null);
		
		return sslContext;
	}

	@Override
	public Class<SSLContext> getObjectType() {
		return SSLContext.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	public String getJksFilePath() {
		return jksFilePath;
	}

	public void setJksFilePath(String jksFilePath) {
		this.jksFilePath = jksFilePath;
	}

	public String getKeyPassword() {
		return keyPassword;
	}

	public void setKeyPassword(String keyPassword) {
		this.keyPassword = keyPassword;
	}

	public String getStorePassword() {
		return storePassword;
	}

	public void setStorePassword(String storePassword) {
		this.storePassword = storePassword;
	}


}