import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @Authors: Tyler, Matt, Daniel
 * @Date Updated: 11/27/17
 * @Model_Used: Model-View-Controller
 *
 * The controller interprets the mouse and keyboard inputs from the user,
 * informing the model and/or the view to change as appropriate.
 */
public class CryptController
{
    private CryptModel model;
    private CryptView   view;
    ComponentListener listener = new ComponentListener();


    public static void main(String[] args)
    {
        new CryptController();
    }

    public void addListeners()
    {
        view.asymmetric.addActionListener(listener);
        view.symmetric.addActionListener(listener);
        view.inputChooserButton.addActionListener(listener);
        view.inputFileChooser.addActionListener(listener);
        view.symmetricKey.addActionListener(listener);
        view.symmetricButton.addActionListener(listener);
        view.generateKeys.addActionListener(listener);
        view.publicKeyButton.addActionListener(listener);
        view.privateKeyButton.addActionListener(listener);
        view.publicKeyChooser.addActionListener(listener);
        view.privateKeyChooser.addActionListener(listener);
        view.encrypt.addActionListener(listener);
        view.decrypt.addActionListener(listener);

    }

    /**
     * Constructor to setup MVC
     */
    public CryptController()
    {
        model = new CryptModel();
        view  = new CryptView(model);
        addListeners();
    }


    /**
     * Method that updates the view of CryptView
     */
    public void updateView()
    {
        view.update();
    }

    class ComponentListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == view.symmetric)  //Symetric key Button
            {
                model.setWindowToUse("Symmetric");
                updateView();
            }
            else if (e.getSource() == view.asymmetric) //Asymetric Key button
            {
                model.setWindowToUse("Asymmetric");
                updateView();
            }

            else if(e.getSource() == view.inputChooserButton) //Input File Chooser Button
            {
                model.setWindowToUse("In File");
                updateView();
            }
            else if(e.getSource() == view.inputFileChooser) //Input File Chooser
            {
                model.setWindowToUse("In File Chooser");
                updateView();
            }
            else if(e.getSource() == view.symmetricButton)
            {
                model.setWindowToUse("Symmetric Button");
                updateView();
            }
            else if(e.getSource() == view.generateKeys)//Generate keys button
            {
                model.setWindowToUse("Generate Keys:");
                updateView();
            }
            else if(e.getSource() == view.publicKeyButton) //Public Key Button
            {
                model.setWindowToUse("Public Key:");
                updateView();
            }
            else if(e.getSource() == view.privateKeyButton) //Public Key Button
            {
                model.setWindowToUse("Private Key:");
                updateView();
            }
            else if(e.getSource() == view.publicKeyChooser) //Public Key Button
            {
                model.setWindowToUse("Public Key Chooser");
                updateView();
            }
            else if(e.getSource() == view.privateKeyChooser) //Public Key Button
            {
                model.setWindowToUse("Private Key Chooser");
                updateView();
            }
            else if(e.getSource() == view.encrypt)
            {
                model.setWindowToUse("Encrypt Button");
                updateView();
            }
            else if(e.getSource() == view.decrypt)
            {
                model.setWindowToUse("Decrypt Button");
                updateView();
            }

        }
    }

}

