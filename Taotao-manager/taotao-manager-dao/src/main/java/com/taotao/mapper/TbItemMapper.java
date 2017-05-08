package com.taotao.mapper;

import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbItemMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_item
     *
     * @mbg.generated Fri Apr 21 12:00:51 CST 2017
     */
    long countByExample(TbItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_item
     *
     * @mbg.generated Fri Apr 21 12:00:51 CST 2017
     */
    int deleteByExample(TbItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_item
     *
     * @mbg.generated Fri Apr 21 12:00:51 CST 2017
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_item
     *
     * @mbg.generated Fri Apr 21 12:00:51 CST 2017
     */
    int insert(TbItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_item
     *
     * @mbg.generated Fri Apr 21 12:00:51 CST 2017
     */
    int insertSelective(TbItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_item
     *
     * @mbg.generated Fri Apr 21 12:00:51 CST 2017
     */
    List<TbItem> selectByExample(TbItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_item
     *
     * @mbg.generated Fri Apr 21 12:00:51 CST 2017
     */
    TbItem selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_item
     *
     * @mbg.generated Fri Apr 21 12:00:51 CST 2017
     */
    int updateByExampleSelective(@Param("record") TbItem record, @Param("example") TbItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_item
     *
     * @mbg.generated Fri Apr 21 12:00:51 CST 2017
     */
    int updateByExample(@Param("record") TbItem record, @Param("example") TbItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_item
     *
     * @mbg.generated Fri Apr 21 12:00:51 CST 2017
     */
    int updateByPrimaryKeySelective(TbItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_item
     *
     * @mbg.generated Fri Apr 21 12:00:51 CST 2017
     */
    int updateByPrimaryKey(TbItem record);
}