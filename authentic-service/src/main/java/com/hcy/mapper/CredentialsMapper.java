package com.hcy.mapper;

import com.hcy.pojo.Credentials;
import org.apache.ibatis.annotations.Param;

/**
 * Description：
 * Author: 黄成勇
 * Date:  2022/2/13 19:04
 */
public interface CredentialsMapper {
    Credentials findCredentialsByName(@Param("name") String name);
}
