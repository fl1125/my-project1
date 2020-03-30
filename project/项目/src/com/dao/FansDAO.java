package com.dao;
import java.util.*;

import com.bean.Banzhu;
import com.bean.Bbstype;
import com.bean.Fans;
import com.bean.User;
public interface FansDAO {
	List<Fans> selectOne(HashMap<String, String> map);
	List<Fans> selectFans(int gzmemberid);
	List<Fans> selectMyFans(int memberid);
	void add(Fans fans);
	void delete(Fans fans);
	void deleteFs(int id);
}
