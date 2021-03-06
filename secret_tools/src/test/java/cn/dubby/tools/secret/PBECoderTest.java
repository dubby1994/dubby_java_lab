package cn.dubby.tools.secret;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by teeyoung on 17/8/29.
 */
public class PBECoderTest {

    @Test
    public void test() throws Exception {
        String inputStr = "abc";
        System.out.println("原文: " + inputStr);
        byte[] input = inputStr.getBytes();

        String pwd = "efg";
        System.out.println("密码: " + pwd);

        byte[] salt = PBECoder.initSalt();

        byte[] data = PBECoder.encrypt(input, pwd, salt);

        System.out.println("加密后: " + PBECoder.encryptBASE64(data));

        byte[] output = PBECoder.decrypt(data, pwd, salt);
        String outputStr = new String(output);

        System.out.println("解密后: " + outputStr);
        assertEquals(inputStr, outputStr);
    }

}
