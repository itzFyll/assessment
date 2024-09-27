package org.aodocs.aodocassessmentapp.logic.service;

import com.google.api.services.sheets.v4.model.BatchUpdateSpreadsheetRequest;
import com.google.api.services.sheets.v4.model.Request;
import org.aodocs.aodocassessmentapp.model.dto.ContactDto;
import org.aodocs.aodocassessmentapp.model.dto.EmailDto;
import org.aodocs.aodocassessmentapp.utils.ColorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class TestSheetSequenceService {
    private final Logger _logger = LoggerFactory.getLogger(TestSheetSequenceService.class);

    private final GoogleDriveService driveService;
    private final GoogleSheetsService sheetsService;

    private final ContactDto _contactDto = new ContactDto("AODocs", "California", "United States", "(310) 310-1234");

    @Autowired
    public TestSheetSequenceService(
            GoogleDriveService googleDriveService,
            GoogleSheetsService googleSheetsService) {
        driveService = googleDriveService;
        sheetsService = googleSheetsService;
    }

    public void testSequence(EmailDto email) throws IOException {
        final String originalSheetId = "1hLgSCS0VbEbkkhkeFLFdzzUrs4nD9eJ5KFHBVJZmD1I";
        var newSheetId = driveService.copySheet(originalSheetId).getId();
        var formatWhereCountryIsUSRequest = sheetsService.createAddConditionalFormatBackgroundPerColumnByExactValueRequest(2, "United States", ColorUtil.colorFromHex(0xA4C3E7));
        var formatWhereCountryIsCanadaRequest = sheetsService.createAddConditionalFormatBackgroundPerColumnByExactValueRequest(2, "Canada", ColorUtil.colorFromHex(0xDC6A67));
        var addContactAsRowRequest = sheetsService.createAddContactAsRowRequest(_contactDto);
        var deleteDuplicatesByNameRequest = sheetsService.createDeleteDuplicatesByNameRequest();

        List<Request> batchRequests = Arrays.asList(formatWhereCountryIsUSRequest, formatWhereCountryIsCanadaRequest, addContactAsRowRequest, deleteDuplicatesByNameRequest);
        BatchUpdateSpreadsheetRequest batchRequest = new BatchUpdateSpreadsheetRequest().setRequests(batchRequests);
        sheetsService.executeBatchUpdateRequest(newSheetId, batchRequest);

        if(email != null && email.getEmail() != null && !email.getEmail().isBlank()) {
            _logger.info("Sharing file by email");
            driveService.shareByEmailPermission(newSheetId, email.getEmail());
        }
        _logger.info("Testing sequence completed successfully");
    }
}
