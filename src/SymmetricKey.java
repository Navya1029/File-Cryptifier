import java.io.File;
import java.io.FileOutputStream;
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
 * @Date Updated: 11/29/17
 * @Model_Used: Strategy
 *
 * This is used for Symmetric Key. The plan is to have the user be able to choose what type of encryption / decryption
 * they want to use and that one is implemented without having to have all the different forms.
 */
public class SymmetricKey implements FileCryptoInterface
{
	/**
	 * This method encrypts the file given to it and stores the file in a set location
	 * @param file (File that needs to be encrypted)
	 * @param algorithm (Not currently used)
	 * @param keys (Object that contains the keys)
	 * @return Encrypted File (In case we want to open it up for the user)
	 */
    @Override
    public File fileEncryptor(File file, String algorithm, StoredKeys keys)
    {
    	//This is to get the input file so we can save the output file in the same directory with a different name
		String fileName = file.getPath();
		int ext = fileName.lastIndexOf(".");

		File tempFile = new File("Messages/Encrypted-AES" + fileName.substring(ext));

		//This makes the directory so the file can be stored
		tempFile.getParentFile().mkdirs();

		try
		{
			//This is the type of cipher we will use
			Cipher c = Cipher.getInstance("AES");

			//Creates the cipher in encrypt mode using this key
			c.init(Cipher.ENCRYPT_MODE, keys.getSymmetricKey());

			//Encrypts the files bytes
			byte[] cipherText = c.doFinal(Files.readAllBytes(file.toPath()));

			FileOutputStream out = new FileOutputStream(tempFile);
			byte[] output = c.doFinal(cipherText);
			out.write(output);

			out.flush();
			out.close();
		} catch (Exception e) { System.out.println("AES Encryption Failed"); }
		
        return tempFile;
    }

	/**
	 * This method decrypts the file given to it and stores the file in a set location
	 * @param file (File that needs to be decrypted)
	 * @param algorithm (Not currently used)
	 * @param keys (Object that contains the keys)
	 * @return Decrypted File (In case we want to open it up for the user)
	 */
    @Override
    public File fileDecryptor(File file, String algorithm, StoredKeys keys)
    {
		//This is to get the input file so we can save the output file in the same directory with a different name
		String fileName = file.getPath();
		int ext = fileName.lastIndexOf(".");

		File tempFile = new File("Messages/Decrypted-AES" + fileName.substring(ext));

		//This makes the directory so the file can be stored
		tempFile.getParentFile().mkdirs();

		try
		{
			//This is the type of cipher we will use
			Cipher c = Cipher.getInstance("AES");

			//Creates the cipher in encrypt mode using this key
			c.init(Cipher.DECRYPT_MODE, keys.getSymmetricKey());

			//Encrypts the files bytes
			byte[] cipherText = c.doFinal(Files.readAllBytes(file.toPath()));

			FileOutputStream out = new FileOutputStream(tempFile);
			byte[] output = c.doFinal(cipherText);
			out.write(output);

			out.flush();
			out.close();
		} catch (Exception e) {
			System.out.println("AES Decryption Failed\n" + e.getMessage()); }

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
