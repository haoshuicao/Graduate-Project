package swust.edu.cn.threeExaminations.service.impl;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import jxl.read.biff.Record;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import swust.edu.cn.threeExaminations.dao.AreaMapper;
import swust.edu.cn.threeExaminations.dao.ThreeCheckCountMapper;
import swust.edu.cn.threeExaminations.dao.ThreeCheckServiceMapper;
import swust.edu.cn.threeExaminations.model.Area;
import swust.edu.cn.threeExaminations.model.ThreeCheckCount;
import swust.edu.cn.threeExaminations.model.ThreeCheckServiceWithBLOBs;
import swust.edu.cn.threeExaminations.service.ThreeCheckServiceService;

@Service("threeCheckServiceService")
public class ThreeCheckServiceServiceImpl implements ThreeCheckServiceService {
	private ThreeCheckServiceMapper threeCheckServiceMapper;
	private ThreeCheckCountMapper threeCheckCountMapper;

	public ThreeCheckCountMapper getThreeCheckCountMapper() {
		return threeCheckCountMapper;
	}
	@Autowired
	public void setThreeCheckCountMapper(
			ThreeCheckCountMapper threeCheckCountMapper) {
		this.threeCheckCountMapper = threeCheckCountMapper;
	}
	private AreaMapper areaMapper;
	public AreaMapper getAreaMapper() {
		return areaMapper;
	}
	@Autowired
	public void setAreaMapper(AreaMapper areaMapper) {
		this.areaMapper = areaMapper;
	}

	public ThreeCheckServiceMapper getThreeCheckServiceMapper() {
		return threeCheckServiceMapper;
	}

	@Autowired
	public void setThreeCheckServiceMapper(
			ThreeCheckServiceMapper threeCheckServiceMapper) {
		this.threeCheckServiceMapper = threeCheckServiceMapper;
	}

	@SuppressWarnings("finally")
	public List<ThreeCheckServiceWithBLOBs> searchByTownName(String townName) {
		List<ThreeCheckServiceWithBLOBs> threeCheckServiceList = new ArrayList<ThreeCheckServiceWithBLOBs>();
		ThreeCheckServiceWithBLOBs threeCheckServiceWithBLOBs = new ThreeCheckServiceWithBLOBs();
		try {
			threeCheckServiceWithBLOBs.setThcsNowlivetown(townName);
			threeCheckServiceList = threeCheckServiceMapper
					.searchByTownName(threeCheckServiceWithBLOBs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println(threeCheckServiceWithBLOBs.getThcsNowlivetown());
			System.out.println(threeCheckServiceList.get(0).getThcsIdnumber());
			return threeCheckServiceList;
		}
	}
	
	public List<ThreeCheckServiceWithBLOBs> findRecordByIdCard(
			String womanIdCard,String userLevel,String nowLiveArea,String userAreaName) {
		List<ThreeCheckServiceWithBLOBs> recordList = new ArrayList<ThreeCheckServiceWithBLOBs>();
		try {
			String huji="%"+userAreaName+"%";
			if(userLevel.equals("乡/镇级")){
				recordList = threeCheckServiceMapper.findRecordByIdCard(womanIdCard,"常住","流入",nowLiveArea,"流出",huji);
			}else{
				recordList = threeCheckServiceMapper.findRecordByIdCard2(womanIdCard,"常住","流入",nowLiveArea,"流出",huji);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return recordList;
	}
	
	public ThreeCheckServiceWithBLOBs findNewRecordByIdCard(String womanIdCard,
			String userLevel,String nowLiveArea,String userAreaName) {
		ThreeCheckServiceWithBLOBs record = null;
		List<ThreeCheckServiceWithBLOBs> recordList = new ArrayList<ThreeCheckServiceWithBLOBs>();
		recordList = findRecordByIdCard(womanIdCard,userLevel,nowLiveArea,userAreaName);
		if(recordList.size()>0){
			record = recordList.get(recordList.size()-1);
		}
		return record;
	}
	
	@SuppressWarnings("finally")
	public List<ThreeCheckServiceWithBLOBs> findRecordBySelectCondition1(
			int id, String level, String year, String patch, String areaName,
			String characterSelect) {
		List<ThreeCheckServiceWithBLOBs> record = new ArrayList<ThreeCheckServiceWithBLOBs>();
		ThreeCheckServiceWithBLOBs threeCheckServiceWithBLOBs = new ThreeCheckServiceWithBLOBs();
		try {
			threeCheckServiceWithBLOBs.setThcsYear(year);
			threeCheckServiceWithBLOBs.setThcsBatch(patch);
			threeCheckServiceWithBLOBs.setThcsPregnancy(characterSelect);
			Area area = new Area();
			area.setAreaId(id);
			area = areaMapper.selectNameByAreaId(id);
			if (level.equals("乡/镇级")) {
				threeCheckServiceWithBLOBs.setThcsNowlivetown(area
						.getAreaName());
				threeCheckServiceWithBLOBs.setThcsNowlivevillage(areaName);
				threeCheckServiceWithBLOBs.setThcsLivestate("常住");
				record.addAll(threeCheckServiceMapper
						.findRecordBySelect1(threeCheckServiceWithBLOBs));
				threeCheckServiceWithBLOBs.setThcsLivestate("流入");
				record.addAll(threeCheckServiceMapper
						.findRecordBySelect1(threeCheckServiceWithBLOBs));
			} else {
				threeCheckServiceWithBLOBs.setThcsNowlivecounty(area
						.getAreaName());
				threeCheckServiceWithBLOBs.setThcsNowlivetown(areaName);
				threeCheckServiceWithBLOBs.setThcsLivestate("常住");
				record.addAll(threeCheckServiceMapper
						.findRecordBySelect11(threeCheckServiceWithBLOBs));
				threeCheckServiceWithBLOBs.setThcsLivestate("流入");
				record.addAll(threeCheckServiceMapper
						.findRecordBySelect11(threeCheckServiceWithBLOBs));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return record;
		}
	}
	@SuppressWarnings("finally")
	public List<ThreeCheckServiceWithBLOBs> findRecordBySelectCondition2(
			int id, String level, String year, String patch, String areaName) {
		List<ThreeCheckServiceWithBLOBs> record = new ArrayList<ThreeCheckServiceWithBLOBs>();
		ThreeCheckServiceWithBLOBs threeCheckServiceWithBLOBs = new ThreeCheckServiceWithBLOBs();
		try {
			threeCheckServiceWithBLOBs.setThcsYear(year);
			threeCheckServiceWithBLOBs.setThcsBatch(patch);
			Area area = new Area();
			area.setAreaId(id);
			area = areaMapper.selectNameByAreaId(id);
			if (level.equals("乡/镇级")) {
				threeCheckServiceWithBLOBs.setThcsNowlivetown(area
						.getAreaName());
				threeCheckServiceWithBLOBs.setThcsNowlivevillage(areaName);
				threeCheckServiceWithBLOBs.setThcsLivestate("常住");
				record.addAll(threeCheckServiceMapper
						.findRecordBySelect2(threeCheckServiceWithBLOBs));
				threeCheckServiceWithBLOBs.setThcsLivestate("流入");
				record.addAll(threeCheckServiceMapper
						.findRecordBySelect2(threeCheckServiceWithBLOBs));
			} else {
				threeCheckServiceWithBLOBs.setThcsNowlivecounty(area
						.getAreaName());
				threeCheckServiceWithBLOBs.setThcsNowlivetown(areaName);
				threeCheckServiceWithBLOBs.setThcsLivestate("常住");
				record.addAll(threeCheckServiceMapper
						.findRecordBySelect22(threeCheckServiceWithBLOBs));
				threeCheckServiceWithBLOBs.setThcsLivestate("流入");
				record.addAll(threeCheckServiceMapper
						.findRecordBySelect22(threeCheckServiceWithBLOBs));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return record;
		}
	}
	@SuppressWarnings("finally")
	public List<ThreeCheckServiceWithBLOBs> findRecordBySelectCondition3(
			int id, String level, String year, String patch) {
		List<ThreeCheckServiceWithBLOBs> record = new ArrayList<ThreeCheckServiceWithBLOBs>();
		ThreeCheckServiceWithBLOBs threeCheckServiceWithBLOBs = new ThreeCheckServiceWithBLOBs();
		try {
			threeCheckServiceWithBLOBs.setThcsYear(year);
			threeCheckServiceWithBLOBs.setThcsBatch(patch);
			Area area = new Area();
			area.setAreaId(id);
			area = areaMapper.selectNameByAreaId(id);
			if (level.equals("乡/镇级")) {
				threeCheckServiceWithBLOBs.setThcsNowlivetown(area
						.getAreaName());
				threeCheckServiceWithBLOBs.setThcsLivestate("常住");
				record.addAll(threeCheckServiceMapper
						.findRecordBySelect3(threeCheckServiceWithBLOBs));
				threeCheckServiceWithBLOBs.setThcsLivestate("流入");
				record.addAll(threeCheckServiceMapper
						.findRecordBySelect3(threeCheckServiceWithBLOBs));
			} else {
				threeCheckServiceWithBLOBs.setThcsNowlivecounty(area
						.getAreaName());
				threeCheckServiceWithBLOBs.setThcsLivestate("常住");
				record.addAll(threeCheckServiceMapper
						.findRecordBySelect33(threeCheckServiceWithBLOBs));
				threeCheckServiceWithBLOBs.setThcsLivestate("流入");
				record.addAll(threeCheckServiceMapper
						.findRecordBySelect33(threeCheckServiceWithBLOBs));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return record;
		}
	}
	@SuppressWarnings("finally")
	public List<ThreeCheckServiceWithBLOBs> findRecordBySelectCondition4(
			int id, String level, String year, String patch, String peopleClass) {
		List<ThreeCheckServiceWithBLOBs> record = new ArrayList<ThreeCheckServiceWithBLOBs>();
		ThreeCheckServiceWithBLOBs threeCheckServiceWithBLOBs = new ThreeCheckServiceWithBLOBs();
		try {
			threeCheckServiceWithBLOBs.setThcsYear(year);
			threeCheckServiceWithBLOBs.setThcsBatch(patch);
			threeCheckServiceWithBLOBs.setThcsLivestate(peopleClass);
			
			Area area = new Area();
			area.setAreaId(id);
			area = areaMapper.selectNameByAreaId(id);
			if (level.equals("乡/镇级")) {
				threeCheckServiceWithBLOBs.setThcsNowlivetown(area
						.getAreaName());
				record = threeCheckServiceMapper
							.findRecordBySelect4(threeCheckServiceWithBLOBs);
			} else {
				threeCheckServiceWithBLOBs.setThcsNowlivecounty(area
						.getAreaName());
				record = threeCheckServiceMapper
						.findRecordBySelect44(threeCheckServiceWithBLOBs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return record;
		}
	}
	@SuppressWarnings("finally")
	public List<ThreeCheckServiceWithBLOBs> findRecordBySelectCondition5(
			int id, String level, String year, String patch,
			String peopleClass, String characterSelect) {
		List<ThreeCheckServiceWithBLOBs> record = new ArrayList<ThreeCheckServiceWithBLOBs>();
		ThreeCheckServiceWithBLOBs threeCheckServiceWithBLOBs = new ThreeCheckServiceWithBLOBs();
		try {
			threeCheckServiceWithBLOBs.setThcsYear(year);
			threeCheckServiceWithBLOBs.setThcsBatch(patch);
			threeCheckServiceWithBLOBs.setThcsLivestate(peopleClass);
			threeCheckServiceWithBLOBs.setThcsPregnancy(characterSelect);
			Area area = new Area();
			area.setAreaId(id);
			area = areaMapper.selectNameByAreaId(id);
			if (level.equals("乡/镇级")) {
				threeCheckServiceWithBLOBs.setThcsNowlivetown(area
						.getAreaName());						
			    record = threeCheckServiceMapper
							.findRecordBySelect5(threeCheckServiceWithBLOBs);
			} else {
				threeCheckServiceWithBLOBs.setThcsNowlivecounty(area
						.getAreaName());
				record = threeCheckServiceMapper
							.findRecordBySelect55(threeCheckServiceWithBLOBs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return record;
		}
	}
	@SuppressWarnings("finally")
	public List<ThreeCheckServiceWithBLOBs> findRecordBySelectCondition6(
			int id, String level, String year, String patch, String areaName,
			String peopleClass, String characterSelect) {
		List<ThreeCheckServiceWithBLOBs> record = new ArrayList<ThreeCheckServiceWithBLOBs>();
		ThreeCheckServiceWithBLOBs threeCheckServiceWithBLOBs = new ThreeCheckServiceWithBLOBs();
		try {
			threeCheckServiceWithBLOBs.setThcsYear(year);
			threeCheckServiceWithBLOBs.setThcsBatch(patch);
			threeCheckServiceWithBLOBs.setThcsLivestate(peopleClass);
			threeCheckServiceWithBLOBs.setThcsPregnancy(characterSelect);
			Area area = new Area();
			area.setAreaId(id);
			area = areaMapper.selectNameByAreaId(id);
			if (level.equals("乡/镇级")) {
				threeCheckServiceWithBLOBs.setThcsNowlivetown(area
						.getAreaName());
				threeCheckServiceWithBLOBs.setThcsNowlivevillage(areaName);
				record = threeCheckServiceMapper
							.findRecordBySelect6(threeCheckServiceWithBLOBs);
			} else {
				threeCheckServiceWithBLOBs.setThcsNowlivecounty(area
						.getAreaName());
				threeCheckServiceWithBLOBs.setThcsNowlivetown(areaName);
				record = threeCheckServiceMapper
							.findRecordBySelect66(threeCheckServiceWithBLOBs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return record;
		}
	}
	@SuppressWarnings("finally")
	public List<ThreeCheckServiceWithBLOBs> findRecordBySelectCondition7(
			int id, String level, String year, String patch,
			String characterSelect) {
		List<ThreeCheckServiceWithBLOBs> record = new ArrayList<ThreeCheckServiceWithBLOBs>();
		ThreeCheckServiceWithBLOBs threeCheckServiceWithBLOBs = new ThreeCheckServiceWithBLOBs();
		try {
			threeCheckServiceWithBLOBs.setThcsYear(year);
			threeCheckServiceWithBLOBs.setThcsBatch(patch);
			threeCheckServiceWithBLOBs.setThcsPregnancy(characterSelect);
			Area area = new Area();
			area.setAreaId(id);
			area = areaMapper.selectNameByAreaId(id);
			if (level.equals("乡/镇级")) {
				threeCheckServiceWithBLOBs.setThcsNowlivetown(area
						.getAreaName());
				threeCheckServiceWithBLOBs.setThcsLivestate("常住");
				record.addAll(threeCheckServiceMapper
						.findRecordBySelect7(threeCheckServiceWithBLOBs));
				threeCheckServiceWithBLOBs.setThcsLivestate("流入");
				record.addAll(threeCheckServiceMapper
						.findRecordBySelect7(threeCheckServiceWithBLOBs));
			} else {
				threeCheckServiceWithBLOBs.setThcsNowlivecounty(area
						.getAreaName());
				threeCheckServiceWithBLOBs.setThcsLivestate("常住");
				record.addAll(threeCheckServiceMapper
						.findRecordBySelect77(threeCheckServiceWithBLOBs));
				threeCheckServiceWithBLOBs.setThcsLivestate("流入");
				record.addAll(threeCheckServiceMapper
						.findRecordBySelect77(threeCheckServiceWithBLOBs));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return record;
		}
	}
	@SuppressWarnings("finally")
	public List<ThreeCheckServiceWithBLOBs> findRecordBySelectCondition8(
			int id, String level, String year, String patch, String areaName,
			String peopleClass) {
		List<ThreeCheckServiceWithBLOBs> record = new ArrayList<ThreeCheckServiceWithBLOBs>();
		ThreeCheckServiceWithBLOBs threeCheckServiceWithBLOBs = new ThreeCheckServiceWithBLOBs();
		try {
			threeCheckServiceWithBLOBs.setThcsYear(year);
			threeCheckServiceWithBLOBs.setThcsBatch(patch);
			threeCheckServiceWithBLOBs.setThcsLivestate(peopleClass);
			Area area = new Area();
			area.setAreaId(id);
			area = areaMapper.selectNameByAreaId(id);
			if (level.equals("乡/镇级")) {
				threeCheckServiceWithBLOBs.setThcsNowlivetown(area
						.getAreaName());
				threeCheckServiceWithBLOBs.setThcsNowlivevillage(areaName);
	            record = threeCheckServiceMapper
							.findRecordBySelect8(threeCheckServiceWithBLOBs);
			} else {
				threeCheckServiceWithBLOBs.setThcsNowlivecounty(area
						.getAreaName());
				threeCheckServiceWithBLOBs.setThcsNowlivetown(areaName);
				record = threeCheckServiceMapper
							.findRecordBySelect88(threeCheckServiceWithBLOBs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return record;
		}
	}
	public ThreeCheckServiceWithBLOBs setServiceAllInfo(int areaId, String womanName, String womanIdCard,
			String womanProvince, String womanCity, String womanCounty,
			String womanTown, String womanVallige, String womanCurProvince,
			String womanCurCity, String womanCurCounty, String womanCurTown,
			String womanCurVallige, String liveState, String checkYear,
			String checkPatch, String checkDate, String checkPlace, String hoop,
			String pregnant, String disease, String checkSuggest) {
		ThreeCheckServiceWithBLOBs threeCheckServiceWithBLOBs = new ThreeCheckServiceWithBLOBs();		
		threeCheckServiceWithBLOBs.setThcsName(womanName);
		threeCheckServiceWithBLOBs.setThcsIdnumber(womanIdCard);
		threeCheckServiceWithBLOBs.setThcsHouseholdregister(womanProvince
					+ womanCity + womanCounty + womanTown + womanVallige);
		threeCheckServiceWithBLOBs.setThcsNowliveprovince(womanCurProvince);
		threeCheckServiceWithBLOBs.setThcsNowlivecity(womanCurCity);
		threeCheckServiceWithBLOBs.setThcsNowlivecounty(womanCurCounty);
		threeCheckServiceWithBLOBs.setThcsNowlivetown(womanCurTown);
		threeCheckServiceWithBLOBs.setThcsNowlivevillage(womanCurVallige);
		threeCheckServiceWithBLOBs.setThcsBatch(checkPatch);
		threeCheckServiceWithBLOBs.setThcsChecktime(checkDate);
		threeCheckServiceWithBLOBs.setThcsCheakplace(checkPlace);
		threeCheckServiceWithBLOBs.setThcsHoop(hoop);
		threeCheckServiceWithBLOBs.setThcsPregnancy(pregnant);
		threeCheckServiceWithBLOBs.setThcsDisease(disease);
		threeCheckServiceWithBLOBs.setThcsChecksuggest(checkSuggest);
		threeCheckServiceWithBLOBs.setThcsLivestate(liveState);
		threeCheckServiceWithBLOBs.setThcsIsexistchecklist(1);
		threeCheckServiceWithBLOBs.setThcsCheckstate("已查");
		threeCheckServiceWithBLOBs.setThcsYear(checkYear);
		threeCheckServiceWithBLOBs.setThcsNocheckreason("无");
		threeCheckServiceWithBLOBs.setThcsIsremedy(0);
		
		threeCheckServiceWithBLOBs.setThcsThreecheckservicecol("0");		
		return threeCheckServiceWithBLOBs;
	}
	public void updateService(ThreeCheckServiceWithBLOBs threeCheckServiceWithBLOBs){
		threeCheckServiceMapper.updateByPrimaryKey(threeCheckServiceWithBLOBs);
	}
	@SuppressWarnings("finally")
	public ThreeCheckServiceWithBLOBs insert(int areaId, String womanName, String womanIdCard,
			String womanProvince, String womanCity, String womanCounty,
			String womanTown, String womanVallige, String womanCurProvince,
			String womanCurCity, String womanCurCounty, String womanCurTown,
			String womanCurVallige, String liveState, String checkYear,
			String checkPatch, String checkDate, String checkPlace, String hoop,
			String pregnant, String disease, String checkSuggest) {
		ThreeCheckServiceWithBLOBs threeCheckServiceWithBLOBs = new ThreeCheckServiceWithBLOBs();
		try {		
			threeCheckServiceWithBLOBs.setThcsName(womanName);
			threeCheckServiceWithBLOBs.setThcsIdnumber(womanIdCard);
			threeCheckServiceWithBLOBs.setThcsHouseholdregister(womanProvince
					+ womanCity + womanCounty + womanTown + womanVallige);
			threeCheckServiceWithBLOBs.setThcsNowliveprovince(womanCurProvince);
			threeCheckServiceWithBLOBs.setThcsNowlivecity(womanCurCity);
			threeCheckServiceWithBLOBs.setThcsNowlivecounty(womanCurCounty);
			threeCheckServiceWithBLOBs.setThcsNowlivetown(womanCurTown);
			threeCheckServiceWithBLOBs.setThcsNowlivevillage(womanCurVallige);
			threeCheckServiceWithBLOBs.setThcsBatch(checkPatch);
			threeCheckServiceWithBLOBs.setThcsChecktime(checkDate);
			threeCheckServiceWithBLOBs.setThcsCheakplace(checkPlace);
			threeCheckServiceWithBLOBs.setThcsHoop(hoop);
			threeCheckServiceWithBLOBs.setThcsPregnancy(pregnant);
			threeCheckServiceWithBLOBs.setThcsDisease(disease);
			threeCheckServiceWithBLOBs.setThcsChecksuggest(checkSuggest);
			threeCheckServiceWithBLOBs.setThcsLivestate(liveState);
			threeCheckServiceWithBLOBs.setThcsIsexistchecklist(1);
			threeCheckServiceWithBLOBs.setThcsCheckstate("已查");
			threeCheckServiceWithBLOBs.setThcsYear(checkYear);
			threeCheckServiceWithBLOBs.setThcsNocheckreason("无");
			threeCheckServiceWithBLOBs.setThcsIsremedy(0);
			
			threeCheckServiceWithBLOBs.setThcsThreecheckservicecol("0");
			threeCheckServiceMapper.insert(threeCheckServiceWithBLOBs);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			return threeCheckServiceWithBLOBs;
		}
	}
	public void importlist(int areaId,String userAreaString, String idNumber, String name,
			String householdRegister, String nowLiveProvince,
			String nowLiveCity, String nowLiveCounty, String nowLiveTown,
			String nowLiveVillage, String liveState, String isExistCheckList,
			String checkState, String checkTime, String year, String batch,
			String noCheckReason, String checkSuggest, String disease,
			String hoop, String pregnancy, String isRemedy, String cheakPlace,
			String threeCheckServicecol) {
		try {
			ThreeCheckServiceWithBLOBs threeCheckServiceWithBLOBs = new ThreeCheckServiceWithBLOBs();
			threeCheckServiceWithBLOBs.setThcsIdnumber(idNumber);
			threeCheckServiceWithBLOBs.setThcsName(name);
			threeCheckServiceWithBLOBs
					.setThcsHouseholdregister(householdRegister);
			threeCheckServiceWithBLOBs.setThcsNowliveprovince(nowLiveProvince);
			threeCheckServiceWithBLOBs.setThcsNowlivecity(nowLiveCity);
			threeCheckServiceWithBLOBs.setThcsNowlivecounty(nowLiveCounty);
			threeCheckServiceWithBLOBs.setThcsNowlivetown(nowLiveTown);
			threeCheckServiceWithBLOBs.setThcsNowlivevillage(nowLiveVillage);
			threeCheckServiceWithBLOBs.setThcsLivestate(liveState);
			int isexist = Integer.parseInt(isExistCheckList);
			threeCheckServiceWithBLOBs.setThcsIsexistchecklist(isexist);
			threeCheckServiceWithBLOBs.setThcsCheckstate(checkState);
			threeCheckServiceWithBLOBs.setThcsChecktime(checkTime);
			threeCheckServiceWithBLOBs.setThcsYear(year);
			threeCheckServiceWithBLOBs.setThcsBatch(batch);
			threeCheckServiceWithBLOBs.setThcsNocheckreason(noCheckReason);
			threeCheckServiceWithBLOBs.setThcsChecksuggest(checkSuggest);
			threeCheckServiceWithBLOBs.setThcsDisease(disease);
			threeCheckServiceWithBLOBs.setThcsHoop(hoop);
			threeCheckServiceWithBLOBs.setThcsPregnancy(pregnancy);
			threeCheckServiceWithBLOBs.setThcsIsremedy(0);
			
			threeCheckServiceWithBLOBs.setThcsCheakplace(cheakPlace);
			threeCheckServiceWithBLOBs
					.setThcsThreecheckservicecol(threeCheckServicecol);
			ThreeCheckServiceWithBLOBs record = new ThreeCheckServiceWithBLOBs();
			record.setThcsIdnumber(idNumber);
			record.setThcsYear(year);
			record.setThcsBatch(batch);
			record.setThcsLivestate(liveState);
			record = threeCheckServiceMapper.findRepeat2(record);
			if (record == null) {
				threeCheckServiceMapper.insert(threeCheckServiceWithBLOBs);
				String householdRegisterVillage = threeCheckServiceWithBLOBs.getThcsHouseholdregister().replace(userAreaString, "");
				updateCountReportByInsertService(areaId,householdRegisterVillage,threeCheckServiceWithBLOBs);
			} else if(record != null && record.getThcsCheckstate().equals("已查")
					&& record.getThcsHoop().equals(hoop) 
					&& record.getThcsDisease().equals(disease)
					&& record.getThcsPregnancy().equals(pregnancy)){
			} else {
				if(checkState.equals("已查")){
					if(record.getThcsCheckstate().equals("未查")){
						threeCheckServiceWithBLOBs.setThcsId(record.getThcsId());
						threeCheckServiceMapper.updateByPrimaryKey(threeCheckServiceWithBLOBs);
						String householdRegisterVillage = threeCheckServiceWithBLOBs.getThcsHouseholdregister().replace(userAreaString, "");						
						updateCountReportByUpdateService(areaId,householdRegisterVillage,threeCheckServiceWithBLOBs,record);
					} else {
						if(record.getThcsPregnancy().equals("计外孕")){
							if(pregnancy.equals("计外孕")){
								threeCheckServiceWithBLOBs.setThcsIsremedy(0);
							}else{
								threeCheckServiceWithBLOBs.setThcsIsremedy(1);							
							}
						} else {
							record.setThcsPregnancy(threeCheckServiceWithBLOBs.getThcsPregnancy());
							threeCheckServiceWithBLOBs.setThcsIsremedy(0);
						}
						String householdRegisterVillage = threeCheckServiceWithBLOBs.getThcsHouseholdregister().replace(userAreaString, "");
						updateCountReportByUpdateService(areaId,householdRegisterVillage,threeCheckServiceWithBLOBs,record);
						record.setThcsHoop(threeCheckServiceWithBLOBs.getThcsHoop());
						record.setThcsDisease(threeCheckServiceWithBLOBs.getThcsDisease());
						record.setThcsIsremedy(threeCheckServiceWithBLOBs.getThcsIsremedy());
						if(!record.getThcsPregnancy().equals("计外孕")){
							record.setThcsPregnancy(threeCheckServiceWithBLOBs.getThcsPregnancy());
						}
						threeCheckServiceMapper.updateByPrimaryKey(record);						
					}
				}	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void updateCountReportByInsertService(int areaId,String householdRegisterVillage,ThreeCheckServiceWithBLOBs threeCheckServiceWithBLOBs){
		if(threeCheckServiceWithBLOBs.getThcsLivestate().equals("流出")){
			updateCountReport(areaId, householdRegisterVillage,threeCheckServiceWithBLOBs);
		}else{
		    updateCountReport(areaId, threeCheckServiceWithBLOBs.getThcsNowlivevillage(),threeCheckServiceWithBLOBs);
		}
		Area area = new Area();
		area.setAreaId(areaId);
		area = areaMapper.selectNameParentIdByAreaId(area);
		int townLevelParentAreaId = area.getAreaParentid();
		String townLevelAreaName = area.getAreaName();
		updateCountReport(townLevelParentAreaId, townLevelAreaName,
				threeCheckServiceWithBLOBs);
		Area area2 = new Area();
		area2.setAreaId(area.getAreaParentid());
		area2 = areaMapper.selectNameParentIdByAreaId(area2);
		int countyLevelParentAreaId = area2.getAreaParentid();
		String countyLevelAreaName = area2.getAreaName();
		updateCountReport(countyLevelParentAreaId, countyLevelAreaName,
				threeCheckServiceWithBLOBs);
		Area area3 = new Area();
		area3.setAreaId(area2.getAreaParentid());
		area3 = areaMapper.selectNameParentIdByAreaId(area3);
		int cityLevelParentAreaId = area3.getAreaParentid();
		String cityLevelAreaName = area3.getAreaName();
		updateCountReport(cityLevelParentAreaId, cityLevelAreaName,
				threeCheckServiceWithBLOBs);
		Area area4 = new Area();
		area4.setAreaId(area3.getAreaParentid());
		area4 = areaMapper.selectNameParentIdByAreaId(area4);
		int provinceLevelAreaId = area4.getAreaParentid();
		String provinceLevelParentAreaName = area4.getAreaName();
		updateCountReport(provinceLevelAreaId, provinceLevelParentAreaName,
				threeCheckServiceWithBLOBs);
	}
	public void updateCountReport(int id, String nowArea,
			ThreeCheckServiceWithBLOBs threeCheckServiceWithBLOBs) {
		try {
			ThreeCheckCount CountInfo = null;
			ThreeCheckCount CountInfo1 = new ThreeCheckCount();
			ThreeCheckCount CountInfo11 = new ThreeCheckCount();
			
			CountInfo1.setThccYear(threeCheckServiceWithBLOBs.getThcsYear());
			CountInfo1.setThccBatch(threeCheckServiceWithBLOBs.getThcsBatch());
			CountInfo1.setThccLivestate(threeCheckServiceWithBLOBs
					.getThcsLivestate());
			CountInfo1.setThccVillage(nowArea);
			CountInfo1.setThccParentAreaId(id);
			CountInfo = threeCheckCountMapper.findRecord(CountInfo1);
			
			if (CountInfo == null) {
				CountInfo11.setThccYear(threeCheckServiceWithBLOBs
						.getThcsYear());
				CountInfo11.setThccBatch(threeCheckServiceWithBLOBs
						.getThcsBatch());
				CountInfo11.setThccLivestate(threeCheckServiceWithBLOBs
						.getThcsLivestate());
				CountInfo11.setThccVillage(nowArea);
				CountInfo11.setThccParentAreaId(id);
				CountInfo11.setThccShouldchecknumber(1);
				if (threeCheckServiceWithBLOBs.getThcsCheckstate().equals("已查")) {
					CountInfo11.setThccHavechecknumber(1);
					CountInfo11.setThccCheckfreenumber(0);
				} else {
					CountInfo11.setThccHavechecknumber(0);
					CountInfo11.setThccCheckfreenumber(1);
				}
				if (threeCheckServiceWithBLOBs.getThcsHoop().equals(1)) {
					CountInfo11.setThccHavehoopnumber(1);
				} else {
					CountInfo11.setThccHavehoopnumber(0);
				}
				if (threeCheckServiceWithBLOBs.getThcsPregnancy().equals("计外孕")) {
					CountInfo11.setThccOutplanprenumber(1);
				} else {
					CountInfo11.setThccOutplanprenumber(0);
				}
				if (threeCheckServiceWithBLOBs.getThcsIsremedy().equals(1)) {
					CountInfo11.setThccRemedynumber(1);
				} else {
					CountInfo11.setThccRemedynumber(0);
				}
				if (threeCheckServiceWithBLOBs.getThcsDisease().equals("无病")) {
					CountInfo11.setThccGymornumber(0);
				} else {
					CountInfo11.setThccGymornumber(1);
				}
				threeCheckCountMapper.insert(CountInfo11);
			}
			else {
				int shouldCount = CountInfo.getThccShouldchecknumber() + 1;
				CountInfo.setThccShouldchecknumber(shouldCount);
				String checkState = threeCheckServiceWithBLOBs.getThcsCheckstate();
				if (checkState.equals("已查")) {
					int haveCount = CountInfo.getThccHavechecknumber() + 1;
					CountInfo.setThccHavechecknumber(haveCount);
				} else {
					int noCount = CountInfo.getThccCheckfreenumber() + 1;
					CountInfo.setThccCheckfreenumber(noCount);
				}
				String haveHoop = threeCheckServiceWithBLOBs.getThcsHoop();
				if (haveHoop.equals("有环")) {
					int hoopCount = CountInfo.getThccHavehoopnumber() + 1;
					CountInfo.setThccHavehoopnumber(hoopCount);
				} else {
				}
				String outYun = threeCheckServiceWithBLOBs.getThcsPregnancy();
				if (outYun.equals("计外孕")) {
					int outCount = CountInfo.getThccOutplanprenumber() + 1;
					CountInfo.setThccOutplanprenumber(outCount);
				} else {
				}
				int isbujiu = threeCheckServiceWithBLOBs.getThcsIsremedy();
				if (isbujiu != 0) {
					int bujiuCount = CountInfo.getThccRemedynumber() + 1;
					CountInfo.setThccRemedynumber(bujiuCount);
				} else {
				}
				String Bing = threeCheckServiceWithBLOBs.getThcsDisease();
				if (Bing.equals("无病")) {
				} else {
					int bingCount = CountInfo.getThccGymornumber() + 1;
					CountInfo.setThccGymornumber(bingCount);
				}
				threeCheckCountMapper.updateByPrimaryKey(CountInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void updateCountReportByUpdateService(int areaId,String householdRegisterVillage,
			ThreeCheckServiceWithBLOBs threeCheckServiceWithBLOBs,
			ThreeCheckServiceWithBLOBs record){
		if(threeCheckServiceWithBLOBs.getThcsLivestate().equals("流出")){
			updateCountReport2(areaId, householdRegisterVillage,threeCheckServiceWithBLOBs,record);
		}else{
		    updateCountReport2(areaId, threeCheckServiceWithBLOBs.getThcsNowlivevillage(),threeCheckServiceWithBLOBs,record);
		}
		Area area = new Area();
		area.setAreaId(areaId);
		area = areaMapper.selectNameParentIdByAreaId(area);
		int townLevelParentAreaId = area.getAreaParentid();
		String townLevelAreaName = area.getAreaName();
		updateCountReport2(townLevelParentAreaId, townLevelAreaName,
				threeCheckServiceWithBLOBs,record);
		Area area2 = new Area();
		area2.setAreaId(area.getAreaParentid());
		area2 = areaMapper.selectNameParentIdByAreaId(area2);
		int countyLevelParentAreaId = area2.getAreaParentid();
		String countyLevelAreaName = area2.getAreaName();
		updateCountReport2(countyLevelParentAreaId, countyLevelAreaName,
				threeCheckServiceWithBLOBs,record);
		Area area3 = new Area();
		area3.setAreaId(area2.getAreaParentid());
		area3 = areaMapper.selectNameParentIdByAreaId(area3);
		int cityLevelParentAreaId = area3.getAreaParentid();
		String cityLevelAreaName = area3.getAreaName();
		updateCountReport2(cityLevelParentAreaId, cityLevelAreaName,
				threeCheckServiceWithBLOBs,record);
		Area area4 = new Area();
		area4.setAreaId(area3.getAreaParentid());
		area4 = areaMapper.selectNameParentIdByAreaId(area4);
		int provinceLevelAreaId = area4.getAreaParentid();
		String provinceLevelParentAreaName = area4.getAreaName();
		updateCountReport2(provinceLevelAreaId, provinceLevelParentAreaName,
				threeCheckServiceWithBLOBs,record);
	}
	public void updateCountReport2(int areaId, String nowArea,
			ThreeCheckServiceWithBLOBs threeCheckServiceWithBLOBs,ThreeCheckServiceWithBLOBs record) {
		try {
			ThreeCheckCount CountInfo1 = new ThreeCheckCount();
			ThreeCheckCount CountInfo = null;
			CountInfo1.setThccYear(threeCheckServiceWithBLOBs.getThcsYear());
			CountInfo1.setThccBatch(threeCheckServiceWithBLOBs.getThcsBatch());
			CountInfo1.setThccLivestate(threeCheckServiceWithBLOBs
					.getThcsLivestate());
			CountInfo1.setThccVillage(nowArea);						
			CountInfo1.setThccParentAreaId(areaId);
			CountInfo = threeCheckCountMapper.findRecord(CountInfo1);
			if (CountInfo != null) {
				if (record.getThcsCheckstate().equals("未查")
						&& threeCheckServiceWithBLOBs.getThcsCheckstate()
								.equals("已查")) {
					int haveCount = CountInfo.getThccHavechecknumber() + 1;
					CountInfo.setThccHavechecknumber(haveCount);
					int noCount = CountInfo.getThccCheckfreenumber() - 1;
					CountInfo.setThccCheckfreenumber(noCount);
				}
				if (record.equals("无环")
						&& threeCheckServiceWithBLOBs.getThcsHoop()
								.equals("有环")) {
					int hoopCount = CountInfo.getThccHavehoopnumber() + 1;
					CountInfo.setThccHavehoopnumber(hoopCount);
				} else if (record.equals("有环")
						&& threeCheckServiceWithBLOBs.getThcsHoop()
								.equals("无环")) {
					int hoopCount = CountInfo.getThccHavehoopnumber() - 1;
					CountInfo.setThccHavehoopnumber(hoopCount);
				}
				if (record.getThcsPregnancy().equals("无孕")
						&& threeCheckServiceWithBLOBs.getThcsPregnancy()
								.equals("计外孕")) {
					int outCount = CountInfo.getThccOutplanprenumber() + 1;
					CountInfo.setThccOutplanprenumber(outCount);
				} 
				if (threeCheckServiceWithBLOBs.getThcsIsremedy()==1 && record.getThcsIsremedy()!=1) { 
					int bujiuCount = CountInfo.getThccRemedynumber() + 1;
				 CountInfo.setThccRemedynumber(bujiuCount); }
				if (record.getThcsDisease().equals("无病")
						&& !threeCheckServiceWithBLOBs.getThcsDisease().equals(
								"无病")) {
					int bingCount = CountInfo.getThccGymornumber() + 1;
					CountInfo.setThccGymornumber(bingCount);
				}
				threeCheckCountMapper.updateByPrimaryKey(CountInfo);
			} else {
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("finally")
	public ThreeCheckServiceWithBLOBs findRepeat(String womanIdCard,
			String checkYear, String checkPatch, String liveState,
			String pregnant, String hoop, String disease) {
		ThreeCheckServiceWithBLOBs record = new ThreeCheckServiceWithBLOBs();
		try {
			record.setThcsIdnumber(womanIdCard);
			record.setThcsYear(checkYear);
			record.setThcsBatch(checkPatch);
			record.setThcsLivestate(liveState);
			record.setThcsPregnancy(pregnant);
			record.setThcsHoop(hoop);
			record.setThcsDisease(disease);
			record = threeCheckServiceMapper.findRepeat(record);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return record;
		}
	}
	@SuppressWarnings("finally")
	public ThreeCheckServiceWithBLOBs findRepeat2(String womanIdCard,
			String checkYear, String checkPatch, String liveState) {
		ThreeCheckServiceWithBLOBs record = new ThreeCheckServiceWithBLOBs();
		try {
			record.setThcsIdnumber(womanIdCard);
			record.setThcsYear(checkYear);
			record.setThcsBatch(checkPatch);
			record.setThcsLivestate(liveState);
			record = threeCheckServiceMapper.findRepeat2(record);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return record;
		}
	}

	@SuppressWarnings("finally")
	public List<ThreeCheckServiceWithBLOBs> getValigeByTown(String areaName) {
		List<ThreeCheckServiceWithBLOBs> areaList = new ArrayList<ThreeCheckServiceWithBLOBs>();
		try {
			areaList = threeCheckServiceMapper
					.selectByTown(areaName,"常住","流入");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return areaList;
		}
	}

	public void setSelect(
			ThreeCheckServiceWithBLOBs threeCheckServiceWithBLOBs,
			ThreeCheckCount threeCheckCount) {
		try {
			threeCheckCount.setThccYear(threeCheckServiceWithBLOBs
					.getThcsYear());
			threeCheckCount.setThccBatch(threeCheckServiceWithBLOBs
					.getThcsBatch());
			threeCheckCount.setThccLivestate(threeCheckServiceWithBLOBs
					.getThcsLivestate());

			ThreeCheckCount readRecord = new ThreeCheckCount();
			readRecord.setThccYear(threeCheckServiceWithBLOBs.getThcsYear());
			readRecord.setThccBatch(threeCheckServiceWithBLOBs.getThcsBatch());
			readRecord.setThccLivestate(threeCheckServiceWithBLOBs
					.getThcsLivestate());
			String area = threeCheckCount.getThccVillage();
			readRecord.setThccVillage(area);
			int id = threeCheckCount.getThccParentAreaId();
			readRecord.setThccParentAreaId(id);
			readRecord = threeCheckCountMapper.findRecord(readRecord);

			int shouldCount = readRecord.getThccShouldchecknumber() + 1;
			threeCheckCount.setThccShouldchecknumber(shouldCount);
			
			String checkState = threeCheckServiceWithBLOBs.getThcsCheckstate();
			if (checkState.equals("已查")) {
				int haveCount = readRecord.getThccHavechecknumber() + 1;
				threeCheckCount.setThccHavechecknumber(haveCount);
				int noCount = readRecord.getThccCheckfreenumber();
				threeCheckCount.setThccCheckfreenumber(noCount);
			} else {
				int haveCount = readRecord.getThccHavechecknumber();
				threeCheckCount.setThccHavechecknumber(haveCount);
				int noCount = readRecord.getThccCheckfreenumber() + 1;
				threeCheckCount.setThccCheckfreenumber(noCount);
			}
			String haveHoop = threeCheckServiceWithBLOBs.getThcsHoop();
			if (haveHoop.equals("有环")) {
				int hoopCount = readRecord.getThccHavehoopnumber() + 1;
				threeCheckCount.setThccHavehoopnumber(hoopCount);
			} else {
				int hoopCount = readRecord.getThccHavehoopnumber();
				threeCheckCount.setThccHavehoopnumber(hoopCount);
			}
			String outYun = threeCheckServiceWithBLOBs.getThcsPregnancy();
			if (outYun.equals("计外孕")) {
				int outCount = readRecord.getThccOutplanprenumber() + 1;
				threeCheckCount.setThccOutplanprenumber(outCount);
			} else {
				int outCount = readRecord.getThccOutplanprenumber();
				threeCheckCount.setThccOutplanprenumber(outCount);
			}
			int isbujiu = threeCheckServiceWithBLOBs.getThcsIsremedy();
			if (isbujiu != 0) {
				int bujiuCount = readRecord.getThccRemedynumber() + 1;
				threeCheckCount.setThccRemedynumber(bujiuCount);
			} else {
				int bujiuCount = readRecord.getThccRemedynumber();
				threeCheckCount.setThccRemedynumber(bujiuCount);
			}
			String Bing = threeCheckServiceWithBLOBs.getThcsDisease();
			if (Bing.equals("无病")) {
				int bingCount = readRecord.getThccGymornumber();
				threeCheckCount.setThccGymornumber(bingCount);
			} else {
				int bingCount = readRecord.getThccGymornumber() + 1;
				threeCheckCount.setThccGymornumber(bingCount);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("finally")
	public List<ThreeCheckServiceWithBLOBs> findRecordBySelectConditionOut1(
			int id, String level, String year, String patch, String areaName,
			String peopleClass) {
		List<ThreeCheckServiceWithBLOBs> record = new ArrayList<ThreeCheckServiceWithBLOBs>();
		ThreeCheckServiceWithBLOBs threeCheckServiceWithBLOBs = new ThreeCheckServiceWithBLOBs();
		try {
			threeCheckServiceWithBLOBs.setThcsYear(year);
			threeCheckServiceWithBLOBs.setThcsBatch(patch);
			if (level.equals("乡/镇级")) {

				Area area = new Area();
				area.setAreaId(id);
				area = areaMapper.selectAllByAreaId(area);

				Area area2 = new Area();
				area2.setAreaId(area.getAreaParentid());
				area2 = areaMapper.selectNameParentIdByAreaId(area2);

				Area area3 = new Area();
				area3.setAreaId(area2.getAreaParentid());
				area3 = areaMapper.selectNameParentIdByAreaId(area3);

				Area area4 = new Area();
				area4.setAreaId(area3.getAreaParentid());
				area4 = areaMapper.selectNameParentIdByAreaId(area4);

				String village = areaName;
				String town = area.getAreaName();
				String county = area2.getAreaName();
				String city = area3.getAreaName();
				String province = area4.getAreaName();

				String huji = province + city + county + town + village;
				threeCheckServiceWithBLOBs.setThcsHouseholdregister(huji);
				
				threeCheckServiceWithBLOBs.setThcsLivestate("流出");
				record.addAll(threeCheckServiceMapper
						.findRecordBySelectOut1(threeCheckServiceWithBLOBs));
			} else {
				Area area5 = new Area();
				area5.setAreaId(id);
				area5 = areaMapper.selectAllByAreaId(area5);

				Area area6 = new Area();
				area6.setAreaId(area5.getAreaParentid());
				area6 = areaMapper.selectNameParentIdByAreaId(area6);

				Area area7 = new Area();
				area7.setAreaId(area6.getAreaParentid());
				area7 = areaMapper.selectNameParentIdByAreaId(area7);

				String town = areaName;
				String county = area5.getAreaName();
				String city = area6.getAreaName();
				String province = area7.getAreaName();

				String huji = province + city + county + town;
				threeCheckServiceWithBLOBs.setThcsHouseholdregister("%"+huji+"%");

				threeCheckServiceWithBLOBs.setThcsLivestate("流出");
				record.addAll(threeCheckServiceMapper
						.findRecordBySelectOut11(threeCheckServiceWithBLOBs));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return record;
		}
	}
	@SuppressWarnings("finally")
	public List<ThreeCheckServiceWithBLOBs> findRecordBySelectConditionOut2(
			int id, String level, String year, String patch, String peopleClass) {
		List<ThreeCheckServiceWithBLOBs> record = new ArrayList<ThreeCheckServiceWithBLOBs>();//某一户籍地的所有流出人口记录
		ThreeCheckServiceWithBLOBs threeCheckServiceWithBLOBs = new ThreeCheckServiceWithBLOBs();
		try {
			threeCheckServiceWithBLOBs.setThcsYear(year);
			threeCheckServiceWithBLOBs.setThcsBatch(patch);
			if (level.equals("乡/镇级")) {
				Area area = new Area();
				area.setAreaId(id);
				area = areaMapper.selectAllByAreaId(area);

				Area area2 = new Area();
				area2.setAreaId(area.getAreaParentid());
				area2 = areaMapper.selectNameParentIdByAreaId(area2);

				Area area3 = new Area();
				area3.setAreaId(area2.getAreaParentid());
				area3 = areaMapper.selectNameParentIdByAreaId(area3);

				Area area4 = new Area();
				area4.setAreaId(area3.getAreaParentid());
				area4 = areaMapper.selectNameParentIdByAreaId(area4);

				String town = area.getAreaName();
				String county = area2.getAreaName();
				String city = area3.getAreaName();
				String province = area4.getAreaName();

				String huji = province + city + county + town;
				threeCheckServiceWithBLOBs.setThcsHouseholdregister("%"+huji+"%");
				threeCheckServiceWithBLOBs.setThcsLivestate("流出");
				record.addAll(threeCheckServiceMapper
						.findRecordBySelectOut2(threeCheckServiceWithBLOBs));
			} else {
				Area area5 = new Area();
				area5.setAreaId(id);
				area5 = areaMapper.selectAllByAreaId(area5);

				Area area6 = new Area();
				area6.setAreaId(area5.getAreaParentid());
				area6 = areaMapper.selectNameParentIdByAreaId(area6);

				Area area7 = new Area();
				area7.setAreaId(area6.getAreaParentid());
				area7 = areaMapper.selectNameParentIdByAreaId(area7);

				String county = area5.getAreaName();
				String city = area6.getAreaName();
				String province = area7.getAreaName();

				String huji = province + city + county;
				threeCheckServiceWithBLOBs.setThcsHouseholdregister("%"+huji+"%");
				threeCheckServiceWithBLOBs.setThcsLivestate("流出");
				record.addAll(threeCheckServiceMapper
						.findRecordBySelectOut22(threeCheckServiceWithBLOBs));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return record;
		}
	}
	@SuppressWarnings("finally")
	public List<ThreeCheckServiceWithBLOBs> findRecordBySelectConditionOut3(
			int id, String level, String year, String patch,
			String peopleClass, String characterSelect) {
		List<ThreeCheckServiceWithBLOBs> record = new ArrayList<ThreeCheckServiceWithBLOBs>();
		ThreeCheckServiceWithBLOBs threeCheckServiceWithBLOBs = new ThreeCheckServiceWithBLOBs();
		try {
			threeCheckServiceWithBLOBs.setThcsYear(year);
			threeCheckServiceWithBLOBs.setThcsBatch(patch);
			threeCheckServiceWithBLOBs.setThcsPregnancy(characterSelect);

			if (level.equals("乡/镇级")) {
				Area area = new Area();
				area.setAreaId(id);
				area = areaMapper.selectAllByAreaId(area);

				Area area2 = new Area();
				area2.setAreaId(area.getAreaParentid());
				area2 = areaMapper.selectNameParentIdByAreaId(area2);

				Area area3 = new Area();
				area3.setAreaId(area2.getAreaParentid());
				area3 = areaMapper.selectNameParentIdByAreaId(area3);

				Area area4 = new Area();
				area4.setAreaId(area3.getAreaParentid());
				area4 = areaMapper.selectNameParentIdByAreaId(area4);

				String town = area.getAreaName();
				String county = area2.getAreaName();
				String city = area3.getAreaName();
				String province = area4.getAreaName();

				String huji = province + city + county + town;
				threeCheckServiceWithBLOBs.setThcsHouseholdregister("%"+huji+"%");

				threeCheckServiceWithBLOBs.setThcsLivestate("流出");
				record.addAll(threeCheckServiceMapper
						.findRecordBySelectOut3(threeCheckServiceWithBLOBs));
			} else {
				Area area5 = new Area();
				area5.setAreaId(id);
				area5 = areaMapper.selectAllByAreaId(area5);

				Area area6 = new Area();
				area6.setAreaId(area5.getAreaParentid());
				area6 = areaMapper.selectNameParentIdByAreaId(area6);

				Area area7 = new Area();
				area7.setAreaId(area6.getAreaParentid());
				area7 = areaMapper.selectNameParentIdByAreaId(area7);

				String county = area5.getAreaName();
				String city = area6.getAreaName();
				String province = area7.getAreaName();

				String huji = province + city + county;
				threeCheckServiceWithBLOBs.setThcsHouseholdregister("%"+huji+"%");
				threeCheckServiceWithBLOBs.setThcsLivestate("流出");
				record.addAll(threeCheckServiceMapper
						.findRecordBySelectOut33(threeCheckServiceWithBLOBs));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return record;
		}
	}
	@SuppressWarnings("finally")
	public List<ThreeCheckServiceWithBLOBs> findRecordBySelectConditionOut4(
			int id, String level, String year, String patch, String areaName,
			String peopleClass, String characterSelect) {
		List<ThreeCheckServiceWithBLOBs> record = new ArrayList<ThreeCheckServiceWithBLOBs>();
		ThreeCheckServiceWithBLOBs threeCheckServiceWithBLOBs = new ThreeCheckServiceWithBLOBs();
		try {

			threeCheckServiceWithBLOBs.setThcsYear(year);
			threeCheckServiceWithBLOBs.setThcsBatch(patch);
			threeCheckServiceWithBLOBs.setThcsPregnancy(characterSelect);

			if (level.equals("乡/镇级")) {

				Area area = new Area();
				area.setAreaId(id);
				area = areaMapper.selectAllByAreaId(area);

				Area area2 = new Area();
				area2.setAreaId(area.getAreaParentid());
				area2 = areaMapper.selectNameParentIdByAreaId(area2);

				Area area3 = new Area();
				area3.setAreaId(area2.getAreaParentid());
				area3 = areaMapper.selectNameParentIdByAreaId(area3);

				Area area4 = new Area();
				area4.setAreaId(area3.getAreaParentid());
				area4 = areaMapper.selectNameParentIdByAreaId(area4);

				String village = areaName;
				String town = area.getAreaName();
				String county = area2.getAreaName();
				String city = area3.getAreaName();
				String province = area4.getAreaName();

				String huji = province + city + county + town + village;
				threeCheckServiceWithBLOBs.setThcsHouseholdregister(huji);

				threeCheckServiceWithBLOBs.setThcsLivestate("流出");
				record.addAll(threeCheckServiceMapper
						.findRecordBySelectOut4(threeCheckServiceWithBLOBs));
			} else {
				Area area5 = new Area();
				area5.setAreaId(id);
				area5 = areaMapper.selectAllByAreaId(area5);

				Area area6 = new Area();
				area6.setAreaId(area5.getAreaParentid());
				area6 = areaMapper.selectNameParentIdByAreaId(area6);

				Area area7 = new Area();
				area7.setAreaId(area6.getAreaParentid());
				area7 = areaMapper.selectNameParentIdByAreaId(area7);

				String town = areaName;
				String county = area5.getAreaName();
				String city = area6.getAreaName();
				String province = area7.getAreaName();

				String huji = province + city + county + town;
				threeCheckServiceWithBLOBs.setThcsHouseholdregister("%"+huji+"%");

				threeCheckServiceWithBLOBs.setThcsLivestate("流出");
				record.addAll(threeCheckServiceMapper
						.findRecordBySelectOut44(threeCheckServiceWithBLOBs));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return record;
		}
	}
	
	@SuppressWarnings("finally")
	public List<ThreeCheckServiceWithBLOBs> findRecordBySelectConditionOut5(
			int id, String level, String year, String patch, String areaName,
			String characterSelect) {
		List<ThreeCheckServiceWithBLOBs> record = new ArrayList<ThreeCheckServiceWithBLOBs>();
		ThreeCheckServiceWithBLOBs threeCheckServiceWithBLOBs = new ThreeCheckServiceWithBLOBs();
		try {
			threeCheckServiceWithBLOBs.setThcsYear(year);
			threeCheckServiceWithBLOBs.setThcsBatch(patch);
			threeCheckServiceWithBLOBs.setThcsPregnancy(characterSelect);
			if (level.equals("乡/镇级")) {
				Area area = new Area();
				area.setAreaId(id);
				area = areaMapper.selectAllByAreaId(area);

				Area area2 = new Area();
				area2.setAreaId(area.getAreaParentid());
				area2 = areaMapper.selectNameParentIdByAreaId(area2);

				Area area3 = new Area();
				area3.setAreaId(area2.getAreaParentid());
				area3 = areaMapper.selectNameParentIdByAreaId(area3);

				Area area4 = new Area();
				area4.setAreaId(area3.getAreaParentid());
				area4 = areaMapper.selectNameParentIdByAreaId(area4);

				String village = areaName;
				String town = area.getAreaName();
				String county = area2.getAreaName();
				String city = area3.getAreaName();
				String province = area4.getAreaName();

				String huji = province + city + county + town + village;
				threeCheckServiceWithBLOBs.setThcsHouseholdregister(huji);
				threeCheckServiceWithBLOBs.setThcsLivestate("流出");
				record = threeCheckServiceMapper
						.findRecordBySelectOut5(threeCheckServiceWithBLOBs);
			} else {
				Area area5 = new Area();
				area5.setAreaId(id);
				area5 = areaMapper.selectAllByAreaId(area5);

				Area area6 = new Area();
				area6.setAreaId(area5.getAreaParentid());
				area6 = areaMapper.selectNameParentIdByAreaId(area6);

				Area area7 = new Area();
				area7.setAreaId(area6.getAreaParentid());
				area7 = areaMapper.selectNameParentIdByAreaId(area7);

				String town = areaName;
				String county = area5.getAreaName();
				String city = area6.getAreaName();
				String province = area7.getAreaName();

				String huji = province + city + county + town;
				threeCheckServiceWithBLOBs.setThcsHouseholdregister("%"+huji+"%");
				threeCheckServiceWithBLOBs.setThcsLivestate("流出");
				record = threeCheckServiceMapper
						.findRecordBySelectOut55(threeCheckServiceWithBLOBs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return record;
		}
	}
	
	@SuppressWarnings("finally")
	public List<ThreeCheckServiceWithBLOBs> findRecordBySelectConditionOut6(
			int id, String level, String year, String patch, String areaName) {
		List<ThreeCheckServiceWithBLOBs> record = new ArrayList<ThreeCheckServiceWithBLOBs>();
		ThreeCheckServiceWithBLOBs threeCheckServiceWithBLOBs = new ThreeCheckServiceWithBLOBs();
		try {
			threeCheckServiceWithBLOBs.setThcsYear(year);
			threeCheckServiceWithBLOBs.setThcsBatch(patch);
			if (level.equals("乡/镇级")) {

				Area area = new Area();
				area.setAreaId(id);
				area = areaMapper.selectAllByAreaId(area);

				Area area2 = new Area();
				area2.setAreaId(area.getAreaParentid());
				area2 = areaMapper.selectNameParentIdByAreaId(area2);

				Area area3 = new Area();
				area3.setAreaId(area2.getAreaParentid());
				area3 = areaMapper.selectNameParentIdByAreaId(area3);

				Area area4 = new Area();
				area4.setAreaId(area3.getAreaParentid());
				area4 = areaMapper.selectNameParentIdByAreaId(area4);

				String village = areaName;
				String town = area.getAreaName();
				String county = area2.getAreaName();
				String city = area3.getAreaName();
				String province = area4.getAreaName();

				String huji = province + city + county + town + village;
				threeCheckServiceWithBLOBs.setThcsHouseholdregister(huji);
				threeCheckServiceWithBLOBs.setThcsLivestate("流出");
				record = threeCheckServiceMapper
						.findRecordBySelectOut6(threeCheckServiceWithBLOBs);
			} else {
				Area area5 = new Area();
				area5.setAreaId(id);
				area5 = areaMapper.selectAllByAreaId(area5);

				Area area6 = new Area();
				area6.setAreaId(area5.getAreaParentid());
				area6 = areaMapper.selectNameParentIdByAreaId(area6);

				Area area7 = new Area();
				area7.setAreaId(area6.getAreaParentid());
				area7 = areaMapper.selectNameParentIdByAreaId(area7);

				String town = areaName;
				String county = area5.getAreaName();
				String city = area6.getAreaName();
				String province = area7.getAreaName();

				String huji = province + city + county + town;
				threeCheckServiceWithBLOBs.setThcsHouseholdregister("%"+huji+"%");
				threeCheckServiceWithBLOBs.setThcsLivestate("流出");
				record = threeCheckServiceMapper
						.findRecordBySelectOut66(threeCheckServiceWithBLOBs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return record;
		}
	}

	@SuppressWarnings("finally")
	public List<ThreeCheckServiceWithBLOBs> findRecordBySelectConditionOut7(
			int id, String level, String year, String patch) {
		List<ThreeCheckServiceWithBLOBs> record = new ArrayList<ThreeCheckServiceWithBLOBs>();
		ThreeCheckServiceWithBLOBs threeCheckServiceWithBLOBs = new ThreeCheckServiceWithBLOBs();
		try {
			threeCheckServiceWithBLOBs.setThcsYear(year);
			threeCheckServiceWithBLOBs.setThcsBatch(patch);
			if (level.equals("乡/镇级")) {
				Area area = new Area();
				area.setAreaId(id);
				area = areaMapper.selectAllByAreaId(area);

				Area area2 = new Area();
				area2.setAreaId(area.getAreaParentid());
				area2 = areaMapper.selectNameParentIdByAreaId(area2);

				Area area3 = new Area();
				area3.setAreaId(area2.getAreaParentid());
				area3 = areaMapper.selectNameParentIdByAreaId(area3);

				Area area4 = new Area();
				area4.setAreaId(area3.getAreaParentid());
				area4 = areaMapper.selectNameParentIdByAreaId(area4);

				String town = area.getAreaName();
				String county = area2.getAreaName();
				String city = area3.getAreaName();
				String province = area4.getAreaName();

				String huji = province + city + county + town;
				threeCheckServiceWithBLOBs.setThcsHouseholdregister("%"+huji+"%");
				threeCheckServiceWithBLOBs.setThcsLivestate("流出");
				record = threeCheckServiceMapper
						.findRecordBySelectOut7(threeCheckServiceWithBLOBs);
			} else {
				Area area5 = new Area();
				area5.setAreaId(id);
				area5 = areaMapper.selectAllByAreaId(area5);

				Area area6 = new Area();
				area6.setAreaId(area5.getAreaParentid());
				area6 = areaMapper.selectNameParentIdByAreaId(area6);

				Area area7 = new Area();
				area7.setAreaId(area6.getAreaParentid());
				area7 = areaMapper.selectNameParentIdByAreaId(area7);

				String county = area5.getAreaName();
				String city = area6.getAreaName();
				String province = area7.getAreaName();

				String huji = province + city + county;
				threeCheckServiceWithBLOBs.setThcsHouseholdregister("%"+huji+"%");
				threeCheckServiceWithBLOBs.setThcsLivestate("流出");
				record = threeCheckServiceMapper
						.findRecordBySelectOut77(threeCheckServiceWithBLOBs);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return record;
		}
	}
	
	@SuppressWarnings("finally")
	public List<ThreeCheckServiceWithBLOBs> findRecordBySelectConditionOut8(
			int id, String level, String year, String patch,
			String characterSelect) {
		List<ThreeCheckServiceWithBLOBs> record = new ArrayList<ThreeCheckServiceWithBLOBs>();
		ThreeCheckServiceWithBLOBs threeCheckServiceWithBLOBs = new ThreeCheckServiceWithBLOBs();
		try {
			threeCheckServiceWithBLOBs.setThcsYear(year);
			threeCheckServiceWithBLOBs.setThcsBatch(patch);
			threeCheckServiceWithBLOBs.setThcsPregnancy(characterSelect);
			if (level.equals("乡/镇级")) {
				Area area = new Area();
				area.setAreaId(id);
				area = areaMapper.selectAllByAreaId(area);

				Area area2 = new Area();
				area2.setAreaId(area.getAreaParentid());
				area2 = areaMapper.selectNameParentIdByAreaId(area2);

				Area area3 = new Area();
				area3.setAreaId(area2.getAreaParentid());
				area3 = areaMapper.selectNameParentIdByAreaId(area3);

				Area area4 = new Area();
				area4.setAreaId(area3.getAreaParentid());
				area4 = areaMapper.selectNameParentIdByAreaId(area4);

				String town = area.getAreaName();
				String county = area2.getAreaName();
				String city = area3.getAreaName();
				String province = area4.getAreaName();

				String huji = province + city + county + town;
				threeCheckServiceWithBLOBs.setThcsHouseholdregister("%"+huji+"%");
				threeCheckServiceWithBLOBs.setThcsLivestate("流出");
				record = threeCheckServiceMapper
						.findRecordBySelectOut8(threeCheckServiceWithBLOBs);
			} else {
				Area area5 = new Area();
				area5.setAreaId(id);
				area5 = areaMapper.selectAllByAreaId(area5);

				Area area6 = new Area();
				area6.setAreaId(area5.getAreaParentid());
				area6 = areaMapper.selectNameParentIdByAreaId(area6);

				Area area7 = new Area();
				area7.setAreaId(area6.getAreaParentid());
				area7 = areaMapper.selectNameParentIdByAreaId(area7);

				String county = area5.getAreaName();
				String city = area6.getAreaName();
				String province = area7.getAreaName();

				String huji = province + city + county;
				threeCheckServiceWithBLOBs.setThcsHouseholdregister("%"+huji+"%");
				threeCheckServiceWithBLOBs.setThcsLivestate("流出");
				record = threeCheckServiceMapper
						.findRecordBySelectOut88(threeCheckServiceWithBLOBs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return record;
		}
	}
	
	@SuppressWarnings("finally")
	public List<ThreeCheckServiceWithBLOBs> getHuJiByUserArea(String areaName1) {
		List<ThreeCheckServiceWithBLOBs> areaList = new ArrayList<ThreeCheckServiceWithBLOBs>();
		ThreeCheckServiceWithBLOBs threeCheckServiceWithBLOBs = new ThreeCheckServiceWithBLOBs();
		try {
			threeCheckServiceWithBLOBs.setThcsHouseholdregister("%"+areaName1+"%");
			threeCheckServiceWithBLOBs.setThcsLivestate("流出");
			areaList = threeCheckServiceMapper
					.selectHuJiByUserArea(threeCheckServiceWithBLOBs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return areaList;
		}
	}

}
