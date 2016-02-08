
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
public class AttachmentFormFileUploadAddingToItemForDefaultActionTest {

    private AttachmentFormFileUploadAddingToItemForDefaultAction action;

    private AttachmentForm attachmentform;

    private Item item;

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
        action = new AttachmentFormFileUploadAddingToItemForDefaultAction();
        attachmentform = new AttachmentForm();
        attachmentform.setFormFiles(new FormFile[] {formFile});

        item = Item.Factory.newInstance();
        item.setId(new Integer(1));

        Product product = Product.Factory.newInstance();
        product.setId(new Integer(1));

        item.setProduct(product);
    }

    @Test
    public void testExecute1() {

    	new NonStrictExpectations() {{
    		formFile.getFileName(); result= "Unit.txt"; result= "Unit.txt"; result= "Unit.txt";
    	}};

    	new MockUp<HibernateSession> () {
    		@Mock public Session currentSession(ServletContext servletContext) {
    			return hibernateSession;
    		}
		};

		try {
			action.execute(actionMapping, attachmentform, httpServeltRequest, httpServletResponse);
		} catch (Exception e) {
			fail();
		}

    }

    @Test
    public void testExecute2() {

    	new NonStrictExpectations() {{
    		formFile.getFileName(); result= "Unit.JPEG"; result= "Unit.JPEG"; result= "Unit.JPEG";
    		criteria.uniqueResult(); result= item;
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

		try {
			action.execute(actionMapping, attachmentform, httpServeltRequest, httpServletResponse);
		} catch (Exception e) {
			fail(e.getMessage());
		}

    }

    @Test
    public void testExecute3() {

    	new NonStrictExpectations() {{
    		formFile.getFileName(); result= "Unit.JPEG"; result= "Unit.JPEG"; result= "Unit.JPEG";
    		criteria.uniqueResult(); result= item;
    		httpServeltRequest.getParameter("from"); result= "detail"; result= "detail";
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
