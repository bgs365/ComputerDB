package com.excilys.cdb.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.page.Page;
import com.excilys.cdb.service.ComputerService;

/**
 * Servlet implementation class Dashboard.
 */
@WebServlet("/dashboard")
public class Dashboard extends HttpServlet {
  private static final long serialVersionUID = 1L;

  int nombrElementParPage = 100;
  List<Computer> computers = ComputerService.INSTANCE.findLimitNumberOfResult(0, nombrElementParPage);
  Page<Computer> computerPage = new Page<Computer>(computers, nombrElementParPage,
      ComputerService.INSTANCE.findAll().size());

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
    int numberOfComputers = ComputerService.INSTANCE.findAll().size();
    String page = "";

    /*
     * Admission of parameters send by jsp.
     */
    page = (request.getParameter("page") != null) ? request.getParameter("page") : "null";

    /*
     * Treatment of send parameters.
     */

    switch (page) {
    case "next":
      computers = ComputerService.INSTANCE.findLimitNumberOfResult(computerPage.nextPage(), nombrElementParPage);
      break;

    case "previews":
      computers = ComputerService.INSTANCE.findLimitNumberOfResult(computerPage.previousPage(), nombrElementParPage);
      break;

    default:
      computers = ComputerService.INSTANCE.findLimitNumberOfResult(computerPage.getNumerosPage(), nombrElementParPage);
      break;
    }

    /*
     * Send parameters.
     */

    request.setAttribute("numberOfComputers", numberOfComputers);

    request.setAttribute("computers", computers);
    request.setAttribute("computerPage", computerPage);

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
    doGet(request, response);
  }

}
