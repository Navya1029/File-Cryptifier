/**
 * @Authors: Tyler, Matt, Daniel
 * @Date Updated: 11/6/17
 * @Model_Used: Model-View-Controller
 *
 * The controller interprets the mouse and keyboard inputs from the user,
 * informing the model and/or the view to change as appropriate.
 */
public class CryptController
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
}
