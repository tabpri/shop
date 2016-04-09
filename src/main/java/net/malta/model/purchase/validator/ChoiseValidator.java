package net.malta.model.purchase.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.malta.dao.post.WPPostsDAO;
import net.malta.dao.product.ItemDAO;
import net.malta.error.Errors;
import net.malta.error.ValidationError;
import net.malta.model.Choise;
import net.malta.model.Item;
import net.malta.model.validator.IValidator;
import net.malta.model.validator.ValidationException;
import net.malta.model.validator.constants.ChoiseConstants;

@Component
public class ChoiseValidator implements IValidator<Choise> {

	@Autowired
	ItemDAO itemDAO;

	@Autowired
	WPPostsDAO postsDAO;

	@Override
	public void validate(Choise choise, Errors errors) throws ValidationException {
		
		Integer itemId = choise.getItem().getId();
		
		if ( itemId == null ) {
			errors.add(new ValidationError(ChoiseConstants.CHOISE_ITEM_ISBLANK,new Object[0]));
		} else {
			Item itemReturned = itemDAO.find(itemId);
			if ( itemReturned == null ) {
				errors.add((new ValidationError(ChoiseConstants.CHOISE_ITEM_DOESNOTEXIST, itemId)));
			} else {
				String name = postsDAO.getName(itemId);
				if ( name == null ) {
					errors.add((new ValidationError(ChoiseConstants.CHOISE_ITEM_NOTCONFIGURED_WPPOSTS, itemId)));					
				}
			}
		}
		
		if ( errors.hasErrors() ) {
			throw new ValidationException(errors);
		}
	}

}