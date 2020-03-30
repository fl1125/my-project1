package com.dao;
import java.util.*;

import com.bean.Yqlink;
public interface YqlinkDAO {
	List<Yqlink> selectAll();
	void add(Yqlink yqlink);
	Yqlink findById(int id);
	void update(Yqlink yqlink);
	void delete(int id);
	List<Yqlink> searchYqlink(String key);
}
