package chapter7;

import java.io.IOException;
import java.io.InputStream;

/**
 * 不同的类加载器对instanceof，isAssignableFrom，isInstance运算结果的影响
 * 类是由加载它的类加载器和这个类本身唯一确定的
 * Created by zhaoshq on 2017/7/24.
 */
public class ClassLoaderTest {

    public static void main(String[] args) throws Exception {

        ClassLoader myLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {

                try {
                    String fileName = name.substring(name.lastIndexOf(".")+1) + ".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if (is == null){
                        return super.loadClass(name);
                    }
                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name,b,0,b.length);
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new ClassNotFoundException(name);
                }

            }
        };

        Object obj1 = Thread.currentThread().getContextClassLoader().loadClass("chapter7.ClassLoaderTest").newInstance();
        Object obj3 = Thread.currentThread().getContextClassLoader().loadClass("chapter7.ClassLoaderTest").newInstance();
        Object obj2 = myLoader.loadClass("chapter7.ClassLoaderTest").newInstance();
        System.out.println(obj1.getClass());
        System.out.println(obj2.getClass());

        System.out.println(obj1 instanceof chapter7.ClassLoaderTest);
        System.out.println(obj2 instanceof chapter7.ClassLoaderTest);

        System.out.println(obj1.getClass().isInstance(obj2));
        System.out.println(obj1.getClass().isInstance(obj3));

        System.out.println(obj1.getClass().isAssignableFrom(obj2.getClass()));
        System.out.println(obj1.getClass().isAssignableFrom(obj3.getClass()));
    }
}
