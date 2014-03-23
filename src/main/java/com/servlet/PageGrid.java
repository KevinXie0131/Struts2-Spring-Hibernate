package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PageGrid extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String page=request.getParameter("page");
		String rows = request.getParameter("rows");
		
		System.out.println(page+"   "+rows);
		
		String json="";
		if(page.equals("1")){
			json="["+
					"{\"id\":\"001\",\"name\":\"AAA\",\"price\":\"111\"},"+
					"{\"id\":\"002\",\"name\":\"BBB\",\"price\":\"222\"},"+
					"{\"id\":\"003\",\"name\":\"CCC\",\"price\":\"333\"},"+
					"{\"id\":\"004\",\"name\":\"DDD\",\"price\":\"444\"},"+
					"{\"id\":\"005\",\"name\":\"EEE\",\"price\":\"555\"},"+
					"{\"id\":\"006\",\"name\":\"FFF\",\"price\":\"666\"},"+
					"{\"id\":\"007\",\"name\":\"GGG\",\"price\":\"777\"},"+
					"{\"id\":\"008\",\"name\":\"HHH\",\"price\":\"888\"},"+
					"{\"id\":\"009\",\"name\":\"III\",\"price\":\"999\"},"+
					"{\"id\":\"009\",\"name\":\"LLL\",\"price\":\"999\"},"+
					"{\"id\":\"010\",\"name\":\"MMM\",\"price\":\"010\"}"
					+"]";
		}else if(page.equals("2")){
			json="["+
					"{\"id\":\"011\",\"name\":\"aaa\",\"price\":\"011\"},"+
					"{\"id\":\"012\",\"name\":\"bbb\",\"price\":\"012\"},"+
					"{\"id\":\"013\",\"name\":\"ccc\",\"price\":\"013\"}"
					+"]";
		}
		out.println(json);
	}

}
