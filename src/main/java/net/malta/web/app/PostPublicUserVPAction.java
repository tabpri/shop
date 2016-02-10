package net.malta.web.app;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import net.enclosing.util.StringFullfiller;
import net.malta.beans.PrefectureCarriageDataMap;
import net.malta.beans.PublicUserForm;
import net.malta.beans.ValidationError;
import net.malta.model.Prefecture;
import net.malta.model.PublicUser;
import net.malta.model.PublicUserImpl;
import net.malta.model.Purchase;
import net.malta.model.json.mapper.PublicUserMapper;
import net.malta.model.wrapper.PurchaseTotal;
import net.malta.web.utils.BeanUtil;
import net.malta.web.utils.HibernateUtil;
import net.malta.web.utils.JSONResponseUtil;
import net.malta.web.utils.PurchaseSessionUtil;
import net.malta.web.utils.SessionData;
import net.malta.web.validator.Errors;
import net.malta.web.validator.PublicUserFormValidator;


public class PostPublicUserVPAction extends Action{
	private static final String PUBLICUSER_DOESNOTEXIST = "PUBLICUSER.DOESNOTEXIST";

	public ActionForward execute(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest req,
			HttpServletResponse res) throws Exception{
		
		Session session = null;
		
		try {
			
			session = HibernateUtil.getCurrentSession(this);
	
			PublicUserForm publicUserform = (PublicUserForm) form;
			
			// validations			
			boolean hasErrors = validate(res,publicUserform);
			if ( hasErrors ) {
				return null;
			}
			
			// no errors. proceeds with the flow
			
	        PublicUser publicUser = null;
	
	        boolean newuser = false;
	        
			Integer userId = publicUserform.getId();
			if( userId != null ){
				publicUser = existingUserCheck(session, publicUserform);
				if ( publicUser == null ) { // if user doesnt exists with the id provided
					final Errors checkErrors = new Errors();
					checkErrors.add(new ValidationError(PUBLICUSER_DOESNOTEXIST,userId));			
					JSONResponseUtil.sendErrorJSON(res,checkErrors);
					return null;
				}			
			} else {
				newuser = true;
			}
			
			if ( newuser ) {
				publicUser = createPublicUser(session,publicUserform);					
			} else {
				publicUser = updatePublicUser(session,publicUser,publicUserform);
			}

			Purchase purchase = PurchaseSessionUtil.getPurchaseFromSession(req.getSession(false), session);
		    setPurchaseTotalAndCarriage(session,purchase,publicUserform);
			
			//updating this publicUser into session ,cookie  and purchase.setThisPublicUser(). also
		    // this does persists of user and purchase into database
			SessionData.update(publicUser, purchase, req, res,session);

			PublicUserMapper mapper = (PublicUserMapper) BeanUtil.getBean("publicUserMapper",this.getServlet().getServletContext());
			net.malta.model.json.PublicUser publicUserJson = mapper.map(publicUser, new net.malta.model.json.PublicUser());
			JSONResponseUtil.writeResponseAsJSON(res, publicUserJson);
			
		} finally {
			HibernateUtil.closeSession(session);
		}
		return null;
	}

	private boolean validate(HttpServletResponse res, PublicUserForm publicUserform) throws Exception{
		PublicUserFormValidator userFormValidator = (PublicUserFormValidator) BeanUtil.getBean("publicUserFormValidator", this.getServlet().getServletContext());		
		Errors errors = new Errors();
		userFormValidator.validate(publicUserform,errors);
		JSONResponseUtil.sendErrorJSON(res, errors);		
		return errors.hasErrors();
	}

	private Prefecture findPrefecture(Session session, Integer prefecture) {
		Criteria criteria2 = session.createCriteria(Prefecture.class);
		criteria2.add(Restrictions.idEq(prefecture));
		return (Prefecture) criteria2.uniqueResult();
	}

	private void setPurchaseTotalAndCarriage(Session session,Purchase purchase,PublicUserForm userForm) {		
	    Integer prefecture=userForm.getPrefecture();
	    int carriage = PrefectureCarriageDataMap.getCarriage(prefecture);
		PurchaseTotal purchaseTotal = new PurchaseTotal(purchase);
		purchaseTotal.calcAndSetTotal();
		purchaseTotal.setCarriage(carriage);		
	}

	private void setPublicUserAttributes(Session session,PublicUserForm publicUserform, PublicUser publicUser) {
		publicUser.setName(publicUserform.getName());
		publicUser.setKana(publicUserform.getKana());
		publicUser.setZipthree(publicUserform.getZip());
		publicUser.setZipfour(publicUserform.getZipfour());
		publicUser.setMailforconfirm(publicUserform.getMailforconfirm());
		publicUser.setMail(publicUserform.getMail());
		publicUser.setAddress(publicUserform.getAddress());
		publicUser.setBuildingname(publicUserform.getBuildingname());
		publicUser.setRegisted(publicUserform.isRegistered());
		publicUser.setFax(publicUserform.getFax());
		publicUser.setPhone(publicUserform.getPhone());
		publicUser.setPassword(publicUserform.getPassword());
		publicUser.setTemp(new Boolean(false));
		Prefecture prefecture = findPrefecture(session, publicUserform.getPrefecture());
		publicUser.setPrefecture(prefecture);
		StringFullfiller.fullfil(publicUser);
	}

	private PublicUser updatePublicUser(Session session, PublicUser publicUser, PublicUserForm publicUserform) {
		setPublicUserAttributes(session, publicUserform, publicUser);
		return publicUser;
	}

	private PublicUser existingUserCheck(Session session, PublicUserForm publicUserform) {
		PublicUser publicUser = null;
		Criteria criteria = session.createCriteria(PublicUser.class);
		criteria.add(Restrictions.idEq(publicUserform.getId()));
		publicUser = (PublicUser) criteria.uniqueResult();
		return publicUser;
	}

	private PublicUser createPublicUser(Session session, PublicUserForm publicUserform) {
		PublicUser publicUser = new PublicUserImpl();
		setPublicUserAttributes(session,publicUserform, publicUser);
		return publicUser;		
	}

}