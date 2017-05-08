package com.taotao.mapper;

import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemDescExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbItemDescMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_item_desc
     *
     * @mbg.generated Fri Apr 21 12:00:51 CST 2017
     */
    long countByExample(TbItemDescExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_item_desc
     *
     * @mbg.generated Fri Apr 21 12:00:51 CST 2017
     */
    int deleteByExample(TbItemDescExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_item_desc
     *
     * @mbg.generated Fri Apr 21 12:00:51 CST 2017
     */
    int deleteByPrimaryKey(Long itemId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_item_desc
     *
     * @mbg.generated Fri Apr 21 12:00:51 CST 2017
     */
    int insert(TbItemDesc record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_item_desc
     *
     * @mbg.generated Fri Apr 21 12:00:51 CST 2017
     */
    int insertSelective(TbItemDesc record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_item_desc
     *
     * @mbg.generated Fri Apr 21 12:00:51 CST 2017
     */
    List<TbItemDesc> selectByExampleWithBLOBs(TbItemDescExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_item_desc
     *
     * @mbg.generated Fri Apr 21 12:00:51 CST 2017
     */
    List<TbItemDesc> selectByExample(TbItemDescExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_item_desc
     *
     * @mbg.generated Fri Apr 21 12:00:51 CST 2017
     */
    TbItemDesc selectByPrimaryKey(Long itemId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_item_desc
     *
     * @mbg.generated Fri Apr 21 12:00:51 CST 2017
     */
    int updateByExampleSelective(@Param("record") TbItemDesc record, @Param("example") TbItemDescExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_item_desc
     *
     * @mbg.generated Fri Apr 21 12:00:51 CST 2017
     */
    int updateByExampleWithBLOBs(@Param("record") TbItemDesc record, @Param("example") TbItemDescExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_item_desc
     *
     * @mbg.generated Fri Apr 21 12:00:51 CST 2017
     */
    int updateByExample(@Param("record") TbItemDesc record, @Param("example") TbItemDescExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_item_desc
     *
     * @mbg.generated Fri Apr 21 12:00:51 CST 2017
     */
    int updateByPrimaryKeySelective(TbItemDesc record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_item_desc
     *
     * @mbg.generated Fri Apr 21 12:00:51 CST 2017
     */
    int updateByPrimaryKeyWithBLOBs(TbItemDesc record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_item_desc
     *
     * @mbg.generated Fri Apr 21 12:00:51 CST 2017
     */
    int updateByPrimaryKey(TbItemDesc record);
}