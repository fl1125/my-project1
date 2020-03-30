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
import com.bean.Member;
import com.bean.User;
import com.dao.BanzhuDAO;
import com.dao.BbstypeDAO;
import com.dao.MemberDAO;
import com.dao.UserDAO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import java.util.*;

@Controller
public class BbstypeController extends BaseController {
	@Resource
	BbstypeDAO bbstypeDAO;
	@Resource
	BanzhuDAO banzhuDAO;
	@Resource
	MemberDAO memberDAO;
	
	
	
	//后台版块列表
	@RequestMapping("admin/bbstypeList")
	public String bbstypeList(HttpServletRequest request) {
		String index = request.getParameter("index");
	   	int pageindex = 1;
	   	if(index!=null){
	   		 pageindex = Integer.parseInt(index);
	   	}
   	    Page<Object> page = PageHelper.startPage(pageindex,6);
		List<Bbstype> list = bbstypeDAO.selectAll();
		for(int i=0;i<list.size();i++){
			List<Bbstype> childlist  = bbstypeDAO.selectChildtype(list.get(i).getId());
			list.get(i).setChildlist(childlist);
			List<Banzhu> banzhulist = banzhuDAO.selectOne(list.get(i).getId());
			if(banzhulist.size()!=0){
				Member banzhu = memberDAO.findById(banzhulist.get(0).getMemberid());
				list.get(i).setBanzhu(banzhu);
			}
		}
		request.setAttribute("list", list);
		request.setAttribute("index", page.getPageNum());
		request.setAttribute("pages", page.getPages());
		request.setAttribute("total", page.getTotal());
		return "admin/bbstypelist";
	}
	
	//添加大类
	@RequestMapping("admin/bbstypeAdd")
	public String bbstypeAdd(Bbstype bbstype, HttpServletRequest request) {
		String type = request.getParameter("type");
		if(type!=null&&type.equals("child")){
			Bbstype  bt = new Bbstype();
		}else{
		bbstypeDAO.add(bbstype);	
		}
		return "redirect:bbstypeList.do";
	}
	
	//显示版块信息
	@RequestMapping("admin/bbstypeShow")
	public String bbstypeShow(int id, HttpServletRequest request) {
		Bbstype bbstype = bbstypeDAO.findById(id);
		request.setAttribute("bbstype", bbstype);
		return "admin/bbstypeedit";
	}
	
	//编辑大类
	@RequestMapping("admin/bbstypeEdit")
	public String bbstypeEdit(Bbstype bbstype, HttpServletRequest request) {
		bbstypeDAO.update(bbstype);
		return "redirect:bbstypeList.do";
	}
	
	//删除类别
	@RequestMapping("admin/bbstypeDel")
	public String bbstypeDel(int id, HttpServletRequest request) {
		bbstypeDAO.delete(id);
		return "redirect:bbstypeList.do";
	}
	
	//搜索类别
	@RequestMapping("admin/searchTypename")
	public String searchTypename(HttpServletRequest request) {
		String index = request.getParameter("index");
		String key = request.getParameter("key");
	   	int pageindex = 1;
	   	if(index!=null){
	   		 pageindex = Integer.parseInt(index);
	   	}
   	    Page<Object> page = PageHelper.startPage(pageindex,6);
		List<Bbstype> list = bbstypeDAO.searchTypename(key);
		for(int i=0;i<list.size();i++){
			List<Bbstype> childlist  = bbstypeDAO.selectChildtype(list.get(i).getId());
			list.get(i).setChildlist(childlist);
		}
		request.setAttribute("list", list);
		request.setAttribute("key", key);
		request.setAttribute("index", page.getPageNum());
		request.setAttribute("pages", page.getPages());
		request.setAttribute("total", page.getTotal());
		return "admin/bbstypesearch";
	}
	
	//ajax查找子类
	@RequestMapping("admin/searchBbstype")
	public void searchBbstype(HttpServletRequest request,HttpServletResponse response) {
		try {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			String fid = request.getParameter("fid");
			
			List<Bbstype> stypelist = bbstypeDAO.selectChildtype(Integer.parseInt(fid));
			String optionstr = "";
			for(Bbstype bbstype:stypelist){
				optionstr +="<option value="+bbstype.getId()+">"+bbstype.getTypename()+"</option>";
			}
			//System.out.println("optionstr==="+optionstr);
			out.print(optionstr);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//撤消版主
	@RequestMapping("admin/bzDel")
	public void bzDel(Banzhu banzhu, HttpServletRequest request,HttpServletResponse response) {
		try {
			banzhuDAO.delete(banzhu);
			PrintWriter out = response.getWriter();
			out.print(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
