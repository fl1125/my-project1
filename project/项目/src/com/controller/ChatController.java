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
import com.bean.Chat;
import com.bean.Member;
import com.bean.News;
import com.bean.Tzinfo;
import com.bean.User;
import com.bean.Yqlink;
import com.dao.BanzhuDAO;
import com.dao.BbstypeDAO;
import com.dao.ChatDAO;
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
public class ChatController extends BaseController {
	@Resource
	ChatDAO chatDAO;
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
	
	//前台意见和建议
	@RequestMapping("chatList")
	public String chatList(HttpServletRequest request){
		List<Yqlink> yqlinklist = yqlinkDAO.selectAll();
		List<Tzinfo> nowtzinfolist = tzinfoDAO.selectNowtzinfo(Info.getDateStr().substring(0,10));
		List<News> newslist = newsDAO.selectAll();
		
		String index = request.getParameter("index");
	   	int pageindex = 1;
	   	if(index!=null){
	   		 pageindex = Integer.parseInt(index);
	   	}
   	    Page<Object> page = PageHelper.startPage(pageindex,6);
		List<Chat> list = chatDAO.selectAll();
		for(Chat chat:list){
			Member member = memberDAO.findById(chat.getMemberid());
			chat.setMember(member);
		}
		request.setAttribute("yqlinklist", yqlinklist);
		request.setAttribute("nowtzinfolist", nowtzinfolist);
		request.setAttribute("newslist", newslist);
		request.setAttribute("list", list);
		request.setAttribute("index", page.getPageNum());
		request.setAttribute("pages", page.getPages());
		request.setAttribute("total", page.getTotal());
		return "yjjy";
	}
	
	//后台意见和建议
	@RequestMapping("admin/msgList")
	public String msgList(HttpServletRequest request){
		String index = request.getParameter("index");
	   	int pageindex = 1;
	   	if(index!=null){
	   		 pageindex = Integer.parseInt(index);
	   	}
   	    Page<Object> page = PageHelper.startPage(pageindex,6);
		List<Chat> list = chatDAO.selectAll();
		for(Chat chat:list){
			Member member = memberDAO.findById(chat.getMemberid());
			chat.setMember(member);
		}
		request.setAttribute("list", list);
		request.setAttribute("index", page.getPageNum());
		request.setAttribute("pages", page.getPages());
		request.setAttribute("total", page.getTotal());
		return "admin/msglist";
	}
	
	//后台意见和建议
	@RequestMapping("admin/searchMsg")
	public String searchMsg(HttpServletRequest request){
		String index = request.getParameter("index");
		String key = request.getParameter("key");
	   	int pageindex = 1;
	   	if(index!=null){
	   		 pageindex = Integer.parseInt(index);
	   	}
   	    Page<Object> page = PageHelper.startPage(pageindex,6);
		List<Chat> list = chatDAO.searchKey(key);
		for(Chat chat:list){
			Member member = memberDAO.findById(chat.getMemberid());
			chat.setMember(member);
		}
		request.setAttribute("list", list);
		request.setAttribute("key", key);
		request.setAttribute("index", page.getPageNum());
		request.setAttribute("pages", page.getPages());
		request.setAttribute("total", page.getTotal());
		return "admin/msgsearch";
	}
	
	//意见和建议添加
	@RequestMapping("chatAdd")
	public void chatAdd(Chat chat, HttpServletRequest request,HttpServletResponse response) {
		try {
			chat.setSavetime(Info.getDateStr());
			chat.setHfmsg("");
			chatDAO.add(chat);
			PrintWriter out = response.getWriter();
			out.print(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//管理员回复意见和建议
	@RequestMapping("admin/chatEdit")
	public String chatAdd(Chat chat, HttpServletRequest request) {
			chatDAO.update(chat);
		return "redirect:msgList.do";
		
	}
	
	
	//删除 
	@RequestMapping("admin/chatDel")
	public String chatDel(int id, HttpServletRequest request) {
			chatDAO.delete(id);
		return "redirect:msgList.do";
		
	}
	
	
	

}
