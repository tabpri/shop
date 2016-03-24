package net.malta.web.struts;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.tiles.TilesRequestProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.malta.model.validator.ValidationException;
import net.malta.web.utils.JSONResponseUtil;

public class ShopRequestProcessor extends TilesRequestProcessor {

	private static final Logger logger = LoggerFactory.getLogger(ShopRequestProcessor.class);
	
	@Override
	protected ActionForward processException(HttpServletRequest request, HttpServletResponse response, Exception exception,
			ActionForm actionForm, ActionMapping mapping) throws IOException, ServletException {
		if ( exception instanceof ValidationException ) {
			logger.debug("validation errors in the request processing. sending the error json");			
			ValidationException ve = (ValidationException) exception;
			JSONResponseUtil.sendErrorJSON(response, ve.getErrors());
			return null;
		} else {			
			return super.processException(request, response, exception, actionForm, mapping);
		}
	}
		
}
