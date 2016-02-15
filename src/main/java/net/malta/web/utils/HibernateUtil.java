/**
 * @author SB
 */
package net.malta.web.utils;

import org.apache.struts.action.Action;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;

import net.enclosing.util.HibernateSession;

public class HibernateUtil {
	
	public static Session openSession(Action action) {
	  BeanFactory factory = WebApplicationContextUtils.getWebApplicationContext(action.getServlet().getServletContext());
	  SessionFactory sessionFactory = (SessionFactory)factory.getBean("sessionFactory");
	  Session session = sessionFactory.openSession();
	  sessionFactory.close();
	  return session;
	}

	public static Session getCurrentSession(Action action) {
		  return HibernateSession.currentSession(action.getServlet().getServletContext());		
	}

	public static void closeSession(Session session) {
		if ( session != null ) {
			session.clear();
			session.close();			
		}
	}
}
