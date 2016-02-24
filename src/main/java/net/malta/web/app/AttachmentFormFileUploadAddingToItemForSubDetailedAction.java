package net.malta.web.app;

import net.malta.beans.AttachmentForm;
import net.malta.model.*;
import net.malta.web.utils.AttachmentUtils;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.enclosing.util.HTTPGetRedirection;
import net.enclosing.util.HibernateSession;
import net.enclosing.util.StringFullfiller;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class AttachmentFormFileUploadAddingToItemForSubDetailedAction extends Action{
	public ActionForward execute(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest req,
			HttpServletResponse res) throws Exception{
		
		Attachment attachment = new AttachmentImpl();
		AttachmentForm attachmentform = (AttachmentForm) form;
		
		///////////////////////validation for file names jpgかJPGかJPEGじゃないなら
		if(! (attachmentform.getFormFiles()[0].getFileName().endsWith(".jpg") || attachmentform.getFormFiles()[0].getFileName().endsWith(".JPG") || attachmentform.getFormFiles()[0].getFileName().endsWith(".JPEG") )){
			req.getSession().setAttribute("systemmessage","画像はJPEG画像のみアップロード可能です。");
//			new HTTPGetRedirection(req, res, "##secoundmodelclass##Detail.do", attachmentform.getItem().toString());
			req.setAttribute("id",attachmentform.getAsdetailed());
			return mapping.findForward("error");
		}
		
		Integer ItemInt = attachmentform.getAsdetailed();
		attachmentform.setAsdetailed(null);
		
//		attachment.setNumber(Integer.parseInt(req.getParameter("setNumber")));
		
		Session session = new HibernateSession().currentSession(this
				.getServlet().getServletContext());
		Criteria criteria2 = session.createCriteria(Item.class);
		criteria2.add(Restrictions.idEq(ItemInt));
		Item item = (Item) criteria2
				.uniqueResult();
		attachment.setAsdetailed(item);
		DbFile dbFile =  AttachmentUtils.createDbFileFromFormFile(attachmentform.getFormFiles()[0]);
		attachment.setDbFile(dbFile);
		attachment.setFiletype("jpg");		

		Transaction transaction = session.beginTransaction();
		session.save(dbFile);
		session.save(attachment);
		transaction.commit();
		session.flush();
		
		
		if(StringUtils.isNotBlank(req.getParameter("from")) && "detail".equals(req.getParameter("from"))){		
			new HTTPGetRedirection(req, res, "PostItemDetail.html", item.getId().toString());
			return null;
		}
		
		new HTTPGetRedirection(req, res, "PostProductDetail.html", item.getProduct().getId().toString());
		return null;
	}
}