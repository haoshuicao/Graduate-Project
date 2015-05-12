package swust.edu.cn.threeExaminations.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import swust.edu.cn.threeExaminations.dao.AreaMapper;
import swust.edu.cn.threeExaminations.model.Area;
import swust.edu.cn.threeExaminations.service.AreaService;

@Service("areaService")
public class AreaServiceImpl implements AreaService{
	private AreaMapper areaMapper;
	
	public AreaMapper getAreaMapper() {
		return areaMapper;
	}

	@Autowired
	public void setAreaMapper(AreaMapper areaMapper) {
		this.areaMapper = areaMapper;
	}


	@SuppressWarnings("finally")
	public int getAreaIdByName(String areaName) {
		// TODO Auto-generated method stub
		Area area = new Area();
		try {
			area.setAreaName(areaName);
			List<Area> list = areaMapper.selectByName(area);
			if(list.size() > 0){
				area = list.get(0);				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return area.getAreaId();
		}
	}

	@SuppressWarnings("finally")
	public List<Area> getAreaByParentId(int areaParentId) {
		// TODO Auto-generated method stub
		List<Area> areaList = new ArrayList<Area>();
		try {
			areaList = areaMapper.selectByParentId(areaParentId);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return areaList;
		}
	}

	@SuppressWarnings("finally")
	public Area getAreaByAreaId(Integer userAreaid) {
		// TODO Auto-generated method stub
		Area area = new Area();
		try {
			area = areaMapper.selectByPrimaryKey(userAreaid);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return area;
		}
	}

	@SuppressWarnings("finally")
	public String getAreaNameByAreaId(int areaid) {
		// TODO Auto-generated method stub
		Area area = new Area();
		try {
			area.setAreaId(areaid);
			List<Area> list = areaMapper.selectNameById(area);
			if(list.size() > 0){
				area = list.get(0);				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return area.getAreaName();
		}
	}
	

	@SuppressWarnings("finally")
	public int setMaxUserNum(Integer areaId, Integer maxUserNum){
		Area area = new Area();
		try{
			area = areaMapper.selectByPrimaryKey(areaId);
			area.setAreaMaxuser(maxUserNum);
			areaMapper.updateByPrimaryKey(area);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			return 0;
		}
	}
	
	@SuppressWarnings("finally")
	public int editUserNum(Integer areaId, Integer exitUserNum){
		Area area = new Area();
		try{
			area = areaMapper.selectByPrimaryKey(areaId);
			area.setAreaExistuser(exitUserNum);;
			areaMapper.updateByPrimaryKey(area);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			return 0;
		}
	}
}
