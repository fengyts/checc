package bean;

import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ng.bayue.util.StringUtils;

/**
 * 支付订单编号生成器 <deprecated>废弃的规则：生成规则：8 & yyyyMMddHHmmss & 4位流水号 & 3位随机数
 * 共22位数字</deprecated> 新规则：8 & 年份最后一位 & 当前时间毫秒数的后10位 & 4位流水号 & 3位随机数 共18位数字
 * 
 */
public class PayOrderNoGenerator {

	private static Logger logger = LoggerFactory.getLogger(PayOrderNoGenerator.class);

	/**
	 * 锁对象，可以为任意对象
	 */
	private static Object lockObj = "payOrderNoLock";
	/**
	 * 支付订单编号计数器
	 */
	private static long payOrderNoCount = 1l;

	private static String perStr = "8";

	/**
	 * 每秒生成支付订单编号数量最大值
	 */
	private int maxPerMSECSize = 10000;

	static {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		String yearLastNum = String.valueOf(cal.get(Calendar.YEAR)).substring(3);

		perStr += yearLastNum;
	}

	/**
	 * 生成支付订单编号
	 * 
	 */
	public String generatePayOrderNo() {
		try {
			// 最终生成的支付订单编号
			String finalOrderNo = "";

			synchronized (lockObj) {
				// long nowLong = Long.parseLong(DateUtils.formatDate(new
				// Date(), "yyyyMMddHHmmss"));
				Long nowLong = new Date().getTime();
				nowLong = Long.valueOf(nowLong.toString().substring(4));
				// 计数器到最大值归1，目前1秒处理峰值9999个
				if (payOrderNoCount >= maxPerMSECSize) {
					payOrderNoCount = 1L;
				}
				// 组装支付订单编号
				String countStr = maxPerMSECSize + payOrderNoCount + "";
				finalOrderNo = perStr + nowLong + countStr.substring(1)
						+ StringUtils.getRandomNum(3);
				payOrderNoCount++;
				logger.info("支付订单编号生成成功：" + finalOrderNo);
				return finalOrderNo;
			}
		} catch (Exception e) {
			logger.error("支付订单编号生成失败", e);
			return null;
		}
	}

	// 测试订单编号生成器
	private static void generatorTest() {
		try {
			for (int i = 0; i < 200; i++) {
				Thread t1 = new Thread(new Runnable() {
					public void run() {
						PayOrderNoGenerator bean = new PayOrderNoGenerator();
						bean.generatePayOrderNo();
					}
				}, "at" + i);
				t1.start();

				Thread t2 = new Thread(new Runnable() {
					public void run() {
						PayOrderNoGenerator bean = new PayOrderNoGenerator();
						bean.generatePayOrderNo();
					}
				}, "bt" + i);
				t2.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
//		 generatorTest();
	}
}
