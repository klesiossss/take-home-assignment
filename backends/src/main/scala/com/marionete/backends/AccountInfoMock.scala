package com.marionete.backends

import com.twitter.finagle.http.{Request, Response}
import com.twitter.finagle.{Failure, Http, ListeningServer, Service, Status, http}
import com.twitter.util.Future

object AccountInfoMock {

  def start(): ListeningServer = {
    val port = "8899"
    println(s"Starting AccountInfoMock service in port $port")
    Http.serve(":" + port, service)
  }

  private val service: Service[Request, Response] = (req: http.Request) => {
    req.path match {
      case "/marionete/account/" => processAccountEndpoint(req)
      case _ =>
        Future.exception(Failure.rejected("no endpoint found"))
    }
  }

  private def processAccountEndpoint(req: Request): Future[Response] = {
    Response()
    req.headerMap.get("Authorization") match {
      case Some(token) =>
        println(s"[AccountInfoMock] Request with $token valid. Returning account info.")
        val response =
          """
                {
                   "account_number":12345
                }
            |""".stripMargin
        val rep = Response(com.twitter.finagle.http.Status.Ok)
        rep.setContentString(response)
        Future.value(rep)

      case None =>
        Future.exception(Failure.rejected("Token not found in header"))
    }
  }

}