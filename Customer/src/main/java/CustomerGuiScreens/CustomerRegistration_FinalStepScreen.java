package CustomerGuiScreens;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextArea;

import BasicCommonClasses.CustomerProfile;
import BasicCommonClasses.ICustomerProfile;
import CustomerContracts.ICustomer;
import CustomerGuiHelpers.CustomerGuiExceptionsHandler;
import CustomerGuiHelpers.TempCustomerProfilePassingData;
import CustomerImplementations.Customer;
import GuiUtils.AbstractApplicationScreen;
import GuiUtils.TextFileReader;
import SMExceptions.SMException;
import UtilsImplementations.InjectionFactory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;


/**
 * CustomerRegistration_FinalStepScreen - Controller for the third and last screen of the customer registration screens.
 * 											This screen handle the terms agreement and the registration action.
 * 
 * @author Lior Ben Ami
 * @since 2017-04
 */
public class CustomerRegistration_FinalStepScreen implements Initializable {

	private static String termsFilePath = "src/main/resources/CustomerRegistrationScreens/terms.txt";
	
	@FXML
	private GridPane finalStepScreenPane;
	
    @FXML
    private JFXButton final_backButton;

    @FXML
    private JFXCheckBox final_acceptChecKBox;

    @FXML
    private JFXButton registerButton;

    @FXML
    private JFXTextArea termsTextArea;
    
    @FXML
    void backButtonPressed(ActionEvent event) {
		AbstractApplicationScreen.setScene("/CustomerRegistrationScreens/CustomerRegistration_IngredientsScreen.fxml");
    }

    @Override
   	public void initialize(URL location, ResourceBundle resources) {
   		AbstractApplicationScreen.fadeTransition(finalStepScreenPane);
   		loadTerms();
   		registerButton.setDisable(true);
   		final_acceptChecKBox.selectedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (newValue) 
					registerButton.setDisable(false);
				else
					registerButton.setDisable(true);
			}
   		
   		});
    }
    
    
    private void loadTerms() {
    	
    	TextFileReader reader = new TextFileReader();
    	List<String> lines = null;
    	try {
			lines = reader.read(new File(termsFilePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	for (String line: lines ) {
    		termsTextArea.appendText(line + "\n");
    	}
	}

	@FXML
    void registerButtonPressed(ActionEvent event) {
		ICustomer customer = InjectionFactory.getInstance(Customer.class);
		ICustomerProfile iProfile = TempCustomerProfilePassingData.customerProfile;
		CustomerProfile profile = new CustomerProfile(iProfile.getUserName(),TempCustomerProfilePassingData.password, iProfile.getFirstName(),
				iProfile.getLastName(), iProfile.getPhoneNumber(), iProfile.getEmailAddress(), iProfile.getCity(), iProfile.getStreet(), 
				iProfile.getBirthdate(), iProfile.getAllergens(), null);
		try {
			customer.registerNewCustomer(profile);
			AbstractApplicationScreen.setScene("/CustomerLoginScreen/CustomerLoginScreen.fxml");
		} catch (SMException e) {
			CustomerGuiExceptionsHandler.handle(e);	
		}
		
	}
}
