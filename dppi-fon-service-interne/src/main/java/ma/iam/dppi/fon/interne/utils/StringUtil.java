package ma.iam.dppi.fon.interne.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Z.BELGHAOUTI
 *
 */
public class StringUtil {
	
	public static boolean isNullOrEmpty(String s) {
		if(s == null) {
			return true;
		}
		if(s.trim().isEmpty()) {
			return true;
		}
		return false;
	}

	public static String joinListLong(List<Long> list, String delim) {
		StringBuilder sb = new StringBuilder();
		if (list == null || list.isEmpty()) {
			return "";
		}
		for (int i = 0; i < list.size() - 1; i++) {
			sb.append(list.get(i));
			sb.append(delim);
		}
		sb.append(list.get(list.size() - 1));
		return sb.toString();
	}

	public static List<Long> toListLong(String str, String delim) {
		if (delim == null)
			return null;
		
		String[] spl = str.split(delim);
		List<Long> list = new ArrayList<>();
		if(str.isEmpty()) {
			return list;	
		}
		try {
			for (String s : spl) {
				list.add(Long.parseLong(s));
			}
			return list;
		} catch (NumberFormatException e) {
			e.getMessage();
			return null;
		}
	}
	
	public static String joinListString(List<String> list, String delim) {
		StringBuilder sb = new StringBuilder();
		if (list == null || list.isEmpty()) {
			return "";
		}
		for (int i = 0; i < list.size() - 1; i++) {
			sb.append("'");
			sb.append(list.get(i));
			sb.append("'");
			sb.append(delim);
		}
		sb.append("'");
		sb.append(list.get(list.size() - 1));
		sb.append("'");
		return sb.toString();
	}

	public static List<String> toListString(String str, String delim) {
		if (delim == null)
			return null;
		String[] spl = str.split(delim);
		List<String> list = new ArrayList<>();
		if(str.isEmpty()) {
			return list;	
		}
		try {
			for (String s : spl) {
				list.add(s);
			}
			return list;
		} catch (NumberFormatException e) {
			e.getMessage();
			return null;
		}
	}
}
