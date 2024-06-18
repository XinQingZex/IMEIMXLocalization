package com.agile.qdmp.standalong.model.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * API
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ApiEnum implements IEnum<String> {
    PART("Parts", "/api/parts/list"),
    JOB("Jobs", "/api/jobs/list"),
    LOT("Lots", "/api/lots/list"),
    SAMPLE("Samples", "/api/samples/list"),
    DIM("Dims", "/api/dims/list"),
    RESULT("Results", "/api/results/list"),
    FAILED_RESULT("Results", "/api/results/getfailed"),
    COMPANY("Companies", "/api/companies/list"),
    DRAWING("Drawings", "/api/drawings/list"),
    GAGE("Gages", "/api/gages/list"),
    RECEIVER("Receivers", "/api/receivers/list"),
    OPERATION("Operations", "/api/operations/list"),
    NCR("NCRs", "/api/ncr/list"),
    NCR_RESULT("NCResults", "/api/ncr/getresults"),
    CONTACT("Contacts", "/api/contacts/list"),
    PROCEDURE("Procedures", "/api/procedures/list"),
    INSPCENTER("InspCenters", "/api/inspcenters/list");

    private String value;
    private String desc;

    ApiEnum(final String value, final String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @JsonValue
    public String getDesc(){
        return this.desc;
    }

    public static ApiEnum getByDesc(String desc) {
        ApiEnum[] enums = values();
        for (ApiEnum apiEnum : enums) {
            if (apiEnum.desc.equalsIgnoreCase(desc)) {
                return apiEnum;
            }
        }
        return null;
    }

}
