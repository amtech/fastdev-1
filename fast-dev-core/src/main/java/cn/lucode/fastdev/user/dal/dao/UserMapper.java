package cn.lucode.fastdev.user.dal.dao;

import cn.lucode.fastdev.user.dal.pojo.User;

public interface UserMapper {
    int deleteByPrimaryKey(String userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User  selectByLoginName(String loginName);

    User  selectByEmail(String email);
}