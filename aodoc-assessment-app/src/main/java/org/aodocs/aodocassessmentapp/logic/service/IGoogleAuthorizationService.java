package org.aodocs.aodocassessmentapp.logic.service;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.http.javanet.NetHttpTransport;

import java.io.IOException;

public interface IGoogleAuthorizationService {
    Credential getCredentials(NetHttpTransport httpTransport) throws IOException;
}
