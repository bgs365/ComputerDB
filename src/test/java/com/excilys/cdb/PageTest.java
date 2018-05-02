package com.excilys.cdb;

import org.junit.Test;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.page.Page;

/**
 * Page test Class.
 *
 * @author sanogo
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class PageTest {

  @SuppressWarnings("unchecked")
  @Mock
  private Page<Company> companyMock = (Page<Company>) mock(Page.class);

  /**
   * Test creation of company page.
   */
  @Test
  public void testFoo() {
    when(companyMock.getObjetT()).thenReturn(null);
  }

}
