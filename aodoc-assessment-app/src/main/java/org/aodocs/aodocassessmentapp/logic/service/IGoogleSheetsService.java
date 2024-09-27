package org.aodocs.aodocassessmentapp.logic.service;

import com.google.api.services.sheets.v4.model.BatchUpdateSpreadsheetRequest;
import com.google.api.services.sheets.v4.model.Color;
import com.google.api.services.sheets.v4.model.Request;
import org.aodocs.aodocassessmentapp.model.dto.ContactDto;

import java.io.IOException;

public interface IGoogleSheetsService {
    void executeBatchUpdateRequest(String sheetId, BatchUpdateSpreadsheetRequest request) throws IOException;
    Request createDeleteDuplicatesByNameRequest();
    Request createAddContactAsRowRequest(ContactDto contact);
    Request createAddConditionalFormatBackgroundPerColumnByExactValueRequest(int columnIndex, String valueCondition, Color backgroundColor);
}
