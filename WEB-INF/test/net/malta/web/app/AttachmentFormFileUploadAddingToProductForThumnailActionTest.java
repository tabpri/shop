
package net.malta.web.app;

import static org.junit.Assert.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import mockit.NonStrictExpectations;
import mockit.integration.junit4.JMockit;
import net.enclosing.util.HibernateSession;
import net.enclosing.util.StringFullfiller;
import net.malta.beans.AttachmentForm;
import net.malta.model.DbFile;
import net.malta.model.Item;
import net.malta.model.Product;
import net.malta.web.utils.AttachmentUtils;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.upload.FormFile;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Denis Zhdanov
 * @since 01/19/2016
 */
@RunWith(JMockit.class)
public class AttachmentFormFileUploadAddingToProductForThumnailActionTest {

    private AttachmentFormFileUploadAddingToProductForThumnailAction action;

    private AttachmentForm attachmentform;

    private Item item;

    private Product product;

    @Mocked
    private ActionMapping actionMapping;

    @Mocked
    private HttpServletRequest httpServeltRequest;

    @Mocked
    private HttpServletResponse httpServletResponse;

    @Mocked
    private ServletContext servletContext;

    @Mocked
    private Session hibernateSession;

    @Mocked
    private Transaction transaction;

    @Mocked
    private Criteria criteria;

    @Mocked
    private FormFile formFile;

    @Mocked
    private DbFile dbFile;

    @Before
    public void setUp() {
        action = new AttachmentFormFileUploadAddingToProductForThumnailAction();
        attachmentform = new AttachmentForm();
        attachmentform.setFormFiles(new FormFile[] {formFile});

        item = Item.Factory.newInstance();
        item.setId(new Integer(1));

        product = Product.Factory.newInstance();
        product.setId(new Integer(1));

        item.setProduct(product);
    }

    @Test
    public void testExecute1() {

    	new NonStrictExpectations() {{
    		formFile.getFileName(); result= "Unit.txt"; result= "Unit.txt"; result= "Unit.txt";
    	}};

    	try {
			action.execute(actionMapping, attachmentform, httpServeltRequest, httpServletResponse);
		} catch (Exception e) {
			fail(e.getMessage());
		}

    }

    @Test
    public void testExecute2() {

    	new NonStrictExpectations() {{
    		formFile.getFileName(); result= "Unit.JPEG"; result= "Unit.JPEG"; result= "Unit.JPEG";
    		criteria.uniqueResult(); result= product;
    		httpServeltRequest.getParameter("product"); result= "1";
    	}};

    	new MockUp<Action> () {
			@Mock public ActionServlet getServlet() {
				return new ActionServlet();
			}
		};
		new MockUp<ActionServlet> () {
			@Mock public ServletContext getServletContext() {
				return servletContext;
			}
		};

    	new MockUp<HibernateSession> () {
    		@Mock public Session currentSession(ServletContext servletContext) {
    			return hibernateSession;
    		}
		};

		new MockUp<AttachmentUtils> () {
    		@Mock public DbFile createDbFileFromFormFile(FormFile formFile) {
    			return dbFile;
    		}
		};

		new MockUp<StringFullfiller> () {
    		@Mock public void fullfil(Object object) {
    		}
		};

		try {
			action.execute(actionMapping, attachmentform, httpServeltRequest, httpServletResponse);
		} catch (Exception e) {
			fail(e.getMessage());
		}

    }

    @After
    public void checkExpectations() {
    }
}
