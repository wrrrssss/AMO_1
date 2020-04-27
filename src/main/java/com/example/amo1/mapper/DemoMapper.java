package com.example.amo1.mapper;

import com.example.amo1.model.Demo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * mapper示例代码
 * by wrs
 */
@Mapper
public interface DemoMapper {

    //增
    @Insert("insert into amo.demo(username,password) values (#{username},#{password})")
//    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void insert(Demo demo);

    //改
    @Update("update amo.demo set username = #{username},password = #{password} where id = #{id}")
    void update(Demo demo);

    //删
    @Delete("delete from amo.demo where id = #{id}")
    void delete(@Param("id") int id);

    //查
    @Select("select * from amo.demo")
    List<Demo> getAllDemo();

    @Select("select * from amo.demo where id = #{id}")
    Demo getDemoById(@Param("id") int id);
}
