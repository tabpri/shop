package net.malta.web.app;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import net.enclosing.util.HibernateSession;
import net.malta.model.Category;
import net.malta.model.Product;
import net.malta.model.json.mapper.ProductsJSONMapper;
import net.malta.web.utils.JSONResponseUtil;
import net.malta.web.utils.Pagination;


public class ProductListAction extends Action{
	public ActionForward execute(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest req,
			HttpServletResponse res) throws Exception{



		Session session = new HibernateSession().currentSession(this
				.getServlet().getServletContext());

		Criteria criteria = session.createCriteria(Product.class);
		criteria.addOrder(Order.desc("id"));
		criteria.add(Restrictions.eq("removed", new Boolean(false)));
		Pagination pagination=new Pagination();
        int pagesize = 6;
        int offset = 0;
        int currentpage = 1;
        
        if(StringUtils.isNotBlank(req.getParameter("pagesize")) && req.getParameter("pagesize")!=null) {
        	//if(NumberUtils.isDigits(req.getParameter("pagesize")))
        		pagesize = Integer.parseInt(req.getParameter("pagesize"));
        }
        
//        if(StringUtils.isNotBlank(req.getParameter("offset")) && req.getParameter("offset")!=null) {
//        	if(NumberUtils.isDigits(req.getParameter("offset")))
//        		offset =  Integer.parseInt(req.getParameter("offset"));
//        }
        
        if(StringUtils.isNotBlank(req.getParameter("currentpage")) && req.getParameter("currentpage")!=null) {
        	if(NumberUtils.isDigits(req.getParameter("currentpage"))){
        		currentpage = Integer.parseInt(req.getParameter("currentpage"));
        		offset = ( currentpage -1 ) * pagesize;
        	}
        	
        }
        
        if(StringUtils.isNotBlank("category") && !req.getParameter("category").equals("")) {
        	Criteria criteriaCategory = session.createCriteria(Category.class);
        	criteriaCategory.add(Restrictions.idEq(Integer.parseInt(req.getParameter("category"))));
        	if(criteriaCategory.uniqueResult()!=null) {
        		criteria.add(Restrictions.eq("category", criteriaCategory.uniqueResult()));
        		//req.setAttribute("category", criteriaCategory.uniqueResult());
        	}
        }
        
		int max=pagination.getMax(criteria.list().size(), pagesize);
		
//		criteria.setMaxResults(pagesize);
		criteria.setFirstResult(offset);

		/*Criteria criteriaAward = session.createCriteria(Award.class);
		req.setAttribute("pages", 1 + ( (int ) ( criteriaAward.list().size() / pagesize ) ));*/

		List productsList = criteria.list();
		ProductsJSONMapper jsonMapper = new ProductsJSONMapper();
		ArrayList<net.malta.model.json.Product> productsJSON = new ArrayList<net.malta.model.json.Product>();
		jsonMapper.map(productsList, productsJSON);
		JSONResponseUtil.writeObjectAsJSON(res, productsJSON);

		return null;
/*
		<c:forEach var="page" begin="1" end="${pages}">
		<a href="ProductList.do?currentpage=${page}">
		${page}
		</a>
		</c:forEach>



*/
	}
		
}