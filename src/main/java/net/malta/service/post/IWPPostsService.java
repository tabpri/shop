package net.malta.service.post;

public interface IWPPostsService {

	String getImg(Integer wp_posts_id);

	String getName(int wp_posts_id);

	int getPrice(int wp_posts_id);

}