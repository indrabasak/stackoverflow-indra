package com.basaki;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;
import java.io.Serializable;

public class Save implements Serializable {

    private static final long serialVersionUID = 8253205603766639690L;

/*    public static long getSerialVersionUID() {
        return serialVersionUID;
    }*/

    @Override
    public String toString() {
        return Long.toString(serialVersionUID);
    }


    public static void main(String... args) {
        /*FileOutputStream fout = null;
        FileInputStream fin = null;
        try{

            Save save = new Save();
            fout = new FileOutputStream("save.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(save);
            oos.close();
            System.out.println("Done");

            fin = new FileInputStream("save.ser");
            ObjectInputStream ois = new ObjectInputStream(fin);
            save = (Save) ois.readObject();
            ois.close();

            String id = String.valueOf(
                    ObjectStreamClass.lookup(Save.class).getSerialVersionUID());
            System.out.println("id: " + id);

        }catch(Exception ex){
            ex.printStackTrace();
        } finally {
            try {
                if (fout != null) {fout.close();}
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                fin.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
    }
}
