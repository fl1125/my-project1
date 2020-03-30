package com.bean;

import java.util.*;

public class Banzhu {
	private int id;
	private int memberid;
	private int fid;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMemberid() {
		return memberid;
	}
	public void setMemberid(int memberid) {
		this.memberid = memberid;
	}
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	@Override
	public String toString() {
		return "Banzhu [fid=" + fid + ", id=" + id + ", memberid=" + memberid
				+ "]";
	}

	
}
