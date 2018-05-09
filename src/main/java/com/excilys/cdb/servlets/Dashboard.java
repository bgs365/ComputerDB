package com.excilys.cdb.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.page.Page;
import com.excilys.cdb.service.ComputerService;

/**
 * Servlet implementation class Dashboard.
 */
@WebServlet("/dashboard")
public class Dashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private int nombrElementPerPage = 10;
	private int numberOfComputers = 0;
	private ComputerService computerService = ComputerService.INSTANCE;
	private List<Computer> computers;
	private Page<Computer> computerPage;
	private String search = "";

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

		/*
		 * Instantiation of parameters to send to jsp.
		 */

		String pageNumber = "";
		String button = "";

		pageNumber = (request.getParameter("pageNumber") != null) ? request.getParameter("pageNumber") : "null";

		button = (request.getParameter("buttonSetNumberElementDisplayed") != null)
		    ? request.getParameter("buttonSetNumberElementDisplayed")
		    : "null";

		search = (request.getParameter("search") != null) ? request.getParameter("search") : "";

		setPageContent(search, 0, nombrElementPerPage);

		computerPage = new Page<Computer>(computers, nombrElementPerPage, numberOfComputers);

		switchNumberOfElementsByPage(button);

		/* Switch page */
		if (!pageNumber.equals("null")) {
			computerPage.setCurentPage(Integer.parseInt(pageNumber));
			setPageContent(search, computerPage.getIndexFirstPageElement(), computerPage.getNombreElementParPage());
		}

		int numberTotalOfPages = (int) Math
		    .ceil(computerPage.getNombreElementTotal() / Double.valueOf(computerPage.getNombreElementParPage()));

		/*
		 * Send parameters.
		 */

		request.setAttribute("search", search);
		request.setAttribute("numberElementPerPage", nombrElementPerPage);
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
		if (computerService.delete(computerId) != 0) {
			deleteState = "Delete success";
			LOGGER.info(deleteState);
		} else {
			LOGGER.info(deleteState);
		}
		request.setAttribute("deleteState", deleteState);
		doGet(request, response);
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
				nombrElementPerPage = 10;
			break;

			case "50":
				nombrElementPerPage = 50;
			break;

			case "100":
				nombrElementPerPage = 100;
			break;
		}
		computerPage.setNombreElementParPage(nombrElementPerPage);

	}

	/**
	 * 
	 * @param pSearch
	 *          name of computer or company
	 * @param pIndexFirstPageElement
	 *          asName
	 * @param pNombrElementPerPageasName
	 */
	private void setPageContent(String pSearch, int pIndexFirstPageElement, int pNombrElementPerPage) {
		if (!search.equals("")) {
			numberOfComputers = computerService.findByComputerAndCompanyName(pSearch).size();
			computers = computerService.findByComputerAndCompanyNameLimit(pSearch, pIndexFirstPageElement,
			    nombrElementPerPage);

		} else {
			numberOfComputers = computerService.findAll().size();
			computers = computerService.findLimitNumberOfResult(pIndexFirstPageElement, pNombrElementPerPage);

		}
	}

}