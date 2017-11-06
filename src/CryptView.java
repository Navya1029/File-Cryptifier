import java.awt.Component;
import java.awt.GridLayout;

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
 * @Date Updated: 11/6/17
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
		symmetricPanel.add(symmetricLabel);
		symmetricPanel.add(symmetricKey);
		
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
        }
        else
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
        }
    }
}
