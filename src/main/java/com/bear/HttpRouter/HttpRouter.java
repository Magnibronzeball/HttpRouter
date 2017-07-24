package com.bear.HttpRouter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by think on 2017/7/24.
 */
@RestController
@RequestMapping("/api")
@EnableZuulProxy
@SpringBootApplication
public class HttpRouter {
    @Resource
    HttpRouterService httpRouterService;
    private final Logger log = LoggerFactory.getLogger(HttpRouter.class);
    public static void main(String[] args) {
        SpringApplication.run(HttpRouter.class, args);
    }

    /**
     * 切换路由
     * @param serviceId
     * @param desUrl
     * @return
     */
    @RequestMapping(value="/changeroute",method = {RequestMethod.GET,RequestMethod.POST})
    public List<Route> changeRoute(@RequestParam("serviceId") String serviceId, @RequestParam("desUrl") String desUrl){
        return httpRouterService.changeRouter(serviceId,desUrl);
    }

    /**
     * 获取路由
     * @return
     */
    @RequestMapping(value="/getroute",method = {RequestMethod.GET,RequestMethod.POST})
    public List<Route> getRoute(){
        return httpRouterService.getRouter();
    }
}
