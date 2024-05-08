package com.bankaya.example.component;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.server.endpoint.MethodEndpoint;

@Component
public class CustomEndpointInterceptor implements EndpointInterceptor {
  private static final Logger logger = LoggerFactory.getLogger(CustomEndpointInterceptor.class);

  @Override
  public boolean handleRequest(MessageContext messageContext, Object o) throws Exception {
    logger.info("Endpoint Request Handling {} : {}", messageContext, o);
    HttpServletRequest httpServletRequest =
        ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
            .getRequest();
    httpServletRequest.setAttribute("soapMethod", ((MethodEndpoint) o).getMethod().getName());
    return true;
  }

  @Override
  public boolean handleResponse(MessageContext messageContext, Object o) throws Exception {
    return true;
  }

  @Override
  public boolean handleFault(MessageContext messageContext, Object o) throws Exception {
    return true;
  }

  @Override
  public void afterCompletion(MessageContext messageContext, Object o, Exception e) throws Exception {

  }
}
