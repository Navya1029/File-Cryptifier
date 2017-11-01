import java.io.File;

/**
 * @Authors Tyler, Matt, Daniel
 * @Date_Updated 11/1/17
 * @Model_Used Strategy
 *
 * This interface lays out our Strategy Pattern for file encryption and decryption.
 */
public interface FileCryptoInterface
{
    File fileEncryptor(File file, String algorithm, StoredKeys keys); //Used to encrypt the file with a specific key

    File fileDecryptor(File file, String algorithm, StoredKeys keys); //Used to decrypt the file with a specific key
}
