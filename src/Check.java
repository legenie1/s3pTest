import org.maviance.s3pjavaclient.ApiClient;
import org.maviance.s3pjavaclient.ApiException;
import org.maviance.s3pjavaclient.api.HealthcheckApi;
import org.maviance.s3pjavaclient.model.Ping;


class Check {

 public static void main(String[] args) {
  ApiClient apiClient = new ApiClient("<HOST>", "<Acess Token>", "<Secret>");

  HealthcheckApi checksApi = new HealthcheckApi(apiClient);
  String xApiVersion = "3.0.5";

  try {
   Ping ping = checksApi.pingGet(xApiVersion);
   System.out.println(ping);
  } catch (ApiException e) {
   System.out.println("An error occurred: \n");
   System.out.println(e.getResponseBody());
  }
 }
}