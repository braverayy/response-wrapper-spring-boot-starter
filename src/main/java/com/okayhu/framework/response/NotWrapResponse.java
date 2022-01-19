package com.okayhu.framework.response;

import java.lang.annotation.*;

/**
 * 如果类或方法上有该注解，则不对响应进行包装
 *
 * @author okayhu
 * @date 2022/1/19
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NotWrapResponse {
}
