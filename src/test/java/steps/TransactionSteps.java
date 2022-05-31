package steps;

import base.model.BankTransaction;
import base.services.TransactionService;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import net.thucydides.core.annotations.Steps;

import java.util.List;
import java.util.Map;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.assertj.core.api.Assertions.assertThat;

public class TransactionSteps {

    @Steps
    TransactionService transactionService;

    @Given("I get the response from the endpoint")
    public void iGetTheResponseFromTheEndpoint() {
        transactionService.sendRequestByGet();
    }

    @When("I clean the endpoint")
    public void iCleanTheEndpoint() {
        transactionService.deleteAllTransactions();
    }

    @When("I create {int} new Transactions")
    public void iCreateNewTransactions(int number) {
        for (int i = 1; i < number + 1; i++) {
            BankTransaction transactionBody = new BankTransaction();
            transactionBody.setName("userName" + i);
            transactionBody.setLastName("userLastName" + i);
            transactionBody.setAccountNumber((123 * i ) + i);
            transactionBody.setAmount((9128 * i) + i);
            transactionBody.setType("withdrawal");
            transactionBody.setEmail("testUser" + i + "@test.com");
            transactionBody.setActive(true);
            transactionBody.setCountry("Colombia");
            transactionBody.setTelephone("+57 312-312-0751 ext" + i);

            transactionService.sendPostQueryWithBody(transactionBody);
            assertThat(transactionService.getLastCreatedTransaction().getEmail()).
                    isEqualTo("testUser" + i + "@test.com");
        }
    }

    @Then("I verify not repeated emails")
    public void iVerifyNotRepeatedEmails() {
        List<BankTransaction> transactionList = transactionService.getTransactionListFromService();
        for (int i = 0; i < transactionList.size()-1; i++){
            for (int k = i+1; k < transactionList.size(); k++){
                assertThat(transactionList.get(i)).isNotEqualTo(transactionList.get(k));
            }
        }
    }

    @Then("I get a total number of transactions equal to {}")
    public void iGetATotalNumberOfTransactionsEqualTo(int number) {
        List<BankTransaction> transaction_list = transactionService.getTransactionListFromService();
        assertThat(transaction_list.size()).isEqualTo(number);
    }

    @Then("I verify the endpoint is empty")
    public void iVerifyTheEndpointIsEmpty() {
        assertThat(transactionService.getTransactionListFromService().isEmpty()).isTrue();
    }

    @Then("I get the response code equals to {}")
    public void iGetTheResponseCodeEqualsTo(int responseCode) {
        restAssuredThat(response -> response.statusCode(responseCode));
    }

    @Then("I UPDATE the Transaction by id with information")
    public void iUpdateTheUserByIdWithInformation(DataTable dataTable) {
        BankTransaction transactionBody = new BankTransaction();
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> columns : rows) {
            transactionBody.setName(columns.get("name"));
            transactionBody.setEmail(columns.get("email"));
            transactionBody.setActive(Boolean.parseBoolean(columns.get("active")));
            transactionBody.setId(columns.get("id"));

            assertThat(transactionService.updateTransactionById(transactionBody, Integer.parseInt(columns.get("id")))).isEqualTo(200);

        }
    }
}
