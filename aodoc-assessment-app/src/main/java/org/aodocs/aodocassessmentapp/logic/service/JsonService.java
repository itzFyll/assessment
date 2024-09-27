package org.aodocs.aodocassessmentapp.logic.service;

import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import org.springframework.stereotype.Component;

@Component
public class JsonService {

    private JsonFactory jsonFactory;

    private JsonService() { jsonFactory = GsonFactory.getDefaultInstance(); }

    public JsonFactory getJsonFactory() { return jsonFactory; }
}
