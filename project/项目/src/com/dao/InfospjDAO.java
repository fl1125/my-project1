package com.dao;
import java.util.*;

import com.bean.Bbstype;
import com.bean.Infospj;
import com.bean.Tzhtinfo;
import com.bean.Tzinfo;
import com.bean.User;
public interface InfospjDAO {
	void add(Infospj infospj);
	List<Infospj> selectInfospj(int infoid);
	Infospj findById(int id);
	void delInfospj(int id);
}
