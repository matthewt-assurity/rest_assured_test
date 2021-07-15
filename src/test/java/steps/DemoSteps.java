package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DemoSteps {

    private Response res;

    @When("I check the details of student {int}")
    public void iCheckTheDetailsOfStudent(int arg0) {
        RestAssured.baseURI = "https://it-foundations.app.ap.assurity.cloud/";
        RestAssured.defaultParser = Parser.JSON;
        res = RestAssured.get("people/" + arg0);
        System.out.println(res.asPrettyString());
    }

    @Then("I can see that their name is {string}")
    public void iCanSeeThatTheirNameIsJaneJones(String arg0) {
        String name = res.path("firstName") + " " + res.path("lastName");
        assertEquals(arg0, name);
        System.out.println(name);
    }

    @And("they have a {string} from {string}")
    public void theyHaveABScComputerScienceFromSydneyTechSchool(String arg0, String arg1) {
        String degree = res.path("degree").toString();
        String university = res.path("university").toString();
        assertEquals(arg0, degree);
        assertEquals(arg1, university);
        System.out.println(degree);
        System.out.println(university);
    }
}
