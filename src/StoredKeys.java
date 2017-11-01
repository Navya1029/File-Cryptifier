import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @Authors Tyler, Matt, Daniel
 * @Date_Updated 11/1/17
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
	    kpg.initialize(512); // 512 is the keysize.
	    
	    KeyPair kp = kpg.generateKeyPair();
	    
	    publicKey = kp.getPublic();
	    privateKey = kp.getPrivate();
	}
}
