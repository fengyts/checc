package business;

import ng.bayue.util.crypto.AESUtils;

public class TestModifyPwd {
	
	private static AESUtils aes = new AESUtils();

	private static void test() {
		String pwd = "CXX2017￥qijiakeji";//RJa42+wUgdIkWrJACp8hCGLNb8mUvl8BCX03+fA/C7s=
		String pwd1 = "RJa42+wUgdIkWrJACp8hCGLNb8mUvl8BCX03+fA/C7s=";
		String encrypt = aes.encrypt(pwd);
		System.out.println(encrypt);
		String decrypt = aes.decrypt(pwd1);
		System.out.println(decrypt);
		
	}

	public static void main(String[] args) {
		test();
	}

}
