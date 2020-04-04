package main.mario;

import java.lang.reflect.Field;
import java.util.Map;

public interface Observer {

    public default void update(Object obj){
        Map<String, Object> map = (Map<String, Object>) obj;

        try {
            for (String key : map.keySet()) {
                Field field = this.getClass().getDeclaredField(key);
                field.set(this, map.get(key));
            }
        }catch(NoSuchFieldException nsf){
            nsf.printStackTrace();
        }catch(IllegalAccessException ia){
            ia.printStackTrace();
        }
    }
}
