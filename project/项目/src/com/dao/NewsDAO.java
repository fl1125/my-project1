package com.dao;
import java.util.*;

import com.bean.Member;
import com.bean.News;
import com.bean.User;
public interface NewsDAO {
	List<News> selectAll();
	void add(News news);
	void delete(int id);
	News findById(int id);
	void update(News news);
	List<News> searchNews(String title);
}
