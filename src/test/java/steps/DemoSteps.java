package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DemoSteps {

    private Response res;

    @When("I check the details of student {int}")
    public void iCheckTheDetailsOfStudent(int arg0) {
        RestAssured.baseURI = "https://it-foundations.app.ap.assurity.cloud/";
        RestAssured.defaultParser = Parser.JSON;
        given().contentType(ContentType.JSON);
        res = RestAssured.get("people/" + arg0);
        System.out.println(res.asPrettyString());
    }

    @Then("I can see that their name is {string}")
    public void iCanSeeThatTheirNameIsJaneJones(String arg0) {
//        String name = res.jsonPath().getJsonObject("firstName") + " " + res.jsonPath().getJsonObject("lastName");
        String fname = res.jsonPath().getJsonObject("firstName");
        String lname = res.jsonPath().getJsonObject("lastName");
        String name = fname + " " + lname;
        assertEquals(arg0, name);
        System.out.println(name);
    }

    @And("they have a {string} from {string}")
    public void theyHaveABScComputerScienceFromSydneyTechSchool(String arg0, String arg1) {
        String degree = res.jsonPath().getJsonObject("degree").toString();
        String university = res.jsonPath().getJsonObject("university").toString();
        assertEquals(arg0, degree);
        assertEquals(arg1, university);
        System.out.println(degree);
        System.out.println(university);
    }
}
