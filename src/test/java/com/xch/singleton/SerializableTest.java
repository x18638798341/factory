package com.xch.singleton;

import com.xch.singleton.seria.SerializableSingleton;
import org.junit.Test;

import java.io.*;

public class SerializableTest {

    @Test
    public void test1() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        SerializableSingleton instance = SerializableSingleton.getInstance();
        oos.writeObject(instance);
        ByteArrayInputStream baid = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(baid);
        Object o = ois.readObject();
        System.out.println(o == instance);

    }
}
