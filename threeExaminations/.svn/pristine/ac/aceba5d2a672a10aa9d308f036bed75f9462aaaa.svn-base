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
	/**
	 * 通过身份证号检索历史服务记录
	 */
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
	
	/**
	 * 通过身份证号检索最新的服务记录
	 */	
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
	
	/**
	 * 人口类别选择全部，特征选择非全部，地区选择非全部，
	 * 查询所在地区常住、流入人口记录
	 */
	@SuppressWarnings("finally")
	public List<ThreeCheckServiceWithBLOBs> findRecordBySelectCondition1(
			int id, String level, String year, String patch, String areaName,
			String characterSelect) {
		// TODO Auto-generated method stub
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
	/**
	 * 人口类别选择全部，特征选择为全部，地区选择为非全部，查询现居住为本地的记录（常住和流入）
	 */
	@SuppressWarnings("finally")
	public List<ThreeCheckServiceWithBLOBs> findRecordBySelectCondition2(
			int id, String level, String year, String patch, String areaName) {
		// TODO Auto-generated method stub
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
	/**
	 * 人口类别为全部，特征选择为全部，地区选择为全部，查询现居住地为当前用户所在地的记录（常住和流入）
	 */
	@SuppressWarnings("finally")
	public List<ThreeCheckServiceWithBLOBs> findRecordBySelectCondition3(
			int id, String level, String year, String patch) {
		// TODO Auto-generated method stub
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
	/**
	 * 人口类别选择非全部，特征选择为全部，地区选择为全部
	 * 人口类别为常住、流入。只包含本地上传的记录常住和流入记录
	 */
	@SuppressWarnings("finally")
	public List<ThreeCheckServiceWithBLOBs> findRecordBySelectCondition4(
			int id, String level, String year, String patch, String peopleClass) {
		// TODO Auto-generated method stub
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
	/**
	 * 人口类别非全部，特征选择为非全部，地区选择为全部
	 * 人口类别为常住、流入。只包含本地上传的常住、流入记录
	 */
	@SuppressWarnings("finally")
	public List<ThreeCheckServiceWithBLOBs> findRecordBySelectCondition5(
			int id, String level, String year, String patch,
			String peopleClass, String characterSelect) {
		// TODO Auto-generated method stub
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
	/**
	 * 人口类别为非全部，特征选择费全部，地区非全部
	 * 人口类别为常住、流入。只包含本地上传的常住和流入记录
	 */
	@SuppressWarnings("finally")
	public List<ThreeCheckServiceWithBLOBs> findRecordBySelectCondition6(
			int id, String level, String year, String patch, String areaName,
			String peopleClass, String characterSelect) {
		// TODO Auto-generated method stub
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
	/**
	 * 人口类别为全部，特征选择为非全部，地区选择为全部
	 * 查询所在地区常住、流入人口记录
	 */
	@SuppressWarnings("finally")
	public List<ThreeCheckServiceWithBLOBs> findRecordBySelectCondition7(
			int id, String level, String year, String patch,
			String characterSelect) {
		// TODO Auto-generated method stub
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
	/**
	 * 人口类别选择非全部，特征选择为全部，地区选择非全部
	 * 人口类别为常住、流入。只包含本地上传的常住和流入记录
	 */
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
	/**
	 * 设置一条服务记录的所有信息
	 * @param areaId
	 * @param womanName
	 * @param womanIdCard
	 * @param womanProvince
	 * @param womanCity
	 * @param womanCounty
	 * @param womanTown
	 * @param womanVallige
	 * @param womanCurProvince
	 * @param womanCurCity
	 * @param womanCurCounty
	 * @param womanCurTown
	 * @param womanCurVallige
	 * @param liveState
	 * @param checkYear
	 * @param checkPatch
	 * @param checkDate
	 * @param checkPlace
	 * @param hoop
	 * @param pregnant
	 * @param disease
	 * @param checkSuggest
	 * @return
	 */
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
			
		/*//1.添加时，读取改妇女最近一条的服务记录，看是否是计外孕
		ThreeCheckServiceWithBLOBs recentlyRecord = new ThreeCheckServiceWithBLOBs();
		String huji="%" +womanProvince+womanCity+womanCounty+womanTown+"%";
		recentlyRecord = findNewRecordByIdCard(womanIdCard,"乡/镇级",womanCurTown,huji);
		//如果记录不为空
		if (recentlyRecord != null) {
			//如果最近一次记录为计外孕，这次还是计外孕，就默认为未补救，
			//这次是无孕或者计内孕，则这次为已补救
			if (recentlyRecord.getThcsPregnancy().equals("计外孕")) {
				if (pregnant.equals("计外孕")) {
					threeCheckServiceWithBLOBs.setThcsIsremedy(0);
				} else {
					threeCheckServiceWithBLOBs.setThcsIsremedy(1);
				}
			} else {
				threeCheckServiceWithBLOBs.setThcsIsremedy(0);
			}
		} else {
			threeCheckServiceWithBLOBs.setThcsIsremedy(0);
		}*/
		
		//2.默认设置为未补救
		threeCheckServiceWithBLOBs.setThcsIsremedy(0);
		
		threeCheckServiceWithBLOBs.setThcsThreecheckservicecol("0");		
		return threeCheckServiceWithBLOBs;
	}
	/**
	 * 更新一条服务记录
	 */
	public void updateService(ThreeCheckServiceWithBLOBs threeCheckServiceWithBLOBs){
		threeCheckServiceMapper.updateByPrimaryKey(threeCheckServiceWithBLOBs);
	}
	/**
	 * 处理添加个人服务记录的插入
	 */
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
			/*
			//1.添加时，读取改妇女最近一条的服务记录，看是否是计外孕
			ThreeCheckServiceWithBLOBs recentlyRecord = new ThreeCheckServiceWithBLOBs();
			String huji=womanProvince+womanCity+womanCounty+womanTown;
			recentlyRecord = findNewRecordByIdCard(womanIdCard,"乡/镇级",womanCurTown,huji);
			//如果记录不为空
			if (recentlyRecord != null) {
				//如果最近一次记录为计外孕，这次还是计外孕，就默认为未补救，
				//这次是无孕或者计内孕，则这次为已补救
				if (recentlyRecord.getThcsPregnancy().equals("计外孕")) {
					if (pregnant.equals("计外孕")) {
						threeCheckServiceWithBLOBs.setThcsIsremedy(0);
					} else {
						threeCheckServiceWithBLOBs.setThcsIsremedy(1);
					}
				} else {
					threeCheckServiceWithBLOBs.setThcsIsremedy(0);
				}
			} else {
				threeCheckServiceWithBLOBs.setThcsIsremedy(0);
			}*/
			
			//2.默认设置为未补救
			threeCheckServiceWithBLOBs.setThcsIsremedy(0);
			
			threeCheckServiceWithBLOBs.setThcsThreecheckservicecol("0");
			//插入数据库
			threeCheckServiceMapper.insert(threeCheckServiceWithBLOBs);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			return threeCheckServiceWithBLOBs;
		}
	}
	/**
	 * 批量导入服务记录，参数为登录用户地区id，登录用户地区全名，服务记录信息
	 */
	public void importlist(int areaId,String userAreaString, String idNumber, String name,
			String householdRegister, String nowLiveProvince,
			String nowLiveCity, String nowLiveCounty, String nowLiveTown,
			String nowLiveVillage, String liveState, String isExistCheckList,
			String checkState, String checkTime, String year, String batch,
			String noCheckReason, String checkSuggest, String disease,
			String hoop, String pregnancy, String isRemedy, String cheakPlace,
			String threeCheckServicecol) {
		// TODO Auto-generated method stub
		try {
			// set数据
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
			
			/*//1.导入时，读取该妇女最近一条的服务记录，看是否是计外孕
			//最近一条记录可能是上一批次的该妇女的记录，也可能是本批次已经上传过的该妇女的记录
			ThreeCheckServiceWithBLOBs recentlyRecord = null;
			recentlyRecord = findNewRecordByIdCard(idNumber,"乡/镇级",nowLiveTown,userAreaString);
			//System.out.println(recentlyRecord.getThcsIdnumber() +"======================");
			//记录不为空时
			if (recentlyRecord != null) {
				//如果最近一次记录为计外孕，这次还是计外孕，就默认为未补救，
				//这次是无孕或者计内孕，则这次为已补救
				if (recentlyRecord.getThcsPregnancy().equals("计外孕")) {
					if (pregnancy.equals("计外孕")) {
						threeCheckServiceWithBLOBs.setThcsIsremedy(0);
					} else {
						threeCheckServiceWithBLOBs.setThcsIsremedy(1);//最新记录为计外孕，现在为无孕，已补救
					}
				} else {
					threeCheckServiceWithBLOBs.setThcsIsremedy(0);
				}
			} else {
				int isremedy1 =Integer.parseInt(isRemedy);
				threeCheckServiceWithBLOBs.setThcsIsremedy(isremedy1);
			}*/
			//2.默认设置为未补救
			threeCheckServiceWithBLOBs.setThcsIsremedy(0);
			
			threeCheckServiceWithBLOBs.setThcsCheakplace(cheakPlace);
			threeCheckServiceWithBLOBs
					.setThcsThreecheckservicecol(threeCheckServicecol);
			
			// 判重,年度、批次、身份证号、居住状态
			ThreeCheckServiceWithBLOBs record = new ThreeCheckServiceWithBLOBs();
			record.setThcsIdnumber(idNumber);
			record.setThcsYear(year);
			record.setThcsBatch(batch);
			record.setThcsLivestate(liveState);
			/*record.setThcsPregnancy(pregnancy);
			record.setThcsHoop(hoop);
			record.setThcsDisease(disease);
			record = threeCheckServiceMapper.findRepeat(record);*/
			
			record = threeCheckServiceMapper.findRepeat2(record);
			//System.out.println(record+"++++++++++++++++++++++++");
			// 如果不存在同年度、批次、居住状态、身份证号的记录，就插入
			if (record == null) {
				threeCheckServiceMapper.insert(threeCheckServiceWithBLOBs);
				//记录中的户籍村
				String householdRegisterVillage = threeCheckServiceWithBLOBs.getThcsHouseholdregister().replace(userAreaString, "");
				//更新统计表中所有级别地区的统计信息
				updateCountReportByInsertService(areaId,householdRegisterVillage,threeCheckServiceWithBLOBs);//userAreaString是登陆用户的地区的全名，以便获取记录中的户籍村

			} else if(record != null && record.getThcsCheckstate().equals("已查")//再次上传同年度、批次、身份证号，并且环病孕一样的已查记录，即重复，不做改变
					&& record.getThcsHoop().equals(hoop) 
					&& record.getThcsDisease().equals(disease)
					&& record.getThcsPregnancy().equals(pregnancy)){
				
			} else {
				// 如果该地区已经存在该年度、批次、身份证号、居住状态的记录，就根据记录内容覆盖更新，以最新的已查服务记录为准
				//再次上传的为已查记录,则更新原纪录
				if(checkState.equals("已查")){
					//原纪录为未查，则直接覆盖更新记录，并更新统计表
					if(record.getThcsCheckstate().equals("未查")){
						threeCheckServiceWithBLOBs.setThcsId(record.getThcsId());
						threeCheckServiceMapper.updateByPrimaryKey(threeCheckServiceWithBLOBs);
						
						//记录中的户籍村
						String householdRegisterVillage = threeCheckServiceWithBLOBs.getThcsHouseholdregister().replace(userAreaString, "");						
						//根据更新记录的内容和原记录的内容去更新统计表中所有级别地区的统计内容
						updateCountReportByUpdateService(areaId,householdRegisterVillage,threeCheckServiceWithBLOBs,record);
					} else {//原记录为已查，只是环病孕情况有改变，则更新原记录的病和环情况，但是计外孕状态不更新，只是改变补救状态，并更新统计表						
						
						//存在该年度、批次、身份证号，根据查孕内容设置补救状态
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
						//因为要用更新前的原记录和新纪录进行比较更新统计表，所有先比较更新统计表，然后才更新原记录
						//记录中的户籍村
						String householdRegisterVillage = threeCheckServiceWithBLOBs.getThcsHouseholdregister().replace(userAreaString, "");
						//根据更新记录的内容和原记录的内容去更新统计表中所有级别地区的统计内容
						updateCountReportByUpdateService(areaId,householdRegisterVillage,threeCheckServiceWithBLOBs,record);
						
						//更新原记录
						record.setThcsHoop(threeCheckServiceWithBLOBs.getThcsHoop());
						record.setThcsDisease(threeCheckServiceWithBLOBs.getThcsDisease());
						record.setThcsIsremedy(threeCheckServiceWithBLOBs.getThcsIsremedy());//更新原记录的补救状态
						System.out.println(record.getThcsIsremedy()+"++++++++++++++++++++++++");
						if(!record.getThcsPregnancy().equals("计外孕")){
							record.setThcsPregnancy(threeCheckServiceWithBLOBs.getThcsPregnancy());//非计外孕变成计外孕，则孕状态要改变
						}
						threeCheckServiceMapper.updateByPrimaryKey(record);						
					}
										
				}	//再次上传的是未查记录，则不做改变			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 插入新的服务记录时，更新省、市、县区、乡镇、村每个级别的统计信息，插入新的统计信息或更新已有统计信息
	 * @param areaId
	 * @param nowArea 
	 * @param threeCheckServiceWithBLOBs
	 */
	public void updateCountReportByInsertService(int areaId,String householdRegisterVillage,ThreeCheckServiceWithBLOBs threeCheckServiceWithBLOBs){
		
		// 更新村级的统计表，areaId是当前登录的镇级用户的id，即镇级地区作为村级统计表中的父类id
		if(threeCheckServiceWithBLOBs.getThcsLivestate().equals("流出")){//记录为流出人口时，其村统计应该是增加户籍地村的的统计人数
			updateCountReport(areaId, householdRegisterVillage,threeCheckServiceWithBLOBs);
		}else{//常住和流出，村统计是增加现居住地的村统计人数
		    updateCountReport(areaId, threeCheckServiceWithBLOBs.getThcsNowlivevillage(),threeCheckServiceWithBLOBs);
		}

		// 更新镇级的统计表
		Area area = new Area();//当前登录的镇级用户的地区
		area.setAreaId(areaId);
		area = areaMapper.selectNameParentIdByAreaId(area);
		int townLevelParentAreaId = area.getAreaParentid();//当前登录的镇级用户的父类id，即县/区级地区作为镇级统计中的父id
		String townLevelAreaName = area.getAreaName();
		updateCountReport(townLevelParentAreaId, townLevelAreaName,
				threeCheckServiceWithBLOBs);

		// 更新县区级的统计表
		Area area2 = new Area();//当前登录用户的直系县区级地区
		area2.setAreaId(area.getAreaParentid());
		area2 = areaMapper.selectNameParentIdByAreaId(area2);
		int countyLevelParentAreaId = area2.getAreaParentid();//父Id为市级
		String countyLevelAreaName = area2.getAreaName();
		updateCountReport(countyLevelParentAreaId, countyLevelAreaName,
				threeCheckServiceWithBLOBs);

		// 更新市级的统计表
		Area area3 = new Area();//当前登录用户的直系市级地区
		area3.setAreaId(area2.getAreaParentid());
		area3 = areaMapper.selectNameParentIdByAreaId(area3);
		int cityLevelParentAreaId = area3.getAreaParentid();//父id为省级
		String cityLevelAreaName = area3.getAreaName();
		updateCountReport(cityLevelParentAreaId, cityLevelAreaName,
				threeCheckServiceWithBLOBs);

		// 更新省级的统计表
		Area area4 = new Area();//当前登录用的的直系省级地区
		area4.setAreaId(area3.getAreaParentid());
		area4 = areaMapper.selectNameParentIdByAreaId(area4);
		int provinceLevelAreaId = area4.getAreaParentid();//父id为0
		String provinceLevelParentAreaName = area4.getAreaName();
		updateCountReport(provinceLevelAreaId, provinceLevelParentAreaName,
				threeCheckServiceWithBLOBs);
	}
	
	/**
	 * 插入新的服务记录时，根据记录内容和不同的地区父类id、地区等，更新统计表，插入一条新的统计信息或更新已有的一条统计信息
	 * @param id
	 * @param nowArea
	 * @param threeCheckServiceWithBLOBs
	 */
	public void updateCountReport(int id, String nowArea,
			ThreeCheckServiceWithBLOBs threeCheckServiceWithBLOBs) {
		try {
			ThreeCheckCount CountInfo = null;//用来保存查询结果的变量
			ThreeCheckCount CountInfo1 = new ThreeCheckCount();//查询原统计表中是否有某地区的统计信息
			ThreeCheckCount CountInfo11 = new ThreeCheckCount();//存放插入的新地区的统计信息
			
			//CountInfo1用于在更新之前进行查找是否存在该条件下的统计记录
			CountInfo1.setThccYear(threeCheckServiceWithBLOBs.getThcsYear());
			CountInfo1.setThccBatch(threeCheckServiceWithBLOBs.getThcsBatch());
			CountInfo1.setThccLivestate(threeCheckServiceWithBLOBs
					.getThcsLivestate());
			CountInfo1.setThccVillage(nowArea);
			CountInfo1.setThccParentAreaId(id);
			CountInfo = threeCheckCountMapper.findRecord(CountInfo1);
			
			//如果没有该条件下的统计记录就插入
			if (CountInfo == null) {
				CountInfo11.setThccYear(threeCheckServiceWithBLOBs
						.getThcsYear());//年度
				CountInfo11.setThccBatch(threeCheckServiceWithBLOBs
						.getThcsBatch());//批次
				CountInfo11.setThccLivestate(threeCheckServiceWithBLOBs
						.getThcsLivestate());//居住状态
				CountInfo11.setThccVillage(nowArea);//居住地区
				CountInfo11.setThccParentAreaId(id);//居住地区的父类ID
				CountInfo11.setThccShouldchecknumber(1);//应查人数默认为1
				
				//如果记录为已查，则已查人数为1，免查人数为0
				if (threeCheckServiceWithBLOBs.getThcsCheckstate().equals("已查")) {
					CountInfo11.setThccHavechecknumber(1);
					CountInfo11.setThccCheckfreenumber(0);
				} else {
					CountInfo11.setThccHavechecknumber(0);
					CountInfo11.setThccCheckfreenumber(1);
				}
				//如果记录为有环，则环人数为1，反之为0
				if (threeCheckServiceWithBLOBs.getThcsHoop().equals(1)) {
					CountInfo11.setThccHavehoopnumber(1);
				} else {
					CountInfo11.setThccHavehoopnumber(0);
				}
				//如果记录为计外孕，则计外孕人数为1，反之为0
				if (threeCheckServiceWithBLOBs.getThcsPregnancy().equals("计外孕")) {
					CountInfo11.setThccOutplanprenumber(1);
				} else {
					CountInfo11.setThccOutplanprenumber(0);
				}
				//如果记录为已补救，则补救人数为1，反之为0
				if (threeCheckServiceWithBLOBs.getThcsIsremedy().equals(1)) {
					CountInfo11.setThccRemedynumber(1);
				} else {
					CountInfo11.setThccRemedynumber(0);
				}
				//如果记录为无病，则妇科病人数为0，反之为1
				if (threeCheckServiceWithBLOBs.getThcsDisease().equals("无病")) {
					CountInfo11.setThccGymornumber(0);
				} else {
					CountInfo11.setThccGymornumber(1);
				}
				//执行条件插入
				threeCheckCountMapper.insert(CountInfo11);
			}
			//如果有该条件下的统计记录，则直接条件更新
			else {

				/*threeCheckCount.setThccVillage(nowArea);
				threeCheckCount.setThccParentAreaId(id);
				//根据不同的地区，地区父类ID，进行每一级的更新
				setSelect(threeCheckServiceWithBLOBs, threeCheckCount);
				threeCheckCountMapper.updateInfo(threeCheckCount);*/
				
				//应查人数+1
				int shouldCount = CountInfo.getThccShouldchecknumber() + 1;
				CountInfo.setThccShouldchecknumber(shouldCount);
				//该记录为已查，已查人数+1，免查人数不变；否则已查人数不变，免查人数+1
				String checkState = threeCheckServiceWithBLOBs.getThcsCheckstate();
				if (checkState.equals("已查")) {
					int haveCount = CountInfo.getThccHavechecknumber() + 1;
					CountInfo.setThccHavechecknumber(haveCount);
					/*int noCount = CountInfo.getThccCheckfreenumber();
					CountInfo.setThccCheckfreenumber(noCount);*/
				} else {
					/*int haveCount = CountInfo.getThccHavechecknumber();
					CountInfo.setThccHavechecknumber(haveCount);*/
					int noCount = CountInfo.getThccCheckfreenumber() + 1;
					CountInfo.setThccCheckfreenumber(noCount);
				}
				//该记录为有环，则有环人数+1，反之有环人数不变
				String haveHoop = threeCheckServiceWithBLOBs.getThcsHoop();
				if (haveHoop.equals("有环")) {
					int hoopCount = CountInfo.getThccHavehoopnumber() + 1;
					CountInfo.setThccHavehoopnumber(hoopCount);
				} else {
					/*int hoopCount = CountInfo.getThccHavehoopnumber();
					CountInfo.setThccHavehoopnumber(hoopCount);*/
				}
				//该记录为计外孕，则计外孕人数+1，反之计外孕人数不变
				String outYun = threeCheckServiceWithBLOBs.getThcsPregnancy();
				if (outYun.equals("计外孕")) {
					int outCount = CountInfo.getThccOutplanprenumber() + 1;
					CountInfo.setThccOutplanprenumber(outCount);
				} else {
					/*int outCount = CountInfo.getThccOutplanprenumber();
					CountInfo.setThccOutplanprenumber(outCount);*/
				}
				//该记录为已补救，则补救人数+1，反之补救人数不变
				int isbujiu = threeCheckServiceWithBLOBs.getThcsIsremedy();
				if (isbujiu != 0) {
					int bujiuCount = CountInfo.getThccRemedynumber() + 1;
					CountInfo.setThccRemedynumber(bujiuCount);
				} else {
					/*int bujiuCount = CountInfo.getThccRemedynumber();
					CountInfo.setThccRemedynumber(bujiuCount);*/
				}
				//该记录为无病，则妇科病人数不变，反之+1
				String Bing = threeCheckServiceWithBLOBs.getThcsDisease();
				if (Bing.equals("无病")) {
					/*int bingCount = CountInfo.getThccGymornumber();
					CountInfo.setThccGymornumber(bingCount);*/
				} else {
					int bingCount = CountInfo.getThccGymornumber() + 1;
					CountInfo.setThccGymornumber(bingCount);
				}
				
				threeCheckCountMapper.updateByPrimaryKey(CountInfo);//更新该地区的统计信息
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 覆盖更新服务记录时，更新省、市、县区、乡镇、村各个级别的统计信息，更新统计表中已有的多条统计信息
	 * @param areaId
	 * @param householdRegisterVillage
	 * @param threeCheckServiceWithBLOBs//新纪录
	 * @param record//查询到的老纪录
	 */
	public void updateCountReportByUpdateService(int areaId,String householdRegisterVillage,
			ThreeCheckServiceWithBLOBs threeCheckServiceWithBLOBs,
			ThreeCheckServiceWithBLOBs record){
		// 更新村级的统计表，areaId是当前登录的镇级用户的id，即镇级地区作为村级统计表中的父类id
		if(threeCheckServiceWithBLOBs.getThcsLivestate().equals("流出")){//记录为流出人口时，其村统计应该是更新户籍地村的的统计人数
			updateCountReport2(areaId, householdRegisterVillage,threeCheckServiceWithBLOBs,record);
		}else{//常住和流出，村统计是更新现居住地的村统计人数
		    updateCountReport2(areaId, threeCheckServiceWithBLOBs.getThcsNowlivevillage(),threeCheckServiceWithBLOBs,record);
		}

		// 更新镇级的统计表
		Area area = new Area();//当前登录的镇级用户的地区
		area.setAreaId(areaId);
		area = areaMapper.selectNameParentIdByAreaId(area);
		int townLevelParentAreaId = area.getAreaParentid();//当前登录的镇级用户的父类id，即县/区级地区作为镇级统计中的父id
		String townLevelAreaName = area.getAreaName();
		updateCountReport2(townLevelParentAreaId, townLevelAreaName,
				threeCheckServiceWithBLOBs,record);

		// 更新县区级的统计表
		Area area2 = new Area();//当前登录用户的直系县区级地区
		area2.setAreaId(area.getAreaParentid());
		area2 = areaMapper.selectNameParentIdByAreaId(area2);
		int countyLevelParentAreaId = area2.getAreaParentid();//父Id为市级
		String countyLevelAreaName = area2.getAreaName();
		updateCountReport2(countyLevelParentAreaId, countyLevelAreaName,
				threeCheckServiceWithBLOBs,record);

		// 更新市级的统计表
		Area area3 = new Area();//当前登录用户的直系市级地区
		area3.setAreaId(area2.getAreaParentid());
		area3 = areaMapper.selectNameParentIdByAreaId(area3);
		int cityLevelParentAreaId = area3.getAreaParentid();//父id为省级
		String cityLevelAreaName = area3.getAreaName();
		updateCountReport2(cityLevelParentAreaId, cityLevelAreaName,
				threeCheckServiceWithBLOBs,record);

		// 更新省级的统计表
		Area area4 = new Area();//当前登录用的的直系省级地区
		area4.setAreaId(area3.getAreaParentid());
		area4 = areaMapper.selectNameParentIdByAreaId(area4);
		int provinceLevelAreaId = area4.getAreaParentid();//父id为0
		String provinceLevelParentAreaName = area4.getAreaName();
		updateCountReport2(provinceLevelAreaId, provinceLevelParentAreaName,
				threeCheckServiceWithBLOBs,record);
	}
	/**
	 * 覆盖更新服务记录时，根据新的已查纪录和原记录的服务记录内容和不同父类地区id，更新统计表中已有的一条统计信息
	 * @param areaId
	 * @param nowArea
	 * @param threeCheckServiceWithBLOBs
	 * @param record
	 */
	public void updateCountReport2(int areaId, String nowArea,
			ThreeCheckServiceWithBLOBs threeCheckServiceWithBLOBs,ThreeCheckServiceWithBLOBs record) {
		try {
			ThreeCheckCount CountInfo1 = new ThreeCheckCount();//用来查询的变量
			ThreeCheckCount CountInfo = null;//保存查询结果的变量
			CountInfo1.setThccYear(threeCheckServiceWithBLOBs.getThcsYear());
			CountInfo1.setThccBatch(threeCheckServiceWithBLOBs.getThcsBatch());
			CountInfo1.setThccLivestate(threeCheckServiceWithBLOBs
					.getThcsLivestate());
			CountInfo1.setThccVillage(nowArea);						
			CountInfo1.setThccParentAreaId(areaId);
			CountInfo = threeCheckCountMapper.findRecord(CountInfo1);
			if (CountInfo != null) {//覆盖更新服务记录时时，地区没有发生变化，则有该地区的统计记录
				System.out.println(threeCheckServiceWithBLOBs.getThcsIsremedy()+"=======================");
				// 有该类别的服务记录记录，必有该类别的统计记录，找到该统计记录，并更新
				// 未查变成已查
				if (record.getThcsCheckstate().equals("未查")
						&& threeCheckServiceWithBLOBs.getThcsCheckstate()
								.equals("已查")) {
					// 未查变成已查，应查人数不变，已查人数+1，为查人数-1
					int haveCount = CountInfo.getThccHavechecknumber() + 1;
					CountInfo.setThccHavechecknumber(haveCount);
					int noCount = CountInfo.getThccCheckfreenumber() - 1;
					CountInfo.setThccCheckfreenumber(noCount);
				}
				// 无环变有环，有环+1
				if (record.equals("无环")
						&& threeCheckServiceWithBLOBs.getThcsHoop()
								.equals("有环")) {
					int hoopCount = CountInfo.getThccHavehoopnumber() + 1;
					CountInfo.setThccHavehoopnumber(hoopCount);
				} else if (record.equals("有环")
						&& threeCheckServiceWithBLOBs.getThcsHoop()
								.equals("无环")) {// 有环变无环，有环-1
					int hoopCount = CountInfo.getThccHavehoopnumber() - 1;
					CountInfo.setThccHavehoopnumber(hoopCount);
				}
				// 无孕变计外孕,计外孕+1；计外孕变无孕，计外孕人数还是不改变，只是改变补救人数
				if (record.getThcsPregnancy().equals("无孕")
						&& threeCheckServiceWithBLOBs.getThcsPregnancy()
								.equals("计外孕")) {
					int outCount = CountInfo.getThccOutplanprenumber() + 1;
					CountInfo.setThccOutplanprenumber(outCount);
				} /*else if (record.getThcsPregnancy().equals("计外孕")
						&& !threeCheckServiceWithBLOBs.getThcsPregnancy()
								.equals("计外孕")) {
					int bujiuCount = CountInfo.getThccRemedynumber() + 1;
					CountInfo.setThccRemedynumber(bujiuCount);
				}*/
				
				 //原记录为未补救，该记录为已补救，则补救人数+1，反之补救人数不变 
				if (threeCheckServiceWithBLOBs.getThcsIsremedy()==1 && record.getThcsIsremedy()!=1) { 
					int bujiuCount = CountInfo.getThccRemedynumber() + 1;
				 CountInfo.setThccRemedynumber(bujiuCount); }
				 
				// 无病变有病，有病人数+1
				if (record.getThcsDisease().equals("无病")
						&& !threeCheckServiceWithBLOBs.getThcsDisease().equals(
								"无病")) {
					int bingCount = CountInfo.getThccGymornumber() + 1;
					CountInfo.setThccGymornumber(bingCount);
				}

				threeCheckCountMapper.updateByPrimaryKey(CountInfo);// 更新该地区的统计信息
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
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		List<ThreeCheckServiceWithBLOBs> areaList = new ArrayList<ThreeCheckServiceWithBLOBs>();
		//ThreeCheckServiceWithBLOBs threeCheckServiceWithBLOBs = new ThreeCheckServiceWithBLOBs();
		try {
			//threeCheckServiceWithBLOBs.setThcsNowlivetown(areaName);
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
		// TODO Auto-generated method stub
		try {
			threeCheckCount.setThccYear(threeCheckServiceWithBLOBs
					.getThcsYear());//设置年度
			threeCheckCount.setThccBatch(threeCheckServiceWithBLOBs
					.getThcsBatch());//设置批次
			threeCheckCount.setThccLivestate(threeCheckServiceWithBLOBs
					.getThcsLivestate());//设置居住状态

			//在更新前，将已存在的统计记录读出来进行比较，然后更新
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

			//应查人数+1
			int shouldCount = readRecord.getThccShouldchecknumber() + 1;
			threeCheckCount.setThccShouldchecknumber(shouldCount);
			
			//该记录为已查，已查人数+1，免查人数不变；否则已查人数不变，免查人数+1
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
			//该记录为有环，则有环人数+1，反之有环人数不变
			String haveHoop = threeCheckServiceWithBLOBs.getThcsHoop();
			if (haveHoop.equals("有环")) {
				int hoopCount = readRecord.getThccHavehoopnumber() + 1;
				threeCheckCount.setThccHavehoopnumber(hoopCount);
			} else {
				int hoopCount = readRecord.getThccHavehoopnumber();
				threeCheckCount.setThccHavehoopnumber(hoopCount);
			}
			//该记录为计外孕，则计外孕人数+1，反之计外孕人数不变
			String outYun = threeCheckServiceWithBLOBs.getThcsPregnancy();
			if (outYun.equals("计外孕")) {
				int outCount = readRecord.getThccOutplanprenumber() + 1;
				threeCheckCount.setThccOutplanprenumber(outCount);
			} else {
				int outCount = readRecord.getThccOutplanprenumber();
				threeCheckCount.setThccOutplanprenumber(outCount);
			}
			//该记录为已补救，则补救人数+1，反之补救人数不变
			int isbujiu = threeCheckServiceWithBLOBs.getThcsIsremedy();
			if (isbujiu != 0) {
				int bujiuCount = readRecord.getThccRemedynumber() + 1;
				threeCheckCount.setThccRemedynumber(bujiuCount);
			} else {
				int bujiuCount = readRecord.getThccRemedynumber();
				threeCheckCount.setThccRemedynumber(bujiuCount);
			}
			//该记录为无病，则妇科病人数不变，反之+1
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
	/**
	 * 人口类别选择非全部，特征选择为全部，地区选择非全部
	 * 人口类别为流出查询:1.户籍地+流入（别的地方上传的记录）;2.户籍地+流出（寄证回来本地添加的记录）
	 * 再次修改需求，还原为开始的版本，不同步，只能查看本地上传的记录,只有2
	 */
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
				System.out.println("@@@@@@@@@@@@@@@@@@"+huji);
				
				/*threeCheckServiceWithBLOBs.setThcsLivestate("流入");
				record.addAll(threeCheckServiceMapper
						.findRecordBySelectOut1(threeCheckServiceWithBLOBs));*/
				
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

				/*threeCheckServiceWithBLOBs.setThcsLivestate("流入");
				record.addAll(threeCheckServiceMapper
						.findRecordBySelectOut11(threeCheckServiceWithBLOBs));*/
				
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
	/**
	 * 人口类别选择非全部，特征选择为全部，地区选择为全部
	 * 人口类别为流出查询:1.户籍地+流入（别的地方上传的记录）;2.户籍地+流出（寄证回来本地添加的记录）
	 * 再次修改需求，还原为开始的版本，不同步，只能查看本地上传的记录,只有2
	 */
	@SuppressWarnings("finally")
	public List<ThreeCheckServiceWithBLOBs> findRecordBySelectConditionOut2(
			int id, String level, String year, String patch, String peopleClass) {
		List<ThreeCheckServiceWithBLOBs> record = new ArrayList<ThreeCheckServiceWithBLOBs>();//某一户籍地的所有流出人口记录
		ThreeCheckServiceWithBLOBs threeCheckServiceWithBLOBs = new ThreeCheckServiceWithBLOBs();
		try {
			//年度、批次
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

				/*//户籍地+流入
				threeCheckServiceWithBLOBs.setThcsLivestate("流入");
				record.addAll(threeCheckServiceMapper
						.findRecordBySelectOut2(threeCheckServiceWithBLOBs));*/
				//户籍地+流出
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

				/*//户籍地+流入
				threeCheckServiceWithBLOBs.setThcsLivestate("流入");
				record.addAll(threeCheckServiceMapper
						.findRecordBySelectOut22(threeCheckServiceWithBLOBs));*/
				
				//户籍地+流出
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
	/**
	 * 人口类别非全部，特征选择为非全部，地区选择为全部
	 * 人口类别为流出查询:1.户籍地+流入（别的地方上传的记录）;2.户籍地+流出（寄证回来本地添加的记录）
	 * 再次修改需求，还原为开始的版本，不同步，只能查看本地上传的记录,只有2
	 */
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

				/*threeCheckServiceWithBLOBs.setThcsLivestate("流入");
				record.addAll(threeCheckServiceMapper
						.findRecordBySelectOut3(threeCheckServiceWithBLOBs));*/
				
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

				/*threeCheckServiceWithBLOBs.setThcsLivestate("流入");
				record.addAll(threeCheckServiceMapper
						.findRecordBySelectOut33(threeCheckServiceWithBLOBs));*/
				
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
	/**
	 * 人口类别为非全部，特征选择费全部，地区非全部
	 * 人口类别为流出查询:1.户籍地+流入（别的地方上传的记录）;2.户籍地+流出（寄证回来本地添加的记录）
	 * 再次修改需求，还原为开始的版本，不同步，只能查看本地上传的记录,只有2
	 */
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

				/*threeCheckServiceWithBLOBs.setThcsLivestate("流入");
				record.addAll(threeCheckServiceMapper
						.findRecordBySelectOut4(threeCheckServiceWithBLOBs));*/
				
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

				/*threeCheckServiceWithBLOBs.setThcsLivestate("流入");
				record.addAll(threeCheckServiceMapper
						.findRecordBySelectOut44(threeCheckServiceWithBLOBs));*/
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
	/**
	 * 人口类别选择全部，特征选择非全部，地区选择非全部，查询所在地区流出人口记录
	 * 查询户籍地为当前用户所在地区的流出记录
	 */
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
	/**
	 * 人口类别选择全部，特征选择为全部，地区选择为非全部，
	 * 查询户籍地为当前用户所在地区（常住和流出）
	 */
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
	/**
	 * 人口类别为全部，特征选择为全部，地区选择为全部，
	 * 查询户籍地为当前用户所在地区的记录（常住和流出）
	 */
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
	/**
	 * 人口类别为全部，特征选择为非全部，地区选择为全部
	 * 查询所在地区流出人口记录（户籍地为当前用户所在地区，现居住地为其他地区，居住状态为流入）
	 */
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
	/**
	 * 通过用户所在地区获取户籍地，模糊查找
	 */
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
