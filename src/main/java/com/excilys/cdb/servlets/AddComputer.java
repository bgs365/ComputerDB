package com.excilys.cdb.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.CompanyService;

/**
 * Servlet implementation class AddComputer.
 */
@WebServlet("/addComputer")
public class AddComputer extends HttpServlet {
  private static final long serialVersionUID = 1L;
  static final Logger LOGGER = LoggerFactory.getLogger(AddComputer.class);
  private List<Company> companies = CompanyService.INSTANCE.findAll();

  /**
   * @see HttpServlet#HttpServlet()
   */
  public AddComputer() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   *
   * @param request
   *          a
   * @param response
   *          a
   * @throws ServletException
   *           a
   * @throws IOException
   *           a
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    /*
     * Send parameters.
     */
    request.setAttribute("companies", companies);
    /*
     * Transmission of data to jsp.
     */
    this.getServletContext().getRequestDispatcher("/WEB-INF/views/addComputer.jsp").forward(request, response);
  }

  /**
   *
   * @param request
   *          a
   * @param response
   *          a
   * @throws ServletException
   *           a
   * @throws IOException
   *           a
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    LOGGER.info("Creation of new computer");
    String name = (request.getParameter("computerName") != null) ? request.getParameter("computerName") : null;
    String receiveIntroduced = (request.getParameter("introduced") != null) ? request.getParameter("introduced")
        : "null";
    String receiveDiscontinued = (request.getParameter("discontinued") != null) ? request.getParameter("discontinued")
        : "null";
    String companyId = (request.getParameter("companyId") != null) ? request.getParameter("companyId") : "null";

    LocalDate introduced = null;
    LocalDate discontinued = null;
    Company company = new Company();
    SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yyyy");

    if (receiveIntroduced.equals("null")) {
      try {
        sdf.parse(receiveIntroduced);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("mm/dd/yyyy");
        introduced = LocalDate.parse(receiveIntroduced, formatter);
      } catch (ParseException e) {
        LOGGER.info("Error because of introduced date :" + e);
      }
    }

    if (!receiveDiscontinued.equals("null")) {
      try {
        sdf.parse(receiveDiscontinued);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("mm/dd/yyyy");
        discontinued = LocalDate.parse(receiveDiscontinued, formatter);
      } catch (ParseException e) {
        LOGGER.info("Error because of introduced date :" + e);
      }
    }

    if (companyId.equals("null")) {
      LOGGER.info("You are creating a computer whitout company");
    } else {
      company.setId(Integer.parseInt(companyId));
    }

    Computer computer = new Computer(0, name, introduced, discontinued, company);
    System.out.println(computer);

  }

  /**
   *
   * @param name
   * @return validComputer computerNameValidator
   */
  private boolean verifComputerNameBeforeSave(String name) {
    boolean validComputer = false;
    if (name.length() < 5) {
      LOGGER.info("Name too short :" + name);
    } else {
      validComputer = true;
    }
    return validComputer;
  }

}
