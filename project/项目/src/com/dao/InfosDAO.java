package com.dao;
import java.util.*;

import com.bean.Bbstype;
import com.bean.Infos;
import com.bean.User;
public interface InfosDAO {
	List<Infos> selectAll(String type);
	void add(Infos infos);
	Infos findById(int id);
	void update(Infos infos);
	void delete(int id);
	List<Infos> searchByTitle(HashMap map);
}
