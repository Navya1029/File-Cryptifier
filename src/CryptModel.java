import java.io.File;
import java.security.NoSuchAlgorithmException;

import javax.crypto.NoSuchPaddingException;

/**
 * @Authors: Tyler, Matt, Daniel
 * @Date Updated: 11/6/17
 * @Model_Used: Model-View-Controller
 *
 * The model manages the behavior and data of the application domain,
 * responds to requests for information about its state (usually from the view),
 * and responds to instructions to change state (usually from the controller).
 */
public class CryptModel
{
    //Holds the strategy to be used for Encryption or Decryption
    FileCryptoInterface strategy;
    
    //What window to use for the proper info
    String windowToUse;
    
    //Used for the keys
    StoredKeys sk = StoredKeys.getInstance();

    /**
     * Method that assigns default strategy
     */
    public CryptModel()
    {
        strategy = new SymmetricKey();
        
        //Used to update the view to have proper window for Encryption / Decryption
        windowToUse = "Symmetric";
    }

    /**
     * Method that assigns the strategy to use the SymmetricKey class
     */
    public void setSymmetric()
    {
        strategy = new SymmetricKey();
        
        //Used to update the view to have proper window for Encryption / Decryption
        windowToUse = "Symmetric";
    }

    /**
     * Method that assigns the strategy to use the AsymmetricKey class
     * 
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     */
    public void setAsymmetric() throws NoSuchAlgorithmException, NoSuchPaddingException
    {
        strategy = new AsymmetricKey();
        
        //Used to update the view to have proper window for Encryption / Decryption
        windowToUse = "Asymmetric";
    }
    
    /**
     * Gets the window needed for the view
     * 
     * @return Window to Use for view
     */
    public String getWindowToUse()
    {
        return windowToUse;
    }
    
    /**
     * Method used to encrypt the file and uses the strategy put in place
     * 
     * @param file (input file)
     * @param algorithm (not currently used)
     * @param keys (the keys for encryption and decryption)
     * @return file (output file)
     */
    public File encryptFile(File file, String algorithm, StoredKeys keys)
    {
        return strategy.fileEncryptor(file, algorithm, keys);
    }
    
    /**
     * Method used to decrypt the file and uses the strategy put in place
     * 
     * @param file (input file)
     * @param algorithm (not currently used)
     * @param keys (the keys for encryption and decryption)
     * @return file (output file)
     */
    public File decryptFile(File file, String algorithm, StoredKeys keys)
    {
        return strategy.fileDecryptor(file, algorithm, keys);
    }
    
    /**
     * Method that stores the symmetric key to the one the user specifies
     * 
     * @param key (Key to store)
     */
    public void setSymmetricKey(String key)
    {
        sk.setSymmetricKey(key);
    }
    
    /**
     * Method that stores the public key to the one the user specifies
     * 
     * @param file (input file)
     * @throws Exception (If things go wrong)
     */
    public void setPublicKey(File file) throws Exception
    {
        sk.getPublicFromFile(file.getAbsolutePath());
    }
    
    /**
     * Method that stores the private key to the one the user specifies
     * 
     * @param file (input file)
     * @throws Exception (If things go wrong)
     */
    public void setPrivateKey(File file) throws Exception
    {
        sk.getPublicFromFile(file.getAbsolutePath());
    }
}
