package swust.edu.cn.threeExaminations.service;

import java.util.Date;
import java.util.List;

import swust.edu.cn.threeExaminations.model.ThreeCheckCount;
import swust.edu.cn.threeExaminations.model.ThreeCheckServiceWithBLOBs;

public interface ThreeCheckCountService {

	List<ThreeCheckCount> searchBySelect1(int id,String level,String year, String patch);

	List<ThreeCheckCount> searchBySelect2(int id,String level,String year, String patch,
			String vallige);

	List<ThreeCheckCount> searchBySelect3(int id,String level,String year, String patch,
			String peopleClass);

	List<ThreeCheckCount> searchBySelect4(int id,String level,String year, String patch,
			String vallige, String peopleClass);

	void update(ThreeCheckServiceWithBLOBs insertRecord,int areaId, String womanName, String womanIdCard,
			String womanProvince, String womanCity, String womanCounty,
			String womanTown, String womanVallige, String womanCurProvince,
			String womanCurCity, String womanCurCounty, String womanCurTown,
			String womanCurVallige, String liveState,String checkYear,String checkPatch,
			String checkDate, String checkPlace, String hoop, String pregnant,
			String disease, String checkSuggest);

	void setSelect(ThreeCheckServiceWithBLOBs insertRecord,int areaId, String womanName, String womanIdCard,
			String womanProvince, String womanCity, String womanCounty,
			String womanTown, String womanVallige, String womanCurProvince,
			String womanCurCity, String womanCurCounty, String womanCurTown,
			String womanCurVallige, String liveState,String checkYear, String checkPatch,
			String checkDate, String checkPlace, String hoop, String pregnant,
			String disease, String checkSuggest,ThreeCheckCount threeCheckCount);

}
