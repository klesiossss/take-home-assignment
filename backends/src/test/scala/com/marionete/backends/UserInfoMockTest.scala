package com.marionete.backends

import com.twitter.finagle.http.{Request, Response, Status}
import com.twitter.finagle.{Http, Service, http}
import com.twitter.util.Await
import org.scalatest.{BeforeAndAfterAll, FeatureSpec, GivenWhenThen}

class UserInfoMockTest extends FeatureSpec with GivenWhenThen with BeforeAndAfterAll{

  override def beforeAll(): Unit ={
    UserInfoMock.start()
  }

  feature("[UserAccountMock] To ensure that is working as we expect") {
    scenario("[UserAccountMock] We made a good request with Authentication in header and return result successfully") {
      Given("A [UserAccountMock] running and a Http client with Authentication header")
      val client: Service[Request, Response] = Http.newService("localhost:8898")
      When("When I made a request")
      val request = http.Request(http.Method.Get, "/marionete/user/")
      request.headerMap.add("Authorization", "valid_token")
      val future = client(request)
      Then("The Future response is successful")
      val response = Await.result(future)
      assert(response.status == Status.Ok)
    }

    scenario("[UserAccountMock] We made a bad request without Authentication in header and return result Error") {
      Given("A [UserAccountMock] running and a Http client without Authentication header")
      val client: Service[Request, Response] = Http.newService("localhost:8898")
      When("When I made a request")
      val request = http.Request(http.Method.Get, "/marionete/user/")
      val future = client(request)
      Then("The Future response is wrong")
      val response = Await.result(future)
      assert(response.status == Status.InternalServerError)
    }

    scenario("[UserAccountMock] We made a bad request with wrong endpoint and return result Error") {
      Given("A [UserAccountMock] running and a Http client without Authentication header")
      val client: Service[Request, Response] = Http.newService("localhost:8898")
      When("When I made a request")
      val request = http.Request(http.Method.Get, "/marionete/foo/")
      val future = client(request)
      Then("The Future response is wrong")
      val response = Await.result(future)
      assert(response.status == Status.NotFound)
    }
  }
}