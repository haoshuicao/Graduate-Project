package swust.edu.cn.threeExaminations.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import swust.edu.cn.threeExaminations.dao.AreaMapper;
import swust.edu.cn.threeExaminations.dao.ThreeCheckCountMapper;
import swust.edu.cn.threeExaminations.dao.ThreeCheckServiceMapper;
import swust.edu.cn.threeExaminations.model.Area;
import swust.edu.cn.threeExaminations.model.ThreeCheckCount;
import swust.edu.cn.threeExaminations.model.ThreeCheckServiceWithBLOBs;
import swust.edu.cn.threeExaminations.service.ThreeCheckCountService;


@Service("threeCheckCountService")
public class ThreeCheckCountServiceImpl implements ThreeCheckCountService{
	private ThreeCheckServiceMapper threeCheckServiceMapper;
	public ThreeCheckServiceMapper getThreeCheckServiceMapper() {
		return threeCheckServiceMapper;
	}
	@Autowired
	public void setThreeCheckServiceMapper(
			ThreeCheckServiceMapper threeCheckServiceMapper) {
		this.threeCheckServiceMapper = threeCheckServiceMapper;
	}
	private ThreeCheckCount publicReadRecord;
	
	private void setPublicReadRecord(ThreeCheckCount publicReadRecord) {
		this.publicReadRecord = publicReadRecord;
	}
	private ThreeCheckCountMapper threeCheckCountMapper;
  
	public ThreeCheckCountMapper getThreeCheckCountMapper() {
		return threeCheckCountMapper;
	}
	@Autowired
	public void setThreeCheckCountMapper(ThreeCheckCountMapper threeCheckCountMapper) {
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
	@SuppressWarnings("finally")
	public List<ThreeCheckCount> searchBySelect1(int id,String level,String year, String patch) {
		List<ThreeCheckCount> record = new ArrayList<ThreeCheckCount>();
		ThreeCheckCount threeCheckCount = new ThreeCheckCount();
		try {
			threeCheckCount.setThccYear(year);
			threeCheckCount.setThccBatch(patch);
			threeCheckCount.setThccParentAreaId(id);
			record = threeCheckCountMapper.searchBySelect1(threeCheckCount);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return record;
		}
	}
	@SuppressWarnings("finally")
	public List<ThreeCheckCount> searchBySelect2(int id,String level,String year, String patch,
			String vallige) {
		List<ThreeCheckCount> record = new ArrayList<ThreeCheckCount>();
		ThreeCheckCount threeCheckCount = new ThreeCheckCount();
		try {
			threeCheckCount.setThccYear(year);
			threeCheckCount.setThccBatch(patch);
			threeCheckCount.setThccParentAreaId(id);
			threeCheckCount.setThccVillage(vallige);
			record = threeCheckCountMapper.searchBySelect2(threeCheckCount);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return record;
		}
	}
	@SuppressWarnings("finally")
	public List<ThreeCheckCount> searchBySelect3(int id,String level,String year, String patch,
			String peopleClass) {
		List<ThreeCheckCount> record = new ArrayList<ThreeCheckCount>();
		ThreeCheckCount threeCheckCount = new ThreeCheckCount();
		try {
			threeCheckCount.setThccYear(year);
			threeCheckCount.setThccBatch(patch);
			threeCheckCount.setThccParentAreaId(id);
			threeCheckCount.setThccLivestate(peopleClass);
			record = threeCheckCountMapper.searchBySelect3(threeCheckCount);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return record;
		}
	}
	@SuppressWarnings("finally")
	public List<ThreeCheckCount> searchBySelect4(int id,String level,String year, String patch,
			String vallige, String peopleClass) {
		List<ThreeCheckCount> record = new ArrayList<ThreeCheckCount>();
		ThreeCheckCount threeCheckCount = new ThreeCheckCount();
		try {
			threeCheckCount.setThccYear(year);
			threeCheckCount.setThccBatch(patch);
			threeCheckCount.setThccParentAreaId(id);
			threeCheckCount.setThccVillage(vallige);
			threeCheckCount.setThccLivestate(peopleClass);
			record = threeCheckCountMapper.searchBySelect4(threeCheckCount);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return record;
		}
	}
	public void update(ThreeCheckServiceWithBLOBs insertRecord,int areaId,String womanName, String womanIdCard,
			String womanProvince, String womanCity, String womanCounty,
			String womanTown, String womanVallige, String womanCurProvince,
			String womanCurCity, String womanCurCounty, String womanCurTown,
			String womanCurVallige, String liveState,String checkYear, String checkPatch,
			String checkDate, String checkPlace, String hoop, String pregnant,
			String disease, String checkSuggest) {
		try {
			ThreeCheckCount threeCheckCount = new ThreeCheckCount();
			if(liveState.equals("流出")){
				threeCheckCount.setThccVillage(womanVallige);
			}else{
				threeCheckCount.setThccVillage(womanCurVallige);
			}
			threeCheckCount.setThccParentAreaId(areaId);
			setSelect(insertRecord,areaId,womanName, womanIdCard, womanProvince,
					 womanCity,womanCounty,womanTown,womanVallige,
					 womanCurProvince,womanCurCity,womanCurCounty,
					 womanCurTown,womanCurVallige,liveState,checkYear,checkPatch,
					 checkDate,checkPlace,hoop,
					 pregnant,disease, checkSuggest,threeCheckCount);
			if(publicReadRecord==null){
				threeCheckCountMapper.insert(threeCheckCount);
			}else{
				threeCheckCountMapper.updateInfo(threeCheckCount);
			}
			ThreeCheckCount threeCheckCount1 = new ThreeCheckCount();
			Area area = new Area();
			area.setAreaId(areaId);
			area = areaMapper.selectNameParentIdByAreaId(area);
			threeCheckCount1.setThccVillage(area.getAreaName());
			threeCheckCount1.setThccParentAreaId(area.getAreaParentid());
			setSelect(insertRecord,area.getAreaParentid(),womanName, womanIdCard, womanProvince,
					 womanCity,womanCounty,womanTown,womanVallige,
					 womanCurProvince,womanCurCity,womanCurCounty,
					 womanCurTown,womanCurVallige,liveState,checkYear,checkPatch,
					 checkDate,checkPlace,hoop,
					 pregnant,disease, checkSuggest,threeCheckCount1);
			if(publicReadRecord==null){
				threeCheckCountMapper.insert(threeCheckCount1);
			}else{
				threeCheckCountMapper.updateInfo(threeCheckCount1);
			}
			ThreeCheckCount threeCheckCount2 = new ThreeCheckCount();
			Area area2 = new Area();
			area2.setAreaId(area.getAreaParentid());
			area2 = areaMapper.selectNameParentIdByAreaId(area2);
			threeCheckCount2.setThccVillage(area2.getAreaName());
			threeCheckCount2.setThccParentAreaId(area2.getAreaParentid());
			setSelect(insertRecord,area2.getAreaParentid(),womanName, womanIdCard, womanProvince,
					 womanCity,womanCounty,womanTown,womanVallige,
					 womanCurProvince,womanCurCity,womanCurCounty,
					 womanCurTown,womanCurVallige,liveState,checkYear,checkPatch,
					 checkDate,checkPlace,hoop,
					 pregnant,disease, checkSuggest,threeCheckCount2);
			if(publicReadRecord==null){
				threeCheckCountMapper.insert(threeCheckCount2);
			}else{
				threeCheckCountMapper.updateInfo(threeCheckCount2);
			}
			ThreeCheckCount threeCheckCount3 = new ThreeCheckCount();
			Area area3 = new Area();
			area3.setAreaId(area2.getAreaParentid());
			area3 = areaMapper.selectNameParentIdByAreaId(area3);
			threeCheckCount3.setThccVillage(area3.getAreaName());
			threeCheckCount3.setThccParentAreaId(area3.getAreaParentid());
			setSelect(insertRecord,area3.getAreaParentid(),womanName, womanIdCard, womanProvince,
					 womanCity,womanCounty,womanTown,womanVallige,
					 womanCurProvince,womanCurCity,womanCurCounty,
					 womanCurTown,womanCurVallige,liveState,checkYear,checkPatch,
					 checkDate,checkPlace,hoop,
					 pregnant,disease, checkSuggest,threeCheckCount3);
			if(publicReadRecord==null){
				threeCheckCountMapper.insert(threeCheckCount3);
			}else{
				threeCheckCountMapper.updateInfo(threeCheckCount3);
			}
			ThreeCheckCount threeCheckCount4 = new ThreeCheckCount();
			Area area4 = new Area();
			area4.setAreaId(area3.getAreaParentid());
			area4 = areaMapper.selectNameParentIdByAreaId(area4);
			threeCheckCount4.setThccVillage(area4.getAreaName());
			threeCheckCount4.setThccParentAreaId(area4.getAreaParentid());
			setSelect(insertRecord,area4.getAreaParentid(),womanName, womanIdCard, womanProvince,
					 womanCity,womanCounty,womanTown,womanVallige,
					 womanCurProvince,womanCurCity,womanCurCounty,
					 womanCurTown,womanCurVallige,liveState,checkYear,checkPatch,
					 checkDate,checkPlace,hoop,
					 pregnant,disease, checkSuggest,threeCheckCount4);
			if(publicReadRecord==null){
				threeCheckCountMapper.insert(threeCheckCount4);
			}else{
				threeCheckCountMapper.updateInfo(threeCheckCount4);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void setSelect(ThreeCheckServiceWithBLOBs insertRecord,int areaId, String womanName, String womanIdCard,
			String womanProvince, String womanCity, String womanCounty,
			String womanTown, String womanVallige, String womanCurProvince,
			String womanCurCity, String womanCurCounty, String womanCurTown,
			String womanCurVallige, String liveState,String checkYear, String checkPatch,
			String checkDate, String checkPlace, String hoop, String pregnant,
			String disease, String checkSuggest,ThreeCheckCount threeCheckCount) {
		try {
			ThreeCheckServiceWithBLOBs threeCheckServiceWithBLOBs = new ThreeCheckServiceWithBLOBs();
			threeCheckServiceWithBLOBs = insertRecord;
			threeCheckCount.setThccYear(checkYear);
			threeCheckCount.setThccBatch(checkPatch);
			threeCheckCount.setThccLivestate(liveState);

			ThreeCheckCount readRecord = new ThreeCheckCount();
			readRecord.setThccYear(checkYear);
			readRecord.setThccBatch(checkPatch);
			readRecord.setThccLivestate(liveState);
			
			String area = threeCheckCount.getThccVillage();
			readRecord.setThccVillage(area);
			
			int id = threeCheckCount.getThccParentAreaId();
			readRecord.setThccParentAreaId(id);
			
			readRecord = threeCheckCountMapper.findRecord(readRecord);
			setPublicReadRecord(readRecord);
			if(readRecord==null){
				threeCheckCount.setThccShouldchecknumber(1);
				threeCheckCount.setThccHavechecknumber(1);
				threeCheckCount.setThccCheckfreenumber(0);
				if(threeCheckServiceWithBLOBs.getThcsHoop().equals("有环")){
					threeCheckCount.setThccHavehoopnumber(1);
				}else{
					threeCheckCount.setThccHavehoopnumber(0);
				}
				if(threeCheckServiceWithBLOBs.getThcsPregnancy().equals("计外孕")){
					threeCheckCount.setThccOutplanprenumber(1);
				}else{
					threeCheckCount.setThccOutplanprenumber(0);
				}
				if(threeCheckServiceWithBLOBs.getThcsIsremedy().equals(0)){
					threeCheckCount.setThccRemedynumber(0);
				}else{
					threeCheckCount.setThccRemedynumber(1);
				}
				if(threeCheckServiceWithBLOBs.getThcsDisease().equals("无病")){
					threeCheckCount.setThccGymornumber(0);
				}else{
					threeCheckCount.setThccGymornumber(1);
				}
				
			}else{
				List<ThreeCheckServiceWithBLOBs> idList = new ArrayList<ThreeCheckServiceWithBLOBs>();
				ThreeCheckServiceWithBLOBs idRecord = new ThreeCheckServiceWithBLOBs();
				idRecord.setThcsIdnumber(womanIdCard);
				idRecord.setThcsYear(checkYear);
				idRecord.setThcsBatch(checkPatch);
				idRecord.setThcsLivestate(liveState);
				idRecord.setThcsNowlivevillage(womanCurVallige);
				idList = threeCheckServiceMapper.findIdList(idRecord);
				int size = idList.size()-1;
				
				if(size==0){
					int shouldCount = readRecord.getThccShouldchecknumber()+1;
					threeCheckCount.setThccShouldchecknumber(shouldCount);
					
					int haveCount = readRecord.getThccHavechecknumber()+1;
					threeCheckCount.setThccHavechecknumber(haveCount);
					
					int noCount = readRecord.getThccCheckfreenumber();
					threeCheckCount.setThccCheckfreenumber(noCount);
					
					String haveHoop = threeCheckServiceWithBLOBs.getThcsHoop();
					if(haveHoop.equals("有环")){
						int hoopCount = readRecord.getThccHavehoopnumber()+1;
						threeCheckCount.setThccHavehoopnumber(hoopCount);
					}else{
						int hoopCount = readRecord.getThccHavehoopnumber();
						threeCheckCount.setThccHavehoopnumber(hoopCount);
					}
					String outYun = threeCheckServiceWithBLOBs.getThcsPregnancy();
					if(outYun.equals("计外孕")){
						int outCount = readRecord.getThccOutplanprenumber()+1;
						threeCheckCount.setThccOutplanprenumber(outCount);
					}else{
						int outCount = readRecord.getThccOutplanprenumber();
						threeCheckCount.setThccOutplanprenumber(outCount);
					}
					int isbujiu = threeCheckServiceWithBLOBs.getThcsIsremedy();
					if(isbujiu!=0){
						int bujiuCount = readRecord.getThccRemedynumber()+1;
						threeCheckCount.setThccRemedynumber(bujiuCount);
					}else{
						int bujiuCount = readRecord.getThccRemedynumber();
						threeCheckCount.setThccRemedynumber(bujiuCount);
					}
					String Bing = threeCheckServiceWithBLOBs.getThcsDisease();
					if(Bing.equals("无病")){
						int bingCount = readRecord.getThccGymornumber();
						threeCheckCount.setThccGymornumber(bingCount);
					}else{
						int bingCount = readRecord.getThccGymornumber()+1;
						threeCheckCount.setThccGymornumber(bingCount);
					}
				}else{
					int shouldCount = readRecord.getThccShouldchecknumber();
					threeCheckCount.setThccShouldchecknumber(shouldCount);
					
					int haveCount = readRecord.getThccHavechecknumber();
					threeCheckCount.setThccHavechecknumber(haveCount);
					int noCount = readRecord.getThccCheckfreenumber();
					threeCheckCount.setThccCheckfreenumber(noCount);
					
					String haveHoop = threeCheckServiceWithBLOBs.getThcsHoop();
					if(idList.get(1).getThcsHoop().equals("有环")){
						if(haveHoop.equals("有环")){
							int hoopCount = readRecord.getThccHavehoopnumber();
							threeCheckCount.setThccHavehoopnumber(hoopCount);
						}else{
							int hoopCount = readRecord.getThccHavehoopnumber()-1;
							threeCheckCount.setThccHavehoopnumber(hoopCount);
						}
					}else{
						if(haveHoop.equals("有环")){
							int hoopCount = readRecord.getThccHavehoopnumber()+1;
							threeCheckCount.setThccHavehoopnumber(hoopCount);
						}else{
							int hoopCount = readRecord.getThccHavehoopnumber();
							threeCheckCount.setThccHavehoopnumber(hoopCount);
						}
					}
					String outYun = threeCheckServiceWithBLOBs.getThcsPregnancy();
					if(idList.get(1).getThcsPregnancy().equals("计外孕")){
						if(outYun.equals("计外孕")){
							int outCount = readRecord.getThccOutplanprenumber();
							threeCheckCount.setThccOutplanprenumber(outCount);
							int bujiuCount = readRecord.getThccRemedynumber();
							threeCheckCount.setThccRemedynumber(bujiuCount);
						}else{
							int outCount = readRecord.getThccOutplanprenumber();
							threeCheckCount.setThccOutplanprenumber(outCount);
							int bujiuCount = readRecord.getThccRemedynumber()+1;
							threeCheckCount.setThccRemedynumber(bujiuCount);
						}
					}else{
						if(outYun.equals("计外孕")){
							int outCount = readRecord.getThccOutplanprenumber()+1;
							threeCheckCount.setThccOutplanprenumber(outCount);
							int bujiuCount = readRecord.getThccRemedynumber();
							threeCheckCount.setThccRemedynumber(bujiuCount);
						}else{
							int outCount = readRecord.getThccOutplanprenumber();
							threeCheckCount.setThccOutplanprenumber(outCount);
							int bujiuCount = readRecord.getThccRemedynumber();
							threeCheckCount.setThccRemedynumber(bujiuCount);
						}
					}
					String Bing = threeCheckServiceWithBLOBs.getThcsDisease();
					if(idList.get(1).getThcsDisease().equals("无病")){
						if(Bing.equals("无病")){
							int bingCount = readRecord.getThccGymornumber();
							threeCheckCount.setThccGymornumber(bingCount);
						}else{
							int bingCount = readRecord.getThccGymornumber()+1;
							threeCheckCount.setThccGymornumber(bingCount);
						}
					}else{
						if(Bing.equals("无病")){
							int bingCount = readRecord.getThccGymornumber()-1;
							threeCheckCount.setThccGymornumber(bingCount);
						}else{
							int bingCount = readRecord.getThccGymornumber();
							threeCheckCount.setThccGymornumber(bingCount);
						}
					}		
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
