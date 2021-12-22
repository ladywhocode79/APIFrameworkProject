package stepDefinations;

import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {
    @Before("@DeletePlace")
    public void beforeScenario() throws IOException {
        stepDefinations objStepDefination = new stepDefinations();

        //if place_id is null then only we need to create addplace becuase we are running
        //delete api independent of addplaceapi
        if(stepDefinations.place_id == null)
        {
        objStepDefination.add_place_payload_with("Sonal","English","Asia");
        objStepDefination.user_calls_with_http_request("AddPlaceAPI","POST");
        objStepDefination.verify_place_id_created_maps_to_using("Sonal","getPlaceAPI");
        }

    }
}
