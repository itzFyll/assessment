package org.aodocs.aodocassessmentapp.logic.service;

import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.Permission;

import java.io.IOException;

public interface IGoogleDriveService {
    File copySheet(String sheetId) throws IOException;
    Permission shareByEmailPermission(String fileId, String email) throws IOException;
}
