package com.test.project.async.redis;

import java.util.Map;

public interface UseRedis {

    String getValue(String key,String hashKey);
    void setValue(String dest , String key);
    boolean checkNextAble(String key);

}
