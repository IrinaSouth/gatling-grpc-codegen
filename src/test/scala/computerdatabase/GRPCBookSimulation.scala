package computerdatabase

import example.book.{BookServiceGrpc, GetBookRequest}
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import com.github.phisgr.gatling.grpc.Predef._
import io.grpc.ManagedChannelBuilder

class GRPCBookSimulation extends Simulation {

  val grpcConf = grpc(ManagedChannelBuilder.forAddress("localhost", 8080).usePlaintext())

  val s = scenario("Example")
    .exec(setUpGrpc)
    .exec(
      grpc("Book")
        .rpc(BookServiceGrpc.METHOD_GET_BOOKS)
        .payload(GetBookRequest(isbn = 1234567898))
    )

  setUp(
    s.inject(atOnceUsers(54))
  ).protocols(grpcConf)
}