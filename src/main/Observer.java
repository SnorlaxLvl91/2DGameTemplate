package main;

import java.lang.reflect.Field;
import java.util.Map;

/**
 *
 */
public interface Observer {

    /**
     *
     * @param obj
     */
    public default void update(Object obj){
        Map<String[], Object> map = (Map<String[], Object>) obj;

        try {
            for (String[] keys : map.keySet()) {
                int[] values = (int[]) map.get(keys);

                for(int i = 0; i < keys.length; i++) {
                    Field field = this.getClass().getDeclaredField(keys[i]);
                    field.set(this, values[i]);
                }
            }
            doSomething();
        }catch(NoSuchFieldException nsf){
            nsf.printStackTrace();
        }catch(IllegalAccessException ia){
            ia.printStackTrace();
        }
    }

    public default void doSomething(){};
}
