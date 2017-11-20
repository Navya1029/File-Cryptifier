import java.awt.Component;
import java.awt.GridLayout;
import java.security.NoSuchAlgorithmException;

import javax.crypto.NoSuchPaddingException;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * @Authors: Tyler, Matt, Daniel
 * @Date Updated: 11/20/17
 * @Model_Used: Model-View-Controller
 *
 * The view manages the display of information.
 */
public class CryptView
{
    private CryptModel model;

    JRadioButton symmetric;
	JRadioButton asymmetric;
	ButtonGroup encryptionType;
	JPanel encryptionSelection;
	
	JLabel inputFileLabel;
	JLabel outputFileLabel;
	JFileChooser inputFileChooser;
	JButton inputChooserButton;
	JFileChooser outputFileChooser;
	JButton outputChooserButton;
	JPanel fileIO;
	
	JPanel top;
	
	JButton encrypt;
	JButton decrypt;
	JPanel bottom;
	
	JLabel symmetricLabel;
	JTextField symmetricKey;
	JPanel symmetricPanel;
	JButton symmetricButton;
	
	JButton generateKeys;
	
	JLabel publicKeyLabel;
	JLabel privateKeyLabel;
	JFileChooser publicKeyChooser;
	JButton publicKeyButton;
	JFileChooser privateKeyChooser;
	JButton privateKeyButton;
	JPanel keys;
	
	JPanel asymmetricPanel;
	
	JPanel container;
	JFrame frame;
	JFrame chooserFrame;
	
	public void init()
	{
		symmetric = new JRadioButton("Symmetric Encryption");
		asymmetric = new JRadioButton("Asymmetric Encryption");
		encryptionType = new ButtonGroup();
		encryptionType.add(symmetric);
		encryptionType.add(asymmetric);
		encryptionSelection = new JPanel();
		encryptionSelection.add(symmetric);
		encryptionSelection.add(asymmetric);
		
		inputFileLabel = new JLabel("In File:", SwingConstants.CENTER);
		outputFileLabel = new JLabel("Out File:", SwingConstants.CENTER);
		inputFileChooser = new JFileChooser();
		inputChooserButton = new JButton("Find");
		outputFileChooser = new JFileChooser();
		outputChooserButton = new JButton("Find");
		fileIO = new JPanel();
		fileIO.setLayout(new GridLayout(2, 2));
		fileIO.add(inputFileLabel);
		fileIO.add(inputChooserButton);
		fileIO.add(outputFileLabel);
		fileIO.add(outputChooserButton);
		
		top = new JPanel();
		top.setLayout(new GridLayout(1, 2));
		top.add(encryptionSelection);
		top.add(fileIO);
		
		encrypt = new JButton("Encrypt");
		decrypt = new JButton("Decrypt");
		bottom = new JPanel();
		bottom.setLayout(new GridLayout(1, 2));
		bottom.add(encrypt);
		bottom.add(decrypt);
		
		symmetricLabel = new JLabel("Symmetric Key:");
		symmetricKey = new JTextField("", 30);
		symmetricPanel = new JPanel();
		symmetricButton = new JButton("Set Symmetric Key");
		symmetricPanel.add(symmetricLabel);
		symmetricPanel.add(symmetricKey);
		symmetricPanel.add(symmetricButton);
		
		generateKeys = new JButton("Generate Keys:");
		
		publicKeyLabel = new JLabel("Public Key:", SwingConstants.CENTER);
		privateKeyLabel = new JLabel("Private Key:", SwingConstants.CENTER);
		publicKeyChooser = new JFileChooser();
		privateKeyChooser = new JFileChooser();
		publicKeyButton = new JButton("Find");
		privateKeyButton = new JButton("Find");
		keys = new JPanel();
		keys.setLayout(new GridLayout(2, 2));
		keys.add(publicKeyLabel);
		keys.add(publicKeyButton);
		keys.add(privateKeyLabel);
		keys.add(privateKeyButton);
		
		asymmetricPanel = new JPanel();
		asymmetricPanel.setLayout(new GridLayout(1, 2));
		asymmetricPanel.add(generateKeys);
		asymmetricPanel.add(keys);
		
		container = new JPanel();
		container.setLayout(new GridLayout(3, 1));
		container.add(top);
		container.add(bottom);
		
		frame = new JFrame("File Encryption/Decryption");
		frame.add(container);							
		frame.pack();
	    frame.setLocationRelativeTo(null);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setVisible(true);

	    chooserFrame = new JFrame();
	}
    /**
     * Constructor that gets the model from the Controller
     * @param model
     */
    public CryptView(CryptModel model)
    {
        this.model = model;
        init();
    }

    /**
     * Method used to update the view
     */
    public void update()
    {
        if (model.getWindowToUse().equals("Symmetric"))
        {
        	Component[] components = container.getComponents();
			for (int i = 0; i < components.length; i++)
			{
				container.remove(components[i]);
			}
			container.add(top);
			container.add(symmetricPanel);
			container.add(bottom);
			container.revalidate();
			container.repaint();
			model.setSymmetric();
        }
        else if (model.getWindowToUse().equals("Asymmetric"))
        {
			Component[] components = container.getComponents();
			for (int i = 0; i < components.length; i++)
			{
				container.remove(components[i]);
			}
			container.add(top);
			container.add(asymmetricPanel);
			container.add(bottom);
			container.revalidate();
			container.repaint();
			try
			{
				model.setAsymmetric();
			}
			catch (NoSuchAlgorithmException e)
			{
				e.printStackTrace();
			}
			catch (NoSuchPaddingException e)
			{
				e.printStackTrace();
			}
		}
        else if (model.getWindowToUse().equals("In File"))
		{
			inputFileChooser.showOpenDialog(chooserFrame);
		}
		else if (model.getWindowToUse().equals("In File Chooser"))
		{
			model.setInFile(inputFileChooser.getSelectedFile());
		}
		else if (model.getWindowToUse().equals("Out File"))
		{
			outputFileChooser.showOpenDialog(chooserFrame);
		}
		else if (model.getWindowToUse().equals("Out File Chooser"))
		{
			model.setOutFile(outputFileChooser.getSelectedFile().getAbsolutePath());
		}
		else if (model.getWindowToUse().equals("Generate Keys:"))
		{
			try
			{
				model.sk.generatePublicPrivateKeys();
			} catch (NoSuchAlgorithmException e)
			{
				e.printStackTrace();
			}
		}
		else if (model.getWindowToUse().equals("Symmetric Button"))
		{
			model.setSymmetricKey(symmetricKey.getText());
		}
		else if (model.getWindowToUse().equals("Public Key:"))
		{
			publicKeyChooser.showOpenDialog(chooserFrame);
		}
		else if (model.getWindowToUse().equals("Private Key:"))
		{
			privateKeyChooser.showOpenDialog(chooserFrame);
		}
		else if (model.getWindowToUse().equals("Public Key Chooser"))
		{
			try
			{
				model.setPublicKey(publicKeyChooser.getSelectedFile());
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		else if (model.getWindowToUse().equals("Private Key Chooser"))
		{
			try
			{
				System.out.println("Private Key Chooser");
				model.setPrivateKey(privateKeyChooser.getSelectedFile());
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
        else if (model.getWindowToUse().equals("Encrypt Button"))
		{
			System.out.println("Encrypt Button");
			model.encryptFile("Not Currently Implemented");
		}
		else if (model.getWindowToUse().equals("Decrypt Button"))
		{
			model.decryptFile("Not Currently Implemented");
		}

    }
}
