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
import com.bean.User;
import com.bean.Yqlink;
import com.dao.BbstypeDAO;
import com.dao.UserDAO;
import com.dao.YqlinkDAO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import java.util.*;

@Controller
public class YqlinkController extends BaseController {
	@Resource
	YqlinkDAO yqlinkDAO;
	
	//后台版块列表
	@RequestMapping("admin/yqlinkList")
	public String yqlinkList(HttpServletRequest request) {
		String index = request.getParameter("index");
	   	int pageindex = 1;
	   	if(index!=null){
	   		 pageindex = Integer.parseInt(index);
	   	}
   	    Page<Object> page = PageHelper.startPage(pageindex,6);
		List<Yqlink> list = yqlinkDAO.selectAll();
		request.setAttribute("list", list);
		request.setAttribute("index", page.getPageNum());
		request.setAttribute("pages", page.getPages());
		request.setAttribute("total", page.getTotal());
		return "admin/yqlinklist";
	}
	
	//添加友情链接
	@RequestMapping("admin/yqlinkAdd")
	public String yqlinkAdd(Yqlink yqlink, HttpServletRequest request) {
		yqlinkDAO.add(yqlink);	
		return "redirect:yqlinkList.do";
	}
	
	//显示友情链接信息
	@RequestMapping("admin/yqlinkShow")
	public String yqlinkShow(int id, HttpServletRequest request) {
		Yqlink yqlink = yqlinkDAO.findById(id);
		request.setAttribute("yqlink", yqlink);
		return "admin/yqlinkedit";
	}
	
	//编辑友情链接
	@RequestMapping("admin/yqlinkEdit")
	public String yqlinkEdit(Yqlink yqlink, HttpServletRequest request) {
		yqlinkDAO.update(yqlink);
		return "redirect:yqlinkList.do";
	}
	
	//删除类别
	@RequestMapping("admin/yqlinkDel")
	public String yqlinkDel(int id, HttpServletRequest request) {
		yqlinkDAO.delete(id);
		return "redirect:yqlinkList.do";
	}
	
	//搜索类别
	@RequestMapping("admin/searchYqlink")
	public String searchYqlink(HttpServletRequest request) {
		String index = request.getParameter("index");
		String key = request.getParameter("key");
	   	int pageindex = 1;
	   	if(index!=null){
	   		 pageindex = Integer.parseInt(index);
	   	}
   	    Page<Object> page = PageHelper.startPage(pageindex,6);
		List<Yqlink> list = yqlinkDAO.searchYqlink(key);
		request.setAttribute("list", list);
		request.setAttribute("key", key);
		request.setAttribute("index", page.getPageNum());
		request.setAttribute("pages", page.getPages());
		request.setAttribute("total", page.getTotal());
		return "admin/yqlinksearch";
	}
	

}
