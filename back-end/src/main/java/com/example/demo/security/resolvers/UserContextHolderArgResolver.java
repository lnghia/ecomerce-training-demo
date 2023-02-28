package com.example.demo.security.resolvers;

import com.example.demo.dto.contextholder.UserContextHolder;
import org.joda.time.DateTimeZone;
import org.springframework.core.MethodParameter;
import org.springframework.util.Assert;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

public class UserContextHolderArgResolver implements HandlerMethodArgumentResolver {


    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return UserContextHolder.class.equals(methodParameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        UserContextHolder.UserContextHolderBuilder userContextHolderBuilder = UserContextHolder.builder();
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);

        Assert.notNull(request, "The incoming request must not be null");
        if(request.getAttribute("timezone") != null) {
            userContextHolderBuilder.timeZone((DateTimeZone) request.getAttribute("timezone"));
        }

        return userContextHolderBuilder.build();
    }
}
