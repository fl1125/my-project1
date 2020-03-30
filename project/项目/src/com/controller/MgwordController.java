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

import com.bean.Bbstype;
import com.bean.Member;
import com.bean.Mgword;
import com.bean.News;
import com.bean.Tzinfo;
import com.bean.User;
import com.bean.Yqlink;
import com.dao.BbstypeDAO;
import com.dao.MemberDAO;
import com.dao.MgwordDAO;
import com.dao.NewsDAO;
import com.dao.TzinfoDAO;
import com.dao.UserDAO;
import com.dao.YqlinkDAO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.util.Info;

import java.util.*;

@Controller
public class MgwordController extends BaseController {
	@Resource
	MgwordDAO mgwordDAO;
	
	//后台敏感字符列表
	@RequestMapping("admin/mgwordList")
	public String newsList(HttpServletRequest request) {
		String index = request.getParameter("index");
	   	int pageindex = 1;
	   	if(index!=null){
	   		 pageindex = Integer.parseInt(index);
	   	}
   	    Page<Object> page = PageHelper.startPage(pageindex,6);
		List<Mgword> list = mgwordDAO.selectAll();
		request.setAttribute("list", list);
		request.setAttribute("index", page.getPageNum());
		request.setAttribute("pages", page.getPages());
		request.setAttribute("total", page.getTotal());
		return "admin/mgwordlist";
	}
	
	//后台搜索敏感字符列表
	@RequestMapping("admin/searchMgword")
	public String searchMgword(HttpServletRequest request) {
		String index = request.getParameter("index");
		String key = request.getParameter("key");
	   	int pageindex = 1;
	   	if(index!=null){
	   		 pageindex = Integer.parseInt(index);
	   	}
   	    Page<Object> page = PageHelper.startPage(pageindex,6);
		List<Mgword> list = mgwordDAO.searchMgword(key);
		request.setAttribute("list", list);
		request.setAttribute("key", key);
		request.setAttribute("index", page.getPageNum());
		request.setAttribute("pages", page.getPages());
		request.setAttribute("total", page.getTotal());
		return "admin/searchmgword";
	}
	
	
	//添加敏感学符
	@RequestMapping("admin/mgwordAdd")
	public String mgwordAdd(Mgword mgword, HttpServletRequest request) {
		mgwordDAO.add(mgword);
		return "redirect:mgwordList.do";
	}
	
	//显示要修改的信息
	@RequestMapping("admin/mgwordShow")
	public String mgwordShow(int id, HttpServletRequest request) {
		Mgword mgword =mgwordDAO.findById(id);
		request.setAttribute("mgword", mgword);
		return "admin/mgwordedit";
	}
	
	//修改敏感字符
	@RequestMapping("admin/mgwordEdit")
	public String mgwordShow(Mgword mgword, HttpServletRequest request) {
		mgwordDAO.update(mgword);
		request.setAttribute("mgword", mgword);
		return "redirect:mgwordList.do";
	}
	
	//删除敏感字符
	@RequestMapping("admin/mgwordDel")
	public String mgwordDel(int id, HttpServletRequest request) {
		mgwordDAO.delete(id);
		return "redirect:mgwordList.do";
	}
	
	

}
