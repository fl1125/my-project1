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
import com.bean.Infospj;
import com.bean.Member;
import com.bean.Mgword;
import com.bean.News;
import com.bean.Tzhtinfo;
import com.bean.Tzinfo;
import com.bean.User;
import com.bean.Yqlink;
import com.dao.BbstypeDAO;
import com.dao.InfospjDAO;
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
public class InfospjController extends BaseController {
	@Resource
	InfospjDAO infospjDAO;
	
	
	
	
	//前台评论信息
	@RequestMapping("infospjAdd")
	public String infospjAdd(Infospj infospj, HttpServletRequest request) {
		Member member = (Member)request.getSession().getAttribute("member");
		if(member!=null){
			infospj.setMid(member.getId());
			infospj.setSavetime(Info.getDateStr());
			infospjDAO.add(infospj);
			return "redirect:infosShowQT.do?id="+infospj.getInfoid();
		}else{
			return "login";
		}
	}
	
	
	

}
