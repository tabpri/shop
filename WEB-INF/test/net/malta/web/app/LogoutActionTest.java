
package net.malta.web.app;

import static org.junit.Assert.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mockit.Mocked;
import mockit.integration.junit4.JMockit;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Denis Zhdanov
 * @since 01/19/2016
 */
@RunWith(JMockit.class)
public class LogoutActionTest {

    private LogoutAction action;

    @Mocked
    private ActionMapping actionMapping;

    @Mocked
    private ActionForm actionForm;

    @Mocked
    private HttpServletRequest httpServeltRequest;

    @Mocked
    private HttpServletResponse httpServletResponse;

    @Before
    public void setUp() {
        action = new LogoutAction();
    }

    @Test
    public void testExecute1() {
    	try {
			action.execute(actionMapping, actionForm, httpServeltRequest, httpServletResponse);
		} catch (Exception e) {
			fail(e.getMessage());
		}
    }

    @After
    public void checkExpectations() {
    }
}
