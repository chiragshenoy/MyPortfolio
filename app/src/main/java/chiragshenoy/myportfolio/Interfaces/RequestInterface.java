package chiragshenoy.myportfolio.Interfaces;

/**
 * Created by Chirag Shenoy on 03-Mar-16.
 */

import java.util.HashMap;

public interface RequestInterface {

    /**
     * Converts the url params to be converted into a byteArrray
     */

    byte[] getJSONByteArray(HashMap<String, String> params);
}