package com.isoftstone.finance.cwgsapp.manage;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Process;
import android.util.Log;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.Volley;
import com.litesuits.orm.LiteOrm;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

public class CwgsApplication extends Application
{
  private static final String DB_NAME = "gljr.db";
  private static int MainId;
  private static CwgsApplication application;
  private static Context context;
  private static Handler handler;
  public static LiteOrm mLiteOrm;
  public static DisplayImageOptions options;
  public static RequestQueue requestQueue;
  public static float sHeight;
  public static float sWidth;

  public static Context getAppContext()
  {
    return context;
  }

  public static CwgsApplication getApplication()
  {
    return application;
  }

  private HostnameVerifier getHostnameVerifier()
  {
    return new HostnameVerifier()
    {
      public boolean verify(String paramAnonymousString, SSLSession paramAnonymousSSLSession)
      {
        return HttpsURLConnection.getDefaultHostnameVerifier().verify("localhost", paramAnonymousSSLSession);
      }
    };
  }

  public static LiteOrm getLiteOrm()
  {
    if (mLiteOrm == null)
    {
      mLiteOrm = LiteOrm.newSingleInstance(context, "gljr.db");
      mLiteOrm.setDebugged(true);
    }
    return mLiteOrm;
  }

  public static RequestQueue getRequestQueue()
  {
    return requestQueue;
  }

  private SSLSocketFactory getSSLSocketFactory()
    throws CertificateException, KeyStoreException, IOException, NoSuchAlgorithmException, KeyManagementException
  {
    CertificateFactory localCertificateFactory = CertificateFactory.getInstance("X.509");
    InputStream localInputStream = getResources().openRawResource(2131099648);
    Certificate localCertificate = localCertificateFactory.generateCertificate(localInputStream);
    localInputStream.close();
    KeyStore localKeyStore = KeyStore.getInstance("BKS");
    localKeyStore.load(null, null);
    localKeyStore.setCertificateEntry("ca", localCertificate);
    TrustManagerFactory localTrustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
    localTrustManagerFactory.init(localKeyStore);
    TrustManager[] arrayOfTrustManager = getWrappedTrustManagers(localTrustManagerFactory.getTrustManagers());
    SSLContext localSSLContext = SSLContext.getInstance("SSL");
    localSSLContext.init(null, arrayOfTrustManager, null);
    return localSSLContext.getSocketFactory();
  }

  private TrustManager[] getWrappedTrustManagers(TrustManager[] paramArrayOfTrustManager)
  {
    final X509TrustManager localX509TrustManager = (X509TrustManager)paramArrayOfTrustManager[0];
    TrustManager[] arrayOfTrustManager = new TrustManager[1];
    arrayOfTrustManager[0] = new X509TrustManager()
    {
      public void checkClientTrusted(X509Certificate[] paramAnonymousArrayOfX509Certificate, String paramAnonymousString)
      {
        if (paramAnonymousArrayOfX509Certificate != null);
        try
        {
          if (paramAnonymousArrayOfX509Certificate.length > 0)
          {
            paramAnonymousArrayOfX509Certificate[0].checkValidity();
            return;
          }
          localX509TrustManager.checkClientTrusted(paramAnonymousArrayOfX509Certificate, paramAnonymousString);
          return;
        }
        catch (CertificateException localCertificateException)
        {
          Log.w("checkClientTrusted", localCertificateException.toString());
        }
      }

      public void checkServerTrusted(X509Certificate[] paramAnonymousArrayOfX509Certificate, String paramAnonymousString)
      {
        if (paramAnonymousArrayOfX509Certificate != null);
        try
        {
          if (paramAnonymousArrayOfX509Certificate.length > 0)
          {
            paramAnonymousArrayOfX509Certificate[0].checkValidity();
            return;
          }
          localX509TrustManager.checkServerTrusted(paramAnonymousArrayOfX509Certificate, paramAnonymousString);
          return;
        }
        catch (CertificateException localCertificateException)
        {
          Log.w("checkServerTrusted", localCertificateException.toString());
        }
      }

      public X509Certificate[] getAcceptedIssuers()
      {
        return localX509TrustManager.getAcceptedIssuers();
      }
    };
    return arrayOfTrustManager;
  }

  public static void initImageLoader(Context paramContext)
  {
    ImageLoaderConfiguration localImageLoaderConfiguration = new ImageLoaderConfiguration.Builder(paramContext).threadPriority(3).denyCacheImageMultipleSizesInMemory().diskCacheFileNameGenerator(new Md5FileNameGenerator()).diskCacheSize(52428800).tasksProcessingOrder(QueueProcessingType.LIFO).writeDebugLogs().build();
    ImageLoader.getInstance().init(localImageLoaderConfiguration);
  }

  public void onCreate()
  {
    super.onCreate();
    context = getApplicationContext();
    requestQueue = Volley.newRequestQueue(this, new HurlStack()
    {
      protected HttpURLConnection createConnection(URL paramAnonymousURL)
        throws IOException
      {
        HttpsURLConnection localHttpsURLConnection = (HttpsURLConnection)super.createConnection(paramAnonymousURL);
        try
        {
          HttpsURLConnection.setDefaultHostnameVerifier(new CwgsApplication.NullHostNameVerifier(CwgsApplication.this));
          localHttpsURLConnection.setSSLSocketFactory(CwgsApplication.this.getSSLSocketFactory());
          localHttpsURLConnection.setHostnameVerifier(CwgsApplication.this.getHostnameVerifier());
          return localHttpsURLConnection;
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
        }
        return localHttpsURLConnection;
      }
    });
    options = new DisplayImageOptions.Builder().showStubImage(2130903140).showImageForEmptyUri(2130903140).showImageOnFail(2130903140).cacheInMemory(true).cacheOnDisc(true).bitmapConfig(Bitmap.Config.RGB_565).build();
    sWidth = getResources().getDisplayMetrics().widthPixels;
    sHeight = getResources().getDisplayMetrics().heightPixels;
    application = this;
    MainId = Process.myTid();
    initImageLoader(getAppContext());
  }

  public class NullHostNameVerifier
    implements HostnameVerifier
  {
    public NullHostNameVerifier()
    {
    }

    public boolean verify(String paramString, SSLSession paramSSLSession)
    {
      Log.i("RestUtilImpl", "Approving certificate for " + paramString);
      return true;
    }
  }
}