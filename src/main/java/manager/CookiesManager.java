package manager;

import utils.MD5Utils;

/**
 * Created by dasun on 2017/7/29.
 */
public class CookiesManager {
    private static final String KEY="mydemo";
    public static String generatorMD5CookieValue(String password){
        String md5Psd= MD5Utils.toMD5(password+":"+KEY);
        return md5Psd;
    }
}
