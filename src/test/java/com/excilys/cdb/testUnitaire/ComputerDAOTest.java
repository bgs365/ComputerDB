package com.excilys.cdb.testUnitaire;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.excilys.cdb.dao.ComputerDAO;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.service.ComputerService;

/**
 * Test computer persistance.
 *
 * @author sanogo
 *
 */
public class ComputerDAOTest {

  /**
   * init test data.
   */
  @Before
  public void init() {
    TransactionsOnTestData.INSTANCE.initData();
  }

  /**
   * destroy test data.
   */
  @After
  public void destroy() {
    TransactionsOnTestData.INSTANCE.destroyDate();
  }

  /**
   * Test find all method.
   */
  @Test
  public void testFindAll() {
    List<Computer> computers = ComputerDAO.INSTANCE.findAll();
    assertEquals(574, computers.size());
    assertEquals("MacBook Pro 15.4 inch", computers.get(0).getName());
    assertEquals("iPhone 4S", computers.get(computers.size() - 1).getName());
  }

  /**
   * Test find by id method.
   */
  @Test
  public void testFindbyId() {
    Computer computer = ComputerDAO.INSTANCE.findById(16).get();
    assertEquals("Apple II", computer.getName());
    assertEquals(LocalDate.parse("1977-04-01"), computer.getIntroduced());
    assertEquals(LocalDate.parse("1993-10-01"), computer.getDiscontinued());
    assertEquals(1, computer.getCompany().getId());
    computer = ComputerDAO.INSTANCE.findById(1000).get();
    assertFalse(computer.getId() != 0);
  }

  /**
   * Test find by name method.
   */
  @Test
  public void testFindbyName() {
    List<Computer> computers = ComputerDAO.INSTANCE.findByName("Apple II");
    assertEquals(8, computers.size());
    assertEquals(16, computers.get(6).getId());
    assertEquals(LocalDate.parse("1977-04-01"), computers.get(6).getIntroduced());
    assertEquals(LocalDate.parse("1993-10-01"), computers.get(6).getDiscontinued());
    assertEquals(1, computers.get(6).getCompany().getId());
    computers = ComputerDAO.INSTANCE.findByName("ce nom n'est pas dans la bdd");
    assertEquals(0, computers.size());
  }

  /**
   * Test find by company.
   */
  @Test
  public void testFindbyCompany() {
    List<Computer> computers = ComputerDAO.INSTANCE.findByCompany(1);
    /*
     * Le test est fait sur le 2e index de la requete suivante SELECT * FROM
     * computer LEFT JOIN company ON company.id = computer.company_id WHERE
     * computer.company_id = 1"
     */
    assertEquals(39, computers.size());
    assertEquals(6, computers.get(1).getId());
    assertEquals(LocalDate.parse("2006-01-10"), computers.get(1).getIntroduced());
    assertEquals(null, computers.get(1).getDiscontinued());
    assertEquals("MacBook Pro", computers.get(1).getName());

    computers = ComputerDAO.INSTANCE.findByCompany(1000);
    assertEquals(0, computers.size());
  }

  /**
   * Test find Limit Number Of Result method.
   */
  @Test
  public void findLimitNumberOfResult() {
    List<Computer> computers = ComputerDAO.INSTANCE.findLimitNumberOfResult(0, 10);
    assertEquals(10, computers.size());
    assertEquals("MacBook Pro 15.4 inch", computers.get(0).getName());
    assertEquals("Apple IIc Plus", computers.get(9).getName());
    computers = ComputerDAO.INSTANCE.findLimitNumberOfResult(569, 10);
    assertEquals(5, computers.size());
    assertEquals("HP Veer", computers.get(0).getName());
    assertEquals("iPhone 4S", computers.get(4).getName());
    computers = ComputerDAO.INSTANCE.findLimitNumberOfResult(1000, 10);
    assertEquals(0, computers.size());
  }
  
  /**
   * Test find Limit Number Of Result by name.
   */
  @Test
  public void findByComputerAndCompanyNameLimit() {
    List<Computer> computers = ComputerDAO.INSTANCE.findByComputerAndCompanyNameLimit("Apple", 0, 10);
    assertEquals(10, computers.size());
    assertEquals("Apple I", computers.get(0).getName());
    assertEquals("Apple Lisa", computers.get(9).getName());
    computers = ComputerDAO.INSTANCE.findByComputerAndCompanyNameLimit("Apple",40, 10);
    assertEquals(6, computers.size());
    assertEquals("Macintosh SE", computers.get(0).getName());
    assertEquals("Upcoming iPhone 5", computers.get(5).getName());
    computers = ComputerDAO.INSTANCE.findByComputerAndCompanyNameLimit("Apple",1000, 10);
    assertEquals(0, computers.size());
  }
  
  /**
   * Test find Limit Number Of Result by name.
   */
  @Test
  public void findByComputerAndCompanyName() {
    List<Computer> computers = ComputerDAO.INSTANCE.findByComputerAndCompanyName("Apple");
    assertEquals(46, computers.size());
    assertEquals("Apple I", computers.get(0).getName());
    assertEquals("Upcoming iPhone 5", computers.get(45).getName());
    computers = ComputerDAO.INSTANCE.findByComputerAndCompanyName("This name is not in database");
    assertEquals(0, computers.size());
  }

  /**
   * Test save computer.
   */
  @Test
  public void testSave() {
    Computer computer = new Computer();
    computer.setId(575);
    computer.setName("Test save computer");
    computer.setIntroduced(LocalDate.parse("2006-01-10"));
    computer.setDiscontinued(LocalDate.parse("2012-01-10"));
    computer.setCompany(CompanyService.INSTANCE.findById(1));
    ComputerDAO.INSTANCE.save(computer);
    assertNotNull(ComputerDAO.INSTANCE.findByCompany(575));

    computer.setId(576);
    computer.setCompany(CompanyService.INSTANCE.findById(50));
    ComputerDAO.INSTANCE.save(computer);
    assertNotNull(ComputerDAO.INSTANCE.findByCompany(576));
  }

  /**
   * Test update computer.
   */
  @Test
  public void testUpdate() {
    Computer computer = ComputerService.INSTANCE.findById(575);
    computer.setName("Test update computer");
    computer.setIntroduced(LocalDate.parse("2007-02-11"));
    computer.setDiscontinued(LocalDate.parse("2013-02-11"));
    computer.setCompany(CompanyService.INSTANCE.findById(2));
    ComputerDAO.INSTANCE.update(computer);
    assertEquals("Test update computer", computer.getName());
    assertEquals(LocalDate.parse("2007-02-11"), computer.getIntroduced());
    assertEquals(LocalDate.parse("2013-02-11"), computer.getDiscontinued());
    assertEquals(2, computer.getCompany().getId());
    
    computer = ComputerService.INSTANCE.findById(1);
    computer.setName("Test change macbook");
    computer.setCompany(null);
    assertEquals(1,ComputerDAO.INSTANCE.update(computer));
    assertEquals("Test change macbook", computer.getName());
    
    computer = ComputerService.INSTANCE.findById(2);
    computer.setName("Test change 2");
    computer.setCompany(new Company(0,null));
    assertEquals(1,ComputerDAO.INSTANCE.update(computer));
    assertEquals("Test change 2", computer.getName());
  }

  /**
   * Test delete computer.
   */
  @Test
  public void testdelete() {
    ComputerDAO.INSTANCE.delete(575);
    assertEquals(0, ComputerDAO.INSTANCE.findById(575).get().getId());
    assertEquals(null, ComputerDAO.INSTANCE.findById(575).get().getName());
  }

}