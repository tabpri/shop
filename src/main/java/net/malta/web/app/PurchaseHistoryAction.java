package net.malta.web.app;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import net.malta.model.Purchase;
import net.malta.model.PurchaseInfo;
import net.malta.model.json.mapper.PurchasesMapper;
import net.malta.service.purchase.IPurchaseService;
import net.malta.web.utils.BeanUtil;
import net.malta.web.utils.JSONResponseUtil;
import net.malta.web.utils.SessionData;

public class PurchaseHistoryAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {		
		ServletContext context = this.getServlet().getServletContext();
		IPurchaseService purchaseService =  (IPurchaseService) BeanUtil.getBean("purchaseService", 
				context);
		PurchaseInfo sessionPuchaseInfo = SessionData.getInstance(context).getSessionPuchaseInfo(request);
		List<Purchase> purchases = purchaseService.getPurchases(sessionPuchaseInfo.getUserId());
		
		PurchasesMapper purchasesMapper =  (PurchasesMapper) BeanUtil.getBean("purchasesMapper", context);
		List<net.malta.model.purchase.json.Purchase> purchaseJsons = purchasesMapper.map(purchases, 
				new ArrayList<net.malta.model.purchase.json.Purchase>());
		JSONResponseUtil.writeResponseAsJSON(response, purchaseJsons);
		
		return null;
	}

	
}
