package cn.dubby.zxing;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

/**
 * Created by teeyoung on 17/9/8.
 */
public class ZXingTools {

    public static boolean generateQRCode(String content, String fileName, int width, int height) {
        Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();

        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

        BitMatrix matrix = null;

        try {
            matrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
        } catch (WriterException e) {
            return false;
        }

        File file = new File(fileName);
        try {
            MatrixToImageWriter.writeToPath(matrix, "png", file.toPath());
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    public static String scanQRCode(String content, String fileName) {
        File file = new File(fileName);
        return null;
    }

    public static void main(String[] args) {
        String contents = "http://www.dubby.cn";
        System.out.println(ZXingTools.generateQRCode(contents, "qrcode.png", 500, 500));
    }
}
