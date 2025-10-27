package com.university.aop;


import io.quarkus.logging.Log;
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

    LOG.info("Starting " + methodName);
    try{
        Object result = context.proceed();
        long time = System.currentTimeMillis() - start;
        LOG.info("Finished " + methodName + " in " + time + " ms)");
        return result;

    } catch (Exception e) {
        Log.error(" Error in method " + methodName, e);
        throw e;
    }

    }

}
