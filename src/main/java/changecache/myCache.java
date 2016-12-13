package  cache;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.stream.IntStream;

/*
 * Created by OTBA}|{HbIu` on 06.09.16.
*/


public class myCache {
/*
    public static void main(String[] args) {
        someMagic();
        final Integer tmp =5;
        System.out.println(tmp);
    }

    public static void someMagic() {
        final Class intCache=Integer.class.getDeclaredClasses()[0];
        try{
            final Field cacheArray=intCache.getDeclaredField("changecache");
            Field modifiers= cacheArray.getClass().getDeclaredField("modifiers");
            modifiers.setAccessible(true);
            modifiers.setInt(cacheArray,cacheArray.getModifiers() & ~Modifier.FINAL);
            cacheArray.setAccessible(true);

            final Integer[] newCache=new Integer[256];
            IntStream.range(0,256).forEach(i->newCache[i]=100);
            cacheArray.set(null,newCache);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }*/
}
