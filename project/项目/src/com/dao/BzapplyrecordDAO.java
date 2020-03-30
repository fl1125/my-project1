package com.dao;
import java.util.*;

import com.bean.Bbstype;
import com.bean.Bzapplyrecord;
import com.bean.User;
public interface BzapplyrecordDAO {
	List<Bzapplyrecord> selectMybzapply(int memberid);
	List<Bzapplyrecord> selectIssqlist(Bzapplyrecord bzapplyrecord);
	List<Bzapplyrecord> selectIsbzlist(Bzapplyrecord bzapplyrecord);
	void add(Bzapplyrecord bzapplyrecord);
	List<Bzapplyrecord> selectAll();
	void updateTgshstatus(int id);
	void updateJjshstatus(int id);
	void delete(int id);
}
