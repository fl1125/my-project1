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
import com.bean.Pbinfo;
import com.bean.Tzhtinfo;
import com.bean.Tzinfo;
import com.bean.User;
import com.bean.Yqlink;
import com.dao.BanzhuDAO;
import com.dao.BbstypeDAO;
import com.dao.MemberDAO;
import com.dao.NewsDAO;
import com.dao.PbinfoDAO;
import com.dao.TzhtinfoDAO;
import com.dao.TzinfoDAO;
import com.dao.UserDAO;
import com.dao.YqlinkDAO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.util.Info;

import java.util.*;

@Controller
public class TzinfoController extends BaseController {
	@Resource
	TzinfoDAO tzinfoDAO;
	@Resource
	BbstypeDAO bbstypeDAO;
	@Resource
	MemberDAO memberDAO;
	@Resource
	NewsDAO newsDAO;
	@Resource
	YqlinkDAO yqlinkDAO;
	@Resource
	PbinfoDAO pbinfoDAO;
	@Resource
	TzhtinfoDAO tzhtinfoDAO;
	@Resource
	BanzhuDAO banzhuDAO;
	
	
	
	
	
	//后台版块列表
	@RequestMapping("admin/tzinfoList")
	public String tzinfoList(HttpServletRequest request) {
		//将要返回大类子类到后台搜索
		List<Bbstype> flist = bbstypeDAO.selectFathertype();
		for(Bbstype bbstype:flist){
			List<Bbstype> childlist = bbstypeDAO.selectChildtype(Integer.parseInt(bbstype.getFatherid()));
			bbstype.setChildlist(childlist);
		}
		String index = request.getParameter("index");
	   	int pageindex = 1;
	   	if(index!=null){
	   		 pageindex = Integer.parseInt(index);
	   	}
   	    Page<Object> page = PageHelper.startPage(pageindex,6);
		List<Tzinfo> list = tzinfoDAO.selectAll();
		for(int i=0;i<list.size();i++){
			Bbstype ftype = bbstypeDAO.findById(list.get(i).getFid());
			Bbstype stype = bbstypeDAO.findById(list.get(i).getSid());
			Member mb = memberDAO.findById(list.get(i).getAuthor());
			list.get(i).setStype(stype);
			list.get(i).setFtype(ftype);
			list.get(i).setMember(mb);
			//查看回贴数
			List<Tzhtinfo> allhtlist = tzhtinfoDAO.selectHtinfo(list.get(i).getId());
			list.get(i).setAllhtlist(allhtlist);
		}
		
		request.setAttribute("list", list);
		request.setAttribute("flist", flist);
		request.setAttribute("index", page.getPageNum());
		request.setAttribute("pages", page.getPages());
		request.setAttribute("total", page.getTotal());
		return "admin/tzlist";
	}
	
	
	//后台搜索帖子
	@RequestMapping("admin/searchTzinfo")
	public String searchTzinfo(HttpServletRequest request) {
		String key = request.getParameter("key");
		String key1 = request.getParameter("key1");
		String key2 = request.getParameter("key2");
		//将要返回大类子类到后台搜索
		List<Bbstype> flist = bbstypeDAO.selectFathertype();
		for(Bbstype bbstype:flist){
			List<Bbstype> childlist = bbstypeDAO.selectChildtype(Integer.parseInt(bbstype.getFatherid()));
			bbstype.setChildlist(childlist);
		}
		String index = request.getParameter("index");
	   	int pageindex = 1;
	   	if(index!=null){
	   		 pageindex = Integer.parseInt(index);
	   	}
   	    Page<Object> page = PageHelper.startPage(pageindex,6);
   	    HashMap map = new HashMap();
   	    map.put("title", key);
   	    map.put("fid", key1);
   	    map.put("sid", key2);
		List<Tzinfo> list = tzinfoDAO.searchTzinfo(map);
		for(int i=0;i<list.size();i++){
			Bbstype ftype = bbstypeDAO.findById(list.get(i).getFid());
			Bbstype stype = bbstypeDAO.findById(list.get(i).getSid());
			Member mb = memberDAO.findById(list.get(i).getAuthor());
			list.get(i).setStype(stype);
			list.get(i).setFtype(ftype);
			list.get(i).setMember(mb);
			//查看回贴数
			List<Tzhtinfo> allhtlist = tzhtinfoDAO.selectHtinfo(list.get(i).getId());
			list.get(i).setAllhtlist(allhtlist);
		}
		request.setAttribute("key", key);
		request.setAttribute("key1", key1);
		request.setAttribute("key2", key2);
		request.setAttribute("list", list);
		request.setAttribute("flist", flist);
		request.setAttribute("index", page.getPageNum());
		request.setAttribute("pages", page.getPages());
		request.setAttribute("total", page.getTotal());
		return "admin/tzsearch";
	}
	
	//查找发贴人到贴子添加页面
	@RequestMapping("tzShowmember")
	public String tzShowmember(HttpServletRequest request) {
		Member m = (Member)request.getSession().getAttribute("member");
		if(m!=null){
		Member member = memberDAO.findById(m.getId());
		List<Bbstype> bbstypelist = bbstypeDAO.selectAll();
		for(Bbstype bbstype:bbstypelist){
			List<Bbstype> childlist = bbstypeDAO.selectChildtype(bbstype.getId());
			bbstype.setChildlist(childlist);
		}
		request.setAttribute("bbstypelist", bbstypelist);
		request.setAttribute("member", member);
		return "tzadd";
		}else{
		return "login";
		}
	}
	
	//发贴
	@RequestMapping("tzinfoAdd")
	public String tzinfoAdd(Tzinfo tzinfo,HttpServletRequest request) {
		Bbstype bbstype = bbstypeDAO.findById(tzinfo.getSid());
		tzinfo.setSavetime(Info.getDateStr());
		tzinfo.setLooknum(0);
		tzinfo.setDznum(0);
		tzinfo.setFid(Integer.parseInt(bbstype.getFatherid()));
		tzinfo.setUpdatetime("");
		tzinfo.setIstop("no");
		tzinfo.setIsjh("no");
		tzinfoDAO.add(tzinfo);
		return "redirect:memberCenter.do";
	}
	
	
	//贴子详情
	@RequestMapping("tzDetail")
	public String tzDetail(int id, HttpServletRequest request) {
		Member mmm = (Member)request.getSession().getAttribute("member");
		tzinfoDAO.updateLooknum(id);
		Tzinfo tzinfo = tzinfoDAO.findById(id);
		Member member = memberDAO.findById(tzinfo.getAuthor());
		tzinfo.setMember(member);
		request.setAttribute("tzinfo", tzinfo);
		
		List<Yqlink> yqlinklist = yqlinkDAO.selectAll();
		//今日话题
		List<Tzinfo> nowtzinfolist = tzinfoDAO.selectNowtzinfo(Info.getDateStr().substring(0,10));
		List<News> newslist = newsDAO.selectAll();
		
		List<Bbstype> typelist = bbstypeDAO.selectAll();
		for(Bbstype bbstype:typelist){
			//子类
			List<Bbstype> childlist = bbstypeDAO.selectChildtype(bbstype.getId());
			bbstype.setChildlist(childlist);
			//分类贴子
			List<Tzinfo> ftypetzinfolist = tzinfoDAO.selectTypetzinfo(bbstype.getId());
			bbstype.setFtypetzinfolist(ftypetzinfolist);
			
		}
		//回贴信息
		int tzid = id;
		List<Tzhtinfo> tzhtlist = tzhtinfoDAO.selectHtinfo(tzid);
		String index = request.getParameter("index");
	   	int pageindex = 1;
	   	if(index!=null){
	   		 pageindex = Integer.parseInt(index);
	   	}
   	    Page<Object> page = PageHelper.startPage(pageindex,10);
		for(Tzhtinfo tzhtinfo:tzhtlist){
			Member htmember = memberDAO.findById(tzhtinfo.getAuthor());
			tzhtinfo.setHtmember(htmember);
		}
		
		
		
		//查看是否是版主
		String isbz = "";
		if(mmm!=null){
		Banzhu banzhu = new Banzhu();
		banzhu.setMemberid(mmm.getId());
		banzhu.setFid(tzinfo.getFid());
		List<Banzhu> banzhulist = banzhuDAO.selectIsbz(banzhu);
		if(banzhulist.size()!=0){
			isbz="isbz";
		}
		}
		
		request.setAttribute("isbz", isbz);
		request.setAttribute("tzhtlist", tzhtlist);
		request.setAttribute("nowtzinfolist", nowtzinfolist);
		request.setAttribute("yqlinklist", yqlinklist);
		request.setAttribute("typelist", typelist);
		request.setAttribute("newslist", newslist);
		request.setAttribute("id", id);
		
		request.setAttribute("index", page.getPageNum());
		request.setAttribute("pages", page.getPages());
		request.setAttribute("total", page.getTotal());
		
		return "tzdetail";
	}
	
	//前台贴子列表页
	@RequestMapping("tzList")
	public String tzList(String fid,String sid,HttpServletRequest request) {
		Member mmm = (Member)request.getSession().getAttribute("member");
		//友情链接
		List<Yqlink> yqlinklist = yqlinkDAO.selectAll();
		//今日话题
		List<Tzinfo> nowtzinfolist = tzinfoDAO.selectNowtzinfo(Info.getDateStr().substring(0,10));
		//新闻
		List<News> newslist = newsDAO.selectAll();
		//子类
		List<Bbstype> childlist = bbstypeDAO.selectChildtype(Integer.parseInt(fid));
		//精华或置顶
		List<Tzinfo> toplist = tzinfoDAO.selectTopzinfo(Integer.parseInt(fid));
		for(Tzinfo toptzinfo:toplist){
			Bbstype stype = bbstypeDAO.findById(toptzinfo.getSid());
			Member member = memberDAO.findById(toptzinfo.getAuthor());
			toptzinfo.setStype(stype);
			toptzinfo.setMember(member);
			//置顶回贴list
			List<Tzhtinfo> tophtlist = tzhtinfoDAO.selectHtinfo(toptzinfo.getId());
			toptzinfo.setTophtlist(tophtlist);
		}
		
		
		String index = request.getParameter("index");
	   	int pageindex = 1;
	   	if(index!=null){
	   		 pageindex = Integer.parseInt(index);
	   	}
   	    Page<Object> page = PageHelper.startPage(pageindex,10);
   	    //普通贴子
   	    HashMap map = new HashMap();
   	    map.put("fid", fid);
   	    map.put("sid", sid);
   	    //过滤屏蔽的帖子
   	    List parlist = new ArrayList<String>();
   	    if(mmm!=null){
   	    List<Pbinfo> pbinfolist = pbinfoDAO.selectPbmember(mmm.getId());
   	    for(Pbinfo pbinfo:pbinfolist){
   	    	parlist.add(pbinfo.getPbmemberid()+",");
   	    }
   	    }
   	    map.put("parlist", parlist);
		List<Tzinfo> ptlist = tzinfoDAO.selectPtzinfo(map);
		for(Tzinfo tzinfo:ptlist){
			Bbstype stype = bbstypeDAO.findById(tzinfo.getSid());
			Member member = memberDAO.findById(tzinfo.getAuthor());
			tzinfo.setStype(stype);
			tzinfo.setMember(member);
			//普通回帖
			List<Tzhtinfo> pthtlist = tzhtinfoDAO.selectHtinfo(tzinfo.getId());
			tzinfo.setPthtlist(pthtlist);
		}
		request.setAttribute("childlist", childlist);
		request.setAttribute("fid", fid);
		request.setAttribute("nowtzinfolist", nowtzinfolist);
		request.setAttribute("yqlinklist", yqlinklist);
		request.setAttribute("ptlist", ptlist);
		request.setAttribute("toplist", toplist);
		request.setAttribute("newslist", newslist);
		
		
		request.setAttribute("index", page.getPageNum());
		request.setAttribute("pages", page.getPages());
		request.setAttribute("total", page.getTotal());
		return "tzlist";
	}
	
	//帖子置顶
	@RequestMapping("tzzd")
	public void tzzd(int id, HttpServletRequest request,HttpServletResponse response) {
		
		try {
			tzinfoDAO.updateIstop(id);
			PrintWriter out = response.getWriter();
			out.print(id);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//后台帖子置顶
	@RequestMapping("admin/topTzinfo")
	public void topTzinfo(int id, HttpServletRequest request,HttpServletResponse response) {
		
		try {
			tzinfoDAO.updateIstop(id);
			PrintWriter out = response.getWriter();
			out.print(id);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//帖子取消置顶
	@RequestMapping("tzzdqx")
	public void tzzdqx(int id, HttpServletRequest request,HttpServletResponse response) {
		try {
			tzinfoDAO.updateNotop(id);
			PrintWriter out = response.getWriter();
			out.print(id);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//后台帖子取消置顶
	@RequestMapping("admin/notopTzinfo")
	public void notopTzinfo(int id, HttpServletRequest request,HttpServletResponse response) {
		try {
			tzinfoDAO.updateNotop(id);
			PrintWriter out = response.getWriter();
			out.print(id);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//帖子加精
	@RequestMapping("tzjjqx")
	public void tzjjqx(int id, HttpServletRequest request,HttpServletResponse response) {
		try {
			tzinfoDAO.updateNojh(id);
			PrintWriter out = response.getWriter();
			out.print(id);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//后台帖子加精
	@RequestMapping("admin/noJinghua")
	public void noJinghua(int id, HttpServletRequest request,HttpServletResponse response) {
		try {
			tzinfoDAO.updateNojh(id);
			PrintWriter out = response.getWriter();
			out.print(id);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//前台取消精华
	@RequestMapping("tzjj")
	public void tzjj(int id, HttpServletRequest request,HttpServletResponse response) {
		try {
			tzinfoDAO.updateIsjh(id);
			PrintWriter out = response.getWriter();
			out.print(id);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//后台取消精华
	@RequestMapping("admin/jingHua")
	public void jingHua(int id, HttpServletRequest request,HttpServletResponse response) {
		try {
			tzinfoDAO.updateIsjh(id);
			PrintWriter out = response.getWriter();
			out.print(id);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//查找帖子到编辑帖子页面
	@RequestMapping("tzinfoShow")
	public String tzinfoShow(int id,HttpServletRequest request) {
		Member m = (Member)request.getSession().getAttribute("member");
		Member member = memberDAO.findById(m.getId());
		Tzinfo tzinfo = tzinfoDAO.findById(id);
		
		List<Bbstype> typelist = bbstypeDAO.selectAll();
		for(Bbstype bbstype:typelist){
			//子类
			List<Bbstype> childlist = bbstypeDAO.selectChildtype(bbstype.getId());
			bbstype.setChildlist(childlist);
			//分类贴子
			List<Tzinfo> ftypetzinfolist = tzinfoDAO.selectTypetzinfo(bbstype.getId());
			bbstype.setFtypetzinfolist(ftypetzinfolist);
			
		}
		request.setAttribute("member", member);
		request.setAttribute("typelist", typelist);
		request.setAttribute("tzinfo", tzinfo);
		return "tzedit";
	}
	
	//编辑帖子
	@RequestMapping("tzinfoEdit")
	public String tzinfoEdit(Tzinfo tzinfo,HttpServletRequest request) {
		tzinfoDAO.updata(tzinfo);
		return "redirect:memberCenter.do";
	}
	
	
	//删除贴子和回贴
	@RequestMapping("admin/tzinfoDel")
	public void tzinfoDel(int id,HttpServletRequest request,HttpServletResponse response) {
		try {
			PrintWriter out = response.getWriter();
			List<Tzhtinfo> htlist = tzhtinfoDAO.selectHtinfo(id);
			for(Tzhtinfo tzhtinfo:htlist){
				tzhtinfoDAO.delHt(tzhtinfo.getId());
			}
			tzinfoDAO.delete(id);
			out.print(id);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//迁移贴子
	@RequestMapping("admin/findBbstype")
	public String findBbstype(HttpServletRequest request) {
		//将要返回大类子类到后台搜索
		List<Bbstype> flist = bbstypeDAO.selectFathertype();
		for(Bbstype bbstype:flist){
			List<Bbstype> childlist = bbstypeDAO.selectChildtype(bbstype.getId());
//			for(int i=0;i<childlist.size();i++){
//				System.out.println("childlist===="+childlist.get(i).toString());
//			}
			bbstype.setChildlist(childlist);
		}
		String id = request.getParameter("id");
		request.setAttribute("flist", flist);
		request.setAttribute("id", id);
		return "admin/tzmove";
	}
	
	//迁移贴子
	@RequestMapping("admin/tzMove")
	public String tzMove(HttpServletRequest request) {
		String id = request.getParameter("id");
		String sid = request.getParameter("sid");
		Bbstype bbstype = bbstypeDAO.findById(Integer.parseInt(sid));
		Tzinfo tzinfo = new Tzinfo();
		tzinfo.setId(Integer.parseInt(id));
		tzinfo.setSid(Integer.parseInt(sid));
		tzinfo.setFid(Integer.parseInt(bbstype.getFatherid()));
		tzinfoDAO.updateType(tzinfo);
		return "redirect:tzinfoList.do";
	}
	
	//设置帖子能否回贴
	@RequestMapping("admin/isCanht")
	public String isCanht(HttpServletRequest request) {
		String id = request.getParameter("id");
		Tzinfo tzinfo  = (Tzinfo)tzinfoDAO.findById(Integer.parseInt(id));
		if(tzinfo.getCanht().equals("yes")){
			tzinfo.setCanht("no");
		}else{
			tzinfo.setCanht("yes");
		}
		tzinfo.setId(Integer.parseInt(id));
		tzinfoDAO.updateCanht(tzinfo);
		return "redirect:tzinfoList.do";
	}
	
	

}
