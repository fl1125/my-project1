package com.dao;
import java.util.*;

import com.bean.Banzhu;
import com.bean.Bbstype;
import com.bean.User;
public interface BanzhuDAO {
	List<Banzhu> selectOne(int fid);
	void add(Banzhu banzhu);
	List<Banzhu> selectIsbz(Banzhu banzhu);
	void delete(Banzhu banzhu);
}
