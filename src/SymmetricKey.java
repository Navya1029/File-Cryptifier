import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;

/**
 * @Authors: Tyler, Matt, Daniel
 * @Date Updated: 11/3/17
 * @Model_Used: Strategy
 *
 * This is used for Symmetric Key. The plan is to have the user be able to choose what type of encryption / decryption
 * they want to use and that one is implemented without having to have all the different forms.
 */
public class SymmetricKey implements FileCryptoInterface
{

    @Override
    public File fileEncryptor(File file, String algorithm, StoredKeys keys)
    {
    		File tempFile = new File("AES-Encrypted");

    		try 
    		{
    			//This is the type of cipher we will use
				Cipher c = Cipher.getInstance("AES");

				//Takes the key and converts it to something we can use to decode
				byte[] key = keys.getSymmetricKey().getBytes("UTF-8");
				MessageDigest sha = MessageDigest.getInstance("SHA-1");
				key = sha.digest(key);
				key = Arrays.copyOf(key, 16);

				SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES"); //Creates a secret key

				//Creates the cipher in encrypt mode using this key
				c.init(Cipher.ENCRYPT_MODE, secretKeySpec);

				//Encrypts the files bytes
				byte[] cipherText = c.doFinal(Files.readAllBytes(file.toPath()));

				StringBuffer sb = new StringBuffer(); //A string buffer
				for(int i = 0; i < cipherText.length; i++)
				{
					sb.append(Integer.toString((cipherText[i] & 0xff) + 0x100, 16).substring(1)); //puts the decrypted stuff in the buffer
				}

				FileWriter fw = new FileWriter(tempFile); //Writes files
				fw.write(sb.toString()); //writes to file
				fw.close(); //Closes the file
		} catch (Exception e) { System.out.println("AES Encryption Failed"); }
		
        return tempFile;
    }

    @Override
    public File fileDecryptor(File file, String algorithm, StoredKeys keys)
    {
		File tempFile = new File("AES-Decrypted");

		try
		{
			//This is the type of cipher we will use
			Cipher c = Cipher.getInstance("AES");

			//Takes the key and converts it to something we can use to encode
			byte[] key = keys.getSymmetricKey().getBytes("UTF-8");
			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			key = sha.digest(key);
			key = Arrays.copyOf(key, 16);

			SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");

			//Creates the cipher in decrypt mode using this key
			c.init(Cipher.DECRYPT_MODE, secretKeySpec);

			//Encrypts the files bytes
			byte[] cipherText = c.doFinal(Files.readAllBytes(file.toPath()));

			StringBuffer sb = new StringBuffer(); //A string buffer
			for(int i = 0; i < cipherText.length; i++)
			{
				sb.append(Integer.toString((cipherText[i] & 0xff) + 0x100, 16).substring(1)); //puts the encrypted stuff in the buffer
			}

			FileWriter fw = new FileWriter(tempFile); //Writes files
			fw.write(sb.toString()); //writes to file
			fw.close(); //Closes the file
		} catch (Exception e) { System.out.println("AES Decryption Failed"); }

		return tempFile;
    }
    
    private Key generateKeyFromString(final String secKey) throws Exception
	{
		final byte[] keyVal = new BASE64Decoder().decodeBuffer(secKey);
		System.out.println(keyVal);
		final Key key = new SecretKeySpec(keyVal, "AES");
		System.out.println(key);
		return key;
	}
}
