package com.dao;
import java.util.*;

import com.bean.Bbstype;
import com.bean.Signin;
import com.bean.User;
public interface SigninDAO {
	List<Signin> isSignin(HashMap map);
	void add(Signin signin);
}
