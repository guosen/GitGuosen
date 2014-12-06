package com.joke.android.provider;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import android.content.Context;
import android.util.Log;
import com.joke.android.base.NetWorkException;
import com.joke.android.base.NetWorkRequestException;
import com.joke.android.util.Constants;

public class HttpProvider {
    
    private Context             context;
    protected final String      serverUrl = Constants.Base_HTTP_URL;
    protected final String      debugUrl=Constants.Base_Debug_URL;
    private static final String TAG       = "HttpProvider";
    
    public HttpProvider(Context context) {
        if (context == null) {
            throw new NullPointerException("context == null");
        }
        this.context = context;
    }
    
    private HttpClient getHttpClient() {
        HttpParams httpParameters = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParameters, 8 * 1000);
        HttpConnectionParams.setSoTimeout(httpParameters, 8 * 1000);
        
        return new DefaultHttpClient(httpParameters);
    }
    
    public String get(String uri, Map<String, Object> headPair, Map<String, Object> parametersPair) {
        if (uri == null) {
            throw new NullPointerException("uri == null");
        }
        
        HttpClient httpClient = getHttpClient();
        
        StringBuilder sb = new StringBuilder(uri);
        if (parametersPair != null && parametersPair.size() > 0) {
            sb.append("?");
            for (String key : parametersPair.keySet()) {
                if (sb.length() != (uri.length() + 1)) {
                    sb.append("&");
                }
                sb.append(key).append("=").append(parametersPair.get(key));
            }
        }
        HttpGet request = new HttpGet(sb.toString());
        if (headPair != null && headPair.size() > 0) {
            for (String key : headPair.keySet()) {
                request.setHeader(key, headPair.get(key).toString());
            }
        }
        
        if (Constants.DEBUG) {
            Log.v(TAG, sb.toString());
        }
        
        addHeader(request, sb.toString());
        
        HttpResponse response = null;
        int exceptionType = 0;
        Exception exception = null;
        String back = null;
        
        try {
            response = httpClient.execute(request);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                try {
                    back = EntityUtils.toString(response.getEntity());
                } catch (Exception e) {
                    exceptionType = 2;
                    exception = e;
                }
            } else {
                exceptionType = 2;
                exception = new Exception("http status code=" + response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            exceptionType = 1;
            exception = e;
        }
        
        /**
         * ��ӡ������Ϣ
         * add by chenyanmo
         * 2013��11��27��11:35:42
         **/
        if (Constants.DEBUG) {
            Log.e(TAG, "==== RequestURI ====");
            Log.e(TAG, "" + request.getURI());
            Header[] names = request.getAllHeaders();
            Log.e(TAG, "==== Header ====");
            for (int i = 0; i < names.length; i++) {
                String name = names[i].getName();
                String value = names[i].getValue();
                Log.e(TAG, name + ":" + value);
            }
            
            String mPString = "";
            if (parametersPair != null && parametersPair.size() > 0) {
                Log.e(TAG, "==== Params ====\n" + mPString);
                for (String key : parametersPair.keySet()) {
                    Log.e(TAG, key + " : " + parametersPair.get(key));
                }
            }
            if (back != null) {
                Log.e(TAG, "==== Result ====\n" + back);
            }
        }
        
        httpClient.getConnectionManager().shutdown();
        switch (exceptionType) {
            case 1:
                throw new NetWorkException(exception);
            case 2:
                throw new NetWorkRequestException(exception);
        }
        return back;
    }
    
   /* public void downloadFile(String uri, String filePath, WriteProgressBar pb) {
        if (uri == null || filePath == null) {
            throw new NullPointerException("uri == null || filePath == null");
        }
        
        HttpClient httpClient = getHttpClient();
        try {
            HttpGet request = new HttpGet(uri);
            addHeader(request, uri);
            HttpResponse response = httpClient.execute(request);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                InputStream is = response.getEntity().getContent();
                if (pb != null) {
                    pb.setTotal(response.getEntity().getContentLength());
                }
                new DefaultFileDao(context).writeInputStream(StoreType.SDCard, filePath, is, pb);
            } else {
                throw new RuntimeException("http status code=" + response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
    }*/
    
    public String post(String uri, Map<String, Object> headPair, Map<String, Object> parametersPair) {
        if (uri == null) {
            throw new NullPointerException("uri == null");
        }
        
        HttpClient httpClient = getHttpClient();
        
        HttpPost request = new HttpPost(uri);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        if (parametersPair != null && parametersPair.size() > 0) {
            for (String key : parametersPair.keySet()) {
                if (parametersPair.get(key) != null) {
                    params.add(new BasicNameValuePair(key, parametersPair.get(key).toString()));
                } else {
                    params.add(new BasicNameValuePair(key, ""));
                }
            }
        }
        if (headPair != null && headPair.size() > 0) {
            for (String key : headPair.keySet()) {
                if (headPair.get(key) != null) {
                    request.setHeader(key, headPair.get(key).toString());
                } else {
                    request.setHeader(key, "");
                }
                request.setHeader(key, headPair.get(key).toString());
            }
        }
        UrlEncodedFormEntity entity = null;
        try {
            entity = new UrlEncodedFormEntity(params, HTTP.UTF_8);
        } catch (UnsupportedEncodingException e1) {
        }
        request.setEntity(entity);
        addHeader(request, uri);
        
        HttpResponse response = null;
        int exceptionType = 0;
        Exception exception = null;
        String back = null;
        try {
            response = httpClient.execute(request);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                try {
                    back = EntityUtils.toString(response.getEntity(),"UTF-8");
                } catch (Exception e) {
                    exceptionType = 2;
                    exception = e;
                }
            } else {
                exceptionType = 2;
                exception = new Exception("http status code=" + response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            exceptionType = 1;
            exception = e;
        }
        
        httpClient.getConnectionManager().shutdown();
        switch (exceptionType) {
            case 1:
                throw new NetWorkException(exception);
            case 2:
                throw new NetWorkRequestException(exception);
        }
        return back;
    }
    
  /*  private String getMD5Key(String uri) {
        StringBuilder sb = new StringBuilder(uri);
        sb.delete(0, serverUrl.length());
        String temp = sb.append(ApplicationUtil.getSecurityKey(context)).toString();
        String md5Key = new MD5Util().md5(temp);
        return md5Key;
    }*/
    
    private void addHeader(HttpRequest request, String uri) {
       // request.setHeader("Hash", getMD5Key(uri));
       // request.setHeader("IMEI", AndroidDeviceUtil.getImei(context));
      //  request.setHeader("Device", AndroidDeviceUtil.getDeviceName());
        // ���ַ��ϵͳ�汾
     //   request.setHeader("MacAddress", AndroidDeviceUtil.getMacAddress(context));
      //  request.setHeader("OsVersion", android.os.Build.VERSION.RELEASE);
        
       // request.setHeader("Token", getToken());
        
    }
    
  /*  private String getToken() {
        String token = null;
        Login login = ModelManager.getInstance().getUser().getLogin();
        if (login != null) {
            token = login.getToken();
        }
        return token;
    }*/
    
    public String urlEncoder(String str) {
        try {
            return URLEncoder.encode(str, "utf-8");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
}
