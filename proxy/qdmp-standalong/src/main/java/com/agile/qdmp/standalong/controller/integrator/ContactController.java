package com.agile.qdmp.standalong.controller.integrator;

import com.agile.qdmp.standalong.config.SysConfig;
import com.agile.qdmp.standalong.model.convert.integrator.ContactConverter;
import com.agile.qdmp.standalong.model.dto.integrator.ContactDTO;
import com.agile.qdmp.standalong.model.dto.integrator.query.ContactQueryDTO;
import com.agile.qdmp.standalong.model.entity.integrator.Contact;
import com.agile.qdmp.standalong.service.integrator.IContactService;
//import com.agile.qdmp.standalong.utils.OpenCvUtil;
import com.agile.qdmp.standalong.utils.Photo;
import com.agile.tem.common.core.controller.SuperController;
import com.agile.tem.common.core.controller.exception.BizException;
import com.agile.tem.common.core.util.R;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.Objects;


/**
 * Contacts 前端控制器
 *
 * @author hyzh code generator
 * @date 2022-12-02 11:15:44
 */
@Slf4j
@RestController
@RequestMapping("/api" )
@Api(value = "contact", tags = "Contacts管理")
public class ContactController extends SuperController {
    @Resource
    private SysConfig sysConfig;
    private final IContactService contactService;
    public ContactController(IContactService contactService) {
        this.contactService = contactService;
    }

    /**
     * 新增Contacts
     * @param contactDTO Contacts
     * @return
     */
    @ApiOperation(value = "新增Contacts", notes = "新增Contacts")
    @PostMapping("/contact")
    public R<ContactDTO> createContact(@RequestBody ContactDTO contactDTO) {
        log.debug("REST request to save Contact : {}", contactDTO);
        if (contactDTO.getId() != null) {
            throw new BizException("不能包含主键");
        }
        Contact data = ContactConverter.INSTANCE.from(contactDTO);
        boolean result = contactService.save(data);
        if(result) {
            contactDTO.setId(data.getId());
            return R.ok(contactDTO, "添加成功");
        } else {
            return R.failed(contactDTO, "添加失败");
        }
    }

    /**
     * 修改Contacts
     * @param contactDTO Contacts
     * @return
     */
    @ApiOperation(value = "修改Contacts", notes = "修改Contacts")
    @PutMapping("/contact")
    public R<ContactDTO> updateContact(@RequestBody ContactDTO contactDTO) {
        log.debug("REST request to update Contact : {}", contactDTO);
        if (contactDTO.getId() == null) {
            throw new BizException("缺少主键");
        }
        if(StringUtils.isNotBlank(contactDTO.getFace())) {
            String faceUrl = generateImage(sysConfig.getFileDirectory() + "face", contactDTO.getFace());
            if(StringUtils.isNotBlank(faceUrl)) {
                contactDTO.setFace(faceUrl);
            }
        }
        log.info("contactDTO {}", JSONObject.toJSONString(contactDTO, true));
        Contact data = ContactConverter.INSTANCE.from(contactDTO);
        return contactService.updateById(data) ? R.ok(contactDTO, "修改成功") : R.failed(contactDTO, "修改失败");
    }

    /**
     * 根据查询条件获取分页数据
     * @param queryDTO Contacts
     * @return
     */
    @ApiOperation(value = "分页查询Contacts", notes = "分页查询Contacts")
    // @JsonFieldFilter(type = ContactDTO.class, include = {"id", ""})
    // @JsonFieldFilter(type = ContactDTO.class, exclude = {"id", ""})
    // @JsonFieldFilter(type = IPage.class, exclude = {"optimizeCountSql", "hitCount", "orders", "searchCount"})
    // @JsonFieldFilters( value = {
    //     @JsonFieldFilter(type = ContactDTO.class, include = {"id", "username", "mobile"}),
    //     @JsonFieldFilter(type = ContactDTO.class, include = {"id", "username", "mobile"})
    // })
    // @Desensitization
    @GetMapping("/contacts")
    public R<IPage<ContactDTO>> getAllContacts(ContactQueryDTO queryDTO) {
        log.debug("REST request to get a page of Contacts");
        String[] ascParameters = null;
        String[] descParameters = null;
        if(StringUtils.isNotBlank(queryDTO.getAscParameter())) {
            ascParameters = new String[] {queryDTO.getAscParameter()};
        }
        if(StringUtils.isNotBlank(queryDTO.getDescParameter())) {
            descParameters = new String[] {queryDTO.getDescParameter()};
        }
        IPage<ContactDTO> result = contactService.lambdaQuery()
                .like(StringUtils.isNotEmpty(queryDTO.getKeyWord()), Contact::getFirstName, queryDTO.getKeyWord())
                .eq(StringUtils.isNotEmpty(queryDTO.getFirstName()), Contact::getFirstName, queryDTO.getFirstName())
                .eq(StringUtils.isNotEmpty(queryDTO.getLastName()), Contact::getLastName, queryDTO.getLastName())
                .eq(StringUtils.isNotEmpty(queryDTO.getInspCenterGuid()), Contact::getInspCenterGuid, queryDTO.getInspCenterGuid())
                .eq(StringUtils.isNotEmpty(queryDTO.getCompanyGuid()), Contact::getCompanyGuid, queryDTO.getCompanyGuid())
                .eq(Objects.nonNull(queryDTO.getServerId()), Contact::getServerId, queryDTO.getServerId())
                .page(this.<Contact>getPage(queryDTO.getCursor(), queryDTO.getLimit(), queryDTO.getSearchCount(), ascParameters, descParameters))
                .convert(dto -> ContactConverter.INSTANCE.to(dto));
        return R.ok(result, "查询列表成功");
    }

    /**
     * 根据ID获取Contacts
     * @param id id
     * @return
     */
    @ApiOperation(value = "通过id查询Contacts", notes = "通过id查询Contacts")
    // @JsonFieldFilter(type = ContactDTO.class, include = {"id", ""})
    // @JsonFieldFilter(type = ContactDTO.class, exclude = {"id", ""})
    // @JsonFieldFilters( value = {
    //     @JsonFieldFilter(type = ContactDTO.class, include = {"id", "username", "mobile"}),
    //     @JsonFieldFilter(type = ContactDTO.class, include = {"id", "username", "mobile"})
    // })
    // @Desensitization
    @GetMapping("/contact/{id}")
    public R<ContactDTO> getContact(@PathVariable Long id) {
        log.debug("REST request to get Contact : {}", id);
        Contact contact = contactService.getById(id);
        return contact == null ? R.failed("未查询到数据") : R.ok(ContactConverter.INSTANCE.to(contact), "查询成功");
    }

    /**
     * 根据Username获取Contact
     * @param username
     * @return
     */
    @ApiOperation(value = "根据Username获取Contact", notes = "根据Username获取Contact")
    @GetMapping("/contact/username/{username}")
    public R<ContactDTO> getContactByUsername(@PathVariable String username) {
        log.debug("REST request to get Contact : {}", username);
        List<Contact> contacts = contactService.lambdaQuery().eq(Contact::getUsername, username).list();
        return contacts != null && contacts.size() > 0 ? R.ok(ContactConverter.INSTANCE.to(contacts.get(0)), "查询成功") :R.failed("未查询到数据");
    }

    /**
     * 通过id删除Contacts
     * @param id id
     * @return
     */
    @ApiOperation(value = "通过id删除Contacts", notes = "通过id删除Contacts")
    @DeleteMapping("/contact/{id}")
    public R deleteContact(@PathVariable Long id) {
        log.debug("REST request to delete Contacts : {}", id);
        return contactService.removeById(id) ? R.ok("删除成功") : R.failed("删除失败");
    }

    /**
     * 人脸对比
     * @param contactDTO
     * @return
     */
    @ApiOperation(value = "人脸对比", notes = "人脸对比")
    @PostMapping("/open/photo/detect")
    public R<ContactDTO> detect(@RequestBody ContactDTO contactDTO){
//        log.debug("REST request to upload photo : {}", contactDTO);
//        if(StringUtils.isBlank(contactDTO.getFace())){
//            return R.failed("缺少面部底图");
//        }
//
//        String dirPath = System.getProperty("java.io.tmpdir") + File.separator + "face";
//        String targetPath = generateImage(dirPath, contactDTO.getFace());
//
//        if(StringUtils.isBlank(targetPath)) {
//            return R.failed("照片对比失败，请重试");
//        }
//
//        Contact res = null;
//        Double score = 0d;
//        List<Contact> contactList = contactService.lambdaQuery().select(Contact::getId, Contact::getUsername, Contact::getPassword, Contact::getFace).list();
//        for(Contact contact : contactList) {
//            if(StringUtils.isBlank(contact.getFace())) {
//                continue;
//            }
//            Double val = OpenCvUtil.match(dirPath + File.separator +targetPath, sysConfig.getFileDirectory() + "face" + File.separator + contact.getFace());
//            if(val > score) {
//                res = contact;
//                score = val;
//            }
//        }
//        return res == null ? R.failed("未找到照片对应人员") : R.ok(ContactConverter.INSTANCE.to(res));
        return R.failed("该功能未启用");
    }


    /**
     * 对字节数组字符串进行Base64解码并生成图片
     * @param base64str
     * @return
     */
    private String generateImage(String dir, String base64str) {
        Path dirPath = Paths.get(dir);
        String fileName = ((int) System.currentTimeMillis() / 1000) + ".png";
        if(Files.notExists(dirPath)) {
            try {
                Files.createDirectory(dirPath);
            } catch (IOException e) {
                log.error(e.getMessage());
                return null;
            }
        }

        String savePath = dirPath + File.separator + fileName;
        Base64.Decoder decoder = Base64.getDecoder();
        try(OutputStream out = new FileOutputStream(savePath)) {
            //Base64解码
            byte[] b = decoder.decode(base64str);
            for(int i=0;i<b.length;++i) {
                //调整异常数据
                if(b[i]<0) {
                    b[i]+=256;
                }
            }
            out.write(b);
            out.flush();
            out.close();
            return fileName;
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }
}
