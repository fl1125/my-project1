package com.dao;
import java.util.*;

import com.bean.Bbstype;
import com.bean.Mgword;
import com.bean.User;
public interface MgwordDAO {
	List<Mgword> selectAll();
	void add(Mgword mgword);
	Mgword findById(int id);
	void update(Mgword mgword);
	void delete(int id);
	List<Mgword> searchMgword(String key);
}
