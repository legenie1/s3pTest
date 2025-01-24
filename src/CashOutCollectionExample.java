import org.maviance.s3pjavaclient.ApiClient;
import org.maviance.s3pjavaclient.ApiException;
import org.maviance.s3pjavaclient.api.ConfirmApi;
import org.maviance.s3pjavaclient.api.MasterdataApi;
import org.maviance.s3pjavaclient.api.VerifyApi;
import org.maviance.s3pjavaclient.api.InitiateApi;
import org.maviance.s3pjavaclient.model.*;

import java.util.List;

public class CashOutCollectionExample {
    // Some sample values - these are not valid identifiers
    // Cash Out service number -> In this case a msisdn
    private static final String serviceNumber = "677777777";
    private static final int serviceId = 20053;

    // Customer details
    private static final String phone = "237653754334";
    private static final String email = "devert@test.com";
    private static final String xApiVersion = "3.0.5";

    public static void main(String[] args) {
        ApiClient apiClient = new ApiClient("<HOST>", "<Acess Token>", "<Secret>");
        
        apiClient.setDebugging(false);
        ConfirmApi confirmApi = new ConfirmApi(apiClient);
        InitiateApi initiateApi = new InitiateApi(apiClient);
        MasterdataApi masterDataApi = new MasterdataApi(apiClient);

        try {
        
            // Retrieve available cash out packages
            
            List<Cashout> packages = masterDataApi.cashoutGet(xApiVersion, serviceId);
            
            // Select the first packages for sake of demonstration
            Cashout cashout = packages.get(0);

            System.out.println("The cashout data is: "+cashout);

            // Retrieve pricing information by requesting a quote for a set amount for the linked payment item id   

            QuoteRequest quote = new QuoteRequest();
            quote.setAmount(100);
            quote.setPayItemId(cashout.getPayItemId());
            Quote offer = initiateApi.quotestdPost(xApiVersion, quote);
            System.out.println("The quote data is: "+offer);

            // Finalize by confirming the collection
            
            CollectionRequest collection = new CollectionRequest();
            collection.setCustomerPhonenumber(phone);
            collection.setCustomerEmailaddress(email);
            collection.setQuoteId(offer.getQuoteId());
            collection.setTag("Test cashout");
            collection.setCdata("Test Cdata with Julius");
            collection.setServiceNumber(""+serviceNumber);
            CollectionResponse payment = confirmApi.collectstdPost(xApiVersion, collection);
            System.out.println("The payment is: "+payment);

            // Lookup record in Smobilpay by PTN to retrieve the payment status
            
            VerifyApi verifyApi = new VerifyApi(apiClient);
            System.out.println("The payment verify data is: "+payment);
            List<PaymentStatus> historystds =  verifyApi.verifytxGet(xApiVersion, payment.getPtn(), null);
            System.out.println("The history data is: "+historystds);
            if (historystds.size() != 1) {
            // Should have found exactly one record."
                System.exit(0);
            }
        } catch (ApiException e) {
            // Add more detailed handling here 
            System.out.println("An error occurred: \n");
            System.out.println(e.getResponseBody());
        }

    }
}
