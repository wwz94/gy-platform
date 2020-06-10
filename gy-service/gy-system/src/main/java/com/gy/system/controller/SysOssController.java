package com.gy.system.controller;

import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.gy.common.auth.annotation.HasPermissions;
import com.gy.common.core.controller.BaseController;
import com.gy.common.core.domain.R;
import com.gy.common.exception.file.OssException;
import com.gy.common.utils.ValidatorUtils;
import com.gy.system.domain.SysOss;
import com.gy.system.oss.CloudConstant;
import com.gy.system.oss.CloudConstant.CloudService;
import com.gy.system.oss.CloudStorageConfig;
import com.gy.system.oss.CloudStorageService;
import com.gy.system.oss.OSSFactory;
import com.gy.system.oss.valdator.AliyunGroup;
import com.gy.system.oss.valdator.QcloudGroup;
import com.gy.system.oss.valdator.QiniuGroup;
import com.gy.system.service.ISysConfigService;
import com.gy.system.service.ISysOssService;

/**
 * 文件上传 提供者
 * 
 * @author zmr
 * @date 2019-05-16
 */
@RestController
@RequestMapping("oss")
public class SysOssController extends BaseController
{
    private final static String KEY = CloudConstant.CLOUD_STORAGE_CONFIG_KEY;

    @Autowired
    private ISysOssService      sysOssService;

    @Autowired
    private ISysConfigService   sysConfigService;

    /**
     * 云存储配置信息
     */
    @RequestMapping("config")
    @HasPermissions("sys:oss:config")
    public CloudStorageConfig config()
    {
        String jsonconfig = sysConfigService.selectConfigByKey(CloudConstant.CLOUD_STORAGE_CONFIG_KEY);
        // 获取云存储配置信息
        CloudStorageConfig config = JSON.parseObject(jsonconfig, CloudStorageConfig.class);
        return config;
    }

    /**
     * 保存云存储配置信息
     */
    @RequestMapping("saveConfig")
    @HasPermissions("sys:oss:config")
    public R saveConfig(CloudStorageConfig config)
    {
        // 校验类型
        ValidatorUtils.validateEntity(config);
        if (config.getType() == CloudService.QINIU.getValue())
        {
            // 校验七牛数据
            ValidatorUtils.validateEntity(config, QiniuGroup.class);
        }
        else if (config.getType() == CloudService.ALIYUN.getValue())
        {
            // 校验阿里云数据
            ValidatorUtils.validateEntity(config, AliyunGroup.class);
        }
        else if (config.getType() == CloudService.QCLOUD.getValue())
        {
            // 校验腾讯云数据
            ValidatorUtils.validateEntity(config, QcloudGroup.class);
        }
        return toAjax(sysConfigService.updateValueByKey(KEY, new Gson().toJson(config)));
    }

    /**
     * 查询文件上传
     */
    @GetMapping("get/{id}")
    public SysOss get(@PathVariable("id") Long id)
    {
        return sysOssService.selectSysOssById(id);
    }

    /**
     * 查询文件上传列表
     */
    @GetMapping("list")
    public R list(SysOss sysOss)
    {
        startPage();
        return result(sysOssService.selectSysOssList(sysOss));
    }

    /**
     * 修改
     */
    @PostMapping("update")
    @HasPermissions("sys:oss:edit")
    public R editSave(@RequestBody SysOss sysOss)
    {
        return toAjax(sysOssService.updateSysOss(sysOss));
    }

    /**
     * 修改保存文件上传
     * @throws IOException 
     */
    @PostMapping("upload")
    @HasPermissions("sys:oss:add")
    public R editSave(@RequestParam("file") MultipartFile file) throws IOException
    {
        if (file.isEmpty())
        {
            throw new OssException("上传文件不能为空");
        }
        // 上传文件
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        CloudStorageService storage = OSSFactory.build();
        String url = storage.uploadSuffix(file.getBytes(), suffix);
        // 保存文件信息
        SysOss ossEntity = new SysOss();
        ossEntity.setUrl(url);
        ossEntity.setFileSuffix(suffix);
        ossEntity.setCreateBy(getLoginName());
        ossEntity.setFileName(fileName);
        ossEntity.setCreateTime(new Date());
        ossEntity.setService(storage.getService());
        return toAjax(sysOssService.insertSysOss(ossEntity));
    }

    /**
     * 删除文件上传
     */
    @PostMapping("remove")
    @HasPermissions("sys:oss:remove")
    public R remove(String ids)
    {
        return toAjax(sysOssService.deleteSysOssByIds(ids));
    }
}
