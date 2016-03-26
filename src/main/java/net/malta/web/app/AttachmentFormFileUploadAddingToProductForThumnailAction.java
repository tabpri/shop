package net.malta.web.app;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.enclosing.util.HTTPGetRedirection;
import net.enclosing.util.HibernateSession;
import net.enclosing.util.StringFullfiller;
import net.malta.beans.AttachmentForm;
import net.malta.model.Attachment;
import net.malta.model.AttachmentImpl;
import net.malta.model.DbFile;
import net.malta.model.Product;
import net.malta.web.utils.AttachmentUtils;
import static net.malta.web.utils.ActionUtil.*;

public class AttachmentFormFileUploadAddingToProductForThumnailAction extends Action{
	
	private static final Logger logger = LoggerFactory.getLogger(AttachmentFormFileUploadAddingToProductForThumnailAction.class);

	public ActionForward execute(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest req,
			HttpServletResponse res) throws Exception{

		logger.debug("AttachmentFormFileUploadAddingToProductForThumnailAction - start --- " + req.getParameter("product") + "  date : " + new Date());

		Attachment attachment = new AttachmentImpl();
		AttachmentForm attachmentform = (AttachmentForm) form;
		
		///////////////////////validation for file names jpgかJPGかJPEGじゃないなら
		if(! (attachmentform.getFormFiles()[0].getFileName().endsWith(".jpg") || attachmentform.getFormFiles()[0].getFileName().endsWith(".JPG") || attachmentform.getFormFiles()[0].getFileName().endsWith(".JPEG") )){
			req.getSession().setAttribute("systemmessage","画像はJPEG画像のみアップロード可能です。");
//			new HTTPGetRedirection(req, res, "##secoundmodelclass##Detail.do", attachmentform.getProduct().toString());
			req.setAttribute("id",req.getParameter("product"));
			return mapping.findForward("error");
		}
		
		Integer ProductInt = Integer.valueOf(req.getParameter("product"));
		
//		attachment.setNumber(Integer.parseInt(req.getParameter("setNumber")));
		
		Session session = new HibernateSession().currentSession(this
				.getServlet().getServletContext());
		Criteria criteria2 = session.createCriteria(Product.class);
		criteria2.add(Restrictions.idEq(ProductInt));
		Product product = (Product) criteria2
				.uniqueResult();
//		attachment.setProduct(product);
		product.setThumnail(attachment);

		DbFile dbFile =  AttachmentUtils.createDbFileFromFormFile(attachmentform.getFormFiles()[0]);
		attachment.setDbFile(dbFile);

		attachment.setFiletype("jpg");
		StringFullfiller.fullfil(attachment);
		
		Transaction transaction = session.beginTransaction();
		session.save(dbFile);
		session.save(attachment);
		session.saveOrUpdate(product);
		transaction.commit();
		session.flush();
		
		logger.debug("AttachmentFormFileUploadAddingToProductForThumnailAction - res.redirecting to admin/PostProductDetail.do");
		new HTTPGetRedirection(req, res, getModulePrefix(req)+"PostProductDetail.do", ProductInt.toString());
		return null;
	}
}