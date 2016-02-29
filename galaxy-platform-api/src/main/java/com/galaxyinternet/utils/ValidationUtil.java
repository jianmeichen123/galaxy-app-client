package com.galaxyinternet.utils;

import java.util.Calendar;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

import com.galaxyinternet.exception.PlatformException;



public abstract class ValidationUtil {



	public static boolean isNullOrEmpty( String fieldName, String value) {
		if (hasText(value)) {
			return false;
		}
		throwPlatformException(MessageStatus.FIELD_NOT_ALLOWED_EMPTY, fieldName);
		return true;
	}


	public static boolean isEmptyOrLessThan(
			
			String fieldName,
			String value,
			int minLength) {
		return validate(fieldName, value, minLength, 0);
	}

	public static boolean isLessThan(
			
			String fieldName,
			String value,
			int minLength) {
		return validate(fieldName, true, value, minLength, 0);
	}

	public static boolean isEmptyOrMoreThan(
			
			String fieldName,
			String value,
			int maxLength) {
		return validate( fieldName, value, 0, maxLength);
	}

	public static boolean isMoreThan(
			
			String fieldName,
			String value,
			int maxLength) {
		return validate(fieldName, true, value, 0, maxLength);
	}

	public static boolean isEmptyOrNotInRange(
			
			String fieldName,
			String value,
			int minLength,
			int maxLength) {
		return validate( fieldName, value, minLength, maxLength);
	}

	public static boolean isNotInRange(
			
			String fieldName,
			String value,
			int minLength,
			int maxLength) {
		return validate(fieldName, true, value, minLength, maxLength);
	}

	public static boolean validate(String fieldName,String value,int minLength,int maxLength){
		return validate( fieldName, false, value, minLength, maxLength);
	}

	public static boolean validate(String fieldName,Boolean isAllowEmpty,String value,int minLength,int maxLength) {
		if (isAllowEmpty) {
			if (!hasText(value)) {
				return false;
			}
		}else if (isNullOrEmpty(fieldName, value)) {
			return true;
		}
		int length = value.length();
		if (minLength > 0 && maxLength < 1 && length < minLength) {
			throwPlatformException(MessageStatus.FIELD_LENGTH_MUST_MORE, fieldName, minLength);
			return true;
		}
		if (maxLength > 0 && minLength < 1 && length > maxLength) {
			throwPlatformException(MessageStatus.FIELD_LENGTH_MUST_LESS, fieldName, maxLength);
			return true;
		}
		if (maxLength > 0 && minLength > 0 && (length > maxLength || length < minLength)) {
			throwPlatformException(MessageStatus.FIELD_LENGTH_MUST_BETWEEN, fieldName, minLength, maxLength);
			return true;
		}
		return false;
	}

	private final static Pattern emailPattern = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");

	public static boolean isValidEmail( String fieldName, String value) {

		if (isNullOrEmpty(fieldName, value)) {
			return false;
		}
		if (emailPattern.matcher(value).find()) {
			return true;
		}
		throwPlatformException(MessageStatus.FIELD_NOT_EMAIL, fieldName);
		return false;
	}

	private final static Pattern mobilePattern = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");

	public static boolean isValidMobile( String fieldName, String value) {
		if (mobilePattern.matcher(value).matches()) {
			return true;
		}
		throwPlatformException(MessageStatus.FIELD_NOT_MOBILE, fieldName);
		return false;
	}

	private final static Pattern idNumPattern = Pattern.compile("(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])");

	public static boolean isValidIDNumber( String fieldName, String value) {
		if (idNumPattern.matcher(value).matches()) {
			return true;
		}
		throwPlatformException(MessageStatus.FIELD_NOT_ID_NUM, fieldName);
		return false;
	}

	public static boolean isNull( String fieldName, Object obj) {
		if (obj == null) {
			throwPlatformException(MessageStatus.FIELD_NOT_ALLOWED_EMPTY, fieldName);
			return true;
		}
		return false;
	}

	public static boolean isValidDate( String fieldName, long dateInMs) {
		try {
			if (dateInMs <= 0) {
				throwPlatformException(MessageStatus.FIELD_NOT_ALLOWED_EMPTY, fieldName);
				return false;
			}
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(dateInMs);
			return true;
		} catch (Exception e) {
			throwPlatformException(MessageStatus.FIELD_NOT_DATE, fieldName);
			return false;
		}

	}

	public static boolean hasText(String value) {
		return StringUtils.hasText(value);
	}
	
	
	public  static void throwPlatformException(MessageStatus status ,Object...args ){
		String message = null;
		if(args.length == 0){
			message = status.getMessage();
		}else {
			message = String.format(status.getMessage(), args);
		}
		throw new PlatformException(status.getStatus(), message);
	}

}
