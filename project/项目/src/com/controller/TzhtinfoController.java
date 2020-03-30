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
import com.bean.Tzhtinfo;
import com.bean.Tzinfo;
import com.bean.User;
import com.bean.Yqlink;
import com.dao.BbstypeDAO;
import com.dao.MemberDAO;
import com.dao.MgwordDAO;
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
public class TzhtinfoController extends BaseController {
	@Resource
	TzhtinfoDAO tzhtinfoDAO;
	@Resource
	TzinfoDAO tzinfoDAO;
	@Resource
	BbstypeDAO bbstypeDAO;
	@Resource
	MgwordDAO mgwordDAO;
	
	
	
	//前台帖子回贴信息
	@RequestMapping("tzhtinfoAdd")
	public String tzhtinfoAdd(Tzhtinfo tzhtinfo, HttpServletRequest request) {
		Member member = (Member)request.getSession().getAttribute("member");
		List<Mgword> mgwordlist = mgwordDAO.selectAll();
		if(member!=null){
			for(Mgword mgword:mgwordlist){
				int strsize = mgword.getWordnote().length();
				String xinstr = "";
				for(int i=0;i<strsize;i++){
					xinstr+="*";
				}
				//tzhtinfo.getNote().indexOf(mgword.getWordnote())
				if(tzhtinfo.getNote().indexOf(mgword.getWordnote())!=-1){
					String c =  tzhtinfo.getNote().replace(mgword.getWordnote(), xinstr);
					tzhtinfo.setNote(c);
				}
			}
			tzhtinfo.setAuthor(member.getId());
			tzhtinfo.setSavetime(Info.getDateStr());
			tzhtinfo.setDznum(0);
			tzhtinfo.setCanht("");
			tzhtinfoDAO.add(tzhtinfo);
		return "redirect:tzDetail.do?id="+tzhtinfo.getTzid();
		}else{
		return "login";
		}
	}
	
	//回贴点赞
	@RequestMapping("tzhtDz")
	public void tzhtDz(int id, HttpServletRequest request,HttpServletResponse response) {
		
		try {
			Tzhtinfo tzhtinfo = tzhtinfoDAO.findById(id);
			int cz = tzhtinfo.getDznum()+1;
			tzhtinfo.setDznum(cz);
			tzhtinfo.setId(id);
			tzhtinfoDAO.updateDz(tzhtinfo);
			PrintWriter out = response.getWriter();
			out.print(cz);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//删除回贴
	@RequestMapping("delHt")
	public void delHt(int id, HttpServletRequest request,HttpServletResponse response) {
		
		try {
			tzhtinfoDAO.delHt(id);
			PrintWriter out = response.getWriter();
			out.print(id);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//我回复的贴子
	@RequestMapping("myHf")
	public String myHf(HttpServletRequest request){
		Member m = (Member)request.getSession().getAttribute("member");
		if(m!=null){
		List<Tzinfo> publishlist = tzinfoDAO.myTzinfo(m.getId());
		String index = request.getParameter("index");
	   	int pageindex = 1;
	   	if(index!=null){
	   		 pageindex = Integer.parseInt(index);
	   	}
   	    Page<Object> page = PageHelper.startPage(pageindex,6);
		List<Tzhtinfo> list = tzhtinfoDAO.selectNorepetition(m.getId());
		for(Tzhtinfo tzhtinfo:list){
			Tzinfo tzinfo = tzinfoDAO.findById(tzhtinfo.getTzid());
			List<Tzhtinfo> htlist = tzhtinfoDAO.selectHtinfo(tzinfo.getId());
			tzinfo.setAllhtlist(htlist);
			Bbstype ftype = bbstypeDAO.findById(tzinfo.getFid());
			tzhtinfo.setFtype(ftype);
			tzhtinfo.setTzinfo(tzinfo);
		}
		request.setAttribute("list", list);
		request.setAttribute("publishlist", publishlist);
		request.setAttribute("index", page.getPageNum());
		request.setAttribute("pages", page.getPages());
		request.setAttribute("total", page.getTotal());
			return "myhf";
		}else{
			return "login";
		}
	}
	

}
