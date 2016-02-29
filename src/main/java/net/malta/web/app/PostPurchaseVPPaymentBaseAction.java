package net.malta.web.app;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;

import net.malta.model.Purchase;
import net.malta.model.json.mapper.PurchaseMapper;
import net.malta.service.purchase.IPurchaseService;
import net.malta.web.model.PurchaseInfo;
import net.malta.web.utils.BeanUtil;
import net.malta.web.utils.JSONResponseUtil;
import net.malta.web.utils.SessionData;

public abstract class PostPurchaseVPPaymentBaseAction extends Action {

	protected void createNewTempPurchase(HttpServletRequest req, HttpServletResponse res) throws IOException {
		ServletContext context = this.getServlet().getServletContext();
		IPurchaseService purchaseService = (IPurchaseService) BeanUtil.getBean("purchaseService", 
				context);
		
		PurchaseInfo sessionPuchaseInfo = SessionData.getSessionPuchaseInfo(req);
		Integer purchaseId = sessionPuchaseInfo.getPurchaseId();

		Purchase purchase = purchaseService.getPurchase(purchaseId);

		SessionData.createTempPurchase(req, context, sessionPuchaseInfo.getUserId());
		
		PurchaseMapper purchaseMapper = (PurchaseMapper) BeanUtil.getBean("purchaseMapper", context);
		net.malta.model.purchase.json.Purchase purchaseJson = purchaseMapper.map(purchase, new net.malta.model.purchase.json.Purchase());
		JSONResponseUtil.writeResponseAsJSON(res, purchaseJson);		
	}

}