package edu.school.utils;

import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;



public class WebUtils {

	public static  <T> T copyParamToBean(Map value, T bean){//使用泛型来减少request.getParameter的重复代码，优化开发
		try {

			System.out.println("注入之前"+bean);
			BeanUtils.populate(bean,value);
			System.out.println("注入之后"+bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
	}
	
	public static int parseInt(String sid,int defalultvalue) {//把String类型的id转化Integer类型的
		  try {
	            return Integer.parseInt(sid);
	        }catch (Exception e){
	            e.printStackTrace();
	        }
	        return defalultvalue;
	}

	public static String getPath() {
		return "D:\\软件项目\\B-I-Java web项目\\B-I08-心理预约咨询管理系统\\源码\\源码\\xlzx\\web\\upload\\";
	}



}
