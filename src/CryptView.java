import javax.swing.*;

/**
 * @Authors: Tyler, Matt, Daniel
 * @Date Updated: 11/6/17
 * @Model_Used: Model-View-Controller
 *
 * The view manages the display of information.
 */
public class CryptView extends JFrame
{
    private CryptModel model;

    /**
     * Constructor that gets the model from the Controller
     * @param model
     */
    public CryptView(CryptModel model)
    {
        this.model = model;
    }

    /**
     * Method used to update the view
     */
    public void update()
    {
        if (model.getWindowToUse().equals("Symmetric"))
        {
            //Do Symmetric stuff
        }
        else
        {
            //Do Asymmetric stuff
        }
    }
}
