package com.gy.system.feign.factory;

import org.springframework.stereotype.Component;

import com.gy.system.domain.SysRole;
import com.gy.system.feign.RemoteRoleService;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RemoteRoleFallbackFactory implements FallbackFactory<RemoteRoleService>
{/* (non-Javadoc)
  * @see feign.hystrix.FallbackFactory#create(java.lang.Throwable)
  */
    @Override
    public RemoteRoleService create(Throwable throwable)
    {
        log.error(throwable.getMessage());
        return new RemoteRoleService()
        {
            @Override
            public SysRole selectSysRoleByRoleId(long roleId)
            {
                return null;
            }
        };
    }
}
