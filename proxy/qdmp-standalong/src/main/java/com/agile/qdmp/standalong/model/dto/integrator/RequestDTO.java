package com.agile.qdmp.standalong.model.dto.integrator;

import com.agile.tem.common.core.model.BaseDTO;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 用户DTO
 * </p>
 *
 * @author Caratacus
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RequestDTO extends BaseDTO {

    private static final long serialVersionUID = 1L;

    /**
     * token
     */
    @ApiModelProperty(value = "token")
    private String token;

    /**
     * server url
     */
    @ApiModelProperty(value = "server url")
    private String serverUri;

    /**
     * api url
     */
    @ApiModelProperty(value = "api url")
    private String apiUri;

    /**
     * api version
     */
    @ApiModelProperty(value = "api version")
    private String apiVersion;

    /**
     * type
     */
    @ApiModelProperty(value = "type")
    private String type;
    /**
     * realTime
     */
    @ApiModelProperty(value = "realTime")
    private Boolean realTime;
    /**
     * rtKey
     */
    @ApiModelProperty(value = "rtKey")
    private String rtKey;

    /**
     * postData
     */
    @ApiModelProperty(value = "post data")
    private JSONObject data;

    /**
     * 企业id
     */
    @ApiModelProperty(value = "企业id")
    private Long companyId;
    /**
     * 企业名称
     */
    @ApiModelProperty(value = "企业名称")
    private String companyName;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private Long accountId;

    /**
     * 职员id
     */
    @ApiModelProperty(value = "职员id")
    private Long staffId;
    /**
     * 职员名称
     */
    @ApiModelProperty(value = "职员名称")
    private String staffName;

    /**
     * IM Server id
     */
    @ApiModelProperty(value = "IM Server id")
    private Long serverId;
    /**
     * IM Server 名称
     */
    @ApiModelProperty(value = "IM Server 名称")
    private String serverName;

}
