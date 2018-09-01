package customserverutil.language;

import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PlayerLanguage {



    public static String getLanguage(Player p){
        Object ep = null;
        try {
            ep = getMethod("getHandle", p.getClass()).invoke(p, (Object[]) null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        Field f = null;
        try {
            f = ep.getClass().getDeclaredField("locale");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        f.setAccessible(true);
        String language = null;
        try {
            language = (String) f.get(ep);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return language;
    }
    private static Method getMethod(String name, Class<?> clazz) {
        for (Method m : clazz.getDeclaredMethods()) {
            if (m.getName().equals(name))
                return m;
        }
        return null;
    }
}
