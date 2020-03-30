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
import com.bean.News;
import com.bean.Tzhtinfo;
import com.bean.Tzinfo;
import com.bean.User;
import com.bean.Yqlink;
import com.dao.BanzhuDAO;
import com.dao.BbstypeDAO;
import com.dao.MemberDAO;
import com.dao.NewsDAO;
import com.dao.TzhtinfoDAO;
import com.dao.TzinfoDAO;
import com.dao.UserDAO;
import com.dao.YqlinkDAO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.util.Info;

import java.util.*;

@Controller
public class IndexController extends BaseController {
	@Resource
	MemberDAO memberDAO;
	@Resource
	BbstypeDAO bbstypeDAO;
	@Resource
	NewsDAO newsDAO;
	@Resource
	YqlinkDAO yqlinkDAO;
	@Resource
	TzinfoDAO tzinfoDAO;
	@Resource
	BanzhuDAO banzhuDAO;
	@Resource
	TzhtinfoDAO tzhtinfoDAO;
	
	//首页
	@RequestMapping("index")
	public String index(HttpServletRequest request){
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
			
			//版主
			Banzhu banzhu;
			List<Banzhu> banzhulist = banzhuDAO.selectOne(bbstype.getId());
			if(banzhulist.size()!=0){
				banzhu = banzhulist.get(0);
				Member member = memberDAO.findById(banzhu.getMemberid());
				bbstype.setBanzhu(member);
			}
			
		}
		request.setAttribute("nowtzinfolist", nowtzinfolist);
		request.setAttribute("yqlinklist", yqlinklist);
		request.setAttribute("typelist", typelist);
		request.setAttribute("newslist", newslist);
		return "index";
	}
	
	//搜索
	@RequestMapping("search")
	public String search(HttpServletRequest request){
		String key = request.getParameter("q");
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
			
			//版主
			Banzhu banzhu;
			List<Banzhu> banzhulist = banzhuDAO.selectOne(bbstype.getId());
			if(banzhulist.size()!=0){
				banzhu = banzhulist.get(0);
				Member member = memberDAO.findById(banzhu.getMemberid());
				bbstype.setBanzhu(member);
			}
			
		}
		
		request.setAttribute("nowtzinfolist", nowtzinfolist);
		request.setAttribute("yqlinklist", yqlinklist);
		request.setAttribute("typelist", typelist);
		request.setAttribute("newslist", newslist);
		
		
		String index = request.getParameter("index");
	   	int pageindex = 1;
	   	if(index!=null){
	   		 pageindex = Integer.parseInt(index);
	   	}
   	    Page<Object> page = PageHelper.startPage(pageindex,6);
		List<Tzinfo> list = tzinfoDAO.searchQ(key);
		//System.out.println("list===="+list.size());
		for(Tzinfo tzinfo:list){
			Member author = memberDAO.findById(tzinfo.getAuthor());
			Bbstype ftype = bbstypeDAO.findById(tzinfo.getFid());
			Bbstype stype = bbstypeDAO.findById(tzinfo.getSid());
			List<Tzhtinfo> allhtlist = tzhtinfoDAO.selectHtinfo(tzinfo.getId());
			tzinfo.setAllhtlist(allhtlist);
			
			tzinfo.setStype(stype);
			tzinfo.setFtype(ftype);
			tzinfo.setMember(author);
		}
		request.setAttribute("list", list);
		request.setAttribute("key", key);
		request.setAttribute("index", page.getPageNum());
		request.setAttribute("pages", page.getPages());
		request.setAttribute("total", page.getTotal());
		return "search";
	}

}
