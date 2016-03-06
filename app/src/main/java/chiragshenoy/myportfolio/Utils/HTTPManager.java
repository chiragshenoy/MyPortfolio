package chiragshenoy.myportfolio.Utils;

/**
 * Created by Chirag Shenoy on 03-Mar-16.
 */

import android.content.Context;

import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.HurlStack;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

public class HTTPManager {

    private Context mContext;

    public HTTPManager(Context context) {
        mContext = context;
    }

    public HttpStack getHttpStack() {
        HttpStack stack = new HurlStack(null, trustEveryone());
        return stack;
    }

    private SSLSocketFactory trustEveryone() {
        try {
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, new X509TrustManager[]{
                    new X509TrustManager() {
                        public void checkClientTrusted(X509Certificate[] chain, String authType)
                                throws CertificateException {
                        }

                        public void checkServerTrusted(X509Certificate[] chain, String authType)
                                throws CertificateException {
                        }

                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[0];
                        }
                    }
            }, new SecureRandom());

            return context.getSocketFactory();
        } catch (Exception e) { // should never happen
            e.printStackTrace();
            return null;
        }
    }


//    public static String getUserAgent(Context context) {
//        return context.getString(R.string.app_name)
//                + "/Android v"
//                + AppUtils.getCurrentVersion(context) + "-" + AppUtils.getAppVersion(context)
//                + " OS"
//                + Build.VERSION.RELEASE
//                + " /Device "
//                + Build.MODEL;
//    }
}