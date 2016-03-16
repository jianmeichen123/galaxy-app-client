package com.galaxyinternet.platform.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description 用于记录用户的操作 <br>
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Logger {
	/**
	 * @return 是否记录sop的操作日志，默认“否”
	 */
	boolean writeSopOperationLog() default false;
}