package com.isoftstone.finance.cwgsapp.manage;

import android.util.Log;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

public class GsonRequest<T> extends Request<T>
{
  private final Class<T> clazz;
  private final Gson gson = new Gson();
  private final Map<String, String> headers;
  private final Response.Listener<T> listener;
  private final Map<String, String> params;

  public GsonRequest(int paramInt, String paramString, Class<T> paramClass, Map<String, String> paramMap, Response.Listener<T> paramListener, Response.ErrorListener paramErrorListener)
  {
    super(paramInt, paramString, paramErrorListener);
    this.clazz = paramClass;
    this.headers = null;
    this.listener = paramListener;
    this.params = paramMap;
  }

  protected void deliverResponse(T paramT)
  {
    this.listener.onResponse(paramT);
  }

  public Map<String, String> getHeaders()
    throws AuthFailureError
  {
    if (this.headers != null)
      return this.headers;
    return super.getHeaders();
  }

  protected Map<String, String> getParams()
    throws AuthFailureError
  {
    return this.params;
  }

  protected Response<T> parseNetworkResponse(NetworkResponse paramNetworkResponse)
  {
    try
    {
      String str = new String(paramNetworkResponse.data, HttpHeaderParser.parseCharset(paramNetworkResponse.headers));
      Log.v("tag", this.clazz.getName() + ":" + str);
      Response localResponse = Response.success(this.gson.fromJson(str, this.clazz), HttpHeaderParser.parseCacheHeaders(paramNetworkResponse));
      return localResponse;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      return Response.error(new ParseError(localUnsupportedEncodingException));
    }
    catch (JsonSyntaxException localJsonSyntaxException)
    {
      return Response.error(new ParseError(localJsonSyntaxException));
    }
  }
}