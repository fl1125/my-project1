package com.dao;
import java.util.*;

import com.bean.Banzhu;
import com.bean.Bbstype;
import com.bean.Jbrecord;
import com.bean.User;
public interface JbrecordDAO {
	List<Jbrecord> selectOne(HashMap map);
	void add(Jbrecord jbrecord);
	List<Jbrecord> selectAll();
	void delete(int id);
	List<Jbrecord> searchContent(String key);
}
