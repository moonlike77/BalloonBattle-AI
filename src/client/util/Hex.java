package client.util;

import javax.xml.bind.DatatypeConverter;

public class Hex {
    
    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
    
    public static String to_Hex(String str) {
        byte bytes[] = str.getBytes();
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
    
    public static String to_string(String hex) {
        byte[] bytes = DatatypeConverter.parseHexBinary(hex);
        return new String(bytes);
    }
}
