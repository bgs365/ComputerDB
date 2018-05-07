package com.excilys.cdb.servlets;

import java.io.IOException;
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

import com.excilys.cdb.exceptions.CdbException;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.service.ComputerService;

/**
 * Servlet implementation class EditComputer
 */
@WebServlet("/editComputer")
public class EditComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final Logger LOGGER = LoggerFactory.getLogger(EditComputer.class);
	private ComputerService computerService = ComputerService.INSTANCE;
	private CompanyService companyService = CompanyService.INSTANCE;
	// private int computerId = 0;
	// private Computer computer = new Computer();
	private List<Company> companies = companyService.findAll();
	// private boolean premiereConexion = true;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditComputer() {
		super();
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

		String receiveComputerIdFromDashboard = null;
		receiveComputerIdFromDashboard = (request.getParameter("ComputerToModifie") != null)
		    ? request.getParameter("ComputerToModifie")
		    : "0";
		if (!receiveComputerIdFromDashboard.equals("0")) {
			int computerId = Integer.parseInt(receiveComputerIdFromDashboard);
			Computer computer = computerService.findById(computerId);
			request.setAttribute("computer", computer);
		}

		request.setAttribute("companies", companies);
		this.getServletContext().getRequestDispatcher("/WEB-INF/views/editComputer.jsp").forward(request, response);
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

		String receiveComputerId = null;
		receiveComputerId = (request.getParameter("computerId") != null) ? request.getParameter("computerId") : "0";
		LOGGER.info(receiveComputerId);
		int computerId = Integer.parseInt(receiveComputerId);
		Computer computer = computerService.findById(computerId);

		String name = null;
		String receiveIntroduced = null;
		String receiveDiscontinued = null;
		Company receiveCompany = null;
		boolean success = false;
		String errors = "";
		LOGGER.info("Creation of new computer");
		name = (request.getParameter("computerName") != null) ? request.getParameter("computerName") : "null";
		receiveIntroduced = (request.getParameter("introduced") != null) ? request.getParameter("introduced") : "null";
		receiveDiscontinued = (request.getParameter("discontinued") != null) ? request.getParameter("discontinued")
		    : "null";
		String companyId = (request.getParameter("companyId") != null) ? request.getParameter("companyId") : "null";

		LocalDate introduced = null;
		LocalDate discontinued = null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		computer.setName(name);

		if (receiveIntroduced.equals("null") || (receiveIntroduced.length() < 10)) {
			LOGGER.info("No introduced date");
			introduced = null;
		} else {
			introduced = LocalDate.parse(receiveIntroduced, formatter);	
		}
		computer.setIntroduced(introduced);

		if (receiveDiscontinued.equals("null") || (receiveDiscontinued.length() < 10)) {
			LOGGER.info("No discontinued date");
			discontinued = null;
		} else {
			discontinued = LocalDate.parse(receiveDiscontinued, formatter);
		}
		computer.setDiscontinued(discontinued);

		try {
			receiveCompany = CompanyService.INSTANCE.findById(Integer.parseInt(companyId));
			LOGGER.info(receiveCompany + "");
			computer.setCompany(receiveCompany);
		} catch (NumberFormatException e) {
			LOGGER.info("you are creating a computer whitout company" + e);
		}

		LOGGER.debug(computer + "");
		try {
			LOGGER.info(computer + "");
			if (ComputerService.INSTANCE.update(computer) != 0) {
				success = true;
			} else {
				success = false;
			}
		} catch (CdbException e) {
			errors += e.getMessage();
		}

		request.setAttribute("success", success);
		request.setAttribute("computer", computer);

		if (success) {
			doGet(request, response);
		} else {
			request.setAttribute("errors", errors);
			doGet(request, response);
		}
	}

}
