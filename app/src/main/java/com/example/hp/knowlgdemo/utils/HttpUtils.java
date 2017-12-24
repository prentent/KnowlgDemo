package com.example.hp.knowlgdemo.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * http工具类
 */
public class HttpUtils {

    /**
     * @param path 下载的路径
     * @return 网络数据
     */
    public static byte[] download(String path) {
        byte[] ret = null;

        try {
            URL url = new URL(path);

            HttpURLConnection conn = (HttpURLConnection)
                    url.openConnection();

            conn.setRequestMethod("GET");

            conn.setConnectTimeout(10000);
            conn.setReadTimeout(10000);

            conn.connect();

            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {

                InputStream is = conn.getInputStream();


                ByteArrayOutputStream bos = new ByteArrayOutputStream();

                int len = 0;
                byte[] buffer = new byte[512];

                while (-1 != (len = is.read(buffer))) {
                    bos.write(buffer, 0, len);
                }

                //赋值给返回值
                ret = bos.toByteArray();

                is.close();
                bos.close();

            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return ret;
    }

}
