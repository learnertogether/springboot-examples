package com.zxl.examples.filter;

import org.apache.catalina.filters.RemoteIpFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2017/7/27.
 */
//@Configuration
public class WebConfiguration {

    @Bean
    public RemoteIpFilter remoteIpFilter() {
        return new RemoteIpFilter();
    }

    @Bean
    public FilterRegistrationBean testFilterRegistration() {
        //配置无需过滤的路径或者静态资源，如：css，imgage等
        StringBuffer excludedUriStr = new StringBuffer();
        excludedUriStr.append("/login/*");
        excludedUriStr.append("/vue/*");
        excludedUriStr.append(",");
        excludedUriStr.append("/favicon.ico");
        excludedUriStr.append(",");
        excludedUriStr.append("/js/*");

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new MyFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("excludedUri", excludedUriStr.toString());
        registration.setName("MyFilter");
        registration.setOrder(1);
        return registration;
    }

//    @Bean
//    public FilterRegistrationBean testFilterRegistration2() {
//        //配置无需过滤的路径或者静态资源，如：css，imgage等
//        StringBuffer excludedUriStr = new StringBuffer();
//        excludedUriStr.append("/login/*");
//        excludedUriStr.append(",");
//        excludedUriStr.append("/favicon.ico");
//        excludedUriStr.append(",");
//        excludedUriStr.append("/js/*");
//
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//        registration.setFilter(new MyFilter2());
//        registration.addUrlPatterns("/login/login");
//        registration.addInitParameter("excludedUri", excludedUriStr.toString());
//        registration.setName("MyFilter2");
//        registration.setOrder(2);
//        return registration;
//    }

    public class MyFilter implements Filter {

        private String[] excludedUris;

        @Override
        public void destroy() {
            // TODO Auto-generated method stub
        }

        @Override
        public void doFilter(ServletRequest srequest, ServletResponse sresponse, FilterChain filterChain)
                throws IOException, ServletException {
            // TODO Auto-generated method stub
            HttpServletRequest request = (HttpServletRequest) srequest;
            HttpServletResponse response = (HttpServletResponse) sresponse;
            System.out.println("this is MyFilter,url :"+request.getRequestURI());
            String uri = request.getServletPath();
            filterChain.doFilter(srequest, sresponse);
//            if(isExcludedUri(uri)){
//                filterChain.doFilter(srequest, sresponse);
//            }else if(request.getSession().getAttribute("user")!=null){
//                filterChain.doFilter(srequest, sresponse);
//            }else{
//                response.sendRedirect(request.getContextPath() + "/login/toLogin");
//            }
        }

        @Override
        public void init(FilterConfig filterConfig) throws ServletException {
            // TODO Auto-generated method stub
            excludedUris = filterConfig.getInitParameter("excludedUri").split(",");
        }

        private boolean isExcludedUri(String uri) {
            if (excludedUris == null || excludedUris.length <= 0) {
                return false;
            }
            for (String ex : excludedUris) {
                uri = uri.trim();
                ex = ex.trim();
                if (uri.toLowerCase().matches(ex.toLowerCase().replace("*",".*")))
                    return true;
            }
            return false;
        }
    }

//    public class MyFilter2 implements Filter {
//
//        private String[] excludedUris;
//
//        @Override
//        public void destroy() {
//            // TODO Auto-generated method stub
//        }
//
//        @Override
//        public void doFilter(ServletRequest srequest, ServletResponse sresponse, FilterChain filterChain)
//                throws IOException, ServletException {
//            // TODO Auto-generated method stub
//            HttpServletRequest request = (HttpServletRequest) srequest;
//            HttpServletResponse response = (HttpServletResponse) sresponse;
//            System.out.println("this is MyFilter,url :"+request.getRequestURI());
//            String uri = request.getServletPath();
//            if(isExcludedUri(uri)){
//                filterChain.doFilter(srequest, sresponse);
//            }else if(request.getSession().getAttribute("user")!=null){
//                filterChain.doFilter(srequest, sresponse);
//            }else{
//                response.sendRedirect(request.getContextPath() + "/login/toLogin");
//            }
//        }
//
//        @Override
//        public void init(FilterConfig filterConfig) throws ServletException {
//            // TODO Auto-generated method stub
//            excludedUris = filterConfig.getInitParameter("excludedUri").split(",");
//        }
//
//        private boolean isExcludedUri(String uri) {
//            if (excludedUris == null || excludedUris.length <= 0) {
//                return false;
//            }
//            for (String ex : excludedUris) {
//                uri = uri.trim();
//                ex = ex.trim();
//                if (uri.toLowerCase().matches(ex.toLowerCase().replace("*",".*")))
//                    return true;
//            }
//            return false;
//        }
//    }
}
