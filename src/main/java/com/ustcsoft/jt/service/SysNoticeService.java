package com.ustcsoft.jt.service;

import java.util.List;

import com.ustcsoft.framework.vo.UserVO;
import com.ustcsoft.jt.mybatis.Page;
import com.ustcsoft.system.model.MAnnouncementReciverVO;
import com.ustcsoft.system.model.MAnnouncementVO;
import com.ustcsoft.system.model.MUserPesVO;
public interface SysNoticeService {
	
	Page<MAnnouncementVO> searchAnnouncementList(MAnnouncementVO vo,int page, int rows);
	void  insertAnnouncement(MAnnouncementVO setType);
	void deleteAnnouncement(String announcementId);
	MAnnouncementVO loadAnnouncement(String announcementId);
	List<MAnnouncementReciverVO> loadAnnouncementOrg(MAnnouncementReciverVO vo,
			UserVO userinfo);
	List<MAnnouncementReciverVO> searchReciverOrg(MAnnouncementVO vo);
	Page<MAnnouncementVO> findMAnnouncementForPage(MAnnouncementVO mAnnouncementVO, int page, int rows);
	int serchUnreadGG(MUserPesVO mUserPesVO);
	int saveReadGGJL(MUserPesVO mUserPesVO);
	int findGGreadFlag(MUserPesVO mUserPesVO);
	
}
