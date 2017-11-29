import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.NoSuchPaddingException;

/**
 * @Authors: Tyler, Matt, Daniel
 * @Date Updated: 11/29/17
 * @Model_Used: Model-View-Controller
 *
 * The model manages the behavior and data of the application domain,
 * responds to requests for information about its state (usually from the view),
 * and responds to instructions to change state (usually from the controller).
 */
public class CryptModel
{
    //----------Initial Variables----------//
    //Holds the strategy to be used for Encryption or Decryption
    FileCryptoInterface strategy;

    //What window to use for the proper info
    String windowToUse;

    String encMethod;

    //Used for the keys
    StoredKeys sk = StoredKeys.getInstance();

    //File initialization
    File inFile, outFile, keyPublic, keyPrivate;
    //----------Initial Variables----------//

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

        encMethod= "Symmetric";
        
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

        encMethod = "Asymmetric";

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

    public void setWindowToUse(String window)
    {
        windowToUse = window;
    }

    /**
     * Method used to decrypt the file and uses the strategy put in place
     *
     * @param algorithm (not currently used)
     */
    public void encryptFile(String algorithm)
    {
        System.out.println("Encrypt File");
        outFile = strategy.fileEncryptor(inFile, algorithm, sk);
    }

    /**
     * Method used to encrypt the file and uses the strategy put in place
     *
     * @param algorithm (not currently used)
     */
    public void decryptFile(String algorithm)
    {
        System.out.println("Decrypt File");
        outFile = strategy.fileDecryptor(inFile, algorithm, sk);
    }
    
    /**
     * Method that stores the symmetric key to the one the user specifies
     * 
     * @param key (Key to store)
     */
    public void setSymmetricKey(String key)
    {
        try
        {
            sk.setSymmetricKey(key);
        } catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * Method that stores the public key to the one the user specifies
     *
     * @throws Exception (If things go wrong)
     */
    public void setPublicKey(File key) throws Exception
    {
        keyPublic = key;
        sk.getPublicFromFile(keyPublic.getAbsolutePath());
    }
    
    /**
     * Method that stores the private key to the one the user specifies
     * 
     * @throws Exception (If things go wrong)
     */
    public void setPrivateKey(File key) throws Exception
    {
        keyPrivate = key;
        sk.getPrivateFromFile(keyPrivate.getAbsolutePath());
    }

    /**
     * Method that returns public key
     *
     * @return file (public key)
     */
    public File getPublic()
    {
        return keyPublic;
    }

    /**
     * Method that returns private key
     *
     * @return file (private key)
     */
    public File getPrivate()
    {
        return keyPrivate;
    }

    /**
     * Method that sets the output file for the encryption or decryption
     */
    public File getInFile()
    {
        return inFile;
    }

    /**
     * Method that sets the input file for the encryption or decryption
     *
     * @param inFile (input file)
     */
    public void setInFile(File inFile)
    {
        this.inFile = inFile;
        this.inFile.getParentFile().mkdirs();
    }
}
