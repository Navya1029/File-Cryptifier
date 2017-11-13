import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @Authors: Tyler, Matt, Daniel
 * @Date Updated: 11/13/17
 * @Model_Used: Model-View-Controller
 *
 * The controller interprets the mouse and keyboard inputs from the user,
 * informing the model and/or the view to change as appropriate.
 */
public class CryptController implements ActionListener
{
    private CryptModel model;
    private CryptView   view;

    /**
     * Constructor to setup MVC
     */
    public CryptController()
    {
        model = new CryptModel();
        view  = new CryptView(model);
    }

    /**
     * Method that updates the view of CryptView
     */
    public void updateView()
    {
        view.update();
    }

    @Override
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

      else if(e.getSource() == view.inputChooserButton) //Input Button
        {
            model.setWindowToUse("In File");
            updateView();
        }
        else if(e.getSource() == view.outputChooserButton)//Output button
        {
            model.setWindowToUse("Out File");
            updateView();
        }
        else if(e.getSource() == view.encrypt)//Encrypt
        {
            model.setWindowToUse("encrypt");
            updateView();
        }
        else if(e.getSource() == view.decrypt) //Decrypt
        {
            model.setWindowToUse("decrypt");
            updateView();
        }
        else if(e.getSource() == view.generateKeys)//Generate keys button
        {
            model.setWindowToUse("Generate Keys:");
            updateView();
        }

    }
}

