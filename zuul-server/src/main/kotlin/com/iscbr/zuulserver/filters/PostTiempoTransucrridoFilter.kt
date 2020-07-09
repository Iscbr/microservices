package com.iscbr.zuulserver.filters

import com.netflix.zuul.ZuulFilter
import com.netflix.zuul.context.RequestContext
import org.springframework.stereotype.Component

@Component
class PostTiempoTransucrridoFilter: ZuulFilter() {

    /**
     * this method contains the whole logic of the filter.
     */
    override fun run(): Any {
        val requestContext = RequestContext.getCurrentContext()
        val httpRequest = requestContext.request

        println("entering to Post")
        println("${httpRequest.method} request routed to ${httpRequest.requestURL}")

        val startTime = httpRequest.getAttribute("startTime") as Long
        val endTime = System.currentTimeMillis()
        val totalTime = endTime - startTime

        println("time elapsed in seconds: ${totalTime.toDouble() / 1000.00}")

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
    override fun filterType(): String = "post"

    /**
     * This method indicates the order of execution of this filter.
     */
    override fun filterOrder(): Int = 2
}