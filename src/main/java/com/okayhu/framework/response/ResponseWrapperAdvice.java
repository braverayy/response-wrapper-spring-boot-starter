package com.okayhu.framework.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Objects;

/**
 * @author okayhu
 * @date 2022/1/19
 */
@Slf4j
public class ResponseWrapperAdvice implements ResponseBodyAdvice<Object> {

    private final ObjectMapper objectMapper;

    public ResponseWrapperAdvice(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean supports(@NonNull MethodParameter returnType,
                            @NonNull Class<? extends HttpMessageConverter<?>> converterType) {
        return returnType.getParameterType() != ResponseEntity.class
                && (AnnotationUtils.findAnnotation(Objects.requireNonNull(returnType.getMethod()), WrapResponse.class) != null
                || AnnotationUtils.findAnnotation(returnType.getDeclaringClass(), WrapResponse.class) != null)
                && AnnotationUtils.findAnnotation(Objects.requireNonNull(returnType.getMethod()), NotWrapResponse.class) == null
                && AnnotationUtils.findAnnotation(returnType.getDeclaringClass(), NotWrapResponse.class) == null;
    }

    @Override
    public Object beforeBodyWrite(Object body,
                                  @NonNull MethodParameter returnType,
                                  @NonNull MediaType selectedContentType,
                                  @NonNull Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  @NonNull ServerHttpRequest request, @NonNull ServerHttpResponse response) {
        HttpHeaders headers = response.getHeaders();
        headers.add("content-type",MediaType.APPLICATION_JSON_VALUE);

        ResponseEntity<Object> responseEntity = ResponseEntity.success(body);
        return selectedConverterType == StringHttpMessageConverter.class
                ? toJsonString(responseEntity) : responseEntity;
    }

    private String toJsonString(Object value) {
        try {
            return value == null ? null : objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            log.error("Unable to convert object to json string", e);
            return null;
        }
    }
}
