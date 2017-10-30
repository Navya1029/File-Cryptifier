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

    @Override
    public File fileEncryptor(File file, String encryptKey, String algorithm)
    {
        return null; //Needs to be changed
    }

    @Override
    public File fileDecryptor(File file, String decryptKey, String algorithm)
    {
        return null; //Needs to be changed
    }
}
