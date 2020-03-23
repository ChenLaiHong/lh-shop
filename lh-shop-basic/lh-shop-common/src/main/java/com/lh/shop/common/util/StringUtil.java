package com.lh.shop.common.util;


import java.util.ArrayList;
import java.util.List;

public class StringUtil {

	public static boolean isEmpty(String str) {
		if (str == null || "".equals(str.trim())) {
			return true;
		} else {
			return false;
		}
	}


	public static boolean isNotEmpty(String str) {
		if ((str != null) && !"".equals(str.trim())) {
			return true;
		} else {
			return false;
		}
	}


	public static String formatLike(String str) {
		if (isNotEmpty(str)) {
			return "%" + str + "%";
		} else {
			return null;
		}
	}

	//查找需要批量删除的id集合
	public static List StringIds(String ids){
		String[] idsStr = ids.split(",");
		List ints = new ArrayList();
		for(int i =0;i<idsStr.length;i++){
			ints.add(Integer.parseInt(idsStr[i]));
		}
		return ints;
	}
	//查找需要批量删除的id集合
	public static List StringIds(String ids,Integer temp){
		String[] idsStr = ids.split(",");
		List ints = new ArrayList();
		for(int i =0;i<idsStr.length;i++){
			ints.add(idsStr[i].substring(1));
		}
		return ints;
	}

	public static List findPath(String[] imagesURL){
		List temp = new ArrayList();
		for (int i=0; i < imagesURL.length;i++){
			//获得第一个双引号的位置
			int index1=imagesURL[i].indexOf("\"");
			//根据第一个双引号的位置 获得第二个双引号的位置
			int index2=imagesURL[i].indexOf("\"", index1+1);
			//截取 字符串。得到结果 result
			String result=imagesURL[i].substring(index1+1,index2);
			temp.add(result);
		}
		return temp;
	}

	

	
}
