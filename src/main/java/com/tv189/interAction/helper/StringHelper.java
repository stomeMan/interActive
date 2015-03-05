package com.tv189.interAction.helper;

public class StringHelper {
	
	public static final String Empty = "";
	public static final String URL_CONCAT="&";
	public static final String URL_BEGIN="?";
	public static final String PARA_EQUAL="=";
	public static final String SPLITE="|";
	public static final String SLASH = "\\";
	public static final String ENTER="\r\n";
	public static final String EMAIL_SIGN="@";
	public static final String COMMA=",";
	public static final String FULL_COMMA="，";
	public static final String LBRAKETS = "(";
	public static final String RBRAKETS = ")";
	public static final String SPACE = " ";
	public static final String OR = "OR";
	public static final String AND = "AND";
	public static final String DASH = "-";
	public static final String RISK = ":";
	public static final String POINT = ".";
	public static final String NOT = "!";
	
	public static Boolean isNullOrEmpty(String value) {
		return value == null || StringHelper.Empty.equals(value);
	}
	
	public static boolean isNotEmpty(String o) {
		return !isNullOrEmpty(o);
	}
	
	public static String replaceBracket(String str){
		return str.replaceAll("\\[", "").replaceAll("\\]", "");
	}
}
