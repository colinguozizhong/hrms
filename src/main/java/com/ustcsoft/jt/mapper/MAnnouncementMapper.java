package com.ustcsoft.jt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ustcsoft.jt.mybatis.Page;
import com.ustcsoft.system.model.MAnnouncementReciverVO;
import com.ustcsoft.system.model.MAnnouncementVO;
import com.ustcsoft.system.model.MUserPesVO;

public interface MAnnouncementMapper {

	String serchId(@Param("userId") String userId, @Param("tableName") String tableName);

	int checkUser(@Param("userId") String userId);

	int checkUserGongLu(@Param("userId") String userId);
	// 获取公告列表
	List<MAnnouncementVO> searchAnnouncementPage(@Param("vo")MAnnouncementVO vo, @Param("page") Page<MAnnouncementVO> page);
	// 新增公告
	void insertAnnouncement(MAnnouncementVO setType);
	// 修改公告
	void updateAnnouncement(MAnnouncementVO setType);
	//删除公告
	void deleteAnnouncement(String announcementId);
	//加载公告
	MAnnouncementVO loadAnnouncement(String announcementId);
    //查询公告id
	String findMaxAnnouncementId();
   //加载机构树
	List<MAnnouncementReciverVO> searchOrgTree(MAnnouncementReciverVO vo);
    //查找BusinessId
	String searBusinessId(String orgId);
    //插入接收单位
	void insertReciver(MAnnouncementReciverVO rVo);
    //删除接收单位
	void deleteReciverByAnnouncementId(MAnnouncementVO setType);
    //查询接收单位
	List<MAnnouncementReciverVO> searchReciverOrg(MAnnouncementVO mAnnouncementVO);

	List<MAnnouncementVO> findMAnnouncementForPage(
			@Param("mAnnouncementVO") MAnnouncementVO mAnnouncementVO, @Param("page") Page<MAnnouncementVO> page);

	int serchUnreadGG(@Param("mUserPesVO") MUserPesVO mUserPesVO);

	void saveReadGGJL(@Param("mUserPesVO") MUserPesVO mUserPesVO);

	int findGGreadFlag(@Param("mUserPesVO") MUserPesVO mUserPesVO);
}
