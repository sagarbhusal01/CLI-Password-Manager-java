package np.com.Sagarbhusal01.Security;

import np.com.Sagarbhusal01.Drawing.Draw;
import np.com.Sagarbhusal01.Main;
import np.com.Sagarbhusal01.Utility.Config;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

public class Security {
Config config=new Config();

    public String Hashing(String Password)
    {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            System.out.println("SHA-256 algorithm was not found in this context.");
        }

        assert digest != null;

        byte[] EncodedHashInBytes = digest.digest(
                Password.getBytes(StandardCharsets.UTF_8));

        StringBuilder HashString = new StringBuilder();
        for (byte b : EncodedHashInBytes) {
            HashString.append(String.format("%02x", b));
        }
        return HashString.toString();
    }



    String MasterPassword= new Main().getMasterPassword();


    String SALT = "%30%20%10%0";


    byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0 };

    IvParameterSpec ivspec= new IvParameterSpec(iv);




    private SecretKeySpec getSecretKey()
    {


        // Create SecretKeyFactory object
        SecretKeyFactory factory
                = null;
        try {
            factory = SecretKeyFactory.getInstance(
                    "PBKDF2WithHmacSHA256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        // Create KeySpec object and assign with
        // constructor
        KeySpec spec = new PBEKeySpec(
                MasterPassword.toCharArray(), SALT.getBytes(),
                65536, 256);
        SecretKey tmp = null;
        try {
            tmp = factory.generateSecret(spec);
        } catch (InvalidKeySpecException e) {
            System.out.println("Error while getting secret key: "
                    + e.toString());
            new Draw().Separator();

        }

        assert tmp != null;
        return new SecretKeySpec(
                tmp.getEncoded(), "AES");

    }


    public String EncryptData(String PlainData)
    {

        try {
                Cipher cipher = Cipher.getInstance(
                        "AES/CBC/PKCS5Padding");
                cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(),
                        ivspec);


                // Return encrypted string
                return Base64.getEncoder().encodeToString(
                        cipher.doFinal(PlainData.getBytes(
                                StandardCharsets.UTF_8)));
            }
            catch (Exception e) {
                System.out.println("Error while encrypting: "
                        + e.toString());
                new Draw().Separator();
            }
            return null;

    }



    public String DecryptData(String EncryptedData)
    {
        try {
            Cipher cipher = Cipher.getInstance(
                    "AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(),
                    ivspec);
            // Return decrypted string
            return new String(cipher.doFinal(
                    Base64.getDecoder().decode(EncryptedData)));
        }
        catch (Exception e) {
            System.out.println("Error while decrypting: "
                    + e.toString());
            new Draw().Separator();
        }
        return null;
    }

}

    
