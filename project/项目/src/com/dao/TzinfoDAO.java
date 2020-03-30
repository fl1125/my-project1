package com.dao;
import java.util.*;

import com.bean.Bbstype;
import com.bean.Tzinfo;
import com.bean.User;
public interface TzinfoDAO {
	List<Tzinfo> selectAll();
	void add(Tzinfo tzinfo);
	List<Tzinfo> myTzinfo(int author);
	List<Tzinfo> selectNowtzinfo(String savetime);
	List<Tzinfo> selectTypetzinfo(int typeid);
	Tzinfo findById(int id);
	void updateLooknum(int id);
	List<Tzinfo> selectPtzinfo(Map map);
	List<Tzinfo> selectTopzinfo(int fid);
	void updateIstop(int id);
	void updateNotop(int id);
	void updateIsjh(int id);
	void updateNojh(int id);
	void updata(Tzinfo tzinfo);
	void updateCanht(Tzinfo tzinfo);
	void delete(int id);
	List<Tzinfo> searchTzinfo(HashMap map);
	void updateType(Tzinfo tzinfo);
	List<Tzinfo> searchQ(String key);
}
