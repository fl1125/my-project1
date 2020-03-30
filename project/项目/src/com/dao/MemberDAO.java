package com.dao;
import java.util.*;

import com.bean.Member;
import com.bean.User;
public interface MemberDAO {
	List<Member> checkUname(String uname);
	void add(Member member);
	List<Member> selectOne(Member member);
	Member findById(int id);
	void memberEdit(Member member);
	List<Member> selectAll();
	void updateTgShstatus(int id);
	void updateJjShstatus(int id);
	List<Member> searchKey(String key);
	void delete(int id);
	void updateIsjy(int id);
	void updateNojy(int id);
	void updateIsfh(int id);
	void updateNofh(int id);
	List<Member> findpwd(String uname,String upass);
}
