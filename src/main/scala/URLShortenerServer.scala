package com.bfashola.urlShortener

/**
 * Created by bfashola on 7/23/15.
 */
import com.twitter.finagle.http.{Response, Request}
import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.filters.CommonFilters
import com.twitter.finatra.http.routing.HttpRouter
import com.twitter.finatra.logging.filter.{TraceIdMDCFilter, LoggingMDCFilter}
import com.twitter.finatra.logging.modules.LogbackModule

object URLShortenerServerMain extends URLShortenerServer

class URLShortenerServer extends  HttpServer{
  override def modules = Seq(LogbackModule)

  override def configureHttp(router: HttpRouter): Unit = {
    router
      .filter[LoggingMDCFilter[Request, Response]]
      .filter[TraceIdMDCFilter[Request, Response]]
      .filter[CommonFilters]
      .add[URLShortenerController]
  }

}

