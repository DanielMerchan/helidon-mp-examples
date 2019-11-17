package io.helidon.examples.quickstart.mp;

import java.util.Collections;
import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.metrics.annotation.Counted;

@Path("/cards")
@RequestScoped
public class GreetingCards {

  private static final JsonBuilderFactory JSON = Json.createBuilderFactory(Collections.emptyMap());

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Counted(name = "cardCount", absolute = true)
  public JsonObject anyCard() throws InterruptedException {
    return createResponse("Here are some random cards ...");
  }

  private JsonObject createResponse(String msg) {
    return JSON.createObjectBuilder().add("message", msg).build();
  }
}