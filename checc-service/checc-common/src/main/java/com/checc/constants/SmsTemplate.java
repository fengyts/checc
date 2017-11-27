package com.checc.constants;

/**
 * <pre>
 * 聚合短信模板常量类
 * </pre>
 *
 * @author lenovopc
 * @version $Id: SmsTemplate.java, v 0.1 2017年11月27日 上午10:56:06 lenovopc Exp $
 */
public interface SmsTemplate {
	
	public static final String SMS_SP_CODE = "JuHe"; //短信供应商代码

	public static final String APP_KEY = "08c9e5a09b016b245f0cc2cc2781c179";
	

	public enum ContentTemplate {
		Register(52513, "注册"), Forgot_Password(52516, "找回密码");

		private Integer contentId; // 短信模板id, 聚合官网配置生成
		private String desc;

		private ContentTemplate(Integer contentId, String desc) {
			this.contentId = contentId;
			this.desc = desc;
		}

		public Integer getContentId() {
			return contentId;
		}

		public void setContentId(Integer contentId) {
			this.contentId = contentId;
		}

		public String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}

	}

}
