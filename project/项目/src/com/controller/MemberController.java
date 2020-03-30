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
import com.bean.Fans;
import com.bean.Member;
import com.bean.News;
import com.bean.Pbinfo;
import com.bean.Signin;
import com.bean.Tzhtinfo;
import com.bean.Tzinfo;
import com.bean.User;
import com.dao.BbstypeDAO;
import com.dao.FansDAO;
import com.dao.MemberDAO;
import com.dao.PbinfoDAO;
import com.dao.SigninDAO;
import com.dao.TzhtinfoDAO;
import com.dao.TzinfoDAO;
import com.dao.UserDAO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.joyce.mail.test.MailTest;
import com.util.Info;

import java.util.*;

@Controller
public class MemberController extends BaseController {
	@Resource
	MemberDAO memberDAO;
	@Resource
	TzinfoDAO tzinfoDAO;
	@Resource
	BbstypeDAO bbstypeDAO;
	@Resource
	FansDAO fansDAO;
	@Resource
	PbinfoDAO pbinfoDAO;
	@Resource
	TzhtinfoDAO tzhtinfoDAO;
	@Resource
	SigninDAO signinDAO;
	
	
	
	
	//检查用户名的唯一性
	@RequestMapping("checkUname")
	public void checkUname(String uname, HttpServletRequest request, HttpServletResponse response){
		try {
			PrintWriter out = response.getWriter();
			List<Member> list = memberDAO.checkUname(uname);
			if(list.size()==0){
				out.print(0);
			}else{
				out.print(1);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//注册
	@RequestMapping("memberReg")
	public String memberReg(Member member, HttpServletRequest request){
		member.setDelstatus("0");
		member.setIsjy("no");
		member.setShstatus("待审核");
		member.setSavetime(Info.getDateStr());
		member.setIsfh("no");
		memberDAO.add(member);
		return "login";
	}
	//登录
	@RequestMapping("memberLogin")
	public void memberLogin(Member member ,HttpServletRequest request, HttpServletResponse response) {
		
		try {
			PrintWriter out = response.getWriter();
			List<Member> list = memberDAO.selectOne(member);
		    if(list.size()==0){
		    	out.print(0);
		    }else if(list.size()==1){
		    	Member mmm = list.get(0);
		    	if(mmm.getIsfh().equals("no")){
		    	request.getSession().setAttribute("member", list.get(0));
		    	out.print(1);
		    	}else{
		    		out.print(2);
		    	}
		    }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	//退出
	@RequestMapping("memberExit")
	public void memberExit(HttpServletRequest request, HttpServletResponse response){
		try {
			PrintWriter out = response.getWriter();
			request.getSession().removeAttribute("member");
		    	out.print(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//个人信息设置
	@RequestMapping("memberSet")
	public String memberSet(HttpServletRequest request){
		Member m = (Member)request.getSession().getAttribute("member");
		Member member = memberDAO.findById(m.getId());
		request.setAttribute("member", member);
		return "memberset";
	}
	
	//修改个人信息
	@RequestMapping("memberEdit")
	public String memberEdit(Member member,HttpServletRequest request){
		String newupass = request.getParameter("upass1");
		Member m = (Member)memberDAO.findById(member.getId());
		if(m.getUpass().equals(member.getUpass())){
			member.setUpass(newupass);
			memberDAO.memberEdit(member);
			request.setAttribute("msg", "编辑成功");
		}else{
			request.setAttribute("msg", "原密码错误");
		}
		return "memberset";
	}
	
	//我的主页
	@RequestMapping("Home")
	public String Home(int memberid,HttpServletRequest request){
		Member mmm = (Member)request.getSession().getAttribute("member");
		Member member = memberDAO.findById(memberid);
		//查看关注人数
		int gzmemberid = memberid;
		List<Fans> fanslist = fansDAO.selectFans(gzmemberid);
		//查看是否关注
		String isgz = "";
		if(mmm!=null){
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("gzmemberid",String.valueOf(gzmemberid));
			map.put("memberid",String.valueOf(mmm.getId()));
			List<Fans> isgzlist = fansDAO.selectOne(map);
			if(isgzlist.size()>0){
				isgz="1";
			}else{
				isgz="0";
			}
		}
		
		//是否签到
			HashMap sgmap = new HashMap();
			sgmap.put("memberid", memberid);
			sgmap.put("savetime", Info.getDateStr().substring(0,10));
			List<Signin> sglist = signinDAO.isSignin(sgmap);
		
		//是否屏蔽
		String ispb = "";
		int pbmemberid = memberid;
		if(mmm!=null){
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("pbmemberid",String.valueOf(pbmemberid));
			map.put("memberid",String.valueOf(mmm.getId()));
			List<Pbinfo> ispblist = pbinfoDAO.selectOne(map);
			if(ispblist.size()>0){
				ispb="1";
			}else{
				ispb="0";
			}
		}
		
		
		String index = request.getParameter("index");
	   	int pageindex = 1;
	   	if(index!=null){
	   		 pageindex = Integer.parseInt(index);
	   	}
   	    Page<Object> page = PageHelper.startPage(pageindex,6);
		//会员发的帖子
		List<Tzinfo> list = tzinfoDAO.myTzinfo(member.getId());
		for(Tzinfo tzinfo:list){
			List<Tzhtinfo> htlist = tzhtinfoDAO.selectHtinfo(tzinfo.getId());
			tzinfo.setAllhtlist(htlist);
		}
		request.setAttribute("sglist", sglist);
		request.setAttribute("ispb", ispb);
		request.setAttribute("isgz", isgz);
		request.setAttribute("fanslist", fanslist);
		request.setAttribute("list", list);
		request.setAttribute("member", member);
		request.setAttribute("index", page.getPageNum());
		request.setAttribute("pages", page.getPages());
		request.setAttribute("total", page.getTotal());
		return "home";
	}
	//用户中心
	@RequestMapping("memberCenter")
	public String memberCenter(HttpServletRequest request){
		Member m = (Member)request.getSession().getAttribute("member");
		if(m!=null){
		List<Tzhtinfo> myhflist = tzhtinfoDAO.selectNorepetition(m.getId());
		String index = request.getParameter("index");
	   	int pageindex = 1;
	   	if(index!=null){
	   		 pageindex = Integer.parseInt(index);
	   	}
   	    Page<Object> page = PageHelper.startPage(pageindex,6);
		List<Tzinfo> list = tzinfoDAO.myTzinfo(m.getId());
		for(Tzinfo tzinfo:list){
			Bbstype ftype  = bbstypeDAO.findById(tzinfo.getFid());
			Bbstype stype  = bbstypeDAO.findById(tzinfo.getSid());
			tzinfo.setFtype(ftype);
			tzinfo.setStype(stype);
			List<Tzhtinfo> htlist = tzhtinfoDAO.selectHtinfo(tzinfo.getId());
			tzinfo.setAllhtlist(htlist);
		}
		request.setAttribute("list", list);
		request.setAttribute("myhflist", myhflist);
		request.setAttribute("index", page.getPageNum());
		request.setAttribute("pages", page.getPages());
		request.setAttribute("total", page.getTotal());
			return "membercenter";
		}else{
			return "login";
		}
	}
	
	
	
	//后台查看会员列表
	@RequestMapping("admin/memberList")
	public String memberList(HttpServletRequest request){
		String index = request.getParameter("index");
	   	int pageindex = 1;
	   	if(index!=null){
	   	pageindex = Integer.parseInt(index);
	   	}
   	    Page<Object> page = PageHelper.startPage(pageindex,6);
		List<Member> list = memberDAO.selectAll();
		request.setAttribute("list", list);
		request.setAttribute("index", page.getPageNum());
		request.setAttribute("pages", page.getPages());
		request.setAttribute("total", page.getTotal());
		return "admin/memberlist";
	}
	
	//后台搜索会员列表
	@RequestMapping("admin/searchMember")
	public String searchMember(HttpServletRequest request){
		String index = request.getParameter("index");
		String key = request.getParameter("key");
	   	int pageindex = 1;
	   	if(index!=null){
	   	pageindex = Integer.parseInt(index);
	   	}
   	    Page<Object> page = PageHelper.startPage(pageindex,6);
		List<Member> list = memberDAO.searchKey(key);
		request.setAttribute("key", key);
		request.setAttribute("list", list);
		request.setAttribute("index", page.getPageNum());
		request.setAttribute("pages", page.getPages());
		request.setAttribute("total", page.getTotal());
		return "admin/membersearch";
	}
	
	//会员审核
	@RequestMapping("admin/shStatus")
	public String shStatus(HttpServletRequest request){
		String type = request.getParameter("type");
		String id = request.getParameter("id");
		if(type.equals("tg")){
			memberDAO.updateTgShstatus(Integer.parseInt(id));
		}else if(type.equals("jj")){
			memberDAO.updateJjShstatus(Integer.parseInt(id));
		}
		return "redirect:memberList.do";
	}
	
	//删除会员
	@RequestMapping("admin/memberDel")
	public String memberDel(int id,HttpServletRequest request){
		memberDAO.delete(id);
		return "redirect:memberList.do";
	}
	
	//会员禁言
	@RequestMapping("admin/jinYan")
	public void jinYan(int id, HttpServletRequest request,HttpServletResponse response) {
		try {
			memberDAO.updateIsjy(id);
			PrintWriter out = response.getWriter();
			out.print(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//禁言解除
	@RequestMapping("admin/jinyanJc")
	public void jinyanJc(int id, HttpServletRequest request,HttpServletResponse response) {
		try {
			memberDAO.updateNojy(id);
			PrintWriter out = response.getWriter();
			out.print(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//查找版块到升级版主页面
	@RequestMapping("admin/szbz")
	public String szbz(int id,HttpServletRequest request) {
		List<Bbstype> list = bbstypeDAO.selectAll();
		request.setAttribute("list", list);
		request.setAttribute("id", id);
		return "admin/szbz";
	}
	
	//封号
	@RequestMapping("admin/fengHao")
	public void fengHao(int id, HttpServletRequest request,HttpServletResponse response) {
		try {
			memberDAO.updateIsfh(id);
			PrintWriter out = response.getWriter();
			out.print(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//解除封号
	@RequestMapping("admin/jiechuFenhao")
	public void jiechuFenhao(int id, HttpServletRequest request,HttpServletResponse response) {
		try {
			memberDAO.updateNofh(id);
			PrintWriter out = response.getWriter();
			out.print(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//找回密码
	@RequestMapping("/findpwd")
	public void findpwd(String uname,String email, HttpServletRequest request, HttpServletResponse response){
		try {
			PrintWriter out = response.getWriter();
			List<Member> list = memberDAO.findpwd(uname, email);
			if(list.size()==0){
				out.print(0);
			}else{
				MailTest.sendmial("你的密码是:"+list.get(0).getUpass()+",请立即前往重设密码"+Info.getDateStr(),email);
				out.print(1);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
