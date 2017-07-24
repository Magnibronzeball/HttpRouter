package com.bear.HttpRouter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.RoutesRefreshedEvent;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.discovery.DiscoveryClientRouteLocator;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by think on 2017/7/24.
 */
@Component
public class HttpRouterServiceImp implements HttpRouterService {
    private final Logger log = LoggerFactory.getLogger(HttpRouterServiceImp.class);
    @Resource
    ApplicationEventPublisher publisher;
    @Resource
    RouteLocator routeLocator;
    public synchronized List<Route> changeRouter(String serviceId, String desUrl) {
        ZuulProperties.ZuulRoute zuulRoute = new ZuulProperties.ZuulRoute();
        zuulRoute.setLocation(desUrl);
        zuulRoute.setPath("/"+serviceId+"/**");
        zuulRoute.setCustomSensitiveHeaders(true);
        zuulRoute.setId(serviceId);
        zuulRoute.setUrl(desUrl);
        zuulRoute.setServiceId(serviceId);
        ((DiscoveryClientRouteLocator) routeLocator).addRoute(zuulRoute);
        RoutesRefreshedEvent routesRefreshedEvent = new RoutesRefreshedEvent(routeLocator);
        publisher.publishEvent(routesRefreshedEvent);
        return  routeLocator.getRoutes();
    }

    @Override
    public List<Route> getRouter() {
        return  routeLocator.getRoutes();
    }
}
