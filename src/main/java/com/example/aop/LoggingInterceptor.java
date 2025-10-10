package com.example.aop;


import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import org.jboss.logging.Logger;

@Logged
@Interceptor

public class LoggingInterceptor {

    private static final Logger LOG = Logger.getLogger(LoggingInterceptor.class);

    @AroundInvoke
    public Object logMethodCall(InvocationContext context) throws Exception{
    String methodName = context.getMethod().getName();
    long start = System.currentTimeMillis();



    }

}
