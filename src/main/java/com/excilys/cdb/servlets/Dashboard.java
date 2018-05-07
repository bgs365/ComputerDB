package com.excilys.cdb.servlets;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
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
import com.excilys.cdb.page.Page;
import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.service.ComputerService;

/**
 * Servlet implementation class Dashboard.
 */
@WebServlet("/dashboard")
public class Dashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;

	int nombrElementParPage = 50;
	private ComputerService computerService = ComputerService.INSTANCE;
	private CompanyService companyService = CompanyService.INSTANCE;
	private List<Computer> computers ;
	private Page<Computer> computerPage ;

	static final Logger LOGGER = LoggerFactory.getLogger(Dashboard.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Dashboard() {
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
		
		computers = computerService.findLimitNumberOfResult(0, nombrElementParPage);
		computerPage = new Page<Computer>(computers, nombrElementParPage,
		    ComputerService.INSTANCE.findAll().size());
		/*
		 * Instantiation of parameters to send to jsp.
		 */
		int numberOfComputers = computerService.findAll().size();
		String pageNumber = "";
		String button = "";
		String search;

		/*
		 * Admission of parameters send by jsp.
		 */

		pageNumber = (request.getParameter("pageNumber") != null) ? request.getParameter("pageNumber") : "null";

		button = (request.getParameter("buttonSetNumberElementDisplayed") != null)
		    ? request.getParameter("buttonSetNumberElementDisplayed")
		    : "null";
		    
    search = (request.getParameter("search") != null) ? request.getParameter("search") : "null";
    
    if(!search.equals("null")) {
    	computers = computerService.FindByComputerAndCompanyName(search);
    	
    	numberOfComputers = computers.size();
    	computerPage.setCurentPage(1);
    	computerPage = new Page<Computer>(computers, numberOfComputers,numberOfComputers);
    	
    }

		/*
		 * Treatment of send parameters.
		 */

		/* Switch page by number */
		if (!pageNumber.equals("null")) {
			computerPage.setCurentPage(Integer.parseInt(pageNumber));
			computers = computerService.findLimitNumberOfResult(computerPage.getIndexFirstPageElement(),
			    computerPage.getNombreElementParPage());
		}

		switchNumberOfElementsByPage(button);

		int numberTotalOfPages = (int) Math
		    .ceil(computerPage.getNombreElementTotal() / Double.valueOf(computerPage.getNombreElementParPage()));

		/*
		 * Send parameters.
		 */
		
		request.setAttribute("search", search);
		request.setAttribute("numberOfComputers", numberOfComputers);
		request.setAttribute("computers", computers);
		request.setAttribute("computerPage", computerPage);
		request.setAttribute("numberTotalOfPages", numberTotalOfPages);

		/*
		 * Transmission of data to jsp.
		 */
		this.getServletContext().getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request, response);
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
		String deleteState = "Delete fail";
		String selection = "";
		selection = (request.getParameter("selection") != null) ? request.getParameter("selection") : "null";
		int computerId = Integer.parseInt(selection);
		LOGGER.info(selection+" "+computerId);
		if (computerService.delete(computerId) != 0) {
			deleteState = "Delete success";
			LOGGER.info(deleteState);
		}else {
			LOGGER.info(deleteState);
		}
		request.setAttribute("deleteState", deleteState);
		doGet(request,response);
	}

	/**
	 * Change number of displayed elements.
	 *
	 * @param button
	 *          numberOfPages
	 */
	private void switchNumberOfElementsByPage(String button) {
		switch (button) {
			case "10":
				computerPage.setNombreElementParPage(10);
				computers = computerService.findLimitNumberOfResult(computerPage.getIndexFirstPageElement(),
				    computerPage.getNombreElementParPage());
			break;

			case "50":
				computerPage.setNombreElementParPage(50);
				computers = computerService.findLimitNumberOfResult(computerPage.getIndexFirstPageElement(),
				    computerPage.getNombreElementParPage());
			break;

			case "100":
				computerPage.setNombreElementParPage(100);
				computers = computerService.findLimitNumberOfResult(computerPage.getIndexFirstPageElement(),
				    computerPage.getNombreElementParPage());
			break;
		}
	}
	
	

}