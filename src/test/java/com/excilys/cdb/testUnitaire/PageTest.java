package com.excilys.cdb.testUnitaire;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.page.Page;

/**
 * Page test Class.
 *
 * @author sanogo
 *
 */
public class PageTest {

	static final Logger LOGGER = LoggerFactory.getLogger(PageTest.class);
	List<Integer> itemsForPage = new ArrayList<Integer>();
	private Page<Integer> page;
	int numberElementPerPage;
	int totalNumberOfElement;
	int currentPage;

	/**
	 * init test data.
	 */
	@Before
	public void init() {
		currentPage = 1;
	}

	/**
	 * destroy test data.
	 */
	@After
	public void destroy() {
		itemsForPage.clear();
		page = null;
		currentPage = 1;
	}

	/**
	 * Test create page.
	 */
	@Test
	public void testPage() {
		numberElementPerPage = 10;
		totalNumberOfElement = 100;
		itemsForPage = fillData(totalNumberOfElement);
		page = new Page<Integer>(itemsForPage, numberElementPerPage, totalNumberOfElement);
		
		assertEquals(numberElementPerPage, page.getNombreElementPerPage());
		assertEquals(totalNumberOfElement, page.getNombreElementTotal());
		assertEquals(1, page.getNumerosPage());
		assertEquals(((page.getNumerosPage() - 1) * page.getNombreElementPerPage()), page.getIndexFirstPageElement());
	}

	/**
	 * Test next page.
	 */
	@Test
	public void testNextPage() {
		numberElementPerPage = 10;
		totalNumberOfElement = 25;
		itemsForPage = fillData(totalNumberOfElement);
		page = new Page<Integer>(itemsForPage, numberElementPerPage, totalNumberOfElement);
		
		assertEquals(page.nextPage(), numberElementPerPage);
		assertEquals(page.nextPage(), numberElementPerPage += numberElementPerPage);
		assertEquals(page.nextPage(), numberElementPerPage);

	}

	/**
	 * Test previous page.
	 */
	@Test
	public void testPreviousPage() {
		numberElementPerPage = 10;
		totalNumberOfElement = 31;
		itemsForPage = fillData(totalNumberOfElement);
		page = new Page<Integer>(itemsForPage, numberElementPerPage, totalNumberOfElement);
		

		/*Test when we are at page 1*/
		assertEquals(page.previousPage(), 0);
		
		/*Test where current is not page 1*/
		page = new Page<Integer>(itemsForPage, numberElementPerPage, totalNumberOfElement);
		page.nextPage();
		currentPage++;
		page.nextPage();
		currentPage++;
		assertEquals(page.previousPage(), (--currentPage-1)*numberElementPerPage);
		assertEquals(page.previousPage(), (--currentPage-1)*numberElementPerPage);
	}

	/**
	 * Test set page.
	 */
	@Test
	public void testSetCurentPage() {
		numberElementPerPage = 10;
		totalNumberOfElement = 31;
		itemsForPage = fillData(totalNumberOfElement);
		page = new Page<Integer>(itemsForPage, numberElementPerPage, totalNumberOfElement);
		
		/*test a non conflict current page*/
		currentPage = 2;
		page.setCurrentPage(currentPage);
		assertEquals(page.getNumerosPage(), currentPage);
		assertEquals(page.getIndexFirstPageElement(), ((currentPage - 1) * numberElementPerPage) );
		
		/*test a page out of inferior bound */
		int currentPageOutOfInferiorBound = -1;
		page.setCurrentPage(currentPageOutOfInferiorBound);
		assertEquals(currentPage, page.getNumerosPage());
		assertEquals(((currentPage - 1) * numberElementPerPage),page.getIndexFirstPageElement());
		
		/*test a page out of superior bound*/
		int currentPageOutOfSuperiorBound = 50;
		page.setCurrentPage(currentPageOutOfSuperiorBound);
		assertEquals(currentPage, page.getNumerosPage());
		LOGGER.debug("page.getNumerosPage() : "+page.getNumerosPage());
		LOGGER.debug("currentPage : "+currentPage);
		assertEquals(((currentPage - 1) * numberElementPerPage),page.getIndexFirstPageElement());
		LOGGER.debug("page.getIndexFirstPageElement() : "+page.getIndexFirstPageElement());
		LOGGER.debug("currentPage : "+ (currentPage - 1) * numberElementPerPage);
		
		/*test a non conflict last page */
		currentPage = 4;
		LOGGER.debug("test last page");
		page.setCurrentPage(currentPage);
		assertEquals(currentPage, page.getNumerosPage());
		assertEquals(((currentPage - 1) * numberElementPerPage), page.getIndexFirstPageElement() );

	}

	public List<Integer> fillData(int totalNumberOfElement) {
		List<Integer> data = new ArrayList<Integer>();
		for (int i = 0; i < totalNumberOfElement; i++) {
			data.add(i);
		}
		return data;
	}
}