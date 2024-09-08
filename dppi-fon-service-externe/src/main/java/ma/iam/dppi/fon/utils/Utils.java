package ma.iam.dppi.fon.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 
 * @author Z.BELGHAOUTI
 *
 */
public class Utils {

	private Utils() {
	}

	public static String getLogParam() {
		return "Requête: login " + getCurrentUserLogin() + ", IP " + fetchClientIpAddr() + ", Action: ";
	}

	public static String getCurrentUserLogin() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}

	/**
	 * GET ADDRESS IP EXTERNAL CLIENT
	 * 
	 * @return
	 */
	private static String fetchClientIpAddr() {
		String ip = "";
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) (RequestContextHolder
				.getRequestAttributes());
		if (servletRequestAttributes == null) {
			return "";
		}
		HttpServletRequest request = servletRequestAttributes.getRequest();
		if (request != null) {
			ip = request.getHeader("X-FORWARDED-FOR");
			if (ip == null || "".equals(ip)) {
				ip = request.getRemoteAddr();
			}
		}
		if ("0:0:0:0:0:0:0:1".equals(ip))
			ip = "127.0.0.1";
		return ip;
	}

	/**
	 * 
	 * @param numero
	 * @return
	 */
	public static Boolean isTelephoneNumber(String numero) {
		Pattern pattern1 = Pattern.compile("[0-9]{10}");
		Pattern pattern2 = Pattern.compile("[0-9]{12}");
		Matcher matcher1 = pattern1.matcher(numero);
		Matcher matcher2 = pattern2.matcher(numero);
		return matcher1.matches() || matcher2.matches();
	}

	/**
	 * 
	 * @param alphanumeric
	 * @return
	 */
	public static Boolean isOnlyAlphaNumeric(String alphanumeric) {
		Pattern pattern = Pattern.compile("[a-zA-Z0-9]+");
		Matcher matcher = pattern.matcher(alphanumeric);
		return matcher.matches();
	}

	/**
	 * 
	 * @param alphanumeric
	 * @return
	 */
	public static boolean isAuthorizedAlphaNumeric(String alphanumeric) {
		// Pattern pattern = Pattern.compile("[a-zA-Z0-9-’ 'éèêàâôîùç_°]+");
		Pattern pattern = Pattern.compile("[a-zA-Z0-9-’ 'éèêàâôîùç_°]+");
		Matcher matcher = pattern.matcher(alphanumeric);
		return matcher.matches();
	}

	/**
	 * Evolution Date Operation en input
	 */
	public static boolean isValidStringDate(String text) {
		SimpleDateFormat df = null;
		if (text == null || !text.matches("[0-3]\\d/[01]\\d/\\d{4}")
				&& !text.matches("[0-3]\\d/[01]\\d/\\d{4}\\s[0-2]\\d((:[0-5]\\d)?){2}"))
			return false;
		if (text.matches("[0-3]\\d/[01]\\d/\\d{4}"))
			df = new SimpleDateFormat("dd/MM/yyyy");
		else
			df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		df.setLenient(false);
		try {
			df.parse(text);
			return true;
		} catch (ParseException ex) {
			return false;
		}
	}

	public static boolean isSpecAlphaNumeric(String str) {
		return str.contains("$") || str.contains("&");

	}

	/**
	 * @author Z.BELGHAOUTI
	 * @return Path Project
	 */
	public static String getFullPathProject() {

		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		if (requestAttributes instanceof ServletRequestAttributes) {
			HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
			if ((request.getServerPort() == 80) || (request.getServerPort() == 443))
				return request.getScheme() + "://" + request.getServerName() + request.getContextPath();
			else
				return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
						+ request.getContextPath();
		}
		return null;
	}

	/* Logging methods */

	public static String formatMessage(String message) {
		return String.format("%s %s", Utils.getLogParam(), message);
	}

	public static String formatMessage(Exception e) {
		return formatMessage(e.getMessage());
	}
	
	public static Boolean containSpecChar(String str){
		if(str.contains("!") || str.contains("?") || str.contains("&") ||
				str.contains("$") || str.contains("@") || str.contains("#") ||
				str.contains(">") || str.contains("<") || str.contains("%") ||
				str.contains("\"") || str.contains("+")|| str.contains(";") ||
				str.contains("/") || str.contains("*") || str.contains("=") ||
				str.contains("(") || str.contains(")") || str.contains("{") ||
				str.contains("}") || str.contains("[") || str.contains("]") || 
				str.contains("|") || str.contains(":") || str.contains(",")){
			return true;
		}
		return false;
	}
	
	public static Boolean contSpecChar(String str){
		if(str.contains("!") || str.contains("?") || str.contains("&") ||
				str.contains("$") || str.contains("@") || str.contains("#") ||
				str.contains(">") || str.contains("<") || str.contains("%") ||
				str.contains("\"") || str.contains("+")|| str.contains(";") ||
				str.contains("/") || str.contains("*") || str.contains(":") ||
				str.contains("(") || str.contains(")") || str.contains("{") ||
				str.contains("}") || str.contains("[") || str.contains("]") || 
				str.contains("|")){
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @author Z.BELGHAOUTI
	 */
	public static Date getCurrentDateGMTPlus1() throws ParseException{
		
		Calendar calParis = Calendar.getInstance();
		calParis.setTimeZone(TimeZone.getTimeZone("GMT+01"));
		int year = calParis.get(Calendar.YEAR);
		int month = calParis.get(Calendar.MONTH) + 1;
		int day = calParis.get(Calendar.DAY_OF_MONTH);
		int hour = calParis.get(Calendar.HOUR_OF_DAY);
		int minute = calParis.get(Calendar.MINUTE);
		int second = calParis.get(Calendar.SECOND);
		String dateStr = "" + day + "/" + month + "/" + year + " " + hour + ":" + minute + ":" +second;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		return sdf.parse(dateStr);
	}
	
	/**
	 * 
	 * @author Z.BELGHAOUTI
	 */
	public static Date getCurrentDateGMT() throws ParseException{
		
		Calendar calMaroc = Calendar.getInstance();
		calMaroc.setTimeZone(TimeZone.getTimeZone("GMT+00"));
		int year = calMaroc.get(Calendar.YEAR);
		int month = calMaroc.get(Calendar.MONTH) + 1;
		int day = calMaroc.get(Calendar.DAY_OF_MONTH);
		int hour = calMaroc.get(Calendar.HOUR_OF_DAY);
		int minute = calMaroc.get(Calendar.MINUTE);
		int second = calMaroc.get(Calendar.SECOND);
		String dateStr = "" + day + "/" + month + "/" + year + " " + hour + ":" + minute + ":" +second;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		return sdf.parse(dateStr);
	}
	
	public static String joinListString(List<String> list, String delim) {
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
}
