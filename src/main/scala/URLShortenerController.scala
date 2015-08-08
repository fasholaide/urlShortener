package com.bfashola.urlShortener

import com.redis._
import com.twitter.finagle.http._
import com.twitter.finatra._

import scala.collection.mutable
import scala.util.Random

class URLShortenerController() extends Controller {
  val hashMap: mutable.HashMap[Int, String] = new mutable.HashMap[Int, String]()
  get("/eba/value/:url") { request: Request =>
    var redisClient: RedisClient = null
    val url = request.params("url")
    var number = Random.nextInt(1000)
    while (hashMap.contains(number))
      number = Random.nextInt(1000)
    try {
      redisClient = new RedisClient("localhost", 6379)
      redisClient.set(number, url)
      response.ok("The URL has been shortened to /eba/key/" + number)
    }
    catch {
      case ex: Exception => response.ok.fileOrIndex(
        filePath = request.params("*"),
        indexPath = "index.html")
    }
    finally {
      if (redisClient != null && redisClient.connected)
        redisClient.disconnect
    }


  }
  get("/eba/key/:id") { request: Request =>
    var redisConnection: RedisClient = null
    try {
      val id = request.params("id")
      redisConnection = new RedisClient("localhost", 6379)
      var url = redisConnection.get(id) match {
        case Some(value) => value.toString
        case None => "-1"
      }
      if (url == "-1")
        response.ok.fileOrIndex(
          filePath = request.params("*"),
          indexPath = "index.html")
      else
      if (url(0) != 'h' && url(1) != 't' && url(2) != 't' && url(3) != 'p')
        url = "http://" + url
        response.temporaryRedirect.location(url)
    }
    catch {
      case ex: Exception =>
        response.ok.fileOrIndex(
          filePath = request.params("*"),
          indexPath = "index.html")
    }
    finally {
      if (redisConnection.connected && redisConnection != null)
        redisConnection.disconnect
    }

  }
}