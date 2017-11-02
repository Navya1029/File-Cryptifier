import javax.crypto.*;
import java.security.*;
import java.util.Arrays;
import java.io.*;
import java.security.MessageDigest;

/**
 * @Authors: Tyler, Matt, Daniel
 * @Date Updated: 10/30/17
 * @Model_Used: Strategy
 *
 * This is used for Symmetric Key. The plan is to have the user be able to choose what type of encryption / decryption
 * they want to use and that one is implemented without having to have all the different forms.
 */
public class SymmetricKey implements FileCryptoInterface
{
    private Key generateKeyFromString(final String secKey) throws Exception {
        final byte[] keyVal = new BASE64Decoder().decodeBuffer(secKey);
        final Key key = new SecretKeySpec(keyVal, "DES");
        return key;
    }

    @Override
    public File fileEncryptor(File file, String encryptKey, String algorithm)
    {
        File tempFile = new File("DESEncrypt.txt");
        PrintWriter writer = new PrintWriter("DESEncrypt.txt", "UTF8");
        try
        {

            final Cipher c = Cipher.getInstance("DES");
            String text=new String(); //creates new string object
            byte[] plainText = text.getBytes("UTF8"); //creates plainText



            Key key = generateKeyFromString("TEST"); //creates key
            writer.println(key);

            //Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding"); //DES object create
            //writer.println( "\n" + cipher.getProvider().getInfo() ); // Print Out

            c.init(Cipher.ENCRYPT_MODE, key); //Encripting
            byte[] cipherText = cipher.doFinal(plainText); // Encripting using key

            writer.println( "Finish encryption: " );
            writer.println(new String(cipherText, "UTF8"));//retruns new encrypted object
            writer.close();
        }
        catch (Exception e)
        {
            System.out.println("DES failed");
        }
        return tempFile;
    }
    /*public File DESencryption(File file) throws Exception
    {
        File tempFile = new File("DESEncrypt.txt");
        PrintWriter writer = new PrintWriter("DESEncrypt.txt", "UTF8");
        try
        {
            String text=new String(); //creates new string object
            byte[] plainText = text.getBytes("UTF8"); //creates plainText

            KeyGenerator keyGen = KeyGenerator.getInstance("DES"); //genterates instance of key
            keyGen.init(56); //DES key size
            Key key = keyGen.generateKey(); //creates key

            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding"); //DES object create
            writer.println( "\n" + cipher.getProvider().getInfo() ); // Print Out

            cipher.init(Cipher.ENCRYPT_MODE, key); //Encripting
            byte[] cipherText = cipher.doFinal(plainText); // Encripting using key

            writer.println( "Finish encryption: " );
            writer.println(new String(cipherText, "UTF8"));//retruns new encrypted object
            writer.close();
        }
        catch (Exception e)
        {
            System.out.println("DES failed");
        }
        return tempFile;
    }*/

    @Override
    public File fileDecryptor(File file, String decryptKey, String algorithm)
    {
        return null; //Needs to be changed
    }
}
