import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class AES {
    private SecretKey key;
    private static int KEY_SIZE = 256;
    private Cipher encryptionCipher;
    public void init() throws NoSuchAlgorithmException {
        KeyGenerator keygen = KeyGenerator.getInstance("AES");
        keygen.init(KEY_SIZE);

        key = keygen.generateKey();
    }

    public String encrypt(String message) throws IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        byte[] messageInBytes = message.getBytes();
        encryptionCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        encryptionCipher.init(Cipher.ENCRYPT_MODE,key);
        byte[] encryptedBytes = encryptionCipher.doFinal(messageInBytes);
        return encode(encryptedBytes);
    }
    public String decrypt(String encryptedMessage) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        byte[] messageInBytes = decode(encryptedMessage);
        Cipher decryptionCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(encryptionCipher.getIV());
        decryptionCipher.init(Cipher.DECRYPT_MODE, key, ivParameterSpec);
        byte[] decryptedInBytes = decryptionCipher.doFinal(messageInBytes);
        return new String(decryptedInBytes);

    }

    private String encode(byte[] data){
        return Base64.getEncoder().encodeToString(data);
    }
    private byte[] decode(String data){
        return Base64.getDecoder().decode(data);
    }
}
