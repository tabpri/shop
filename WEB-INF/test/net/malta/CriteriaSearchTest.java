
package net.malta;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import mockit.integration.junit4.JMockit;
import net.malta.model.PublicUser;
import net.malta.model.Purchase;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Denis Zhdanov
 * @since 01/19/2016
 */
@RunWith(JMockit.class)
public class CriteriaSearchTest {

    private ApplicationContext context;

    private List list;

    private Set set;

    private CriteriaSearch search;

    private CriteriaSearchParameter parameter;

    private SessionFactory sessionFactory;

    private Session session;

    @Before
    public void setUp() {
    	context = new ClassPathXmlApplicationContext(new String[] {
    			"applicationContext.xml", "applicationContext-localDataSource.xml" });
		sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		session = sessionFactory.openSession();
    }

    @Test
    public void test1() {

    	// case1
    	System.out.println("1--------------------------------------------------------");
    	search = new CriteriaSearch(session, PublicUser.class);

    	CriteriaSearchConfiguration conf = search.getConfiguration();

    	conf.setFirstResult(1);
    	conf.setFetchSize(3);
    	conf.setMaximumResultSize(10);

		search.addParameter(new CriteriaSearchParameter(
				new Integer(1), "id", CriteriaSearchParameter.GREATER_THAN_COMPARATOR));

		parameter = new CriteriaSearchParameter(
				new Integer(5), "id", CriteriaSearchParameter.LESS_THAN_COMPARATOR);

		parameter.setOrderDirection(CriteriaSearchParameter.ORDER_ASC);

		search.addParameter(parameter);

		set = search.executeAsSet();

		for (Iterator ite= set.iterator(); ite.hasNext();) {
			PublicUser publicUser = (PublicUser)ite.next();
			System.out.println("PublicUser.id=" + publicUser.getId());
		}

		// case2
		System.out.println("2--------------------------------------------------------");
		search = new CriteriaSearch(session, PublicUser.class);

		search.addParameter(new CriteriaSearchParameter(
				new Integer(1), "id", CriteriaSearchParameter.GREATER_THAN_OR_EQUAL_COMPARATOR));

		parameter = new CriteriaSearchParameter(
				new Integer(5), "id", CriteriaSearchParameter.LESS_THAN_OR_EQUAL_COMPARATOR);

		parameter.setOrderDirection(CriteriaSearchParameter.ORDER_DESC);

		search.addParameter(parameter);

		list = search.executeAsList();

		for (Iterator ite= list.iterator(); ite.hasNext();) {
			PublicUser publicUser = (PublicUser)ite.next();
			System.out.println("PublicUser.id=" + publicUser.getId());
		}

		// case3
		System.out.println("3--------------------------------------------------------");
		search = new CriteriaSearch(session, PublicUser.class);

		search.addParameter(new CriteriaSearchParameter(
				"ICHIRO", "name", CriteriaSearchParameter.EQUAL_COMPARATOR));

		list = search.executeAsList();

		for (Iterator ite= list.iterator(); ite.hasNext();) {
			PublicUser publicUser = (PublicUser)ite.next();
			System.out.println("PublicUser.id=" + publicUser.getId());
		}

		// case4
		System.out.println("4--------------------------------------------------------");
		search = new CriteriaSearch(session, PublicUser.class);

		search.addParameter(new CriteriaSearchParameter(
				"ICHIRO", "name", CriteriaSearchParameter.NOT_EQUAL_COMPARATOR));

		list = search.executeAsList();

		for (Iterator ite= list.iterator(); ite.hasNext();) {
			PublicUser publicUser = (PublicUser)ite.next();
			System.out.println("PublicUser.id=" + publicUser.getId());
		}

		// case5
		System.out.println("5--------------------------------------------------------");
		search = new CriteriaSearch(session, PublicUser.class);

		search.addParameter(new CriteriaSearchParameter(
				"%i%", "name", CriteriaSearchParameter.INSENSITIVE_LIKE_COMPARATOR));

		list = search.executeAsList();

		for (Iterator ite= list.iterator(); ite.hasNext();) {
			PublicUser publicUser = (PublicUser)ite.next();
			System.out.println("PublicUser.id=" + publicUser.getId());
		}

		// case6
		System.out.println("6--------------------------------------------------------");
		search = new CriteriaSearch(session, PublicUser.class);

		search.addParameter(new CriteriaSearchParameter(
				"%i%", "name", CriteriaSearchParameter.INSENSITIVE_LIKE_COMPARATOR, MatchMode.ANYWHERE));

		list = search.executeAsList();

		for (Iterator ite= list.iterator(); ite.hasNext();) {
			PublicUser publicUser = (PublicUser)ite.next();
			System.out.println("PublicUser.id=" + publicUser.getId());
		}

		// case7
		System.out.println("7--------------------------------------------------------");
		search = new CriteriaSearch(session, PublicUser.class);

		search.addParameter(new CriteriaSearchParameter(
				"%i%", "name", CriteriaSearchParameter.LIKE_COMPARATOR));

		list = search.executeAsList();

		for (Iterator ite= list.iterator(); ite.hasNext();) {
			PublicUser publicUser = (PublicUser)ite.next();
			System.out.println("PublicUser.id=" + publicUser.getId());
		}

		// case8
		System.out.println("8--------------------------------------------------------");
		search = new CriteriaSearch(session, PublicUser.class);

		search.addParameter(new CriteriaSearchParameter(
				"%i%", "name", CriteriaSearchParameter.LIKE_COMPARATOR, MatchMode.ANYWHERE));

		list = search.executeAsList();

		for (Iterator ite= list.iterator(); ite.hasNext();) {
			PublicUser publicUser = (PublicUser)ite.next();
			System.out.println("PublicUser.id=" + publicUser.getId());
		}

		// case9
		System.out.println("9--------------------------------------------------------");
		search = new CriteriaSearch(session, PublicUser.class);

		List nameList = new ArrayList();
		nameList.add("SABURO");
		nameList.add("GORO");
		search.addParameter(new CriteriaSearchParameter(
				nameList, "name", CriteriaSearchParameter.IN_COMPARATOR));

		list = search.executeAsList();

		for (Iterator ite= list.iterator(); ite.hasNext();) {
			PublicUser publicUser = (PublicUser)ite.next();
			System.out.println("PublicUser.id=" + publicUser.getId());
		}

		// case10
		System.out.println("10-------------------------------------------------------");
		search = new CriteriaSearch(session, PublicUser.class);

		parameter = new CriteriaSearchParameter("%I%", "name", CriteriaSearchParameter.LIKE_COMPARATOR);

		parameter.setParameterValue(null);

		search.addParameter(parameter);

		list = search.executeAsList();

		for (Iterator ite= list.iterator(); ite.hasNext();) {
			PublicUser publicUser = (PublicUser)ite.next();
			System.out.println("PublicUser.id=" + publicUser.getId());
		}

		// case11
		System.out.println("11-------------------------------------------------------");
		search = new CriteriaSearch(session, PublicUser.class);

		search.addParameter(new CriteriaSearchParameter(
				new String[] {"%i%", "%O%", null}, "name", CriteriaSearchParameter.LIKE_COMPARATOR));

		list = search.executeAsList();

		for (Iterator ite= list.iterator(); ite.hasNext();) {
			PublicUser publicUser = (PublicUser)ite.next();
			System.out.println("PublicUser.id=" + publicUser.getId());
		}

		// case12
		System.out.println("12-------------------------------------------------------");
		search = new CriteriaSearch(session, PublicUser.class);

		search.addParameter(new CriteriaSearchParameter(
				new String[] {"%i%", "%O%", null}, "name", CriteriaSearchParameter.LIKE_COMPARATOR, MatchMode.ANYWHERE));

		list = search.executeAsList();

		for (Iterator ite= list.iterator(); ite.hasNext();) {
			PublicUser publicUser = (PublicUser)ite.next();
			System.out.println("PublicUser.id=" + publicUser.getId());
		}

		// case13
		System.out.println("13-------------------------------------------------------");
		search = new CriteriaSearch(session, PublicUser.class);

		search.addParameter(new CriteriaSearchParameter(
				new String[] {"%i%", "%O%", null}, "name", CriteriaSearchParameter.INSENSITIVE_LIKE_COMPARATOR));

		list = search.executeAsList();

		for (Iterator ite= list.iterator(); ite.hasNext();) {
			PublicUser publicUser = (PublicUser)ite.next();
			System.out.println("PublicUser.id=" + publicUser.getId());
		}

		// case14
		System.out.println("14-------------------------------------------------------");
		search = new CriteriaSearch(session, PublicUser.class);

		search.addParameter(new CriteriaSearchParameter(
				new String[] {"%i%", "%O%", null}, "name", CriteriaSearchParameter.INSENSITIVE_LIKE_COMPARATOR, MatchMode.ANYWHERE));

		list = search.executeAsList();

		for (Iterator ite= list.iterator(); ite.hasNext();) {
			PublicUser publicUser = (PublicUser)ite.next();
			System.out.println("PublicUser.id=" + publicUser.getId());
		}

		// case15
		System.out.println("15-------------------------------------------------------");
		search = new CriteriaSearch(session, PublicUser.class);

		search.addParameter(new CriteriaSearchParameter(
				new String[] {"JIRO", "GORO", null}, "name", CriteriaSearchParameter.EQUAL_COMPARATOR));

		list = search.executeAsList();

		for (Iterator ite= list.iterator(); ite.hasNext();) {
			PublicUser publicUser = (PublicUser)ite.next();
			System.out.println("PublicUser.id=" + publicUser.getId());
		}

		// case16
		System.out.println("16-------------------------------------------------------");
    	search = new CriteriaSearch(session, PublicUser.class);

		search.addParameter(new CriteriaSearchParameter(
				new Integer[] {1, 2, null}, "id", CriteriaSearchParameter.GREATER_THAN_COMPARATOR));

		parameter = new CriteriaSearchParameter(
				new Integer[] {4, 5, null}, "id", CriteriaSearchParameter.LESS_THAN_COMPARATOR);

		parameter.setOrderDirection(CriteriaSearchParameter.ORDER_ASC);

		search.addParameter(parameter);

		set = search.executeAsSet();

		for (Iterator ite= set.iterator(); ite.hasNext();) {
			PublicUser publicUser = (PublicUser)ite.next();
			System.out.println("PublicUser.id=" + publicUser.getId());
		}

		// case17
		System.out.println("17-------------------------------------------------------");
    	search = new CriteriaSearch(session, PublicUser.class);

		search.addParameter(new CriteriaSearchParameter(
				new Integer[] {1, 2, null}, "id", CriteriaSearchParameter.GREATER_THAN_OR_EQUAL_COMPARATOR));

		parameter = new CriteriaSearchParameter(
				new Integer[] {4, 5, null}, "id", CriteriaSearchParameter.LESS_THAN_OR_EQUAL_COMPARATOR);

		parameter.setOrderDirection(CriteriaSearchParameter.ORDER_DESC);

		search.addParameter(parameter);

		set = search.executeAsSet();

		for (Iterator ite= set.iterator(); ite.hasNext();) {
			PublicUser publicUser = (PublicUser)ite.next();
			System.out.println("PublicUser.id=" + publicUser.getId());
		}

		// case18
		System.out.println("18-------------------------------------------------------");
		search = new CriteriaSearch(session, PublicUser.class);
		search.addParameter(new CriteriaSearchParameter(
				new String[] {"SABURO", "GORO"}, "name", CriteriaSearchParameter.IN_COMPARATOR));

		list = search.executeAsList();

		for (Iterator ite= list.iterator(); ite.hasNext();) {
			PublicUser publicUser = (PublicUser)ite.next();
			System.out.println("PublicUser.id=" + publicUser.getId());
		}

		// case19
		System.out.println("19-------------------------------------------------------");
		search = new CriteriaSearch(session, PublicUser.class);
		search.addParameter(new CriteriaSearchParameter(
				new String[] {"SABURO", "GORO", null}, "name", CriteriaSearchParameter.NOT_EQUAL_COMPARATOR));

		list = search.executeAsList();

		for (Iterator ite= list.iterator(); ite.hasNext();) {
			PublicUser publicUser = (PublicUser)ite.next();
			System.out.println("PublicUser.id=" + publicUser.getId());
		}

		// case20
		System.out.println("20-------------------------------------------------------");
		search = new CriteriaSearch(session, PublicUser.class);
		search.addParameter(new CriteriaSearchParameter(
				new Integer[] {1, 2, null}, "purchases.id", CriteriaSearchParameter.GREATER_THAN_COMPARATOR));

		list = search.executeAsList();

		for (Iterator ite= list.iterator(); ite.hasNext();) {
			PublicUser publicUser = (PublicUser)ite.next();
			System.out.println("PublicUser.id=" + publicUser.getId());
			if (publicUser.getPurchases() != null) {
				for (Iterator ite2= publicUser.getPurchases().iterator(); ite2.hasNext();) {
					Purchase purchase = (Purchase)ite2.next();
					System.out.println("           [Purchase.id=" + purchase.getId() + "]");
				}
			}
		}

		// case21
		System.out.println("21-------------------------------------------------------");
		search = new CriteriaSearch(session, Purchase.class);
		search.addParameter(new CriteriaSearchParameter(
				new Integer[] {1, 2, null}, "publicUser.id", CriteriaSearchParameter.GREATER_THAN_COMPARATOR));

		conf = search.getConfiguration();

		conf.setForceEagerLoading(true);

		list = search.executeAsList();

		for (Iterator ite= list.iterator(); ite.hasNext();) {
			Purchase purchase = (Purchase)ite.next();
			System.out.println("Purchase.id=" + purchase.getId() + " PublicUser.id=" + purchase.getPublicUser().getId());
		}

		// case22
		System.out.println("22-------------------------------------------------------");
		search = new CriteriaSearch(session, PublicUser.class);
		search.addParameter("GORO", "name");

		list = search.executeAsList();

		for (Iterator ite= list.iterator(); ite.hasNext();) {
			PublicUser publicUser = (PublicUser)ite.next();
			System.out.println("PublicUser.id=" + publicUser.getId());
		}

		// case23
		System.out.println("23-------------------------------------------------------");
		search = new CriteriaSearch(session, PublicUser.class);
		search.addParameter(new Integer(5), "purchases.id");

		list = search.executeAsList();

		for (Iterator ite= list.iterator(); ite.hasNext();) {
			PublicUser publicUser = (PublicUser)ite.next();
			System.out.println("PublicUser.id=" + publicUser.getId());
		}

    }

    @After
    public void checkExpectations() {

    	session.close();
		sessionFactory.close();
		sessionFactory =null;


    }
}
