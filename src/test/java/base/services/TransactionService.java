package base.services;

import base.model.BankTransaction;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * This Class contains all methods/actions needed by functions related to Transaction Service
 */
public class TransactionService {
    /**
     * Logger by Log4j2 declaration and initialization
     */
    private static final Logger LOGGER = LogManager.getLogger(TransactionService.class);

    /**
     * Constants definitions
     */
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String TRANSACTION_CONTENT_TYPE = "application/json";
    private static final String ENDPOINT = "https://62901378665ea71fe12cf489.mockapi.io/bankTransaction/";

    /**
     * Local variables
     */
    private Response response;


    /**
     * This method send a GET request
     * Using get function from SerenityREST
     *
     */
    @Step("I get the endpoint by resource")
    public void sendRequestByGet() {
        response = SerenityRest.get(ENDPOINT);

        LOGGER.info("Send GET request --- Time: " + response.getTime() + " -- Status code: " + response.getStatusCode() +
                " -- Session ID: " + response.getSessionId());
    }

    /**
     * This method returns the list of transactions from the main service with all contained elements
     *
     * @return List of transactions from class BankTransaction
     */
    @Step("I get the list of transactions from service")
    public List<BankTransaction> getTransactionListFromService() {
        sendRequestByGet();
        return SerenityRest.lastResponse().jsonPath().getList(".", BankTransaction.class);
    }

    /**
     * This method returns the last Transaction created with all content
     *
     * @return Last Transaction created as User object
     */
    @Step("I Get last transaction from transaction list")
    public BankTransaction getLastCreatedTransaction() {
        List<BankTransaction> transactionListResponse = getTransactionListFromService();
        return transactionListResponse.get(transactionListResponse.size() - 1);
    }

    /**
     * This method returns the Transaction related to an id
     *
     * @return Transaction by a given id
     */
    @Step("I Get transaction from transaction list by id")
    public BankTransaction getTransactionById(int id) {
        List<BankTransaction> transactionListResponse = getTransactionListFromService();
        return transactionListResponse.get(id);
    }

    /**
     * This method send a POST query based on a body as String
     *
     * @param body : minimal requirements for a transaction
     */
    @Step("I send a POST query using body")
    public void sendPostQueryWithBody(Object body) {
        response = SerenityRest.given()
                .contentType(CONTENT_TYPE)
                .header(CONTENT_TYPE, TRANSACTION_CONTENT_TYPE)
                .body(body)
                .post(ENDPOINT);

        LOGGER.info("Send POST Query --- Time: " + response.getTime() + " -- Status code: " + response.getStatusCode() +
                " -- Session ID: " + response.getSessionId());
    }

    /**
     * This method DELETE a transaction by id returning the Response to compare the status code
     *
     * @param id : transaction id
     * @return Response object to assert/compare response code
     */
    @Step("I send a DELETE query by id {int}")
    public Response sendDeleteQueryById(String id) {
        response = SerenityRest.given()
                .contentType(CONTENT_TYPE)
                .header(CONTENT_TYPE, TRANSACTION_CONTENT_TYPE)
                .when()
                .delete(ENDPOINT + id);

        LOGGER.info("Send DELETE Query --- Time: " + response.getTime() + " -- Status code: " + response.getStatusCode() +
                " -- Session ID: " + response.getSessionId());
        return response;
    }

    /**
     * This method updates a Transaction by id using body information
     *
     * @param body : fields and values to be updated
     * @param id : transaction id
     * @return Response Object - Response code
     */
    @Step("I UPDATE Transaction by id using information")
    public int updateTransactionById(Object body, int id) {
        response = SerenityRest.given()
                .contentType(CONTENT_TYPE)
                .header(CONTENT_TYPE, TRANSACTION_CONTENT_TYPE)
                .body(body)
                .put(ENDPOINT + id);

        LOGGER.info("Send UPDATE Query --- Time: " + response.getTime() + " -- Status code: " + response.getStatusCode() +
                " -- Session ID: " + response.getSessionId());

        return response.getStatusCode();
    }

    @Step("I use DELETE request to clean and leave endpoint empty")
    public void deleteAllTransactions() {
        List<BankTransaction> transactionListResponse = getTransactionListFromService();
        if (transactionListResponse.size() > 0) {
            for (BankTransaction transaction : transactionListResponse) {
                response = sendDeleteQueryById(transaction.getId());
            }
        }
    }
}
