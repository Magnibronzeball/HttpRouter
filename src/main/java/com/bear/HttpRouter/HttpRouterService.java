package com.bear.HttpRouter;

import org.springframework.cloud.netflix.zuul.filters.Route;

import java.util.List;

/**
 * Created by think on 2017/7/24.
 */
public interface HttpRouterService {
    /**
     * 切换路由
     * @param serviceId
     * @param desUrl
     * @return 最新的路由信息
     */
    List<Route> changeRouter(String serviceId, String desUrl);

    /**
     * 查询路由
     * @return
     */
    List<Route> getRouter();
}
