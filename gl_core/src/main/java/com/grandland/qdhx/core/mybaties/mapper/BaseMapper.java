package com.grandland.qdhx.core.mybaties.mapper;

import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.grandland.qdhx.core.mybaties.pojo.Example;
import com.grandland.qdhx.core.mybaties.pojo.MyBatisPojo;

/**
 * 数据库通用操作类
 * @param <T>
 * @author loverqi
 * @date 2018年1月9日
 */
@Mapper
public interface BaseMapper<T extends MyBatisPojo> {

    /**
     * 插入对象
     * @param record 需要插入的对象
     * @return 插入成功的条数
     */
    @InsertProvider(type = BaseTemplate.class, method = "insert")
    int insert(T record);

    /**
     *  插入对象，仅插入对象非空的属性
     * @param record 需要插入的对象
     * @return 插入成功的条数
     */
    @InsertProvider(type = BaseTemplate.class, method = "insertSelective")
    int insertSelective(T record);

    /**
     * 根据主键获取对象的方法
     * @param 对象id
     * @return 查询到的对象，查询不到返回null
     */
    @SelectProvider(type = BaseTemplate.class, method = "selectByPrimaryKey")
    T selectByPrimaryKey(T record);

    /**
     * 根据主键值获取对象的方法
     * @param tableName 表名
     * @param id 对象id
     * @return 查询到的对象，查询不到返回null
     */
    @SelectProvider(type = BaseTemplate.class, method = "selectByPrimaryKeyValue")
    T selectByPrimaryKeyValue(@Param("tableName") String tableName, @Param("id") Integer id);

    /**
     * 根据条件查询对象的方法
     * @param tableName 查询的表名
     * @param example 指定的条件
     * @return 所有符合条件的对象
     */
    @SelectProvider(type = BaseTemplate.class, method = "selectByValueExample")
    List<T> selectByValueExample(@Param("tableName") String tableName, @Param("example") Example example);

    /**
     * 根据条件查询页面数据个数的方法
     * @param tableName 查询的表名
     * @param example 指定的条件
     * @return 所有符合条件的对象
     */
    @SelectProvider(type = BaseTemplate.class, method = "selectCountByExample")
    int selectCountByExample(@Param("record") T record, @Param("example") Example example);

    /**
     * 根据条件查询页面数据个数的方法
     * @param tableName 查询的表名
     * @param example 指定的条件
     * @return 所有符合条件的对象
     */
    @SelectProvider(type = BaseTemplate.class, method = "selectCountByValueExample")
    int selectCountByValueExample(@Param("tableName") String tableName, @Param("example") Example example);

    /**
     * 根据条件查询对象的方法
     * @param record 查询的表名
     * @param example 指定的条件
     * @return 所有符合条件的对象
     */
    @SelectProvider(type = BaseTemplate.class, method = "selectByExample")
    List<T> selectByExample(@Param("record") T record, @Param("example") Example example);

    /**
     * 根据主键id更新对象的方法
     * @param record 修改后的对象
     * @return 修改的对象数量
     */
    @UpdateProvider(type = BaseTemplate.class, method = "updateByPrimaryKey")
    int updateByPrimaryKey(T record);

    /**
     * 根据主键id更新对象的方法,仅修改对象中的非空属性
     * @param record
     * @return 修改的对象数量
     */
    @UpdateProvider(type = BaseTemplate.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(T record);

    /**
     * 根据条件更新对象的方法
     * @param record 修改后的对象
     * @param example 更新的条件
     * @return 修改的对象数量
     */
    @UpdateProvider(type = BaseTemplate.class, method = "updateByExample")
    int updateByExample(@Param("record") T record, @Param("example") Example example);

    /**
     * 根据条件更新对象的方法，仅更新对象非空的属性
     * @param record 修改后的对象
     * @param example 更新的条件
     * @return 修改的对象数量
     */
    @UpdateProvider(type = BaseTemplate.class, method = "updateByExampleSelective")
    int updateByExampleSelective(@Param("record") T record, @Param("example") Example example);

    /**
     * 根据主键值和表名删除对象的方法
     * @param tableName 表名
     * @param id 对象的主键id
     * @return 删除的对象的个数
     */
    @UpdateProvider(type = BaseTemplate.class, method = "deleteByPrimaryKeyValue")
    int deleteByPrimaryKeyValue(@Param("tableName") String tableName, @Param("id") Integer id);

    /**
     * 根据主键删除对象的方法
     * @param id 对象的主键id
     * @return 删除的对象的个数
     */
    @DeleteProvider(type = BaseTemplate.class, method = "deleteByPrimaryKey")
    int deleteByPrimaryKey(T record);

    /**
     * 根据条件删除对象的方法
     * @param tableName 查询的表名
     * @param example 删除的条件
     * @return 删除的对象的个数
     */
    @DeleteProvider(type = BaseTemplate.class, method = "deleteByExample")
    int deleteByExample(@Param("record") T record, @Param("example") Example example);

    /**
     * 根据条件删除对象的方法
     * @param tableName 查询的表名
     * @param example 删除的条件
     * @return 删除的对象的个数
     */
    @DeleteProvider(type = BaseTemplate.class, method = "deleteByValueExample")
    int deleteByValueExample(@Param("tableName") String tableName, @Param("example") Example example);
}
