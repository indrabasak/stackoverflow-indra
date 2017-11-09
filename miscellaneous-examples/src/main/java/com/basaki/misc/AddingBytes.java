package com.basaki.misc;

//import com.google.common.primitives.Bytes;

public class AddingBytes {

    public static void main(String... args) {
        //        byte[0] = 1d (in hexa) = 00011101 (in binary)
        //        byte[1] = 32 = 00110010
        //        byte[2] = d1 = 11010001
        //        byte[3] =0x5 = 00000101
        byte b0 = 0x1d;
        byte b1 =  32;
        byte b2 = (byte) 0xD1;
        byte b3 = 0x5;
        System.out.println("b0: " + (b0 & 0xff));
        System.out.println("b1: " + (b1 & 0xff));
        System.out.println("b2: " + (b2 & 0xff));
        System.out.println("b3: " + (b3 & 0xff));

        //00000101 11010001 00110010 00011101
        //00000101 11010001 00110010 00011101
        //97595933

        //10111010 00100100 00000011 101

        byte[] bytes = new byte[4];
        bytes[0] = 0x1d;
        bytes[1] = 32;
        bytes[2] = (byte) 0xD1;
        bytes[3] = 0x5;

        int sum = 0;
        for (byte b : bytes) {
            sum += bytes[0] & 0xff;
        }

        int x = java.nio.ByteBuffer.wrap(bytes).getInt();
        System.out.println(x);
        x = java.nio.ByteBuffer.wrap(bytes).order(java.nio.ByteOrder.LITTLE_ENDIAN).getInt();
        System.out.println(x);

        int value =
                ((bytes[3] & 0xFF) << 24) |
                        ((bytes[2] & 0xFF) << 16) |
                        ((bytes[1] & 0xFF) << 8) |
                        ((bytes[0] & 0xFF) << 0);
        System.out.println(value);

        byte[] b00 = {0x1d};
        byte[] b11 =  {32};
        byte[] b22 = {(byte) 0xD1};
        byte[] b32 = {0x5};
        //byte[] b4 = Bytes.concat(b00, b11, b22, b32);
        //System.out.println(b4[0]);

        int value2 =
                ((bytes[3] & 0xFF) << 23) |
                        ((bytes[2] & 0xFF) << 16) |
                        ((bytes[1] & 0xFF) << 8) |
                        (bytes[0] & 0xFF);

        System.out.println("value2: " + value2);

        int a = 1;
        System.out.println(a << 1);
        int x1 = (bytes[3] & 0xFF) << 24;
        //00000101
        System.out.println("bytes[3]: " + x1); //1010 0000 0000 0000 0000 0000 000
        String x11 = String.format("%8s", Integer.toBinaryString(x).replace(' ', '0'));
        System.out.println("bytes[3]: " + x11);
        int x2 = (bytes[2] & 0xFF) << 16;
        //11010001
        System.out.println("bytes[2]: " + x2); //110100010000000000000000


        //        String s1 = String.format("%8s", Integer.toBinaryString(bytes[3] & 0xFF)).replace(' ', '0');
        //        System.out.println(s1);
        //        String s2 = String.format("%8s", Integer.toBinaryString(bytes[2] & 0xFF)).replace(' ', '0');
        //        String s3 = String.format("%8s", Integer.toBinaryString(bytes[1] & 0xFF)).replace(' ', '0');
        //        String s4 = String.format("%8s", Integer.toBinaryString(bytes[0] & 0xFF)).replace(' ', '0');
        //
        //        String s = s1 + s2 + s3 + s4;

    }
}
