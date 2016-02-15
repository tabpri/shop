/**
 * @author SB
 */
package net.malta.web.app;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import net.malta.model.Category;
import net.malta.model.Product;
import net.malta.model.json.mapper.ProductsMapper;
import net.malta.web.utils.BeanUtil;
import net.malta.web.utils.HibernateUtil;
import net.malta.web.utils.JSONResponseUtil;


public class ProductListAction extends Action{
	public ActionForward execute(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest req,
			HttpServletResponse res) throws Exception{


		Session session = HibernateUtil.openSession(this);
		
		Criteria criteria = session.createCriteria(Product.class);
		criteria.addOrder(Order.desc("id"));
		criteria.add(Restrictions.eq("removed", new Boolean(false)));
		
        String categoryId = req.getParameter("category");
		if( StringUtils.isNotBlank(categoryId) ) {
        	Criteria criteriaCategory = session.createCriteria(Category.class);
        	criteriaCategory.add(Restrictions.idEq(Integer.parseInt(req.getParameter("category"))));
        	Category category = (Category) criteriaCategory.uniqueResult();
			if(category!=null) {
        		criteria.add(Restrictions.eq("category", category));
        	}
        }
        
		List<Product> productsList = criteria.list();
		ArrayList<net.malta.model.product.json.Product> productsJSON = new ArrayList<net.malta.model.product.json.Product>();
		
		ProductsMapper productsMapper = BeanUtil.getProductsMapper(this.getServlet().getServletContext());
		productsMapper.map(productsList, productsJSON);
		JSONResponseUtil.writeResponseAsJSON(res, productsJSON);
		
		return null;
	}
		
}