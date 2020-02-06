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

	

	
}
