package com.isoftstone.finance.cwgsapp.external.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.File;
import java.lang.ref.SoftReference;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WebImageCache
{
  private static final String DISK_CACHE_PATH = "/web_image_cache/";
  private boolean diskCacheEnabled = false;
  private String diskCachePath;
  private ConcurrentHashMap<String, SoftReference<Bitmap>> memoryCache = new ConcurrentHashMap();
  private ExecutorService writeThread;

  public WebImageCache(Context paramContext)
  {
    Context localContext = paramContext.getApplicationContext();
    this.diskCachePath = (localContext.getCacheDir().getAbsolutePath() + "/web_image_cache/");
    File localFile = new File(this.diskCachePath);
    localFile.mkdirs();
    this.diskCacheEnabled = localFile.exists();
    this.writeThread = Executors.newSingleThreadExecutor();
  }

  private void cacheBitmapToDisk(final String paramString, final Bitmap paramBitmap)
  {
    this.writeThread.execute(new Runnable()
    {
      // ERROR //
      public void run()
      {
        // Byte code:
        //   0: aload_0
        //   1: getfield 21	com/example/dell/zhapp/external/imageloader/WebImageCache$1:this$0	Lcom/example/dell/zhapp/external/imageloader/WebImageCache;
        //   4: invokestatic 37	com/example/dell/zhapp/external/imageloader/WebImageCache:access$000	(Lcom/example/dell/zhapp/external/imageloader/WebImageCache;)Z
        //   7: ifeq +74 -> 81
        //   10: aconst_null
        //   11: astore_1
        //   12: new 39	java/io/BufferedOutputStream
        //   15: dup
        //   16: new 41	java/io/FileOutputStream
        //   19: dup
        //   20: new 43	java/io/File
        //   23: dup
        //   24: aload_0
        //   25: getfield 21	com/example/dell/zhapp/external/imageloader/WebImageCache$1:this$0	Lcom/example/dell/zhapp/external/imageloader/WebImageCache;
        //   28: invokestatic 47	com/example/dell/zhapp/external/imageloader/WebImageCache:access$100	(Lcom/example/dell/zhapp/external/imageloader/WebImageCache;)Ljava/lang/String;
        //   31: aload_0
        //   32: getfield 21	com/example/dell/zhapp/external/imageloader/WebImageCache$1:this$0	Lcom/example/dell/zhapp/external/imageloader/WebImageCache;
        //   35: aload_0
        //   36: getfield 23	com/example/dell/zhapp/external/imageloader/WebImageCache$1:val$url	Ljava/lang/String;
        //   39: invokestatic 51	com/example/dell/zhapp/external/imageloader/WebImageCache:access$200	(Lcom/example/dell/zhapp/external/imageloader/WebImageCache;Ljava/lang/String;)Ljava/lang/String;
        //   42: invokespecial 54	java/io/File:<init>	(Ljava/lang/String;Ljava/lang/String;)V
        //   45: invokespecial 57	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
        //   48: sipush 2048
        //   51: invokespecial 60	java/io/BufferedOutputStream:<init>	(Ljava/io/OutputStream;I)V
        //   54: astore_2
        //   55: aload_0
        //   56: getfield 25	com/example/dell/zhapp/external/imageloader/WebImageCache$1:val$bitmap	Landroid/graphics/Bitmap;
        //   59: getstatic 66	android/graphics/Bitmap$CompressFormat:PNG	Landroid/graphics/Bitmap$CompressFormat;
        //   62: bipush 100
        //   64: aload_2
        //   65: invokevirtual 72	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
        //   68: pop
        //   69: aload_2
        //   70: ifnull +11 -> 81
        //   73: aload_2
        //   74: invokevirtual 75	java/io/BufferedOutputStream:flush	()V
        //   77: aload_2
        //   78: invokevirtual 78	java/io/BufferedOutputStream:close	()V
        //   81: return
        //   82: astore_3
        //   83: aload_3
        //   84: invokevirtual 81	java/io/FileNotFoundException:printStackTrace	()V
        //   87: aload_1
        //   88: ifnull -7 -> 81
        //   91: aload_1
        //   92: invokevirtual 75	java/io/BufferedOutputStream:flush	()V
        //   95: aload_1
        //   96: invokevirtual 78	java/io/BufferedOutputStream:close	()V
        //   99: return
        //   100: astore 6
        //   102: return
        //   103: astore 4
        //   105: aload_1
        //   106: ifnull +11 -> 117
        //   109: aload_1
        //   110: invokevirtual 75	java/io/BufferedOutputStream:flush	()V
        //   113: aload_1
        //   114: invokevirtual 78	java/io/BufferedOutputStream:close	()V
        //   117: aload 4
        //   119: athrow
        //   120: astore 5
        //   122: goto -5 -> 117
        //   125: astore 4
        //   127: aload_2
        //   128: astore_1
        //   129: goto -24 -> 105
        //   132: astore_3
        //   133: aload_2
        //   134: astore_1
        //   135: goto -52 -> 83
        //   138: astore 8
        //   140: return
        //
        // Exception table:
        //   from	to	target	type
        //   12	55	82	java/io/FileNotFoundException
        //   91	99	100	java/io/IOException
        //   12	55	103	finally
        //   83	87	103	finally
        //   109	117	120	java/io/IOException
        //   55	69	125	finally
        //   55	69	132	java/io/FileNotFoundException
        //   73	81	138	java/io/IOException
      }
    });
  }

  private void cacheBitmapToMemory(String paramString, Bitmap paramBitmap)
  {
    this.memoryCache.put(getCacheKey(paramString), new SoftReference(paramBitmap));
  }

  private Bitmap getBitmapFromDisk(String paramString)
  {
    boolean bool1 = this.diskCacheEnabled;
    Bitmap localBitmap = null;
    if (bool1)
    {
      String str = getFilePath(paramString);
      boolean bool2 = new File(str).exists();
      localBitmap = null;
      if (bool2)
        localBitmap = BitmapFactory.decodeFile(str);
    }
    return localBitmap;
  }

  private Bitmap getBitmapFromMemory(String paramString)
  {
    SoftReference localSoftReference = (SoftReference)this.memoryCache.get(getCacheKey(paramString));
    Bitmap localBitmap = null;
    if (localSoftReference != null)
      localBitmap = (Bitmap)localSoftReference.get();
    return localBitmap;
  }

  private String getCacheKey(String paramString)
  {
    if (paramString == null)
      throw new RuntimeException("Null url passed in");
    return paramString.replaceAll("[.:/,%?&=]", "+").replaceAll("[+]+", "+");
  }

  private String getFilePath(String paramString)
  {
    return this.diskCachePath + getCacheKey(paramString);
  }

  public void clear()
  {
    this.memoryCache.clear();
    File localFile1 = new File(this.diskCachePath);
    if ((localFile1.exists()) && (localFile1.isDirectory()))
      for (File localFile2 : localFile1.listFiles())
        if ((localFile2.exists()) && (localFile2.isFile()))
          localFile2.delete();
  }

  public Bitmap get(String paramString)
  {
    Bitmap localBitmap = getBitmapFromMemory(paramString);
    if (localBitmap == null)
    {
      localBitmap = getBitmapFromDisk(paramString);
      if (localBitmap != null)
        cacheBitmapToMemory(paramString, localBitmap);
    }
    return localBitmap;
  }

  public void put(String paramString, Bitmap paramBitmap)
  {
    cacheBitmapToMemory(paramString, paramBitmap);
    cacheBitmapToDisk(paramString, paramBitmap);
  }

  public void remove(String paramString)
  {
    if (paramString == null);
    File localFile;
    do
    {
      this.memoryCache.remove(getCacheKey(paramString));
      localFile = new File(this.diskCachePath, getCacheKey(paramString));
    }
    while ((!localFile.exists()) || (!localFile.isFile()));
    localFile.delete();
  }
}