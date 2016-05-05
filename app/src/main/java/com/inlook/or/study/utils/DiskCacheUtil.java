package com.inlook.or.study.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;
import android.os.Environment;

import com.jakewharton.disklrucache.DiskLruCache;
import com.jakewharton.disklrucache.DiskLruCache.Editor;
import com.jakewharton.disklrucache.DiskLruCache.Snapshot;

public class DiskCacheUtil {

    private DiskLruCache mDiskLruCache;
    private static DiskCacheUtil mInstance;
    private Context mContext;
    private String cachePathName = "";
    private static final int APP_VERSION  = 1;
    private static final int VALUE_COUNT = 1;
    private static final long CACHE_SIZE = 10 *1024 * 1024;
    private static final int BUFFER_SIZE = 8*1024;
    private CompressFormat mCompressFormat = CompressFormat.JPEG;
    private int mCompressQuality = 100;
    
    private DiskCacheUtil( Context context){
        mContext = context;
        File directory = getDiskCacheDir(context, cachePathName);
        try {
            mDiskLruCache = DiskLruCache.open(directory, APP_VERSION, VALUE_COUNT, CACHE_SIZE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static DiskCacheUtil getInstance(Context context) {
        if(mInstance == null) {
            mInstance = new DiskCacheUtil(context);
        }
        return mInstance;
    }
    
    public void onDestory() {
        if(mDiskLruCache != null) {
            try {
                mDiskLruCache.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    private File getDiskCacheDir(Context context, String uniqueName) {
        boolean sdCardExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        String baseDir = "";
        if (sdCardExist) {
            baseDir = context.getExternalCacheDir().getPath();
            
            baseDir += File.separatorChar + context.getPackageName();
        } else {
            baseDir = context.getCacheDir().getPath();
        }
        // 创建文件夹
        File dir = new File(baseDir);
        if (dir != null && !dir.exists()) {
            dir.mkdirs();
        }

        return new File(baseDir + File.separator + uniqueName);
    }
    
    
    private String hashKeyForDisk(String key) {
        String cacheKey ;
        try {
            cacheKey = MD5Util.getMD5(key);
        } catch (NoSuchAlgorithmException e) {
          cacheKey = String.valueOf(key.hashCode());
        }
        return cacheKey;
    }
    
    
    public boolean writeBitmapToDisk(String key,Bitmap bitmap) {
        if(key  == null || bitmap == null) {
            return false;
        }
        String cacheKey = hashKeyForDisk(key);
        Editor editor = null;
        try {
            editor = mDiskLruCache.edit(cacheKey);
            if(write(editor,bitmap)) {
                editor.commit();
                mDiskLruCache.flush();
            }
            return true;
        } catch (IOException e) {
            try {
                editor.abort();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        return false;
    }
    
    private boolean write(Editor editor,Bitmap bitmap) throws IOException {
        OutputStream outputStream = null;
        try {
            outputStream = new BufferedOutputStream(editor.newOutputStream(0), BUFFER_SIZE);
            return bitmap.compress(mCompressFormat, mCompressQuality, outputStream);
        } finally {
            if(outputStream !=null) {
                outputStream.close();
            }
        }
    }
    
    
    
    public Bitmap readBitmapFromDisk(String key) {
        if(key  == null ) {
            return null;
        }
        String cacheKey = hashKeyForDisk(key);
        Snapshot snapshot = null;
        try {
            snapshot  = mDiskLruCache.get(cacheKey);
            if(snapshot != null) {
               return BitmapFactory.decodeStream(snapshot.getInputStream(0));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(snapshot != null) {
                snapshot.close();
            }
        }
        return null;
    }
}
