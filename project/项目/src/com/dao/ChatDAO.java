package com.dao;
import java.util.*;

import com.bean.Banzhu;
import com.bean.Bbstype;
import com.bean.Chat;
import com.bean.User;
public interface ChatDAO {
	List<Chat> selectAll();
	void add(Chat chat);
	void update(Chat chat);
	void delete(int id);
	List<Chat> searchKey(String key);
}
