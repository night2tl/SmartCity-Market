package ManagerUnitTests;

import static org.junit.Assert.*;

import java.io.IOException;

import org.apache.log4j.PropertyConfigurator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import BasicCommonClasses.Ingredient;
import ClientServerApi.CommandDescriptor;
import ClientServerApi.CommandWrapper;
import ClientServerApi.ResultDescriptor;
import EmployeeContracts.IManager;
import EmployeeDefs.AEmployeeException.ConnectionFailure;
import EmployeeDefs.AEmployeeException.CriticalError;
import EmployeeDefs.AEmployeeException.InvalidParameter;
import EmployeeDefs.AEmployeeException.ParamIDDoesNotExist;
import EmployeeDefs.AEmployeeException.EmployeeNotConnected;
import EmployeeDefs.AEmployeeException.IngredientStillInUse;
import EmployeeDefs.WorkerDefs;
import EmployeeImplementations.Manager;
import UtilsContracts.IClientRequestHandler;
import UtilsImplementations.Serialization;

/**
 * @author Aviad Cohen
 * @since 2016-04-24 */

@RunWith(MockitoJUnitRunner.class)
public class RemoveIngredientTest {
	private IManager manager;
	@Mock
	private IClientRequestHandler clientRequestHandler;

	@Before
	public void setup() {
		PropertyConfigurator.configure("../log4j.properties");
		manager = new Manager(clientRequestHandler);
	}
	
	static Ingredient newIngredient = new Ingredient(0, "FOLL");
	
	@Test
	public void RemoveIngredientSuccesfulTest() {
		try {
			Mockito.when(clientRequestHandler.sendRequestWithRespond(
					new CommandWrapper(WorkerDefs.loginCommandSenderId, CommandDescriptor.REMOVE_INGREDIENT,
							Serialization.serialize(newIngredient)).serialize()))
					.thenReturn(new CommandWrapper(ResultDescriptor.SM_OK).serialize());
		} catch (IOException e) {
			
			fail();
		}
		
		try {
			manager.removeIngredient(newIngredient, false);
		} catch (InvalidParameter | CriticalError | EmployeeNotConnected | ConnectionFailure | ParamIDDoesNotExist | IngredientStillInUse e1) {
			
			fail();
		}
	}

	@Test
	public void RemoveIngredientInvalidParameterTest() {
		try {
			Mockito.when(clientRequestHandler.sendRequestWithRespond(
					new CommandWrapper(WorkerDefs.loginCommandSenderId, CommandDescriptor.REMOVE_INGREDIENT,
							Serialization.serialize(newIngredient)).serialize()))
					.thenReturn(new CommandWrapper(ResultDescriptor.SM_INVALID_PARAMETER).serialize());
		} catch (IOException e) {
			
			fail();
		}
		
		try {
			manager.removeIngredient(newIngredient, false);
		} catch (InvalidParameter e) {
			/* success */
		} catch (CriticalError | EmployeeNotConnected | ConnectionFailure | ParamIDDoesNotExist | IngredientStillInUse e1) {
			
			fail();
		}
	}
	
	@Test
	public void RemoveIngredientCriticalErrorTest() {
		try {
			Mockito.when(clientRequestHandler.sendRequestWithRespond(
					new CommandWrapper(WorkerDefs.loginCommandSenderId, CommandDescriptor.REMOVE_INGREDIENT,
							Serialization.serialize(newIngredient)).serialize()))
					.thenReturn(new CommandWrapper(ResultDescriptor.SM_ERR).serialize());
		} catch (IOException e) {
			
			fail();
		}
		
		try {
			manager.removeIngredient(newIngredient, false);
		} catch (CriticalError e) {
			/* success */
		} catch (InvalidParameter | EmployeeNotConnected | ConnectionFailure | ParamIDDoesNotExist | IngredientStillInUse e1) {
			
			fail();
		}
	}
	
	@Test
	public void RemoveIngredientEmployeeNotConnectedTest() {
		try {
			Mockito.when(clientRequestHandler.sendRequestWithRespond(
					new CommandWrapper(WorkerDefs.loginCommandSenderId, CommandDescriptor.REMOVE_INGREDIENT,
							Serialization.serialize(newIngredient)).serialize()))
					.thenReturn(new CommandWrapper(ResultDescriptor.SM_SENDER_IS_NOT_CONNECTED).serialize());
		} catch (IOException e) {
			
			fail();
		}
		
		try {
			manager.removeIngredient(newIngredient, false);
		} catch (EmployeeNotConnected e) {
			/* success */
		} catch (InvalidParameter | CriticalError | ConnectionFailure | ParamIDDoesNotExist | IngredientStillInUse e1) {
			
			fail();
		}
	}
	
	@Test
	public void RemoveIngredientParamIDDoesNotExistTest() {
		try {
			Mockito.when(clientRequestHandler.sendRequestWithRespond(
					new CommandWrapper(WorkerDefs.loginCommandSenderId, CommandDescriptor.REMOVE_INGREDIENT,
							Serialization.serialize(newIngredient)).serialize()))
					.thenReturn(new CommandWrapper(ResultDescriptor.PARAM_ID_IS_NOT_EXIST).serialize());
		} catch (IOException e) {
			
			fail();
		}
		
		try {
			manager.removeIngredient(newIngredient, false);
		} catch (ParamIDDoesNotExist e) {
			/* success */
		} catch (InvalidParameter | CriticalError | ConnectionFailure | EmployeeNotConnected | IngredientStillInUse e1) {
			
			fail();
		}
	}
	
	@Test
	public void RemoveIngredientIngredientStillInUseTest() {
		try {
			Mockito.when(clientRequestHandler.sendRequestWithRespond(
					new CommandWrapper(WorkerDefs.loginCommandSenderId, CommandDescriptor.REMOVE_INGREDIENT,
							Serialization.serialize(newIngredient)).serialize()))
					.thenReturn(new CommandWrapper(ResultDescriptor.SM_INGREDIENT_STILL_IN_USE).serialize());
		} catch (IOException e) {
			
			fail();
		}
		
		try {
			manager.removeIngredient(newIngredient, false);
		} catch (IngredientStillInUse e) {
			/* success */
		} catch (InvalidParameter | CriticalError | ConnectionFailure | EmployeeNotConnected | ParamIDDoesNotExist e1) {
			
			fail();
		}
	}
	
	@Test
	public void RemoveIngredientIllegalResultTest() {
		try {
			Mockito.when(clientRequestHandler.sendRequestWithRespond(
					new CommandWrapper(WorkerDefs.loginCommandSenderId, CommandDescriptor.REMOVE_INGREDIENT,
							Serialization.serialize(newIngredient)).serialize()))
					.thenReturn(new CommandWrapper(ResultDescriptor.SM_MANUFACTURER_STILL_IN_USE).serialize());
		} catch (IOException e) {
			
			fail();
		}
		
		try {
			manager.removeIngredient(newIngredient, false);
		} catch (CriticalError e) {
			/* success */
		} catch (InvalidParameter | IngredientStillInUse | ConnectionFailure | EmployeeNotConnected | ParamIDDoesNotExist e1) {
			
			fail();
		}
	}
}