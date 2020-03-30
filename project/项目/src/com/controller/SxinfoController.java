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
import com.bean.Sxinfo;
import com.bean.User;
import com.dao.BbstypeDAO;
import com.dao.MemberDAO;
import com.dao.SxinfoDAO;
import com.dao.UserDAO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.util.Info;

import java.util.*;

@Controller
public class SxinfoController extends BaseController {
	@Resource
	SxinfoDAO sxinfoDAO;
	@Resource
	MemberDAO memberDAO;
	
	
	//查找私信人到私信添加页面
	@RequestMapping("sxShow")
	public String sxShow(int memberid,int sxmemberid, HttpServletRequest request) {
		Member member = memberDAO.findById(sxmemberid);
		request.setAttribute("sxmember", member);
		request.setAttribute("memberid", memberid);
		return "sxadd";
	}
	
	//添加私信
	@RequestMapping("sxAdd")
	public void jinyanJc(Sxinfo sxinfo, HttpServletRequest request,HttpServletResponse response) {
		try {
			sxinfo.setHfnote("");
			sxinfo.setSavetime(Info.getDateStr());
			sxinfoDAO.add(sxinfo);
			PrintWriter out = response.getWriter();
			out.print(sxinfo.getSxmemberid());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//前台页面查询私信
	@RequestMapping("mysendSx")
	public String newsList(HttpServletRequest request) {
		Member member = (Member)request.getSession().getAttribute("member");
		String index = request.getParameter("index");
	   	int pageindex = 1;
	   	if(index!=null){
	   		 pageindex = Integer.parseInt(index);
	   	}
   	    Page<Object> page = PageHelper.startPage(pageindex,6);
		List<Sxinfo> list = sxinfoDAO.selectMysendsx(member.getId());
		for(Sxinfo sxinfo:list){
			Member sxmember = memberDAO.findById(sxinfo.getSxmemberid());
			sxinfo.setSxmember(sxmember);
		}
		request.setAttribute("list", list);
		request.setAttribute("index", page.getPageNum());
		request.setAttribute("pages", page.getPages());
		request.setAttribute("total", page.getTotal());
		return "mysendsx";
	}
	
	//我接收到的私信
	@RequestMapping("myrecSx")
	public String myrecSx(HttpServletRequest request) {
		Member mmm = (Member)request.getSession().getAttribute("member");
		String index = request.getParameter("index");
	   	int pageindex = 1;
	   	if(index!=null){
	   		 pageindex = Integer.parseInt(index);
	   	}
   	    Page<Object> page = PageHelper.startPage(pageindex,6);
		List<Sxinfo> list = sxinfoDAO.selectMyrecsx(mmm.getId());
		for(Sxinfo sxinfo:list){
			Member member = memberDAO.findById(sxinfo.getMemberid());
			Member sxmember = memberDAO.findById(sxinfo.getSxmemberid());
			sxinfo.setMember(member);
			sxinfo.setSxmember(sxmember);
		}
		request.setAttribute("list", list);
		request.setAttribute("index", page.getPageNum());
		request.setAttribute("pages", page.getPages());
		request.setAttribute("total", page.getTotal());
		return "myrecsx";
	}
	
	//回复私信
	@RequestMapping("sxHf")
	public void sxHf(HttpServletRequest request,HttpServletResponse response) {
		try {
			String id = request.getParameter("id");
			String hfnote = request.getParameter("hfnote");
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("id", id);
			map.put("hfnote", hfnote);
			sxinfoDAO.updateSxHf(map);
			PrintWriter out = response.getWriter();
			out.print(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//删除私信
	@RequestMapping("sxDel")
	public String sxDel(int id, HttpServletRequest request) {
		String type = request.getParameter("type");
		if(type.equals("send")){
		sxinfoDAO.delete(id);
		return "redirect:mysendSx.do";
		}else{
			sxinfoDAO.delete(id);
			return "redirect:myrecSx.do";
		}
	}
	

}
