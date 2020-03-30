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
import com.bean.Infos;
import com.bean.Infospj;
import com.bean.Member;
import com.bean.News;
import com.bean.Tzhtinfo;
import com.bean.Tzinfo;
import com.bean.User;
import com.bean.Yqlink;
import com.dao.BanzhuDAO;
import com.dao.BbstypeDAO;
import com.dao.InfosDAO;
import com.dao.InfospjDAO;
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
public class InfosController extends BaseController {
	@Resource
	InfosDAO infosDAO;
	@Resource
	YqlinkDAO yqlinkDAO;
	@Resource
	TzinfoDAO tzinfoDAO;
	@Resource
	NewsDAO newsDAO;
	@Resource
	InfospjDAO infospjDAO;
	@Resource
	MemberDAO memberDAO;
	
	//后台信息查询
	@RequestMapping("admin/infosList")
	public String infosList(HttpServletRequest request) {
		
		List<Yqlink> yqlinklist = yqlinkDAO.selectAll();
		//今日话题
		List<Tzinfo> nowtzinfolist = tzinfoDAO.selectNowtzinfo(Info.getDateStr().substring(0,10));
		List<News> newslist = newsDAO.selectAll();
		
		String type = request.getParameter("type");
		String index = request.getParameter("index");
	   	int pageindex = 1;
	   	if(index!=null){
	   		 pageindex = Integer.parseInt(index);
	   	}
   	    Page<Object> page = PageHelper.startPage(pageindex,6);
		List<Infos> list = infosDAO.selectAll(type);
		request.setAttribute("list", list);
		request.setAttribute("type", type);
		request.setAttribute("index", page.getPageNum());
		request.setAttribute("pages", page.getPages());
		request.setAttribute("total", page.getTotal());
		
		request.setAttribute("nowtzinfolist", nowtzinfolist);
		request.setAttribute("yqlinklist", yqlinklist);
		request.setAttribute("newslist", newslist);
		if(type.equals("1")){//
			return "admin/infoslist";
		}else if(type.equals("2")){//
			return "admin/infoslist";
		}else{//
			return "admin/infoslist";
		}
	}
	
	//添加
	@RequestMapping("admin/infosAdd")
	public String infosAdd(Infos infos, HttpServletRequest request) {
		String type = request.getParameter("type");
		infos.setSavetime(Info.getDateStr());
		infosDAO.add(infos);	
		return "redirect:infosList.do?type="+type;
	}
	
	@RequestMapping("admin/infosShow")
	public String infosShow(int id, HttpServletRequest request) {
		Infos infos = infosDAO.findById(id);
		request.setAttribute("infos", infos);
		request.setAttribute("type", infos.getType());
		return "admin/infosedit";
	}
	
	//编辑
	@RequestMapping("admin/infosEdit")
	public String infosEdit(Infos infos, HttpServletRequest request) {
		String type = request.getParameter("type");
		infosDAO.update(infos);
		return "redirect:infosList.do?type="+type;
	}
	
	//删除
	@RequestMapping("admin/infosDel")
	public String infosDel(int id, HttpServletRequest request) {
		Infos infos = infosDAO.findById(id);
		infosDAO.delete(id);
		return "redirect:infosList.do?type="+infos.getType();
	}
	
	//搜索标题
	@RequestMapping("admin/searchByTitle")
	public String searchByTitle(HttpServletRequest request) {
		String index = request.getParameter("index");
		String key = request.getParameter("key");
		String type = request.getParameter("type");
	   	int pageindex = 1;
	   	if(index!=null){
	   		 pageindex = Integer.parseInt(index);
	   	}
   	    Page<Object> page = PageHelper.startPage(pageindex,6);
   	    HashMap map = new HashMap();
   	    map.put("key", key);
   	    map.put("type", type);
		List<Infos> list = infosDAO.searchByTitle(map);
		request.setAttribute("list", list);
		request.setAttribute("key", key);
		request.setAttribute("type", type);
		request.setAttribute("index", page.getPageNum());
		request.setAttribute("pages", page.getPages());
		request.setAttribute("total", page.getTotal());
		
		if(type.equals("1")){
			return "admin/infoslist";
		}else if(type.equals("2")){
			return "admin/infoslist";
		}else{
			return "admin/infoslist";
		}
	}
	
	
	

	//前台信息查询
	@RequestMapping("infosListQT")
	public String infosListQT(HttpServletRequest request) {
		List<Yqlink> yqlinklist = yqlinkDAO.selectAll();
		//今日话题
		List<Tzinfo> nowtzinfolist = tzinfoDAO.selectNowtzinfo(Info.getDateStr().substring(0,10));
		List<News> newslist = newsDAO.selectAll();
		
		String type = request.getParameter("type");
		String index = request.getParameter("index");
	   	int pageindex = 1;
	   	if(index!=null){
	   		 pageindex = Integer.parseInt(index);
	   	}
   	    Page<Object> page = PageHelper.startPage(pageindex,6);
		List<Infos> list = infosDAO.selectAll(type);
		request.setAttribute("list", list);
		request.setAttribute("type", type);
		request.setAttribute("index", page.getPageNum());
		request.setAttribute("pages", page.getPages());
		request.setAttribute("total", page.getTotal());
		request.setAttribute("nowtzinfolist", nowtzinfolist);
		request.setAttribute("yqlinklist", yqlinklist);
		request.setAttribute("newslist", newslist);
		return "infoslist";
	}
	
	@RequestMapping("infosShowQT")
	public String infosShowQT(int id, HttpServletRequest request) {
		List<Yqlink> yqlinklist = yqlinkDAO.selectAll();
		//今日话题
		List<Tzinfo> nowtzinfolist = tzinfoDAO.selectNowtzinfo(Info.getDateStr().substring(0,10));
		List<News> newslist = newsDAO.selectAll();
		
		
		Infos infos = infosDAO.findById(id);
		request.setAttribute("infos", infos);
		request.setAttribute("type", infos.getType());
		request.setAttribute("nowtzinfolist", nowtzinfolist);
		request.setAttribute("yqlinklist", yqlinklist);
		request.setAttribute("newslist", newslist);
		
		
		
		
		
		String index = request.getParameter("index");
	   	int pageindex = 1;
	   	if(index!=null){
	   		 pageindex = Integer.parseInt(index);
	   	}
   	    Page<Object> page = PageHelper.startPage(pageindex,10);
   	    List<Infospj> pjlist = infospjDAO.selectInfospj(infos.getId());
		for(Infospj infospj:pjlist){
			Member pjmember = memberDAO.findById(infospj.getMid());
			infospj.setHtmember(pjmember);
		}
		request.setAttribute("index", page.getPageNum());
		request.setAttribute("pages", page.getPages());
		request.setAttribute("total", page.getTotal());
		request.setAttribute("pjlist", pjlist);
		request.setAttribute("id", id);
		
		
		return "infosdetail";
	}
	
	
	
	//后台查询信息评论信息
	@RequestMapping("admin/infospjList")
	public String infospjList(HttpServletRequest request) {
		String infoid = request.getParameter("infoid");
		String type = request.getParameter("type");
		String index = request.getParameter("index");
	   	int pageindex = 1;
	   	if(index!=null){
	   		 pageindex = Integer.parseInt(index);
	   	}
   	    Page<Object> page = PageHelper.startPage(pageindex,6);
		List<Infospj> list = infospjDAO.selectInfospj(Integer.valueOf(infoid));
		for(Infospj infospj:list){
			Member pjmember = memberDAO.findById(infospj.getMid());
			infospj.setHtmember(pjmember);
		}
		request.setAttribute("list", list);
		request.setAttribute("infoid", infoid);
		request.setAttribute("type", type);
		request.setAttribute("index", page.getPageNum());
		request.setAttribute("pages", page.getPages());
		request.setAttribute("total", page.getTotal());
		return "admin/infospjlist";
	}
	
	//后台删除评论
	@RequestMapping("admin/delInfospj")
	public String delInfospj(HttpServletRequest request) {
		String id = request.getParameter("id");
		String infoid = request.getParameter("infoid");
		String type = request.getParameter("type");
		infospjDAO.delInfospj(Integer.valueOf(id));
		return "redirect:infospjList.do?type="+type+"&infoid="+infoid;
	}

}
