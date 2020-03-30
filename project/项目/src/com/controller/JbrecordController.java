package com.controller;

import javax.annotation.Resource;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bean.Banzhu;
import com.bean.Bbstype;
import com.bean.Fans;
import com.bean.Jbrecord;
import com.bean.Member;
import com.bean.News;
import com.bean.Tzinfo;
import com.bean.User;
import com.bean.Yqlink;
import com.dao.BbstypeDAO;
import com.dao.FansDAO;
import com.dao.JbrecordDAO;
import com.dao.MemberDAO;
import com.dao.UserDAO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.util.Info;

import java.util.*;

@Controller
public class JbrecordController extends BaseController {
	@Resource
	JbrecordDAO jbrecordDAO;
	@Resource
	MemberDAO memberDAO;
	
	//关注
	@RequestMapping("jbShow")
	public String jbShow( int jbmemberid,HttpServletRequest request){
		Member member = memberDAO.findById(jbmemberid);
		request.setAttribute("jbmember", member);
		return "jbadd";
	}
	
	//举报添加
	@RequestMapping("jbAdd")
	public void jbAdd(HttpServletRequest request,HttpServletResponse response) {
		try {
			String memberid = request.getParameter("memberid");
			String jbmemberid = request.getParameter("jbmemberid");
			String note = request.getParameter("note");
			PrintWriter out = response.getWriter();
			HashMap map = new HashMap();
			map.put("memberid", memberid);
			map.put("jbmemberid", jbmemberid);
			List<Jbrecord> list = jbrecordDAO.selectOne(map);
			if(list.size()==0){
				Jbrecord jbrecord  = new Jbrecord();
				jbrecord.setMemberid(Integer.parseInt(memberid));
				jbrecord.setNote(note);
				jbrecord.setJbmemberid(Integer.parseInt(jbmemberid));
				jbrecordDAO.add(jbrecord);
				out.print(0);
			}else{
				out.print(1);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//后台举报列表
	@RequestMapping("admin/jbList")
	public String jbList(HttpServletRequest request) {
		String index = request.getParameter("index");
	   	int pageindex = 1;
	   	if(index!=null){
	   		 pageindex = Integer.parseInt(index);
	   	}
   	    Page<Object> page = PageHelper.startPage(pageindex,6);
		List<Jbrecord> list = jbrecordDAO.selectAll();
		for(Jbrecord jbrecord:list){
			Member member = memberDAO.findById(jbrecord.getMemberid());
			Member jbmember = memberDAO.findById(jbrecord.getJbmemberid());
			jbrecord.setMember(member);
			jbrecord.setJbmember(jbmember);
		}
		request.setAttribute("list", list);
		request.setAttribute("index", page.getPageNum());
		request.setAttribute("pages", page.getPages());
		request.setAttribute("total", page.getTotal());
		return "admin/jblist";
	}
	
	//后台搜索
	@RequestMapping("admin/jbSearch")
	public String jbSearch(HttpServletRequest request) {
		String index = request.getParameter("index");
		String key = request.getParameter("key");
	   	int pageindex = 1;
	   	if(index!=null){
	   		 pageindex = Integer.parseInt(index);
	   	}
   	    Page<Object> page = PageHelper.startPage(pageindex,6);
		List<Jbrecord> list = jbrecordDAO.searchContent(key);
		for(Jbrecord jbrecord:list){
			Member member = memberDAO.findById(jbrecord.getMemberid());
			Member jbmember = memberDAO.findById(jbrecord.getJbmemberid());
			jbrecord.setMember(member);
			jbrecord.setJbmember(jbmember);
		}
		request.setAttribute("list", list);
		request.setAttribute("key", key);
		request.setAttribute("index", page.getPageNum());
		request.setAttribute("pages", page.getPages());
		request.setAttribute("total", page.getTotal());
		return "admin/jbsearch";
	}
	
	
	//删除举报信息
	@RequestMapping("admin/jbDel")
	public String jbDel(int id,HttpServletRequest request) {
		jbrecordDAO.delete(id);
		return "redirect:jbList.do";
	}
	

}
