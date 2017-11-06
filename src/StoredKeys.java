import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;


/**
 * @Authors Tyler, Matt, Daniel
 * @Date_Updated 11/6/17
 * @Model_Used Singleton
 * 
 * This is a class used to keep track of the keys. This will make it so the encryption and decryption classes
 * are not a total disaster.
 */
public class StoredKeys
{
	private static StoredKeys instance;

	private String SymmetricKey;
	private PublicKey publicKey;
	private PrivateKey privateKey;
	
	/*
	 * Only want one instance of it so the constructor is private.
	 */
	private StoredKeys() { }
	
	public static StoredKeys getInstance()
    {
		if(instance == null)
			instance = new StoredKeys();
		return instance;
	}
	
	//--------------------------------SETTERS--------------------------------------
	public void setSymmetricKey(String key)
	{
		this.SymmetricKey = key;
	}
	
	public void setPublicKey(PublicKey key)
	{
		this.publicKey = key;
	}
	
	public void setPrivateKey(PrivateKey key)
	{
		this.privateKey = key;
	}
	//--------------------------------SETTERS--------------------------------------
	
	//--------------------------------GETTERS--------------------------------------
	public String getSymmetricKey()
	{
		return SymmetricKey;
	}
	
	public PublicKey getPublicKey()
	{
		return publicKey;
	}
	
	public PrivateKey getPrivateKey()
	{
		return privateKey;
	}
	//--------------------------------GETTERS--------------------------------------
	
	/*
	 * This method generates the public and private keys needed for Asymmetric Encryption and Decryption.
	 */
	public void generatePublicPrivateKeys() throws NoSuchAlgorithmException
	{
		//Uses RSA to generate the key pair
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");

		try
		{
			//Initializes the key size
			kpg.initialize(1024);

			//Makes the key pair
			KeyPair kp = kpg.generateKeyPair();

			//Saves the public and private keys to be used for encryption and decryption
			publicKey = kp.getPublic();
			privateKey = kp.getPrivate();

			//This is where we plan to store the keys.
			writeToFile("KeyPair/publicKey", getPublicKey().getEncoded());
			writeToFile("KeyPair/privateKey", getPrivateKey().getEncoded());
		} catch (IOException e)
		{
			System.err.println(e.getMessage());
		}
	}

	//Function that saves the keys to a file so they can be used another time
	public void writeToFile(String path, byte[] key) throws IOException
	{
		File f = new File(path);
		f.getParentFile().mkdirs();

		FileOutputStream fos = new FileOutputStream(f);
		fos.write(key);
		fos.flush();
		fos.close();
	}
	
	//Function that gets the private key from a file specified
	public PrivateKey getPrivateFromFile(String filename) throws Exception
	{
		byte[] keyBytes = Files.readAllBytes(new File(filename).toPath());
		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		
		return kf.generatePrivate(spec);
	}

	//Function taht gets the public key from a file specified
	public PublicKey getPublicFromFile(String filename) throws Exception
	{
		byte[] keyBytes = Files.readAllBytes(new File(filename).toPath());
		X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		
		return kf.generatePublic(spec);
	}
}
