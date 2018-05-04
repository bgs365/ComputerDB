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
	private Computer computer = new Computer();
	private Computer oldComputer = new Computer();
	private List<Company> companies = companyService.findAll();
	private boolean firstConnection = true;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditComputer() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ReceiveComputerId = null;
		if(firstConnection) {
			ReceiveComputerId = (request.getParameter("ComputerId") != null) ? request.getParameter("ComputerId") : "0";
			LOGGER.info(ReceiveComputerId);
			int computerId = Integer.parseInt(ReceiveComputerId);
			oldComputer = computerService.findById(computerId);
	
			request.setAttribute("computer", oldComputer);
			request.setAttribute("company", computer.getCompany());
		}
		
		request.setAttribute("companies", companies);
		this.getServletContext().getRequestDispatcher("/WEB-INF/views/editComputer.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		firstConnection = false;
		String receiveId = null;
		String name = null;
		String receiveIntroduced = null;
		String receiveDiscontinued = null;
		Company receiveCompany = null;
		boolean success = false;
		String errors = "";
		LOGGER.info("Creation of new computer");
		receiveId = (request.getParameter("computerId") != null) ? request.getParameter("computerId") : "null";
		name = (request.getParameter("computerName") != null) ? request.getParameter("computerName") : "null";
		receiveIntroduced = (request.getParameter("introduced") != null) ? request.getParameter("introduced") : "null";
		receiveDiscontinued = (request.getParameter("discontinued") != null) ? request.getParameter("discontinued")
		    : "null";
		String companyId = (request.getParameter("companyId") != null) ? request.getParameter("companyId") : "null";

		LocalDate introduced = null;
		LocalDate discontinued = null;
		int id = 0;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		if (receiveId.equals("null")) {
			LOGGER.info("id non valid");
		} else {
			id = Integer.parseInt(receiveId);
		}

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

		Computer computer = new Computer(id, name, introduced, discontinued, receiveCompany);
		LOGGER.info(computer + " ");
		try {
			LOGGER.info(computer + "");
			if (ComputerService.INSTANCE.update(computer) == 1) {
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
			request.setAttribute("computer", computer);
			request.setAttribute("success", success);
			request.setAttribute("errors", errors);
			doGet(request, response);
		}
	}

}
