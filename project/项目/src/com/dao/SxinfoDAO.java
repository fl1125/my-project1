package com.dao;
import java.util.*;

import com.bean.Banzhu;
import com.bean.Bbstype;
import com.bean.Sxinfo;
import com.bean.User;
public interface SxinfoDAO {
	void add(Sxinfo sxinfo);
	List<Sxinfo> selectMysendsx(int memberid);
	List<Sxinfo> selectMyrecsx(int sxmemberid);
	void updateSxHf(HashMap<String,String> map);
	void delete(int id);
}

