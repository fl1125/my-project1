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
import com.bean.Bzapplyrecord;
import com.bean.Member;
import com.bean.News;
import com.bean.User;
import com.dao.BanzhuDAO;
import com.dao.BbstypeDAO;
import com.dao.BzapplyrecordDAO;
import com.dao.MemberDAO;
import com.dao.UserDAO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.util.Info;

import java.util.*;

@Controller
public class BzapplyrecordController extends BaseController {
	@Resource
	BzapplyrecordDAO bzapplyrecordDAO;
	@Resource
	BbstypeDAO bbstypeDAO;
	@Resource
	MemberDAO memberDAO;
	@Resource
	BanzhuDAO banzhuDAO;
	
	//前台申请版主列表
	@RequestMapping("bzApply")
	public String bzApply(HttpServletRequest request) {
		Member member = (Member)request.getSession().getAttribute("member");
		String index = request.getParameter("index");
	   	int pageindex = 1;
	   	if(index!=null){
	   		 pageindex = Integer.parseInt(index);
	   	}
   	    Page<Object> page = PageHelper.startPage(pageindex,6);
		List<Bzapplyrecord> list = bzapplyrecordDAO.selectMybzapply(member.getId());
		for(Bzapplyrecord bzapplyrecord:list){
			Bbstype bbstype = bbstypeDAO.findById(bzapplyrecord.getFid());
			bzapplyrecord.setFtype(bbstype);
		}
		request.setAttribute("list", list);
		request.setAttribute("index", page.getPageNum());
		request.setAttribute("pages", page.getPages());
		request.setAttribute("total", page.getTotal());
		return "bzapply";
	}
	
	//得到版块类别到申请版主添加页面
	@RequestMapping("bzapplyShowbbstype")
	public String bzapplyShowbbstype(HttpServletRequest request) {
		Member member = (Member)request.getSession().getAttribute("member");
		if(member!=null){
			List<Bbstype> list = bbstypeDAO.selectAll();
			request.setAttribute("list", list);
			request.setAttribute("member", member);
			return "bzapplyadd";
		}else{
			return "login";
		}
	}
	
	//添加版主申请
	@RequestMapping("applyAdd")
	public void applyAdd(HttpServletRequest request,HttpServletResponse response) {
		try {
			String msgstr = "";
			String fid = request.getParameter("fid");
			String memberid = request.getParameter("memberid");
			String note = request.getParameter("note");
			Bzapplyrecord bzapplyrecord = new Bzapplyrecord();
			bzapplyrecord.setFid(Integer.parseInt(fid));
			bzapplyrecord.setMemberid(Integer.parseInt(memberid));
			bzapplyrecord.setNote(note);
			List<Bzapplyrecord> issqlist = bzapplyrecordDAO.selectIssqlist(bzapplyrecord);
			List<Bzapplyrecord> isbzlist = bzapplyrecordDAO.selectIsbzlist(bzapplyrecord);
			if(issqlist.size()==1){
				msgstr = "1";//有一条申请记录
			}
			if(isbzlist.size()==1){
				msgstr = "2";//已是版主
			}
			if(issqlist.size()==0&&isbzlist.size()==0){
				bzapplyrecord.setSavetime(Info.getDateStr());
				bzapplyrecord.setShstatus("待审核");
				bzapplyrecordDAO.add(bzapplyrecord);
			}
			PrintWriter out = response.getWriter();
			out.print(msgstr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//后台版主申请列表
	@RequestMapping("admin/barList")
	public String barList(HttpServletRequest request) {
		String msg = request.getParameter("msg");
		List<Bbstype>  typelist = bbstypeDAO.selectAll();
		String index = request.getParameter("index");
	   	int pageindex = 1;
	   	if(index!=null){
	   		 pageindex = Integer.parseInt(index);
	   	}
   	    Page<Object> page = PageHelper.startPage(pageindex,6);
   	 List<Bzapplyrecord> list = bzapplyrecordDAO.selectAll();
		for(Bzapplyrecord bzapplyrecord:list){
			Bbstype bbstype = bbstypeDAO.findById(bzapplyrecord.getFid());
			Member member = memberDAO.findById(bzapplyrecord.getMemberid());
			bzapplyrecord.setFtype(bbstype);
			bzapplyrecord.setMember(member);
		}
		if(msg!=null&&!msg.equals("")){
			request.setAttribute("msg", "操作失败，该版块已存在版主");
		}
		request.setAttribute("list", list);
		request.setAttribute("typelist", typelist);
		request.setAttribute("index", page.getPageNum());
		request.setAttribute("pages", page.getPages());
		request.setAttribute("total", page.getTotal());
		return "admin/barlist";
	}
	
	//申请版主审核
	@RequestMapping("admin/barShstatus")
	public String barShstatus(HttpServletRequest request) {
		String id = request.getParameter("id");
		String type = request.getParameter("type");
		String fid = request.getParameter("fid");
		String msg ="";
		List<Banzhu> banzhulist = banzhuDAO.selectOne(Integer.parseInt(fid));
		if(type.equals("tg")&&banzhulist.size()==0){
			Member member = (Member)request.getSession().getAttribute("member");
			Banzhu banzhu = new Banzhu();
			banzhu.setFid(Integer.parseInt(fid));
			banzhu.setMemberid(member.getId());
			banzhuDAO.add(banzhu);
			bzapplyrecordDAO.updateTgshstatus(Integer.parseInt(id));
		}else if(type.equals("tg")&&banzhulist.size()!=0){
			msg="msg";
		}else{
			bzapplyrecordDAO.updateJjshstatus(Integer.parseInt(id));
		}
		return "redirect:barList.do?msg="+msg;
	}
	
	//删除记录
	@RequestMapping("admin/bzapplyrecordDel")
	public String bzapplyrecordDel(int id, HttpServletRequest request) {
		bzapplyrecordDAO.delete(id);
		return "redirect:barList.do";
	}
	
	//前台删除记录
	@RequestMapping("bzapplyDel")
	public String bzapplyDel(int id, HttpServletRequest request) {
		bzapplyrecordDAO.delete(id);
		return "redirect:bzApply.do";
	}
	

}
