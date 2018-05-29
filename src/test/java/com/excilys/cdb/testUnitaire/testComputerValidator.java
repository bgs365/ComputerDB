package com.excilys.cdb.testUnitaire;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excilys.cdb.exceptions.CdbException;
import com.excilys.cdb.exceptions.ComputerServiceDateException;
import com.excilys.cdb.exceptions.ComputerServiceIllegalExpression;
import com.excilys.cdb.exceptions.ComputerServiceNameTooShortException;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.springConfig.ApplicationConfig;
import com.excilys.cdb.validator.ComputerValidator;

/**
 * Class test Computer validator.
 *
 * @author sanogo
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
@Configuration
public class testComputerValidator {
	static final Logger LOGGER = LoggerFactory.getLogger(CompanyDAOTest.class);
	private ComputerValidator computerValidator = new ComputerValidator();
	private String name;
	private LocalDate introduced;
	private LocalDate discontinued;
	
	
	@After
  public void destroy() {
  	name = null;
  	introduced = null;
  	discontinued = null;
  }
	
	/**
	 * 
	 * @throws ComputerServiceNameTooShortException
	 */
	@Test(expected = ComputerServiceNameTooShortException.class)
	public void testVerifComputerNameBeforeSaveExecptionCase() throws ComputerServiceNameTooShortException {
		name = "";
		computerValidator.verifComputerNameBeforeSave(name);
	}
	
	/**
	 * test a right computerNAme.
	 * @throws ComputerServiceNameTooShortException 
	 */
	@Test
	public void testVerifComputerNameBeforeSaveRightCase() throws ComputerServiceNameTooShortException {
		name = "12345";
		assertEquals(true,computerValidator.verifComputerNameBeforeSave(name));
	}
	
	/**
	 * exception due to presence of an "<".
	 * @throws ComputerServiceIllegalExpression
	 */
	@Test(expected = ComputerServiceIllegalExpression.class)
	public void testVerifPresenceOfIllegalExpressionBeforeSaveExecptionCase1() throws ComputerServiceIllegalExpression {
		name = "<";
		computerValidator.verifPresenceOfIllegalExpressionBeforeSave(name);
	}
	
	/**
	 * exception due to presence of an ">".
	 * @throws ComputerServiceIllegalExpression
	 */
	@Test(expected = ComputerServiceIllegalExpression.class)
	public void testVerifPresenceOfIllegalExpressionBeforeSaveExecptionCase2() throws ComputerServiceIllegalExpression {
		name = ">";
		computerValidator.verifPresenceOfIllegalExpressionBeforeSave(name);
	}
	
	/**
	 * exception due to presence of an "//".
	 * @throws ComputerServiceIllegalExpression
	 */
	@Test(expected = ComputerServiceIllegalExpression.class)
	public void testVerifPresenceOfIllegalExpressionBeforeSaveExecptionCase3() throws ComputerServiceIllegalExpression {
		name = "//";
		computerValidator.verifPresenceOfIllegalExpressionBeforeSave(name);
	}
	
	/**
	 * exception due to presence of an "\\".
	 * @throws ComputerServiceIllegalExpression
	 */
	@Test(expected = ComputerServiceIllegalExpression.class)
	public void testVerifPresenceOfIllegalExpressionBeforeSaveExecptionCase4() throws ComputerServiceIllegalExpression {
		name = "\\";
		computerValidator.verifPresenceOfIllegalExpressionBeforeSave(name);
	}
	
	/**
	 * exception due to presence of an "/*".
	 * @throws ComputerServiceIllegalExpression
	 */
	@Test(expected = ComputerServiceIllegalExpression.class)
	public void testVerifPresenceOfIllegalExpressionBeforeSaveExecptionCase5() throws ComputerServiceIllegalExpression {
		name = "/*";
		computerValidator.verifPresenceOfIllegalExpressionBeforeSave(name);
	}
	
	/**
	 * exception due to presence of an "*\" .
	 * @throws ComputerServiceIllegalExpression
	 */
	@Test(expected = ComputerServiceIllegalExpression.class)
	public void testVerifPresenceOfIllegalExpressionBeforeSaveExecptionCase6() throws ComputerServiceIllegalExpression {
		name = "*/";
		computerValidator.verifPresenceOfIllegalExpressionBeforeSave(name);
	}
	
	/**
	 * right case .
	 * @throws ComputerServiceIllegalExpression
	 */
	@Test
	public void testVerifPresenceOfIllegalExpressionBeforeSaveRightCase() throws ComputerServiceIllegalExpression {
		name = "12345";
		computerValidator.verifPresenceOfIllegalExpressionBeforeSave(name);
	}
	
	/**
	 * presence of discounted date without introduced date.
	 * @throws ComputerServiceDateException
	 */
	@Test(expected = ComputerServiceDateException.class)
	public void testVerifDateExceptionCase1() throws ComputerServiceDateException {
		discontinued = LocalDate.parse("2007-02-11");
		computerValidator.verifDate(introduced, discontinued);
	}
	
	/**
	 * presence of discounted date is before introduced date.
	 * @throws ComputerServiceDateException
	 */
	@Test(expected = ComputerServiceDateException.class)
	public void testVerifDateExceptionCase2() throws ComputerServiceDateException {
		introduced = LocalDate.parse("2007-02-12");
		discontinued = LocalDate.parse("2007-02-11");
		computerValidator.verifDate(introduced, discontinued);
	}

	/**
	 * right case .
	 * @throws ComputerServiceIllegalExpression
	 * @throws ComputerServiceDateException 
	 */
	@Test
	public void testVerifDateRightCases() throws ComputerServiceDateException {
		assertEquals(true, computerValidator.verifDate(introduced, discontinued));
		introduced = LocalDate.parse("2007-02-12");
		discontinued = LocalDate.parse("2007-02-13");
		assertEquals(true,computerValidator.verifDate(introduced, discontinued));
		introduced = LocalDate.parse("2007-02-12");
		discontinued = null;
		assertEquals(true,computerValidator.verifDate(introduced, discontinued));
	}
	
	/**
	 * right case .
	 * @throws CdbException 
	 */
	@Test
	public void testVerifComputer() throws CdbException {
		Computer computer = new Computer();
		computer.setId(1);
		computer.setName("test1");;
		introduced = LocalDate.parse("2007-02-12");
		discontinued = LocalDate.parse("2007-02-13");
		computer.setIntroduced(introduced);
		computer.setDiscontinued(discontinued);
		assertEquals(true, computerValidator.verifComputer(computer));
	}
	
	/**
	 * name too short for computer.
	 * @throws CdbException
	 */
	@Test(expected = ComputerServiceNameTooShortException.class)
	public void testVerifComputerExceptionNameCase1() throws CdbException {
		Computer computer = new Computer();
		computer.setId(1);
		computer.setName("test");;
		introduced = LocalDate.parse("2007-02-12");
		discontinued = LocalDate.parse("2007-02-13");
		computer.setIntroduced(introduced);
		computer.setDiscontinued(discontinued);
		computerValidator.verifComputer(computer);
	}
	
	/**
	 * name is blanked.
	 * @throws CdbException
	 */
	@Test(expected = ComputerServiceNameTooShortException.class)
	public void testVerifComputerExceptionNameCase2() throws CdbException {
		Computer computer = new Computer();
		computer.setId(1);
		computer.setName("");;
		introduced = LocalDate.parse("2007-02-12");
		discontinued = LocalDate.parse("2007-02-13");
		computer.setIntroduced(introduced);
		computer.setDiscontinued(discontinued);
		computerValidator.verifComputer(computer);
	}
	
}
