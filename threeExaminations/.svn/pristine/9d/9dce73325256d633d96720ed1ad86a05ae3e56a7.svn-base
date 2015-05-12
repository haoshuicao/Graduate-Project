package swust.edu.cn.threeExaminations.dao;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import swust.edu.cn.threeExaminations.model.ThreeCheckService;
import swust.edu.cn.threeExaminations.model.ThreeCheckServiceWithBLOBs;

public interface ThreeCheckServiceMapper {
    int deleteByPrimaryKey(Integer thcsId);

    int insert(ThreeCheckServiceWithBLOBs record);

    int insertSelective(ThreeCheckServiceWithBLOBs record);

    ThreeCheckServiceWithBLOBs selectByPrimaryKey(Integer thcsId);

    int updateByPrimaryKeySelective(ThreeCheckServiceWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ThreeCheckServiceWithBLOBs record);

    int updateByPrimaryKey(ThreeCheckService record);

    List<ThreeCheckServiceWithBLOBs> searchByTownName(ThreeCheckServiceWithBLOBs threeCheckServiceWithBLOBs);

	List<ThreeCheckServiceWithBLOBs> findRecordByIdCard(@Param("thcsIdnumber") String womanIdCard,
			@Param("changzhu") String changzhu,@Param("liuru") String liuru,@Param("nowLiveTown") String nowLiveTown,
			@Param("liuchu") String liuchu,@Param("huji") String huji);
	
	List<ThreeCheckServiceWithBLOBs> findRecordByIdCard2(@Param("thcsIdnumber") String womanIdCard,
			@Param("changzhu") String changzhu,@Param("liuru") String liuru,@Param("nowLiveCounty") String nowLiveCounty,
			@Param("liuchu") String liuchu,@Param("huji") String huji);

	List<ThreeCheckServiceWithBLOBs> findRecordBySelect1(
			ThreeCheckServiceWithBLOBs threeCheckServiceWithBLOBs);

	List<ThreeCheckServiceWithBLOBs> findRecordBySelect2(
			ThreeCheckServiceWithBLOBs threeCheckServiceWithBLOBs);

	List<ThreeCheckServiceWithBLOBs> findRecordBySelect3(
			ThreeCheckServiceWithBLOBs threeCheckServiceWithBLOBs);

	List<ThreeCheckServiceWithBLOBs> findRecordBySelect4(
			ThreeCheckServiceWithBLOBs threeCheckServiceWithBLOBs);

	List<ThreeCheckServiceWithBLOBs> findRecordBySelect5(
			ThreeCheckServiceWithBLOBs threeCheckServiceWithBLOBs);

	List<ThreeCheckServiceWithBLOBs> findRecordBySelect6(
			ThreeCheckServiceWithBLOBs threeCheckServiceWithBLOBs);

	List<ThreeCheckServiceWithBLOBs> findRecordBySelect7(
			ThreeCheckServiceWithBLOBs threeCheckServiceWithBLOBs);

	List<ThreeCheckServiceWithBLOBs> findRecordBySelect8(
			ThreeCheckServiceWithBLOBs threeCheckServiceWithBLOBs);

	/*ThreeCheckServiceWithBLOBs findNewRecordById(
			ThreeCheckServiceWithBLOBs record);*/

	ThreeCheckServiceWithBLOBs findRepeat(ThreeCheckServiceWithBLOBs record);
	
	ThreeCheckServiceWithBLOBs findRepeat2(ThreeCheckServiceWithBLOBs record);

	void update(ThreeCheckServiceWithBLOBs threeCheckServiceWithBLOBs);

	List<ThreeCheckServiceWithBLOBs> selectByTown(
			@Param("thcsNowlivetown") String thcsNowlivetown,@Param("changzhu") String changzhu,
			@Param("liuru") String liuru);

	List<ThreeCheckServiceWithBLOBs> findRecordBySelect11(
			ThreeCheckServiceWithBLOBs threeCheckServiceWithBLOBs);

	List<ThreeCheckServiceWithBLOBs> findRecordBySelect22(
			ThreeCheckServiceWithBLOBs threeCheckServiceWithBLOBs);

	List<ThreeCheckServiceWithBLOBs> findRecordBySelect33(
			ThreeCheckServiceWithBLOBs threeCheckServiceWithBLOBs);

	List<ThreeCheckServiceWithBLOBs> findRecordBySelect44(
			ThreeCheckServiceWithBLOBs threeCheckServiceWithBLOBs);

	List<ThreeCheckServiceWithBLOBs> findRecordBySelect55(
			ThreeCheckServiceWithBLOBs threeCheckServiceWithBLOBs);

	List<ThreeCheckServiceWithBLOBs> findRecordBySelect66(
			ThreeCheckServiceWithBLOBs threeCheckServiceWithBLOBs);

	List<ThreeCheckServiceWithBLOBs> findRecordBySelect77(
			ThreeCheckServiceWithBLOBs threeCheckServiceWithBLOBs);

	List<ThreeCheckServiceWithBLOBs> findRecordBySelect88(
			ThreeCheckServiceWithBLOBs threeCheckServiceWithBLOBs);

	List<ThreeCheckServiceWithBLOBs> findIdList(
			ThreeCheckServiceWithBLOBs idRecord);

	List<ThreeCheckServiceWithBLOBs> findRecordBySelectOut1(
			ThreeCheckServiceWithBLOBs threeCheckServiceWithBLOBs);

	List<ThreeCheckServiceWithBLOBs> findRecordBySelectOut11(
			ThreeCheckServiceWithBLOBs threeCheckServiceWithBLOBs);

	List<ThreeCheckServiceWithBLOBs> findRecordBySelectOut2(
			ThreeCheckServiceWithBLOBs threeCheckServiceWithBLOBs);

	List<ThreeCheckServiceWithBLOBs> findRecordBySelectOut22(
			ThreeCheckServiceWithBLOBs threeCheckServiceWithBLOBs);

	List<ThreeCheckServiceWithBLOBs> findRecordBySelectOut3(
			ThreeCheckServiceWithBLOBs threeCheckServiceWithBLOBs);

	List<ThreeCheckServiceWithBLOBs> findRecordBySelectOut33(
			ThreeCheckServiceWithBLOBs threeCheckServiceWithBLOBs);

	List<ThreeCheckServiceWithBLOBs> findRecordBySelectOut4(
			ThreeCheckServiceWithBLOBs threeCheckServiceWithBLOBs);

	List<ThreeCheckServiceWithBLOBs> findRecordBySelectOut44(
			ThreeCheckServiceWithBLOBs threeCheckServiceWithBLOBs);

	List<ThreeCheckServiceWithBLOBs> findRecordBySelectOut5(
			ThreeCheckServiceWithBLOBs threeCheckServiceWithBLOBs);

	List<ThreeCheckServiceWithBLOBs> findRecordBySelectOut55(
			ThreeCheckServiceWithBLOBs threeCheckServiceWithBLOBs);

	List<ThreeCheckServiceWithBLOBs> findRecordBySelectOut6(
			ThreeCheckServiceWithBLOBs threeCheckServiceWithBLOBs);

	List<ThreeCheckServiceWithBLOBs> findRecordBySelectOut66(
			ThreeCheckServiceWithBLOBs threeCheckServiceWithBLOBs);

	List<ThreeCheckServiceWithBLOBs> findRecordBySelectOut7(
			ThreeCheckServiceWithBLOBs threeCheckServiceWithBLOBs);

	List<ThreeCheckServiceWithBLOBs> findRecordBySelectOut77(
			ThreeCheckServiceWithBLOBs threeCheckServiceWithBLOBs);

	List<ThreeCheckServiceWithBLOBs> findRecordBySelectOut8(
			ThreeCheckServiceWithBLOBs threeCheckServiceWithBLOBs);

	List<ThreeCheckServiceWithBLOBs> findRecordBySelectOut88(
			ThreeCheckServiceWithBLOBs threeCheckServiceWithBLOBs);

	List<ThreeCheckServiceWithBLOBs> selectHuJiByUserArea(
			ThreeCheckServiceWithBLOBs threeCheckServiceWithBLOBs);
}