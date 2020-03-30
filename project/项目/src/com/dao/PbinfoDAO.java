package com.dao;
import java.util.*;

import com.bean.Banzhu;
import com.bean.Bbstype;
import com.bean.Fans;
import com.bean.Pbinfo;
import com.bean.User;
public interface PbinfoDAO {
	void add(Pbinfo pbinfo);
	List<Pbinfo> selectOne(HashMap<String, String> map);
	void delete(Pbinfo pbinfo);
	List<Pbinfo> selectPbmember(int memberid);
	void deletePingbi(int id);
}
