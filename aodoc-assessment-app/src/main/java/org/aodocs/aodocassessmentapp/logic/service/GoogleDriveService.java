package org.aodocs.aodocassessmentapp.logic.service;

import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.Permission;
import org.aodocs.aodocassessmentapp.logic.repository.GoogleDriveRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class GoogleDriveService implements IGoogleDriveService {
    private final Logger _logger = LoggerFactory.getLogger(GoogleDriveService.class);

    final private GoogleDriveRepo _driveRepo;

    @Autowired
    public GoogleDriveService(GoogleDriveRepo driveRepo) {
        _driveRepo = driveRepo;
    }

    /***
     * @param sheetId of the sheet we want to copy
     * @return The new copied File with its ID
     * @throws IOException
     */
    public File copySheet(String sheetId) throws IOException {
        var copiedFile = _driveRepo.files().copy(sheetId, null).execute();

        _logger.info("File copied with ID: {}", copiedFile.getId());

        return copiedFile;
    }

    /***
     * @param fileId of the file we want to share using permissions
     * @return The new created & added permission
     * @throws IOException
     */
    public Permission shareByEmailPermission(String fileId, String email) throws IOException {
        if(email == null || email.isBlank()) {
            _logger.warn("Sharing File by Email error: email is empty");
            return null;
        } else if (fileId == null || fileId.isBlank()) {
            _logger.warn("Sharing File by Email error: fileId is empty");
            return null;
        }

        _logger.info("Sharing File: {} , to Email: {} , with permission writer", fileId, email);

        var shareByEmailPermission = new Permission()
                .setType("user")
                .setEmailAddress(email)
                .setRole("writer");

        return _driveRepo.permissions().create(fileId, shareByEmailPermission).execute();
    }
}
