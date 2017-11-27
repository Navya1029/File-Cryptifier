import java.awt.Component;
import java.awt.GridLayout;
import java.security.NoSuchAlgorithmException;

import javax.crypto.NoSuchPaddingException;
import javax.swing.*;

/**
 * @Authors: Tyler, Matt, Daniel
 * @Date Updated: 11/27/17
 * @Model_Used: Model-View-Controller
 *
 * The view manages the display of information.
 */
public class CryptView
{
    private CryptModel model; //Javax Api variables

    JRadioButton symmetric;
	JRadioButton asymmetric;
	ButtonGroup encryptionType;
	JPanel encryptionSelection;
	
	JLabel inputFileLabel;
	JLabel inputFileLabel2;
	JFileChooser inputFileChooser;
	JButton inputChooserButton;
	JLabel inputFileName;
	JPanel fileIO;
	
	JPanel top;
	
	JButton encrypt;
	JButton decrypt;
	JPanel bottom;

	JTextArea messages;
	
	JLabel symmetricLabel;
	JPasswordField symmetricKey;
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
	
	public void init() //creates buttons and windows
	{
		//This should make things look good for now on all Operating Systems
		//Later we will update the look and feel
		//Warning: If GodMode is enabled on your Windows 10 device this will cause problems.
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e2) {
			e2.printStackTrace();
		} catch (InstantiationException e2) {
			e2.printStackTrace();
		} catch (IllegalAccessException e2) {
			e2.printStackTrace();
		} catch (UnsupportedLookAndFeelException e2) {
			e2.printStackTrace();
		}

		symmetric = new JRadioButton("Symmetric Encryption");
		asymmetric = new JRadioButton("Asymmetric Encryption");
		encryptionType = new ButtonGroup();
		encryptionType.add(symmetric);
		encryptionType.add(asymmetric);
		encryptionSelection = new JPanel();
		encryptionSelection.add(symmetric);
		encryptionSelection.add(asymmetric);
		
		inputFileLabel = new JLabel("In File:", SwingConstants.CENTER);
		inputFileLabel2 = new JLabel("Selected File:", SwingConstants.CENTER);
		inputFileChooser = new JFileChooser();
		inputChooserButton = new JButton("Find");
		inputFileName = new JLabel("");

		fileIO = new JPanel();
		fileIO.setLayout(new GridLayout(2, 2));
		fileIO.add(inputFileLabel);
		fileIO.add(inputChooserButton);
		fileIO.add(inputFileLabel2);
		fileIO.add(inputFileName);
		
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

		messages = new JTextArea("Go to our GitHub project page to learn more on how to use this program.");
		messages.setEditable(false);
		
		symmetricLabel = new JLabel("Symmetric Key:");
		symmetricKey = new JPasswordField("", 30);
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
		container.setLayout(new GridLayout(4, 1));
		container.add(top);
		container.add(bottom);
		container.add(messages);
		
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
    public void update() //updated view for the model
    {
        if (model.getWindowToUse().equals("Symmetric")) //for symetric
        {
        	Component[] components = container.getComponents();
			for (int i = 0; i < components.length; i++)
			{
				container.remove(components[i]);
			}
			container.add(top);
			container.add(symmetricPanel);
			container.add(bottom);
			container.add(messages);
			container.revalidate();
			container.repaint();
			model.setSymmetric();
        }
        else if (model.getWindowToUse().equals("Asymmetric")) //for asymetric
        {
			Component[] components = container.getComponents();
			for (int i = 0; i < components.length; i++)
			{
				container.remove(components[i]);
			}
			container.add(top);
			container.add(asymmetricPanel);
			container.add(bottom);
			container.add(messages);
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
        else if (model.getWindowToUse().equals("In File")) //for infile
		{
			inputFileChooser.showOpenDialog(chooserFrame);
		}
		else if (model.getWindowToUse().equals("In File Chooser")) //for infile chooser
		{
			model.setInFile(inputFileChooser.getSelectedFile());

			//Gets the name of the input file so the user knows what file they are using.
			inputFileName.setText(model.getInFile().getName().toString());
		}
		else if (model.getWindowToUse().equals("Generate Keys:")) //for generate keys
		{
			try
			{
				model.sk.generatePublicPrivateKeys();
			} catch (NoSuchAlgorithmException e)
			{
				e.printStackTrace();
			}
		}
		else if (model.getWindowToUse().equals("Symmetric Button")) //symetric button
		{
			model.setSymmetricKey(symmetricKey.getText());
		}
		else if (model.getWindowToUse().equals("Public Key:")) //public key button
		{
			publicKeyChooser.showOpenDialog(chooserFrame);
		}
		else if (model.getWindowToUse().equals("Private Key:")) //for private key
		{
			privateKeyChooser.showOpenDialog(chooserFrame);
		}
		else if (model.getWindowToUse().equals("Public Key Chooser")) //for public key chooser
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
		else if (model.getWindowToUse().equals("Private Key Chooser")) // private key chooser
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
        else if (model.getWindowToUse().equals("Encrypt Button")) //encrypt button
		{
			System.out.println("Encrypt Button");
			model.encryptFile("Not Currently Implemented");
			messages.setText("Encrypted '" + model.getInFile().getName().toString() + "' with " + model.encMethod + " algorithm.");
		}
		else if (model.getWindowToUse().equals("Decrypt Button")) // for decrypt button
		{
			model.decryptFile("Not Currently Implemented");
			messages.setText("Decrypted '" + model.getInFile().getName().toString() + "' with " + model.encMethod + " algorithm.");
		}
    }
}
