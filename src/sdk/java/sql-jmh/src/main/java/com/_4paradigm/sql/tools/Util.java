package com._4paradigm.sql.tools;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com._4paradigm.featuredb.proto.Base;

public class Util {
    public static String getContent(String httpUrl) {
        try {
            URL url = new URL(httpUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            if (con.getResponseCode() == 200) {
                InputStream is = con.getInputStream();
                StringBuilder builder = new StringBuilder();
                int len = 0;
                byte[] buffer = new byte[1024];
                while ((len = is.read(buffer)) != -1) {
                    byte[] temp = new byte[len];
                    System.arraycopy(buffer, 0 , temp, 0, len);
                    builder.append(new String(temp, "utf-8"));
                }
                return builder.toString();
            } else {
                System.out.println("request failed");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static Base.FeatureDBType getFeatureDBType(String type) throws Exception {
        switch (type.toLowerCase()) {
            case "bool":
            case "boolean":
                return Base.FeatureDBType.kBoolean;
            case "short":
            case "int16":
                return Base.FeatureDBType.kInt16;
            case "int":
            case "int32":
                return Base.FeatureDBType.kInt32;
            case "long":
            case "int64":
                return Base.FeatureDBType.kInt64;
            case "float":
                return Base.FeatureDBType.kFloat;
            case "double":
                return Base.FeatureDBType.kDouble;
            case "date":
                return Base.FeatureDBType.kDate;
            case "timestamp":
            case "timestamp-millis":
                return Base.FeatureDBType.kTimestamp;
            case "string":
                return Base.FeatureDBType.kString;
            case "list":
            case "array":
                return Base.FeatureDBType.kList;
            case "map":
                return Base.FeatureDBType.kMap;
            case "feature":
                return Base.FeatureDBType.kFeature;
            default:
                throw new Exception("type " + type + " is not supported");
        }
    }
}
