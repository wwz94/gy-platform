package com.gy.system.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.gy.common.constant.ServiceNameConstants;
import com.gy.system.domain.SysRole;
import com.gy.system.feign.factory.RemoteRoleFallbackFactory;

/**
 * 角色 Feign服务层
 * 
 * @author zmr
 * @date 2019-05-20
 */
@FeignClient(name = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteRoleFallbackFactory.class)
public interface RemoteRoleService
{
    @GetMapping("role/get/{roleId}")
    public SysRole selectSysRoleByRoleId(@PathVariable("roleId") long roleId);
}
