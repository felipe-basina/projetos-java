package com.github.youmoo.spring.converter;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * 用来标注vo，表示可由domain类转换
 * 
 * @autor youmoo
 * @since 2014-06-14 下午7:52
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
@Lazy
public @interface Convertable {
	Class<? extends Converter> converter();
}
