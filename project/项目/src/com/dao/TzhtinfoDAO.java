package com.dao;
import java.util.*;

import com.bean.Bbstype;
import com.bean.Tzhtinfo;
import com.bean.Tzinfo;
import com.bean.User;
public interface TzhtinfoDAO {
	void add(Tzhtinfo tzhtinfo);
	List<Tzhtinfo> selectHtinfo(int tzid);
	Tzhtinfo findById(int id);
	void updateDz(Tzhtinfo tzhtinfo);
	void delHt(int id);
	List<Tzhtinfo> selectMyAllhf(int memberid);
	List<Tzhtinfo> selectNorepetition(int memberid);
}
