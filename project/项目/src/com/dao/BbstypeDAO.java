package com.dao;
import java.util.*;

import com.bean.Bbstype;
import com.bean.User;
public interface BbstypeDAO {
	List<Bbstype> selectAll();
	List<Bbstype> selectChildtype(int id);
	void add(Bbstype bbstype);
	Bbstype findById(int id);
	void update(Bbstype bbstype);
	void delete(int id);
	List<Bbstype> searchTypename(String key);
	List<Bbstype> selectFathertype();
}
