package com.iscbr.zuulserver.filters

import com.netflix.zuul.ZuulFilter
import com.netflix.zuul.context.RequestContext
import org.springframework.stereotype.Component

@Component
class PreTiempoTransucrridoFilter: ZuulFilter() {

    /**
     * this method contains the whole logic of the filter.
     */
    override fun run(): Any {
        val requestContext = RequestContext.getCurrentContext()
        val httpRequest = requestContext.request

        println("${httpRequest.method} request routed to ${httpRequest.requestURL}")

        val startTime = System.currentTimeMillis()
        httpRequest.setAttribute("startTime", startTime)

        return Any()
    }

    /**
     * this method indicates if this filter is going to be executed or not. If returns true the "run" method is executed
     * otherwise the method is not executed.
     */
    override fun shouldFilter(): Boolean = true

    /**
     * This method indicates what kind of filer is this, in this case it is a PRE filter, before to routing the request
     */
    override fun filterType(): String = "pre"

    /**
     * This method indicates the order of execution of this filter.
     */
    override fun filterOrder(): Int = 1
}