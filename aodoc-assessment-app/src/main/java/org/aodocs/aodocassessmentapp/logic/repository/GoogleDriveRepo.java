package org.aodocs.aodocassessmentapp.logic.repository;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.services.drive.Drive;
import org.aodocs.aodocassessmentapp.logic.service.GoogleAuthorizationService;
import org.aodocs.aodocassessmentapp.logic.service.JsonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;

@Service
public class GoogleDriveRepo extends Drive {
    private final Logger _logger = LoggerFactory.getLogger(GoogleDriveRepo.class);

    @Autowired
    public GoogleDriveRepo(GoogleAuthorizationService authenticatorService,
                           JsonService jsonService) throws GeneralSecurityException, IOException {
        this(authenticatorService, jsonService, GoogleNetHttpTransport.newTrustedTransport());
    }

    private GoogleDriveRepo(GoogleAuthorizationService authenticatorService,
                            JsonService jsonService,
                            NetHttpTransport httpTransport) throws IOException {
        super(httpTransport, jsonService.getJsonFactory(), authenticatorService.getCredentials(httpTransport));
    }
}
