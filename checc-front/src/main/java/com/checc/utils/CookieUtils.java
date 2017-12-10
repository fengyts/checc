package com.checc.utils;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import ng.bayue.util.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author wangyating
 *
 */	
public class CookieUtils implements Serializable{

	/**
	 * <pre>
	 * 
	 * </pre>
	 */
	private static final long serialVersionUID = 7952015536498577415L;

	private final Logger logger = LoggerFactory.getLogger(CookieUtils.class);
	
//	private  String sigkey = "sig";
    public static String cookieDelim1 = "%01"; 
    public static String cookieDelim2 = "%02"; 
//    private  String secretkey = "oidjfajsflkjsafoifiqii987321987432jsaijfoijsalkkz,mxv,mxznv"; 
    
    private  String checcDomain = ".checc.com";
    
//    private  String userDomain = "user.checc.com";

	public String getCheccDomain() {
		return checcDomain;
	}

	public void setCheccDomain(String checcDomain) {
		this.checcDomain = checcDomain;
	}

	/**
	 * 
	 * 设置cookie
	 *
	 * @param request
	 * @param response
	 * @param key
	 * @param value
	 * @param domain 设置cookie的作用域
	 * @param expireTime
	 */
	public void setCookie(HttpServletRequest request, 
			HttpServletResponse response, 
			String key,
			String value,
			String domain,
			int expireTime) {	
		Cookie [] cookies = request.getCookies();
		
		if(cookies != null && cookies.length > 0) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals(key)) {
					cookie.setValue(value);
					if(!StringUtils.isBlank(domain)){
						cookie.setDomain(domain);
						cookie.setPath("/");
					}
					
					response.addCookie(cookie);
					return;
				}			
				else {
					Cookie newCookie = new Cookie(key, value);
					newCookie.setMaxAge(expireTime);
					if(!StringUtils.isBlank(domain)){
						newCookie.setDomain(domain);
						newCookie.setPath("/");
					}
					response.addCookie(newCookie);
					return;
				}
			}
		}
	}

	/**
	 * Getting cookie's value by the given key. 
	 * @param request
	 * @param value, cookie's value
	 */
	public  String getCookieValue(HttpServletRequest request, 
			String key) {	
		Cookie [] cookies = request.getCookies();
		String value = "";
		if(cookies != null && cookies.length > 0) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals(key)) {
					value = cookie.getValue();
				}			
			}
		}
		return value;
	}
	
	public static Cookie [] getCookies(String cookies) throws UnsupportedEncodingException{
		if(StringUtils.isBlank(cookies)) return null;
		cookies = URLEncoder.encode(cookies,"UTF-8");
		
		if(!cookies.contains(cookieDelim1) || !cookies.contains(cookieDelim2)) return null;
		
		String [] part1 = cookies.split(cookieDelim1);
		if(null == part1 || part1.length == 0) return null;
		List<Cookie> list = new ArrayList<Cookie>();
		for (String cookie : part1) {
			String [] part2 = cookie.split(cookieDelim2);
			if(null == part2 || part2.length == 0) continue;;
			list.add(new Cookie(part2[0], part2[1]));
		}
		return list.toArray(new Cookie[list.size()]);
	}
	
	public  Cookie [] getUrlLoginCookies(String cookies) throws UnsupportedEncodingException{
		logger.info("cookies:"+cookies);
		if(StringUtils.isBlank(cookies)) return null;
		
		if(!cookies.contains(cookieDelim1) || !cookies.contains(cookieDelim2)) return null;
		String [] part1 = cookies.split(cookieDelim1);
		
		if(null == part1 || part1.length == 0) return null;
		List<Cookie> list = new ArrayList<Cookie>();
		for (String cookie : part1) {
			String [] part2 = cookie.split(cookieDelim2);
			if(null == part2 || part2.length == 0) continue;;
			list.add(new Cookie(part2[0], part2[1]));
		}
		return list.toArray(new Cookie[list.size()]);
	}
	
	public static String getCookie(Cookie [] cookies, 
			String key) {	
		String value = "";
		if(null != cookies  && cookies.length > 0) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals(key)) {
					value = cookie.getValue();
				}			
			}
		}
		return value;
	}
	
	
//	public static void main(String[] args) throws UnsupportedEncodingException {
//		String cookie1 = "u%02u9773060788%01t%021426485743%01sig%024ad9075e55453510e255c4e1889efc26%01ued%0275bdcabe60598036";
//		String cookie2 = "u\u0002u9773060788\u0001t\u00021426485743\u0001sig\u00024ad9075e55453510e255c4e1889efc26\u0001ued\u000275bdcabe60598036";
//		
//		System.out.println(URLEncoder.encode(cookie2,"UTF-8"));
//		System.out.println(cookie2);
//	}
	
}

