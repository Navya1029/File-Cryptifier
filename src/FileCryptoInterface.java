/**
 * @Authors: Tyler, Matt, Daniel
 * @Date Updated: 10/30/17
 * @Model_Used: Strategy
 *
 * This interface lays out our Strategy Pattern for file encryption and decryption.
 */
public interface FileCryptoInterface
{
    File fileEncryptor(File file, String encryptKey); //Used to encrypt the file with a specific key

    File fileDecryptor(File file, String decryptKey); //Used to decrypt the file with a specific key
}
