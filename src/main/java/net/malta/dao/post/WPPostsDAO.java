package net.malta.dao.post;

import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;

import net.malta.dao.BaseDAO;

public class WPPostsDAO<T> extends BaseDAO<T> {

	public String getImg(int wp_posts_id) {
		 SQLQuery query = this.getSessionFactory().getCurrentSession().createSQLQuery("SELECT meta_value value FROM wp_postmeta where meta_key = 'product-thumbnail' and post_id = " + wp_posts_id);
		 query.addScalar( "value", Hibernate.STRING); 
		 String result = (String)query.uniqueResult();
		 System.err.println(result);
		 // TODO this sql should be changed to get the image url
		 String name =result.split("\\]")[1];
		 System.err.println(name);
		 return name;
	}

	public String getName(int wp_posts_id) {
		 SQLQuery query = this.getSessionFactory().getCurrentSession().createSQLQuery("SELECT post_title value FROM wp_posts where ID = " + wp_posts_id);
		 query.addScalar( "value", Hibernate.STRING); 

		 String result = (String)query.uniqueResult();
		 System.err.println(result);
		 return result;
		 //TODO this sql should be changed to get the name of the product.
	}

	public int getPrice(int wp_posts_id) {
		 SQLQuery query = this.getSessionFactory().getCurrentSession().createSQLQuery("SELECT meta_value value FROM wp_postmeta where meta_key = 'rate' and post_id = " + wp_posts_id);
		 query.addScalar( "value", Hibernate.STRING); 
		  
		 
		 String result = (String)query.uniqueResult();
		 String rate = result.replaceAll("￥", "");
		 rate = rate.replaceAll("\\.", "");
		 rate= rate.trim();
		 System.err.println(rate);
		 return Integer.valueOf(rate);
		 
		 
		 //how to remove yen mark, and . mark in the middle.
		// TODO Auto-generated method stub 
		// here the codes to get the price of wp_post using wp_post_id
		 //now returning test price....
//		return 500;
	}
	
}
