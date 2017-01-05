package com.isoftstone.finance.cwgsapp.external.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class WebImage
  implements SmartImage
{
  private static final int CONNECT_TIMEOUT = 5000;
  private static final int READ_TIMEOUT = 10000;
  private static TrustManager myX509TrustManager = new X509TrustManager()
  {
    public void checkClientTrusted(X509Certificate[] paramAnonymousArrayOfX509Certificate, String paramAnonymousString)
      throws CertificateException
    {
    }

    public void checkServerTrusted(X509Certificate[] paramAnonymousArrayOfX509Certificate, String paramAnonymousString)
      throws CertificateException
    {
    }

    public X509Certificate[] getAcceptedIssuers()
    {
      return null;
    }
  };
  private static WebImageCache webImageCache;
  private String url;

  public WebImage(String paramString)
  {
    this.url = paramString;
  }

  private Bitmap getBitmapFromUrl(String paramString)
  {
    try
    {
      SSLContext localSSLContext = SSLContext.getInstance("TLS");
      TrustManager[] arrayOfTrustManager = new TrustManager[1];
      arrayOfTrustManager[0] = myX509TrustManager;
      localSSLContext.init(null, arrayOfTrustManager, null);
      HttpURLConnection localHttpsURLConnection = (HttpURLConnection)new URL(paramString).openConnection();
//      localHttpsURLConnection.setSSLSocketFactory(localSSLContext.getSocketFactory());
      localHttpsURLConnection.setConnectTimeout(5000);
      localHttpsURLConnection.setReadTimeout(10000);
      Bitmap localBitmap = BitmapFactory.decodeStream((InputStream)localHttpsURLConnection.getContent());
      return localBitmap;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }

  public static void removeFromCache(String paramString)
  {
    if (webImageCache != null)
      webImageCache.remove(paramString);
  }

  public Bitmap getBitmap(Context paramContext)
  {
    if (webImageCache == null)
      webImageCache = new WebImageCache(paramContext);
    String str = this.url;
    Bitmap localBitmap = null;
    if (str != null)
    {
      localBitmap = webImageCache.get(this.url);
      if (localBitmap == null)
      {
        localBitmap = getBitmapFromUrl(this.url);
        if (localBitmap != null)
          webImageCache.put(this.url, localBitmap);
      }
    }
    return localBitmap;
  }
}