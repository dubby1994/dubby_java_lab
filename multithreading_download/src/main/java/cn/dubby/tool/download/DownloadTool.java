package cn.dubby.tool.download;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by yangzheng03 on 2018/1/16.
 * https://www.dubby.cn/
 */
public class DownloadTool {

    private static String prefix = String.valueOf(System.currentTimeMillis());

    private static String path = "";

    public static void main(String[] args) {
        long startTimestamp = System.currentTimeMillis();

        if (args == null || args.length < 1) {
            System.out.println("please input the file url.");
            return;
        }
        final String urlString = args[0];

        if (args.length >= 2) {
            path = args[1];
        }

        int number = 10;

        if (args.length >= 3) {
            number = Integer.parseInt(args[2]);
        }

        System.out.println("Download started, url is \"" + urlString + "\"");

        ExecutorService threadPool = Executors.newFixedThreadPool(number);
        try {
            URL url = new URL(urlString);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("HEAD");

            String contentLengthStr = httpURLConnection.getHeaderField("Content-Length");
            long contentLength = Long.parseLong(contentLengthStr);

            if (contentLength > 1024 * 1024) {
                System.out.println("Content-Length\t" + (contentLength / 1024 / 1024) + "MB");
            } else if (contentLength > 1024) {
                System.out.println("Content-Length\t" + (contentLength / 1024) + "KB");
            } else {
                System.out.println("Content-Length\t" + (contentLength) + "B");
            }


            long tempLength = contentLength / number;
            long start = 0, end = -1;

            Map<Integer, Future<DownloadTemp>> futureMap = new HashMap<>(number);
            for (int i = 0; i < number; ++i) {
                start = end + 1;
                end = end + tempLength;

                if (i == number - 1) {
                    end = contentLength;
                }
                System.out.println("start:\t" + start + "\tend:\t" + end);
                DownloadThread thread = new DownloadThread(i, start, end, urlString);
                futureMap.put(i, threadPool.submit(thread));
            }
            System.out.println();

            String filename = urlString.substring(urlString.lastIndexOf("/") + 1);
            if (!path.equals("")) {
                filename = path + "/" + filename;
            }
            RandomAccessFile resultFile = new RandomAccessFile(filename, "rw");

            for (int i = 0; i < number; ++i) {
                Future<DownloadTemp> future = futureMap.get(i);
                DownloadTemp temp = future.get();
                RandomAccessFile tempFile = new RandomAccessFile(temp.filename, "r");
                tempFile.getChannel().transferTo(0, tempFile.length(), resultFile.getChannel());
            }

            threadPool.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }

        long completedTimestamp = System.currentTimeMillis();

        System.out.println();
        System.out.println("cost " + (completedTimestamp - startTimestamp) / 1000 + "s");
    }

    private static class DownloadThread implements Callable<DownloadTemp> {
        private int index;
        private String filename;
        private long start, end;
        private String urlString;

        DownloadThread(int index, long start, long end, String url) {
            this.urlString = url;
            this.index = index;
            this.start = start;
            this.end = end;
            if (path.equals("")) {
                this.filename = prefix + "-temp-" + index;
            } else {
                this.filename = path + "/" + prefix + "-temp-" + index;
            }

        }

        @Override
        public DownloadTemp call() throws Exception {
            URL url = new URL(urlString);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("Range", "bytes=" + start + "-" + end);

            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                inputStream = httpURLConnection.getInputStream();
                File file = new File(filename);
                outputStream = new FileOutputStream(file);

                while (true) {
                    byte[] bytes = new byte[10240];
                    int length = inputStream.read(bytes);
                    if (length <= 0) {
                        break;
                    }
                    outputStream.write(bytes, 0, length);
                }

                outputStream.flush();
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            }
            DownloadTemp downloadTemp = new DownloadTemp();
            downloadTemp.index = index;
            downloadTemp.filename = filename;
            System.out.println("thread\t" + index + "\tcompleted.");
            return downloadTemp;
        }
    }

    private static class DownloadTemp {
        private int index;
        private String filename;
    }

}
