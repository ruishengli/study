package com.inlook.or.study.utils;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
/**
 * 内存管理
 * MemoryCacheUtil
 * @author or<br/>
 * description: TODO<br/>
 * create: 2015年7月1日 上午10:51:12<br/>
 *
 */
public class MemoryCacheUtil {

    private static MemoryCacheUtil mInstance;
    
    private final int maxMemory = (int)Runtime.getRuntime().maxMemory();
    
    private final int cacheSize = maxMemory / 8;
    
    private LruCache<String, Bitmap> mMemoryCache;
    
    public synchronized static MemoryCacheUtil getInstance() {
        if(mInstance == null) {
            mInstance  = new MemoryCacheUtil();
        }
        return mInstance;
    }
    
    private MemoryCacheUtil() {
        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getByteCount() / 1024;
            }
        };
    }
    
    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if(getBitmapFromMemCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }
    
    public Bitmap getBitmapFromMemCache(String key) {
        return mMemoryCache.get(key);
    }
    
}
