package com.agile.qdmp.standalong.tool.bean.result;

import lombok.Data;

import java.io.Serializable;

@Data
public class WxMinishopPicFileResult implements Serializable {
  private String mediaId;
  private String payMediaId;
  private String tempImgUrl;
}
