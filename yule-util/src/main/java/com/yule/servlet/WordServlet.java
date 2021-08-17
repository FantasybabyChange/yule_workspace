package com.yule.servlet;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.yule.constant.CodeConst;
import com.yule.exception.YuleException;
import com.yule.util.WordUtil;

public class WordServlet extends HttpServlet {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 4742561235016548141L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType(CodeConst.TEXT_HTML);
		response.setCharacterEncoding(CodeConst.UTF_8);
		PrintWriter out = null;
		try{
			String word = request.getParameter("word");
			out = response.getWriter();
			JSONObject obj = JSONObject.fromObject(WordUtil.doFilter(word));
			out.println(obj.toString());
		} catch (Exception e){
			new YuleException("关键字过滤报错!",e);
		} finally{
			if(null!=out){
				out.flush();
				out.close();
			}
		}
	}

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doPost(request, response);
    }
    
    @Override
    public void destroy() {
    	super.destroy();
    }
}