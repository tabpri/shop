/**
 * @author SB
 */
package net.malta.web.app;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import net.malta.model.Purchase;
import net.malta.model.PurchaseInfo;
import net.malta.model.json.mapper.PurchaseMapper;
import net.malta.model.validator.ValidationException;
import net.malta.service.purchase.IChoiseService;
import net.malta.service.purchase.IPurchaseService;
import net.malta.web.utils.BeanUtil;
import net.malta.web.utils.JSONResponseUtil;
import net.malta.web.utils.PurchaseSessionUtil;
import net.malta.web.utils.SessionData;

public class DeleteChoiseAction extends Action{
	
	//private final Logger logger = Logger.getLogger(DeleteChoiseAction.class.getName());
	
	public ActionForward execute(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest req,
			HttpServletResponse res) throws Exception{
		
		try {
			
			Integer choiseId = Integer.parseInt(req.getParameter("id"));

			Integer purchaseId = deleteChoise(req, choiseId);
			
			sendJSON(res, purchaseId);
					
		} catch(ValidationException ve) {			
			JSONResponseUtil.sendErrorJSON(res, ve.getErrors());
		}		
		return null;
	}

	private Integer deleteChoise(HttpServletRequest req, Integer choiseId) {
		
		ServletContext context = this.getServlet().getServletContext();
		PurchaseInfo purchaseInfo = SessionData.getInstance(context).getSessionPuchaseInfo(req);

		Integer purchaseId = purchaseInfo.getPurchaseId();

		IChoiseService choiseService = (IChoiseService) BeanUtil.getBean("choiseService", 
				this.getServlet().getServletContext());

		choiseService.deleteChoise(purchaseId, choiseId);
		return purchaseId;
	}

	private void sendJSON(HttpServletResponse res, Integer purchaseId) throws IOException {
		IPurchaseService purchaseService = (IPurchaseService) BeanUtil.getBean("purchaseService", 
				this.getServlet().getServletContext());			

		Purchase purchase = purchaseService.getPurchase(purchaseId);
		
		PurchaseMapper purchaseMapper = BeanUtil.getPurchaseMapper(this.getServlet().getServletContext());
		net.malta.model.purchase.json.Purchase purchaseJSON = purchaseMapper.map(purchase, new net.malta.model.purchase.json.Purchase());
		JSONResponseUtil.writeResponseAsJSON(res,purchaseJSON);
	}
}