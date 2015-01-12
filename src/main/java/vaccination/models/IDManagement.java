package vaccination.models;


import org.apache.commons.codec.binary.Base64;

import java.util.UUID;

/**
 * ID Management
 */
public class IDManagement {

    public static String generateId() {
        return base64UUID(UUID.randomUUID());
    }

    public static String base64UUID(UUID uuid) {
        byte[] uuidArr = asByteArray(uuid);
        String base64 = Base64.encodeBase64String(uuidArr);
        base64 = base64.replace("=", "-");
        //Plus characters is OK: base64 = base64.replace("+", "|");
        base64 = base64.replace("/", "_");
        return base64;
    }

    public static UUID uuidBase64(String base64) {
        //Plus character is OK: base64 = base64.replace("|", "+");
        base64 = base64.replace("_", "/");
        base64 = base64.replace("-", "=");
        byte[] backArr = Base64.decodeBase64(base64);
        UUID uuid = toUUID(backArr);
        return uuid;
    }

    public static byte[] asByteArray(UUID uuid) {
        long msb = uuid.getMostSignificantBits();
        long lsb = uuid.getLeastSignificantBits();
        byte[] buffer = new byte[16];

        for (int i = 0; i < 8; i++) {
            buffer[i] = (byte) (msb >>> 8 * (7 - i));
        }
        for (int i = 8; i < 16; i++) {
            buffer[i] = (byte) (lsb >>> 8 * (7 - i));
        }

        return buffer;
    }

    public static UUID toUUID(byte[] byteArray) {
        long msb = 0;
        long lsb = 0;
        for (int i = 0; i < 8; i++)
            msb = (msb << 8) | (byteArray[i] & 0xff);
        for (int i = 8; i < 16; i++)
            lsb = (lsb << 8) | (byteArray[i] & 0xff);
        UUID result = new UUID(msb, lsb);

        return result;
    }
}
