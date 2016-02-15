package net.malta.service.post;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.malta.dao.post.WPPostsDAO;

public class WPPostService implements IWPPostsService {

	@SuppressWarnings("rawtypes")
	private WPPostsDAO postsDAO;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public String getImg(Integer wp_posts_id) {		
		return postsDAO.getImg(wp_posts_id);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)	
	public String getName(int wp_posts_id) {
		return postsDAO.getName(wp_posts_id);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)	
	public  int getPrice(int wp_posts_id) {
		return postsDAO.getPrice(wp_posts_id);
	}

	@SuppressWarnings("rawtypes")
	public void setPostsDAO(WPPostsDAO postsDAO) {
		this.postsDAO = postsDAO;
	}

}
