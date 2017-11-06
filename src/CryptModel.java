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

    /**
     * Method that assigns default strategy
     */
    public CryptModel()
    {
        strategy = new SymmetricKey();
        
        //Will need to update the view to have proper window for Encryption / Decryption
    }

    /**
     * Method that assigns the strategy to use the SymmetricKey class
     */
    public void setSymmetric()
    {
        strategy = new SymmetricKey();
        
        //Will need to update the view to have proper window for Encryption / Decryption
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
        
        //Will need to update the view to have proper window for Encryption / Decryption
    }
}
