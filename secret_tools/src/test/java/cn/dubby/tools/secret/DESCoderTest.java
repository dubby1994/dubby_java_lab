package cn.dubby.tools.secret;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by teeyoung on 17/8/29.
 */
public class DESCoderTest {

    @Test
    public void test() throws Exception {
        String inputStr = "DES";
        String key = DESCoder.initKey();
        System.out.println("原文:\t" + inputStr);

        System.out.println("密钥:\t" + key);

        byte[] inputData = inputStr.getBytes();
        inputData = DESCoder.encrypt(inputData, key);

        System.out.println("加密后:\t" + DESCoder.encryptBASE64(inputData));

        byte[] outputData = DESCoder.decrypt(inputData, key);
        String outputStr = new String(outputData);

        System.out.println("解密后:\t" + outputStr);

        assertEquals(inputStr, outputStr);
    }

}
