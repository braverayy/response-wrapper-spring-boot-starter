package com.okayhu.framework.response;

import java.lang.annotation.*;

/**
 * 包装响应，{@link NotWrapResponse} 优先级更高
 *
 * @author okayhu
 * @date 2022/4/28
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WrapResponse {
}
