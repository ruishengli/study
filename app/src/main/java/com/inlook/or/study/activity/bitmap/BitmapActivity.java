package com.inlook.or.study.activity.bitmap;

import java.lang.ref.WeakReference;


import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

import com.inlook.or.study.R;

public class BitmapActivity extends Activity {

    private static final String TAG = BitmapActivity.class.getSimpleName();
    private Bitmap mPlaceHolderBitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout view = new LinearLayout(this);
        view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        view.setOrientation(LinearLayout.VERTICAL);
        view.setGravity(Gravity.CENTER);
        setContentView(view);
        
        ImageView imageView = new ImageView(this);
      
        imageView.setLayoutParams(new LayoutParams(300, 300));
        imageView.setScaleType(ScaleType.FIT_CENTER);
        view.addView(imageView);
        
        mPlaceHolderBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.album_pictures_no);
    }
    
    
    
    private Bitmap decodeSampledBitmapFromResource(Resources resource,int resId,int reqWidth,int reqHeight) {
        BitmapFactory.Options optios = new BitmapFactory.Options();
        optios.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), resId, optios);
        /**
        int w = optios.outWidth;
        int h = optios.outHeight;
        String type = optios.outMimeType;
        **/
        
      int inSampleSize =  calculateInSampleSize(optios,reqWidth,reqHeight);
      optios.inJustDecodeBounds = false;
      optios.inSampleSize = inSampleSize;
      Log.d(TAG, "inSampleSize:"+inSampleSize);
      return BitmapFactory.decodeResource(getResources(), resId, optios);
    }
    /**
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    private int  calculateInSampleSize(BitmapFactory.Options options ,int reqWidth,int reqHeight) {
        int width = options.outWidth;
        int height = options.outHeight;
        int inSampleSize = 1;
        if(width > reqWidth || height > reqHeight) {
            final int halfHeight = height/2;
            final int halfWidth = width/2;
            
            while(halfHeight / inSampleSize>reqHeight && halfWidth /inSampleSize >reqWidth ) {
                inSampleSize *= 2;
            }
        }
        
        return inSampleSize;
    }
    
    
     class BitmapLoadTask  extends AsyncTask<Integer , Integer, Bitmap> {
         private int data;
         private final WeakReference<ImageView> imageViewReference;
         public BitmapLoadTask(ImageView imageView) {
             imageViewReference = new WeakReference<ImageView>(imageView);
         }
        @Override
        protected Bitmap doInBackground(Integer... params) {
            data = params[0];
            return decodeSampledBitmapFromResource(getResources(),data,300,300);
        }
         
        @Override
        protected void onPostExecute(Bitmap result) {
            if(isCancelled()) {
                result = null;
            }
            if(imageViewReference != null && result != null) {
                
                final ImageView imageView = imageViewReference.get();
                final BitmapLoadTask bitmapWorkerTask =  getBitmapWorkerTask(imageView);
                       
                if (this == bitmapWorkerTask && imageView != null) {
                    imageView.setImageBitmap(result);
                }
            }
        }
     }
     
     static class AsyncDrawable extends BitmapDrawable {
         
         private WeakReference<BitmapLoadTask> bitmapLoadTaskReference;
         
         public AsyncDrawable(Resources res,Bitmap bitmap,BitmapLoadTask bitmapLoadTask) {
             super(res, bitmap);
             bitmapLoadTaskReference = new WeakReference<BitmapLoadTask>(bitmapLoadTask);
         }
         
         public BitmapLoadTask getBitmapLoadTask() {
             return bitmapLoadTaskReference.get();
         }
     }
     
     public void loadBitmap(int resId,ImageView imageView) {
         if(cancelPotentialWork(resId, imageView)) {
             BitmapLoadTask bitmapLoadTask = new BitmapLoadTask(imageView);
             final AsyncDrawable asyncDrawable =
                     new AsyncDrawable(getResources(), mPlaceHolderBitmap, bitmapLoadTask);
             imageView.setImageDrawable(asyncDrawable);
             bitmapLoadTask.execute(resId);
         }
        
     }
     
     public static boolean cancelPotentialWork(int data, ImageView imageView) {
         final BitmapLoadTask bitmapWorkerTask = getBitmapWorkerTask(imageView);
         
         if(bitmapWorkerTask != null) {
             if(data == 0 || data != bitmapWorkerTask.data) {
                 bitmapWorkerTask.cancel(true);
             } else {
                 return false;
             }
         }
         return true;
     }
     
     private static BitmapLoadTask getBitmapWorkerTask(ImageView imageView) {
         if(imageView != null) {
             final Drawable drawable = imageView.getDrawable();
             if(drawable instanceof AsyncDrawable) {
                 final AsyncDrawable asyncDrawable =  (AsyncDrawable)drawable;
                 return asyncDrawable.getBitmapLoadTask();
             }
         }
         
         return null;
     }
}
