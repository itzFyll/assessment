package org.aodocs.aodocassessmentapp.model.dto;

import com.google.api.services.sheets.v4.model.CellData;
import com.google.api.services.sheets.v4.model.ExtendedValue;
import jakarta.validation.constraints.NotBlank;
import org.aodocs.aodocassessmentapp.model.annotation.PhoneValidator;

import java.util.Arrays;
import java.util.List;


public class ContactDto {
    @NotBlank
    private String name;

    @NotBlank
    private String region;

    @NotBlank
    private String country;

    @NotBlank
    @PhoneValidator
    private String phone;

    public ContactDto(String name, String region, String country, String phone) {
        this.name = name;
        this.region = region;
        this.country = country;
        this.phone = phone;
    }

    public @NotBlank String getName() {
        return name;
    }

    public void setName(@NotBlank String name) {
        this.name = name;
    }

    public @NotBlank String getRegion() {
        return region;
    }

    public void setRegion(@NotBlank String region) {
        this.region = region;
    }

    public @NotBlank String getCountry() {
        return country;
    }

    public void setCountry(@NotBlank String country) {
        this.country = country;
    }

    public @NotBlank @PhoneValidator String getPhone() {
        return phone;
    }

    public void setPhone(@NotBlank @PhoneValidator String phone) {
        this.phone = phone;
    }

    // TODO Move to Custom Google Serializer/Converter
    public List toCellData() {
        List<CellData> cellDatas = Arrays.asList(
                new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(name)),
                new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(region)),
                new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(country)),
                new CellData().setUserEnteredValue(new ExtendedValue().setStringValue(phone))
        );

        return cellDatas;
    }
}
