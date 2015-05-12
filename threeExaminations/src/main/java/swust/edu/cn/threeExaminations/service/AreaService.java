package swust.edu.cn.threeExaminations.service;

import java.util.List;

import swust.edu.cn.threeExaminations.model.Area;

public interface AreaService {
	int getAreaIdByName(String areaName);

	List<Area> getAreaByParentId(int areaParentId);

	Area getAreaByAreaId(Integer userAreaid);

	String getAreaNameByAreaId(int areaid);
	
	public int setMaxUserNum(Integer areaId, Integer maxUserNum);
	
	public int editUserNum(Integer areaId, Integer exitUserNum);
}
