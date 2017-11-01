import java.io.File;

/**
 * @Authors Tyler, Matt, Daniel
 * @Date_Updated 11/1/17
 * @Model_Used Strategy
 * 
 * This is used for Asymmetric Key. The plan is to have the user be able to choose what type of encryption / decryption
 * they want to use and that one is implemented without having to have all the different forms.
 */
public class AsymmetricKey implements FileCryptoInterface
{
    @Override
    public File fileEncryptor(File file, String algorithm, StoredKeys keys) //The encrypt key is the public key
    {
        return null; //Needs to be changed
    }

    @Override
    public File fileDecryptor(File file, String algorithm, StoredKeys keys) //The decrypt key is the private key
    {
        return null; //Needs to be changed
    }
}
