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
import com.bean.Member;
import com.bean.News;
import com.bean.Pbinfo;
import com.bean.Tzinfo;
import com.bean.User;
import com.bean.Yqlink;
import com.dao.BbstypeDAO;
import com.dao.FansDAO;
import com.dao.MemberDAO;
import com.dao.PbinfoDAO;
import com.dao.UserDAO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.util.Info;

import java.util.*;

@Controller
public class PbinfoController extends BaseController {
	@Resource
	PbinfoDAO pbinfoDAO;
	@Resource
	MemberDAO memberDAO;
	
	
	//屏蔽
	@RequestMapping("pbinfoAdd")
	public void pbinfoAdd(HttpServletRequest request,HttpServletResponse response){
		try {
			String memberid = request.getParameter("memberid");
			String pbmemberid = request.getParameter("pbmemberid");
			Pbinfo pbinfo = new Pbinfo();
			pbinfo.setMemberid(Integer.parseInt(memberid));
			pbinfo.setPbmemberid(Integer.parseInt(pbmemberid));
			pbinfoDAO.add(pbinfo);
			PrintWriter out = response.getWriter();
			out.print(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//取消屏蔽
	@RequestMapping("pbinfoDel")
	public void pbinfoDel(HttpServletRequest request,HttpServletResponse response){
		try {
			String memberid = request.getParameter("memberid");
			String pbmemberid = request.getParameter("pbmemberid");
			HashMap<String,String> map = new HashMap<String, String>();
			map.put("memberid", memberid);
			map.put("pbmemberid", pbmemberid);
			Pbinfo pbinfo = pbinfoDAO.selectOne(map).get(0);
			pbinfoDAO.delete(pbinfo);
			PrintWriter out = response.getWriter();
			out.print(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//我的屏蔽
	@RequestMapping("myPingbi")
	public String myPingbi(HttpServletRequest request) {
		Member member = (Member)request.getSession().getAttribute("member");
		String index = request.getParameter("index");
	   	int pageindex = 1;
	   	if(index!=null){
	   		 pageindex = Integer.parseInt(index);
	   	}
   	    Page<Object> page = PageHelper.startPage(pageindex,6);
		List<Pbinfo> list = pbinfoDAO.selectPbmember(member.getId());
		for(Pbinfo pbinfo:list){
			Member pbmember = memberDAO.findById(pbinfo.getPbmemberid());
			pbinfo.setPbmember(pbmember);
		}
		request.setAttribute("list", list);
		request.setAttribute("index", page.getPageNum());
		request.setAttribute("pages", page.getPages());
		request.setAttribute("total", page.getTotal());
		return "mypb";
	}
	
	//取消屏蔽
	@RequestMapping("pingbiDel")
	public void pingbiDel(int id, HttpServletRequest request,HttpServletResponse response){
		try {
			System.out.println("22222");
			pbinfoDAO.deletePingbi(id);
			PrintWriter out = response.getWriter();
			out.print(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
