package com.example.hp.knowlgdemo.utils;

import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 磁盘缓存的工具类
 */

public class DiskCacheUtils {

    private static DiskCacheUtils diskCacheUtils;
    //这里，就是存入的图片的文件夹的路径
    private static final String FOLDER_PATH =
            "1601xxx" + File.separator + "imgs";
    private final File forder;
    private static final String TAG = "==DiskCacheUtils";

    public static DiskCacheUtils getDiskCacheUtils() {
        if (diskCacheUtils == null)
            diskCacheUtils = new DiskCacheUtils();
        return diskCacheUtils;
    }

    /*
    初始化
     */
    private DiskCacheUtils() {
        if (!Environment.MEDIA_MOUNTED.
                equals(Environment.getExternalStorageState())) {
            //如果没有挂载，可以使用内部存储。
            //context.openFile();
            throw new IllegalStateException("SD卡未挂载");
        }

        //Return the user data directory.
        //这个方法，返回的文件，是 /data/ , 无法获取到读写权限。
        //File dir = Environment.getDataDirectory();
        File dir = Environment.getExternalStorageDirectory();

        //拼接文件夹绝对路径
        String folderPath = dir.getAbsolutePath()
                + File.separator + FOLDER_PATH;

        forder = new File(folderPath);

        if (!forder.exists() || forder.isFile()) {
            //如果文件不存在，获取文件存在，不是个文件夹，那么，需要新建文件夹
            forder.mkdirs();
            LogUtils.i(TAG, "创建成功了" + forder.getAbsolutePath());
        }
    }

    /*
    写入磁盘
     */
    public void put(String str, byte[] bytes) {
        String filePath = getFilePath(str);
        File file = new File(forder, filePath);
        BufferedOutputStream bos = null;
        FileOutputStream out = null;
        try {
            LogUtils.i(TAG, "写入磁盘:" + file.getAbsolutePath());
            out = new FileOutputStream(file);
            bos = new BufferedOutputStream(out);
            bos.write(bytes);
            bos.flush();
            LogUtils.i(TAG, "写入磁盘成功");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            LogUtils.e(TAG, "写入磁盘:" + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /*
    获取到文件数组
     */
    public byte[] get(String str) {
        byte[] ret = null;
        String filePath = getFilePath(str);
        //文件名为空则返回null
        if (TextUtils.isEmpty(filePath))
            return ret;
        File file = new File(forder, filePath);
        //如果不是文件
        if (!file.isFile())
            return ret;
        FileInputStream is = null;
        ByteArrayOutputStream bos = null;
        try {
            is = new FileInputStream(file);
            bos = new ByteArrayOutputStream();
            byte[] bur = new byte[512];
            int lenth = 0;
            while (-1 != (lenth = is.read(bur))) {
                bos.write(bur, 0, lenth);
            }
            ret = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return ret;
    }


    /*
    获取文件末尾的文件名
     */
    private String getFilePath(String str) {
        Uri parse = Uri.parse(str);
        return parse.getLastPathSegment();
    }

}
