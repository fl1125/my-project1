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
import com.bean.Signin;
import com.bean.User;
import com.dao.BanzhuDAO;
import com.dao.BbstypeDAO;
import com.dao.MemberDAO;
import com.dao.SigninDAO;
import com.dao.UserDAO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.util.Info;

import java.util.*;

@Controller
public class SigninController extends BaseController {
	@Resource
	MemberDAO memberDAO;
	@Resource
	SigninDAO signinDAO;
	
	@RequestMapping("signinAdd")
	public void signinAdd(HttpServletRequest request, HttpServletResponse response){
		try {
			String memberid = request.getParameter("memberid");
			PrintWriter out = response.getWriter();
			HashMap map = new HashMap();
			map.put("memberid", memberid);
			map.put("savetime", Info.getDateStr().substring(0,10));
			List<Signin> list = signinDAO.isSignin(map);
			if(list.size()==0){
				Signin sg = new Signin();
				sg.setMemberid(Integer.parseInt(memberid));
				sg.setSavetime(Info.getDateStr().substring(0,10));
				signinDAO.add(sg);
				out.print(0);
			}else{
				out.print(1);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
