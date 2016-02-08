package net.malta.web.utils;

import org.apache.struts.action.Action;
import org.hibernate.Session;

import net.enclosing.util.HibernateSession;

public class HibernateUtil {
	public static Session getCurrentSession(Action action) {
		return new HibernateSession().currentSession(action.getServlet().getServletContext());		
	}
}
