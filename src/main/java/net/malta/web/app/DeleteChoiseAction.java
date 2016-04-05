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

import net.malta.model.Choise;
import net.malta.model.Purchase;
import net.malta.model.PurchaseInfo;
import net.malta.model.json.mapper.ChoiseMapper;
import net.malta.model.json.mapper.PurchaseMapper;
import net.malta.model.validator.ValidationException;
import net.malta.service.purchase.IChoiseService;
import net.malta.service.purchase.IPurchaseService;
import net.malta.web.utils.BeanUtil;
import net.malta.web.utils.JSONResponseUtil;
import net.malta.web.utils.SessionData;

public class DeleteChoiseAction extends Action{
	
	//private final Logger logger = Logger.getLogger(DeleteChoiseAction.class.getName());
	
	public ActionForward execute(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest req,
			HttpServletResponse res) throws Exception{
		
		Integer choiseId = Integer.parseInt(req.getParameter("id"));

		Choise removedChoise = deleteChoise(req, choiseId);
		
		sendJSON(req,res, removedChoise);
			
		return null;
	}

	private Choise deleteChoise(HttpServletRequest req, Integer choiseId) {
		
		ServletContext context = this.getServlet().getServletContext();
		PurchaseInfo purchaseInfo = SessionData.getInstance(context).getSessionPuchaseInfo(req);

		Integer purchaseId = purchaseInfo.getPurchaseId();

		IChoiseService choiseService = (IChoiseService) BeanUtil.getBean("choiseService", 
				this.getServlet().getServletContext());

		Choise removedChoise = choiseService.deleteChoise(purchaseId, choiseId);
		
		return removedChoise;
	}

	private void sendJSON(HttpServletRequest req,HttpServletResponse res, Choise removedChoise) throws IOException {

		ServletContext context = this.getServlet().getServletContext();
		
		PurchaseInfo purchaseInfo = SessionData.getInstance(context).getSessionPuchaseInfo(req);

		IPurchaseService purchaseService = (IPurchaseService) BeanUtil.getBean("purchaseService", 
				this.getServlet().getServletContext());			

		Purchase purchase = purchaseService.getPurchase(purchaseInfo.getPurchaseId());
		
		PurchaseMapper purchaseMapper = BeanUtil.getPurchaseMapper(context);
		
		ChoiseMapper choiseMapper = (ChoiseMapper) BeanUtil.getBean("choiseMapper", context);
		net.malta.model.purchase.json.Choise removedChoiseJSON = choiseMapper.map(removedChoise, new net.malta.model.purchase.json.Choise());
		removedChoiseJSON.setRemoved(true);
		net.malta.model.purchase.json.Purchase purchaseJSON = purchaseMapper.map(purchase, new net.malta.model.purchase.json.Purchase());
		purchaseJSON.addChoise(removedChoiseJSON); // adding the removedChoise to the response json
		JSONResponseUtil.writeResponseAsJSON(res,purchaseJSON);
	}
}