package com.agile.qdmp.standalong.model.dto.integrator;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: wenbinglei
 * @Date: 2022/11/23 18:07
 * @Description:
 */
@Data
@NoArgsConstructor
public class SampleCount {
    private static final long serialVersionUID = 1L;

    private String partGuid;
    private String jobGuid;
    private String lotGuid;
    private Integer status;

    private Integer total;
}
