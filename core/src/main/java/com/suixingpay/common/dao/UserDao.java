package com.suixingpay.common.dao;



import com.suixingpay.common.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserDao {

    String TABLE_NAME="user";//将需要更改的库表定义为一个变量。
    String INSERT_FIELDS="name,password,account,status,modify_date,create_date,is_delete";//将需要加入的字段定义为一个变量
    String SELECT_FIELDS="id,"+INSERT_FIELDS;

    @Insert({"insert into" , TABLE_NAME," (" , INSERT_FIELDS,") values (#{name},#{password},#{account},#{modifyDate}" +
            ",#{createDate},#{isDelete})"})
    int addUser(User user);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where id=#{id}"})
    User userById(int id);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where name=#{name}"})
    User userByname(String name);



    @Update({"update", TABLE_NAME, " set password=#{password} where id=#{id}"})
    void setUserPassword(User user);

    @Delete({"update",TABLE_NAME,"set is_delete=#{is_delete} where id=#{id}"})
    void deleteById(int id);
}
