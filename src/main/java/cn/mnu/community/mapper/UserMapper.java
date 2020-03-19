package cn.mnu.community.mapper;


import cn.mnu.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    @Insert()
    public void insert(User user);
}
