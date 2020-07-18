package com.interview.chequer.web;

import com.interview.chequer.domain.AuthorizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class UserInterceptor extends HandlerInterceptorAdapter {
    final private AuthorizationService authorizationService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long userId = Long.parseLong(request.getParameter("userId"));

        Map pathVariables = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        long workspaceId = Long.parseLong((String) pathVariables.get("workspaceId"));

        authorizationService.validateWorkspaceOwner(userId, workspaceId);

        return super.preHandle(request, response, handler);
    }
}
