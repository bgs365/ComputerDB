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
 * Servlet implementation class AddComputer.
 */
@WebServlet("/addComputer")
public class AddComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<Company> companies;

	static final Logger LOGGER = LoggerFactory.getLogger(AddComputer.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddComputer() {
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
		request.setAttribute("companies", companies);
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
		companies = CompanyService.INSTANCE.findAll();
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

		if (receiveIntroduced.equals("null") || (receiveIntroduced.length() < 10)) {
			LOGGER.info("No introduced date");
		} else {
			introduced = LocalDate.parse(receiveIntroduced, formatter);
		}

		if (receiveDiscontinued.equals("null") || (receiveDiscontinued.length() < 10)) {
			LOGGER.info("No discontinued date");
		} else {
			discontinued = LocalDate.parse(receiveDiscontinued, formatter);
		}

		try {
			receiveCompany = CompanyService.INSTANCE.findById(Integer.parseInt(companyId));
		} catch (NumberFormatException e) {
			LOGGER.info("you are creating a computer whitout company" + e);
		}

		Computer computer = new Computer(0, name, introduced, discontinued, receiveCompany);
		LOGGER.info(computer + " ");
		try {
			LOGGER.info(computer + "");
			if (ComputerService.INSTANCE.save(computer) == 1) {
				success = true;
				LOGGER.info(computer + "");
			} else {
				success = false;
			}
		} catch (CdbException e) {
			errors += e.getMessage();
		}

		if (success) {
			request.setAttribute("success", success);
			doGet(request, response);
		} else {
			request.setAttribute("computerName", name);
			request.setAttribute("introduced", receiveIntroduced);
			request.setAttribute("discontinued", receiveDiscontinued);
			request.setAttribute("company", receiveCompany);
			request.setAttribute("success", success);
			request.setAttribute("errors", errors);
			doGet(request, response);
		}

	}

}
