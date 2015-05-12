package swust.edu.cn.threeExaminations.service;

import java.util.List;

import swust.edu.cn.threeExaminations.model.ThreeCheckServiceWithBLOBs;


public interface ThreeCheckServiceService {
	List<ThreeCheckServiceWithBLOBs> searchByTownName(String townName);
	
	List<ThreeCheckServiceWithBLOBs> findRecordByIdCard(String womanIdCard,String userLevel,String nowLiveArea,String userAreaName);
	
	ThreeCheckServiceWithBLOBs findNewRecordByIdCard(String womanIdCard,String userLevel,String nowLiveArea,String userAreaName);
	
	List<ThreeCheckServiceWithBLOBs> findRecordBySelectCondition1(int id,String level,
			 String year, String patch, String areaName,
			String characterSelect);
	
	List<ThreeCheckServiceWithBLOBs> findRecordBySelectCondition2(int id,String level,
			 String year, String patch,String areaName);
	
	List<ThreeCheckServiceWithBLOBs> findRecordBySelectCondition3(int id,String level,
			 String year, String patch);
	
	List<ThreeCheckServiceWithBLOBs> findRecordBySelectCondition4(int id,String level,
			 String year, String patch, String peopleClass);
	
	List<ThreeCheckServiceWithBLOBs> findRecordBySelectCondition5(int id,String level,
			String year, String patch, String peopleClass,
			String characterSelect);
	
	List<ThreeCheckServiceWithBLOBs> findRecordBySelectCondition6(int id,String level,
			 String year, String patch, String areaName,String peopleClass,
			String characterSelect);
	
	List<ThreeCheckServiceWithBLOBs> findRecordBySelectCondition7(int id,String level,
			 String year, String patch, String characterSelect);
	
	List<ThreeCheckServiceWithBLOBs> findRecordBySelectCondition8(int id,String level,
			 String year, String patch,String areaName, String peopleClass);
	
	ThreeCheckServiceWithBLOBs insert(int areaId,String womanName, String womanIdCard, String womanProvince,
			String womanCity, String womanCounty, String womanTown,
			String womanVallige, String womanCurProvince, String womanCurCity,
			String womanCurCounty, String womanCurTown, String womanCurVallige,String liveState,
			String checkYear,String checkPatch, String checkDate, String checkPlace, String hoop,
			String pregnant, String disease, String checkSuggest);
	
	void importlist(int areaId,String userAreaString,String idNumber, String name, String householdRegister,
			String nowLiveProvince, String nowLiveCity, String nowLiveCounty,
			String nowLiveTown, String nowLiveVillage, String liveState,
			String isExistCheckList, String checkState, String checkTime,
			String year, String batch, String noCheckReason,
			String checkSuggest, String disease, String hoop, String pregnancy,
			String isRemedy, String cheakPlace, String threeCheckServicecol);

	ThreeCheckServiceWithBLOBs findRepeat(String womanIdCard, String checkYear,
			String checkPatch,String liveState, String pregnant,String hoop,String disease);
	
	ThreeCheckServiceWithBLOBs findRepeat2(String womanIdCard, String checkYear,
			String checkPatch,String liveState);
	
	List<ThreeCheckServiceWithBLOBs> getValigeByTown(String areaName);
	
	List<ThreeCheckServiceWithBLOBs> findRecordBySelectConditionOut1(int id,
			String level, String year, String patch, String areaName,
			String peopleClass);
	
	List<ThreeCheckServiceWithBLOBs> findRecordBySelectConditionOut2(int id,
			String level, String year, String patch, String peopleClass);

	List<ThreeCheckServiceWithBLOBs> findRecordBySelectConditionOut3(int id,
			String level, String year, String patch, String peopleClass,
			String characterSelect);
	
	List<ThreeCheckServiceWithBLOBs> findRecordBySelectConditionOut4(int id,
			String level, String year, String patch, String areaName,
			String peopleClass, String characterSelect);
	
	List<ThreeCheckServiceWithBLOBs> findRecordBySelectConditionOut5(int id,
			String level, String year, String patch, String areaName,
			String characterSelect);
	
	List<ThreeCheckServiceWithBLOBs> findRecordBySelectConditionOut6(int id,
			String level, String year, String patch, String areaName);
	
	List<ThreeCheckServiceWithBLOBs> findRecordBySelectConditionOut7(int id,
			String level, String year, String patch);
	
	List<ThreeCheckServiceWithBLOBs> findRecordBySelectConditionOut8(int id,
			String level, String year, String patch, String characterSelect);
	
	List<ThreeCheckServiceWithBLOBs> getHuJiByUserArea(String areaName1);
	
	
	public ThreeCheckServiceWithBLOBs setServiceAllInfo(int areaId, String womanName, String womanIdCard,
			String womanProvince, String womanCity, String womanCounty,
			String womanTown, String womanVallige, String womanCurProvince,
			String womanCurCity, String womanCurCounty, String womanCurTown,
			String womanCurVallige, String liveState, String checkYear,
			String checkPatch, String checkDate, String checkPlace, String hoop,
			String pregnant, String disease, String checkSuggest);
	
	public void updateService(ThreeCheckServiceWithBLOBs threeCheckServiceWithBLOBs);
	
	public void updateCountReportByInsertService(int areaId, String householdRegisterVillage,
			ThreeCheckServiceWithBLOBs threeCheckServiceWithBLOBs);
	
	public void updateCountReportByUpdateService(int areaId, String userAreaString,
			ThreeCheckServiceWithBLOBs threeCheckServiceWithBLOBs,ThreeCheckServiceWithBLOBs record);
}
