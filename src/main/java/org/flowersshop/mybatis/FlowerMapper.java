package org.flowersshop.mybatis;

import org.apache.ibatis.annotations.*;
import org.flowersshop.entities.Bouquet;
import org.flowersshop.entities.Flower;

import java.util.List;

@Mapper
public interface FlowerMapper {
    String FIND_BY_ID = "SELECT * FROM FLOWERS WHERE id = #{id}";
    String FIND_ALL = "SELECT * FROM FLOWERS";
    String DELETE_BY_ID = "DELETE FROM FLOWERS WHERE id = #{id}";
    String UPDATE = "Update FLOWERS set name=#{name}, adress = #{address.id} where id = #{id}";
    String INSERT = "INSERT INTO FLOWERS(id, name) VALUES (#{id}, #{name}";
    String FIND_BOUQUETS = "SELECT * FROM BOUQUETS WHERE ID = #{bouquetId}";

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "bouquets",column = "id", javaType = List.class, many=@Many(select = "findBouquets")),
    })
    @Select(FIND_BY_ID)
    Flower findById(Long id);

    @Select(FIND_BOUQUETS)
    List<Bouquet> findBouquets(Long bouquetId);

    @Select(FIND_ALL)
    List<Flower> findAll();

    @Delete(DELETE_BY_ID)
    void deleteById(@Param("id") Long id);

    @Update(UPDATE)
    int update(Flower Flower);

    @Insert(INSERT)
    int insert(Flower Flower);
}
