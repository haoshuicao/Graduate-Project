package swust.edu.cn.threeExaminations.dao;

import java.util.List;

import swust.edu.cn.threeExaminations.model.Area;

public interface AreaMapper {
    int deleteByPrimaryKey(Integer areaId);

    int insert(Area record);

    int insertSelective(Area record);

    Area selectByPrimaryKey(Integer areaId);

    int updateByPrimaryKeySelective(Area record);

    int updateByPrimaryKey(Area record);
    
    List<Area> selectByName(Area area);

	List<Area> selectByParentId(int areaParentId);

    Area selectAreaIdByAreaName(Area area);

	List<Area> selectNameById(Area area);

	Area selectNameByAreaId(Integer userAreaid);

	Area selectNameParentIdByAreaId(Area area);

	Area selectAllByAreaId(Area area);
}