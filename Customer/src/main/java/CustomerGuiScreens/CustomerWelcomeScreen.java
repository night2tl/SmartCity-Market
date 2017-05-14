package CustomerGuiScreens;

import java.net.URL;
import java.util.ResourceBundle;

import GuiUtils.AbstractApplicationScreen;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

/** 
 * CustomerWelcomeScreen - This class is the controller for the customer welcome screen
 * all action of this scene should be here.
 * 
 * @author Lior Ben Ami
 * @since 2017-01-16 */
public class CustomerWelcomeScreen implements Initializable {

	@FXML
	private StackPane customerWelcomeScreenPane;

	@Override
	public void initialize(URL location, ResourceBundle __) {
		AbstractApplicationScreen.fadeTransition(customerWelcomeScreenPane);   
	}

	@FXML
	public void mouseClicked(MouseEvent __) {
//		ICustomer customer = InjectionFactory.getInstance(Customer.class);
//		try {
//			customer.login("Cart", "Cart");
//		} catch (SMException e) {
//			CustomerGuiExceptionsHandler.handle(e);	
//			return;
//		}
//		catch (Exception e) {
//			Alert alert = new Alert(AlertType.ERROR , e + "");
//			alert.showAndWait();
//			return;
//		}
//		TempCustomerPassingData.customer = customer;
		AbstractApplicationScreen.setScene("/CustomerLoginScreen/CustomerLoginScreen.fxml");
	}
}
