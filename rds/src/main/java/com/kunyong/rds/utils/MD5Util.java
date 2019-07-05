package com.kunyong.rds.utils;


import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {
	
	private final static String key = "smartcy";
    /**
     * MD5方法
     * 
     * @param text 明文
     * @param key 密钥
     * @return 密文
     * @throws Exception
     */
    public static String md5(String text) {
        //加密后的字符串
        String encodeStr=DigestUtils.md5Hex(text + key);
        System.out.println("MD5加密后的字符串为:encodeStr="+encodeStr);
        return encodeStr;
       }

    /**
     * MD5验证方法
     * 
     * @param text 明文
     * @param key 密钥
     * @param md5 密文
     * @return true/false
     * @throws Exception
     */
    public static boolean verify(String text, String md5) throws Exception {
        //根据传入的密钥进行验证
        String md5Text = md5(text);
        if(md5Text.equalsIgnoreCase(md5)){
            System.out.println("MD5验证通过");
            return true;
        }
        return false;
    }
    
    
    public static void main(String []arg) {
    	try {
			MD5Util.md5("88888888");
			
			//5bd883cefacf2297c43c560feac08a11
			MD5Util.verify("666666", "5bd883cefacf2297c43c560feac08a11");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}