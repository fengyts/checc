package com.checc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang3.StringEscapeUtils;

public class XSSHttpServletRequestWrapper extends HttpServletRequestWrapper {

	
	public XSSHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
	}
		
	@Override
	public String getHeader(String name) {
		return StringEscapeUtils.escapeHtml4(super.getHeader(name));
	}

	@Override
	public String getQueryString() {
		return StringEscapeUtils.escapeHtml4(super.getQueryString());
	}

	@Override
	public String getParameter(String name) {
		return StringEscapeUtils.escapeHtml4(super.getParameter(name));
	}

	@Override
	public String[] getParameterValues(String name) {
		String[] values = super.getParameterValues(name);
//		String value = StringUtil.valueOf(values);
//		try {
//			if (null != value 
//					&& !value.contains("<") 
//					&& !value.contains(">")
//					&& !StringUtil.decode(value).contains("<")
//					&& !StringUtil.decode(value).contains(">"))
//				return super.getParameterValues(name);

			if (values != null) {
				String[] escapseValues = new String[values.length];
				for (int i = 0; i < values.length; i++) {
					escapseValues[i] = StringEscapeUtils.escapeHtml4(values[i]).replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;");
//					escapseValues[i] = CharacterConverter.toChineseSign(values[i]);
//					escapseValues[i] = values[i].replaceAll("<", "& lt;").replaceAll(">", "& gt;").replaceAll("\\(", "& #40;").replaceAll("\\)", "& #41;");;
				}
				return escapseValues;
			}
//		} catch (UnsupportedEncodingException e) {
//			logger.error(e.getMessage(), e);
//		}
		return super.getParameterValues(name);
	}
}
