package com.grandland.qdhx.web.service.base;

import java.util.List;

import com.grandland.qdhx.core.bean.ResponsePageDate;
import com.grandland.qdhx.core.mybaties.pojo.Example;
import com.grandland.qdhx.core.mybaties.pojo.MyBatisPojo;

/**
 * 通用操作接口
 * @param <T>
 * @author loverqi
 * @date 2018年1月19日
 */
public interface BaseService<T extends MyBatisPojo> {

    /**
     * 插入对象
     * @param record 需要插入的对象
     * @return 插入成功的条数
     */
    int insert(T record);

    /**
     *  插入对象，仅插入对象非空的属性
     * @param record 需要插入的对象
     * @return 插入成功的条数
     */
    int insertSelective(T record);

    /**
     * 根据主键获取对象的方法
     * @param 对象
     * @return 查询到的对象，查询不到返回null
     */
    T selectByPrimaryKey(T record);

    /**
     * 根据主键值获取对象的方法
     * @param 对象
     * @return 查询到的对象，查询不到返回null
     */
    T selectByPrimaryKey(String tableName, Integer id);

    /**
     * 根据条件查询对象的方法
     * @param example 指定的条件
     * @return 所有符合条件的对象
     */
    List<T> selectByExample(String tableName, Example example);

    /**
     * 根据条件查询对象的方法
     * @param example 指定的条件
     * @return 所有符合条件的对象
     */
    List<T> selectByExample(T t, Example example);

    /**
     * 根据条件查询对象的方法, 支持分页
     * @param example 指定的条件
     * @return 所有符合条件的对象
     */
    ResponsePageDate<T> selectByExampleWithRowbounds(T record, Example example, int page, int pageSize);

    /**
     * 根据条件查询对象的方法, 支持分页
     * @param example 指定的条件
     * @return 所有符合条件的对象
     */
    ResponsePageDate<T> selectByExampleWithRowbounds(String tableName, Example example, int page, int pageSize);

    /**
     * 根据主键id更新对象的方法
     * @param record 修改后的对象
     * @return 修改的对象数量
     */
    int updateByPrimaryKey(T record);

    /**
     * 根据主键id更新对象的方法,仅修改对象中的非空属性
     * @param record
     * @return 修改的对象数量
     */
    int updateByPrimaryKeySelective(T record);

    /**
     * 根据条件更新对象的方法
     * @param record 修改后的对象
     * @param example 更新的条件
     * @return 修改的对象数量
     */
    int updateByExample(T record, Example example);

    /**
     * 根据条件更新对象的方法，仅更新对象非空的属性
     * @param record 修改后的对象
     * @param example 更新的条件
     * @return 修改的对象数量
     */
    int updateByExampleSelective(T record, Example example);

    /**
     * 根据主键删除对象的方法
     * @param id 对象的主键id
     * @return 删除的对象的个数
     */
    int deleteByPrimaryKey(T record);

    /**
     * 根据主键值和表名删除对象的方法
     * @param tableName 表名
     * @param id 对象的主键id
     * @return 删除的对象的个数
     */
    int deleteByPrimaryKey(String tableName, Integer id);

    /**
     * 根据条件删除对象的方法
     * @param example 删除的条件
     * @return 删除的对象的个数
     */
    int deleteByExample(T t, Example example);

    /**
     * 根据条件删除对象的方法
     * @param example 删除的条件
     * @return 删除的对象的个数
     */
    int deleteByExample(String tableName, Example example);

    /**
     * 根据条件查询页面数据个数的方法
     * @param tableName 查询的表名
     * @param example 指定的条件
     * @return 所有符合条件的对象
     */
    int selectCountByExample(String tableName, Example example);

    /**
     * 根据条件查询页面数据个数的方法
     * @param tableName 查询的表名
     * @param example 指定的条件
     * @return 所有符合条件的对象
     */
    int selectCountByExample(T t, Example example);

}
