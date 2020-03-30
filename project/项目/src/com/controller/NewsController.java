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
import com.bean.News;
import com.bean.Tzinfo;
import com.bean.User;
import com.bean.Yqlink;
import com.dao.BbstypeDAO;
import com.dao.MemberDAO;
import com.dao.NewsDAO;
import com.dao.TzinfoDAO;
import com.dao.UserDAO;
import com.dao.YqlinkDAO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.util.Info;

import java.util.*;

@Controller
public class NewsController extends BaseController {
	@Resource
	NewsDAO newsDAO;
	@Resource
	MemberDAO memberDAO;
	@Resource
	BbstypeDAO bbstypeDAO;
	@Resource
	YqlinkDAO yqlinkDAO;
	@Resource
	TzinfoDAO tzinfoDAO;
	
	//后台新闻列表
	@RequestMapping("admin/newsList")
	public String newsList(HttpServletRequest request) {
		String index = request.getParameter("index");
	   	int pageindex = 1;
	   	if(index!=null){
	   		 pageindex = Integer.parseInt(index);
	   	}
   	    Page<Object> page = PageHelper.startPage(pageindex,6);
		List<News> list = newsDAO.selectAll();
		request.setAttribute("list", list);
		request.setAttribute("index", page.getPageNum());
		request.setAttribute("pages", page.getPages());
		request.setAttribute("total", page.getTotal());
		return "admin/newslist";
	}
	
	//后台搜索新闻列表
	@RequestMapping("admin/searchNews")
	public String searchNews(HttpServletRequest request) {
		String index = request.getParameter("index");
		String title = request.getParameter("title");
	   	int pageindex = 1;
	   	if(index!=null){
	   		 pageindex = Integer.parseInt(index);
	   	}
   	    Page<Object> page = PageHelper.startPage(pageindex,6);
		List<News> list = newsDAO.searchNews(title);
		request.setAttribute("list", list);
		request.setAttribute("title", title);
		request.setAttribute("index", page.getPageNum());
		request.setAttribute("pages", page.getPages());
		request.setAttribute("total", page.getTotal());
		return "admin/newssearch";
	}
	//新增新闻
	@RequestMapping("admin/newsAdd")
	public String newsAdd(News news, HttpServletRequest request) {
		news.setSavetime(Info.getDateStr());
		newsDAO.add(news);
		return "redirect:newsList.do";
	}
	
    //删除新闻
	@RequestMapping("admin/newsDel")
	public String newsDel(int id, HttpServletRequest request) {
		newsDAO.delete(id);
		return "redirect:newsList.do";
	}
	
	//ID查找新闻
	@RequestMapping("admin/newsShow")
	public String newsShow(int id, HttpServletRequest request) {
		News news = newsDAO.findById(id);
		request.setAttribute("news", news);
		return "admin/newsedit";
	}
	
	//更新新闻
	@RequestMapping("admin/newsEdit")
	public String newsDel(News news, HttpServletRequest request) {
		newsDAO.update(news);
		return "redirect:newsList.do";
	}
	
	//前台新闻详情
	@RequestMapping("newsDetail")
	public String newsDetail(int id, HttpServletRequest request) {
		News news = newsDAO.findById(id);
		request.setAttribute("news", news);
		
		
		List<Yqlink> yqlinklist = yqlinkDAO.selectAll();
		List<Bbstype> typelist = bbstypeDAO.selectAll();
		//今日话题
		List<Tzinfo> nowtzinfolist = tzinfoDAO.selectNowtzinfo(Info.getDateStr().substring(0,10));
		List<News> newslist = newsDAO.selectAll();
		for(Bbstype bbstype:typelist){
			//子类
			List<Bbstype> childlist = bbstypeDAO.selectChildtype(bbstype.getId());
			bbstype.setChildlist(childlist);
			//分类贴子
			List<Tzinfo> ftypetzinfolist = tzinfoDAO.selectTypetzinfo(bbstype.getId());
			bbstype.setFtypetzinfolist(ftypetzinfolist);
			
		}
		request.setAttribute("nowtzinfolist", nowtzinfolist);
		request.setAttribute("yqlinklist", yqlinklist);
		request.setAttribute("typelist", typelist);
		request.setAttribute("newslist", newslist);
		return "newsdetail";
	}
	

}
