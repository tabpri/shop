package net.malta.web.app;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import net.malta.model.Purchase;
import net.malta.model.json.mapper.PurchaseMapper;
import net.malta.service.purchase.IPurchaseService;
import net.malta.web.model.PurchaseInfo;
import net.malta.web.utils.BeanUtil;
import net.malta.web.utils.JSONResponseUtil;
import net.malta.web.utils.PurchaseSessionUtil;
import net.malta.web.utils.SessionData;

public class ShowPurchaseAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		String purchaseIdReq = req.getParameter("id");
		
		PurchaseInfo purchaseInfo = SessionData.getSessionPuchaseInfo(req);
		
		Integer purchaseId = (purchaseIdReq == null ) ? purchaseInfo.getPurchaseId() : 
			Integer.valueOf(purchaseIdReq);

		IPurchaseService purchaseService = (IPurchaseService) BeanUtil.getBean("purchaseService", this.getServlet().getServletContext());
					
		Purchase purchase = purchaseService.getPurchase(purchaseId);
		
		PurchaseMapper purchaseMapper = BeanUtil.getPurchaseMapper(this.getServlet().getServletContext());
		net.malta.model.purchase.json.Purchase purchaseJSON = purchaseMapper.map(purchase, new net.malta.model.purchase.json.Purchase());
		JSONResponseUtil.writeResponseAsJSON(res,purchaseJSON);
		
		return null;
	}
}