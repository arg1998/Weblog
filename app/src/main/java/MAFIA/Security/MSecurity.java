package MAFIA.Security;

import java.security.MessageDigest;

public class MSecurity
{
    public static String hashString(String text)
    {
        try
        {
            final MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] bytes = text.getBytes("UTF-8");
            digest.update(bytes, 0, bytes.length);
            return bytesToHex(digest.digest());
        }
        catch (Exception ex)
        {
            return null;
        }
    }
    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte byt : bytes) result.append(Integer.toString((byt & 0xff) + 0x100, 16).substring(1));
        return result.toString();
    }
}