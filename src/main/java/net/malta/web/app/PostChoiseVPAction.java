package net.malta.web.app;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import net.malta.beans.ChoiseForm;
import net.malta.beans.mapper.ChoiseFormMapper;
import net.malta.model.Choise;
import net.malta.model.ChoiseImpl;
import net.malta.model.PurchaseInfo;
import net.malta.model.json.mapper.PurchaseChoiseMapper;
import net.malta.model.validator.ValidationException;
import net.malta.service.purchase.IChoiseService;
import net.malta.web.utils.BeanUtil;
import net.malta.web.utils.JSONResponseUtil;
import net.malta.web.utils.SessionData;


public class PostChoiseVPAction extends Action{
	
	public ActionForward execute(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest req,
			HttpServletResponse res) throws Exception{
		
		try{
			
			ServletContext context = this.getServlet().getServletContext();
			
			ChoiseForm choiseForm = (ChoiseForm) form;
			
			Choise choise = mapChoiseForm(choiseForm);
			
			PurchaseInfo purchaseInfo = SessionData.getInstance(context).getSessionPuchaseInfo(req);

			Integer purchaseId = purchaseInfo.getPurchaseId();
			
			choise = persist(choiseForm, choise, purchaseId);
						
			sendJSON(res, choise);
						
		} catch(ValidationException ve) {
			JSONResponseUtil.sendErrorJSON(res, ve.getErrors());
		}
		return null;
	}

	private void sendJSON(HttpServletResponse res, Choise choise) throws IOException {
		PurchaseChoiseMapper mapper = (PurchaseChoiseMapper) BeanUtil.getBean("purchaseChoiseMapper",
				this.getServlet().getServletContext());		
		net.malta.model.purchase.json.Choise choiseJSON = mapper.map(choise, new net.malta.model.purchase.json.Choise());
		JSONResponseUtil.writeResponseAsJSON(res, choiseJSON);
	}

	private Choise persist(ChoiseForm choiseForm, Choise choise, Integer purchaseId) {
		IChoiseService choiseService = (IChoiseService) BeanUtil.getBean("choiseService", 
				this.getServlet().getServletContext());
				
		if( choiseForm.getId() != null ){ // existing choise
			choise = choiseService.updateChoise(purchaseId, choise);
		} else { // new choise
			choise = choiseService.addChoise(purchaseId, choise);
		}
		return choise;
	}

	private Choise mapChoiseForm(ChoiseForm choiseForm) {
		ChoiseFormMapper choiseFormMapper = (ChoiseFormMapper) BeanUtil.getBean("choiseFormMapper", 
				this.getServlet().getServletContext());		
		Choise choise = choiseFormMapper.map(choiseForm, new ChoiseImpl());
		return choise;
	}
}