package com.checc.wechatpay.util;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import ng.bayue.constants.CharsetConstant;
import ng.bayue.util.StringUtils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpSSLRequest {
	
	public static CloseableHttpClient registerSSL() throws NoSuchAlgorithmException,
			KeyManagementException {
		SSLContext sslContext = SSLContext.getInstance("TLS");
		X509TrustManager tm = new X509TrustManager() {
			public void checkClientTrusted(X509Certificate[] chain, String authType)
					throws java.security.cert.CertificateException {
			}

			public void checkServerTrusted(X509Certificate[] chain, String authType)
					throws java.security.cert.CertificateException {
				if ((chain == null) || (chain.length == 0)) {
					throw new IllegalArgumentException("证书链为空或长度为零");
				}
				if ((authType == null) || (authType.length() == 0)) {
					throw new IllegalArgumentException("认证类型为空或长度为零");
				}
				boolean br = false;
				Principal principal = null;
				for (X509Certificate x509Certificate : chain) {
					principal = x509Certificate.getSubjectX500Principal();
					if (principal != null) {
						br = true;
						return;
					}
				}
				if (!(br)) {
					throw new CertificateException("服务器证书验证失败");
				}
			}

			public X509Certificate[] getAcceptedIssuers() {
				return new X509Certificate[0];
			}
		};
		sslContext.init(null, new TrustManager[] { tm }, new SecureRandom());
		SSLConnectionSocketFactory sslcsf = new SSLConnectionSocketFactory(sslContext);

		return HttpClients.custom().setSSLSocketFactory(sslcsf).build();
	}
	
	public static String doRequest(String url, String data, String charset) throws Exception {
		HttpClient httpClient = registerSSL();
		HttpPost httpPost = new HttpPost(url);
		RequestConfig config = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(5000)
				.setConnectionRequestTimeout(5000).setExpectContinueEnabled(false).build();
		httpPost.setConfig(config);
		if (StringUtils.isBlank(charset)) {
			charset = CharsetConstant.UTF8;
		}
//		ContentType contentType = ContentType.create("text/plain", charset);
//		StringEntity stringEntity = new StringEntity(charset, contentType);
		StringEntity stringEntity = new StringEntity(data, charset);
		httpPost.setEntity(stringEntity);
		HttpResponse response = httpClient.execute(httpPost);
		HttpEntity entity = response.getEntity();
		String res = EntityUtils.toString(entity, charset);
		return res;
	}
	
}
