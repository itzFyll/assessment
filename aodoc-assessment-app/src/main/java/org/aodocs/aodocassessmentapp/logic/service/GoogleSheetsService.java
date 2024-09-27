package org.aodocs.aodocassessmentapp.logic.service;

import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.services.sheets.v4.model.*;
import org.aodocs.aodocassessmentapp.model.dto.ContactDto;
import org.aodocs.aodocassessmentapp.logic.repository.GoogleSheetsRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service
public class GoogleSheetsService implements IGoogleSheetsService {
    private final Logger _logger = LoggerFactory.getLogger(GoogleSheetsService.class);

    final private GoogleSheetsRepo _sheetsRepo;

    @Autowired
    public GoogleSheetsService(GoogleSheetsRepo sheetsRepo) {
        _sheetsRepo = sheetsRepo;
    }

    public Request createDeleteDuplicatesByNameRequest() {
        return new Request().setDeleteDuplicates(new DeleteDuplicatesRequest()
                .setComparisonColumns(List.of(
                        new DimensionRange()
                                .setStartIndex(0) // Filter by Name column only
                                .setEndIndex(1)
                                .setDimension("COLUMNS"))));
    }

    public Request createAddContactAsRowRequest(ContactDto contact) {
        return new Request().setAppendCells(new AppendCellsRequest()
                .setRows(List.of(new RowData().setValues(contact.toCellData())))
                .setFields("*")
        );
    }

    public Request createAddConditionalFormatBackgroundPerColumnByExactValueRequest(int columnIndex, String valueCondition, Color backgroundColor) {
        int headerHeight = 1;

        GridRange columnRange = new GridRange()
                .setSheetId(0) // First sheet
                .setStartRowIndex(headerHeight) // Skip header
                .setStartColumnIndex(columnIndex) // Column Country
                .setEndColumnIndex(columnIndex + 1); // Limit to 1 column (exclusive)

        return createConditionalFormatRuleSetBackgroundColor(columnRange, valueCondition, backgroundColor);
    }

    // TODO make generic to work with Drive & Sheets (etc.)
    public void executeBatchUpdateRequest(String sheetId, BatchUpdateSpreadsheetRequest request) throws IOException {
        var batchUpdateRequest = _sheetsRepo.spreadsheets().batchUpdate(sheetId, request);
        executeGenericRequest(batchUpdateRequest);
        _logger.info("END");
    }

    // TODO error handling
    // TODO move to generic handler class
    private void executeGenericRequest(AbstractGoogleClientRequest request) throws IOException {
        try {
            request.execute();
        } catch (GoogleJsonResponseException e) {
            GoogleJsonError error = e.getDetails();
            if (error.getCode() == 404) {
                _logger.error("Spreadsheet not found with id {}");
            } else {
                throw e;
            }
        }
    }

    // TODO create generic rule builder composer
    /***
     * @param range Range to affect the rule
     * @param valueCondition Value to be equals (case sensitive)
     * @param backgroundColor Color to highlight the background of matched cells
     * @return
     */
    private Request createConditionalFormatRuleSetBackgroundColor(GridRange range, String valueCondition, Color backgroundColor) {
        return new Request().setAddConditionalFormatRule(new AddConditionalFormatRuleRequest()
                .setRule(new ConditionalFormatRule()
                        .setRanges(List.of(range))
                        .setBooleanRule(new BooleanRule()
                                .setCondition(new BooleanCondition()
                                        .setType("TEXT_EQ")
                                        .setValues(Collections.singletonList(
                                                new ConditionValue().setUserEnteredValue(valueCondition)
                                        ))
                                )
                                .setFormat(new CellFormat().setBackgroundColor(backgroundColor))
                        ))
                .setIndex(0));
    }
}
