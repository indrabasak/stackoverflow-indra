package com.basaki.classloader;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.junit.Test;

/**
 * {@code LoadingBookFromBinaryArrayTest} is a test for custom class loading
 * from byte array. It also tests magic number error -
 * `java.lang.ClassFormatError: Incompatible magic value` due to
 * corrupt byte array at the start of the array
 * <p>
 * <p/>
 *
 * @author Indra Basak
 * @since 12/6/17
 */
public class LoadingBookFromBinaryArrayTest {

    private static class MyCustomClassLoader extends ClassLoader {

        public Class loadCustomClass(String name, byte[] bytes) {
            return defineClass(name, bytes, 0, bytes.length);
        }
    }

    public static String BOOK_CLAZZ =
            "yv66vgAAADQAHQoABQAYCQAEABkJAAQAGgcAGwcAHAEABXRpdGxlAQASTGphdmEvbGFuZy9TdHJpbmc7AQAGYXV0aG9yAQAGPGluaXQ-AQADKClWAQAEQ29kZQEAD0xpbmVOdW1iZXJUYWJsZQEAEkxvY2FsVmFyaWFibGVUYWJsZQEABHRoaXMBABdMY29tL2Jhc2FraS9tb2RlbC9Cb29rOwEACGdldFRpdGxlAQAUKClMamF2YS9sYW5nL1N0cmluZzsBAAhzZXRUaXRsZQEAFShMamF2YS9sYW5nL1N0cmluZzspVgEACWdldEF1dGhvcgEACXNldEF1dGhvcgEAClNvdXJjZUZpbGUBAAlCb29rLmphdmEMAAkACgwABgAHDAAIAAcBABVjb20vYmFzYWtpL21vZGVsL0Jvb2sBABBqYXZhL2xhbmcvT2JqZWN0ACEABAAFAAAAAgACAAYABwAAAAIACAAHAAAABQABAAkACgABAAsAAAAvAAEAAQAAAAUqtwABsQAAAAIADAAAAAYAAQAAAAMADQAAAAwAAQAAAAUADgAPAAAAAQAQABEAAQALAAAALwABAAEAAAAFKrQAArAAAAACAAwAAAAGAAEAAAAJAA0AAAAMAAEAAAAFAA4ADwAAAAEAEgATAAEACwAAAD4AAgACAAAABiortQACsQAAAAIADAAAAAoAAgAAAA0ABQAOAA0AAAAWAAIAAAAGAA4ADwAAAAAABgAGAAcAAQABABQAEQABAAsAAAAvAAEAAQAAAAUqtAADsAAAAAIADAAAAAYAAQAAABEADQAAAAwAAQAAAAUADgAPAAAAAQAVABMAAQALAAAAPgACAAIAAAAGKiu1AAOxAAAAAgAMAAAACgACAAAAFQAFABYADQAAABYAAgAAAAYADgAPAAAAAAAGAAgABwABAAEAFgAAAAIAFw==";

    @Test
    public void testLoadingClassWithCorrectMagicNumber() throws IllegalAccessException, InstantiationException, DecoderException {
        byte[] bytes = java.util.Base64.getUrlDecoder().decode(BOOK_CLAZZ);
        MyCustomClassLoader classLoader = new MyCustomClassLoader();
        Class clazz =
                classLoader.loadCustomClass("com.basaki.model.Book", bytes);
        System.out.println(clazz);
    }

    @Test(expected = ClassFormatError.class)
    public void testLoadingClassWithIncorrectCorrectMagicNumber() throws DecoderException {
        byte[] bytes = java.util.Base64.getUrlDecoder().decode(BOOK_CLAZZ);

        String hex8 = Hex.encodeHexString(bytes);
        System.out.println(hex8);
        //cafebabe00000034
        //0123456789123456
        //String hex9 = hex8.substring(0, 14) + "35" +  hex8.substring(16, hex8.length());
        String hex9 = "b" + hex8.substring(1, hex8.length());
        byte[] bytes9 = Hex.decodeHex(hex9.toCharArray());
        System.out.println(hex9);
        MyCustomClassLoader classLoader = new MyCustomClassLoader();
        Class clazz =
                classLoader.loadCustomClass("com.basaki.model.Book", bytes9);
        System.out.println(clazz);
    }

    @Test
    public void testLoadingBookV6() throws IllegalAccessException, InstantiationException {
//        String bookClazz =
//                "yv66vgAAADIAHQoABQAYCQAEABkJAAQAGgcAGwcAHAEABXRpdGxlAQASTGphdmEvbGFuZy9TdHJp\n" +
//                        "bmc7AQAGYXV0aG9yAQAGPGluaXQ+AQADKClWAQAEQ29kZQEAD0xpbmVOdW1iZXJUYWJsZQEAEkxv\n" +
//                        "Y2FsVmFyaWFibGVUYWJsZQEABHRoaXMBABdMY29tL2Jhc2FraS9tb2RlbC9Cb29rOwEACGdldFRp\n" +
//                        "dGxlAQAUKClMamF2YS9sYW5nL1N0cmluZzsBAAhzZXRUaXRsZQEAFShMamF2YS9sYW5nL1N0cmlu\n" +
//                        "ZzspVgEACWdldEF1dGhvcgEACXNldEF1dGhvcgEAClNvdXJjZUZpbGUBAAlCb29rLmphdmEMAAkA\n" +
//                        "CgwABgAHDAAIAAcBABVjb20vYmFzYWtpL21vZGVsL0Jvb2sBABBqYXZhL2xhbmcvT2JqZWN0ACEA\n" +
//                        "BAAFAAAAAgACAAYABwAAAAIACAAHAAAABQABAAkACgABAAsAAAAvAAEAAQAAAAUqtwABsQAAAAIA\n" +
//                        "DAAAAAYAAQAAAAMADQAAAAwAAQAAAAUADgAPAAAAAQAQABEAAQALAAAALwABAAEAAAAFKrQAArAA\n" +
//                        "AAACAAwAAAAGAAEAAAAJAA0AAAAMAAEAAAAFAA4ADwAAAAEAEgATAAEACwAAAD4AAgACAAAABior\n" +
//                        "tQACsQAAAAIADAAAAAoAAgAAAA0ABQAOAA0AAAAWAAIAAAAGAA4ADwAAAAAABgAGAAcAAQABABQA\n" +
//                        "EQABAAsAAAAvAAEAAQAAAAUqtAADsAAAAAIADAAAAAYAAQAAABEADQAAAAwAAQAAAAUADgAPAAAA\n" +
//                        "AQAVABMAAQALAAAAPgACAAIAAAAGKiu1AAOxAAAAAgAMAAAACgACAAAAFQAFABYADQAAABYAAgAA\n" +
//                        "AAYADgAPAAAAAAAGAAgABwABAAEAFgAAAAIAFw==";
        String bookClazz = "yv66vgAAADIAHQoABQAYCQAEABkJAAQAGgcAGwcAHAEABXRpdGxlAQASTGphdmEvbGFuZy9TdHJpbmc" +
                "7AQAGYXV0aG9yAQAGPGluaXQ+AQADKClWAQAEQ29kZQEAD0xpbmVOdW1iZXJUYWJsZQEAEkxvY2FsVmFyaWFibGVUYW" +
                "JsZQEABHRoaXMBABdMY29tL2Jhc2FraS9tb2RlbC9Cb29rOwEACGdldFRpdGxlAQAUKClMamF2YS9sYW5nL1N0cmluZ" +
                "zsBAAhzZXRUaXRsZQEAFShMamF2YS9sYW5nL1N0cmluZzspVgEACWdldEF1dGhvcgEACXNldEF1dGhvcgEAClNvdXJj" +
                "ZUZpbGUBAAlCb29rLmphdmEMAAkACgwABgAHDAAIAAcBABVjb20vYmFzYWtpL21vZGVsL0Jvb2sBABBqYXZhL2xhbmc" +
                "vT2JqZWN0ACEABAAFAAAAAgACAAYABwAAAAIACAAHAAAABQABAAkACgABAAsAAAAvAAEAAQAAAAUqtwABsQAAAAIADAA" +
                "AAAYAAQAAAAMADQAAAAwAAQAAAAUADgAPAAAAAQAQABEAAQALAAAALwABAAEAAAAFKrQAArAAAAACAAwAAAAGAAEAAAAJ" +
                "AA0AAAAMAAEAAAAFAA4ADwAAAAEAEgATAAEACwAAAD4AAgACAAAABiortQACsQAAAAIADAAAAAoAAgAAAA0ABQAOAA0AA" +
                "AAWAAIAAAAGAA4ADwAAAAAABgAGAAcAAQABABQAEQABAAsAAAAvAAEAAQAAAAUqtAADsAAAAAIADAAAAAYAAQAAABEADQ" +
                "AAAAwAAQAAAAUADgAPAAAAAQAVABMAAQALAAAAPgACAAIAAAAGKiu1AAOxAAAAAgAMAAAACgACAAAAFQAFABYADQAAABYA" +
                "AgAAAAYADgAPAAAAAAAGAAgABwABAAEAFgAAAAIAFw==";

        //byte[] bytes = new sun.misc.BASE64Decoder().decodeBuffer(bookClazz);
        byte[] bytes = java.util.Base64.getDecoder().decode(bookClazz);
        MyCustomClassLoader ccl = new MyCustomClassLoader();
        Class cz = ccl.loadCustomClass("com.basaki.model.Book", bytes);
        System.out.println(cz);
        Object o = cz.newInstance();
    }
}



