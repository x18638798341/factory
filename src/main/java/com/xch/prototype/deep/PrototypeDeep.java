package com.xch.prototype.deep;

import com.xch.prototype.simple.Prototype;
import com.xch.prototype.simple.PrototypeSimple;

import java.io.*;

public class PrototypeDeep extends PrototypeSimple implements Serializable  {
    @Override
    public Prototype clone() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);
            ByteArrayInputStream baid = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(baid);
            return  (PrototypeDeep) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
