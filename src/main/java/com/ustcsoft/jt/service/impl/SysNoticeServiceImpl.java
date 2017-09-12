package com.ustcsoft.jt.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ustcsoft.framework.vo.UserVO;
import com.ustcsoft.jt.mapper.MAnnouncementMapper;
import com.ustcsoft.jt.mybatis.Page;
import com.ustcsoft.jt.service.SysNoticeService;
import com.ustcsoft.jt.util.CommonUtils;
import com.ustcsoft.system.model.MAnnouncementReciverVO;
import com.ustcsoft.system.model.MAnnouncementVO;
import com.ustcsoft.system.model.MUserPesVO;

@Service
public class SysNoticeServiceImpl implements SysNoticeService {
	@Resource
	private MAnnouncementMapper mAnnouncementMapper;
	
	// 获取公告列表
	@Override
	public Page<MAnnouncementVO> searchAnnouncementList(MAnnouncementVO vo,int page, int rows){
		
		      // TODO Auto-generated method stub
				Page<MAnnouncementVO> pageVO = Page.buildPageRequest(page, rows);
				List<MAnnouncementVO> list = null;
				list =  mAnnouncementMapper.searchAnnouncementPage (vo,pageVO);	
				pageVO.setItems(list);
				return pageVO;
	}
	// 新增、编辑公告
		@Override
		public void  insertAnnouncement(MAnnouncementVO setType) {
			if ("".equals(setType.getAnnouncementId()) || setType.getAnnouncementId()== null) {
				String id = UUID.randomUUID().toString().replace("-", "");
				setType.setAnnouncementId(id);
				setType.setDelFlg("00");
				mAnnouncementMapper.insertAnnouncement(setType);
			} else {
				setType.setSenderOrgId(setType.getUserOrgId());
				mAnnouncementMapper.updateAnnouncement(setType);
				mAnnouncementMapper.deleteReciverByAnnouncementId(setType);
			}
			for(int i=0;i<setType.getListOrg().size();i++){
				MAnnouncementReciverVO rVo = setType.getListOrg().get(i);
				rVo.setAnnouncementId(setType.getAnnouncementId());
				mAnnouncementMapper.insertReciver(rVo);
			}
		}
		// 删除公告
		@Override
	    public void deleteAnnouncement(String announcementId){
			mAnnouncementMapper.deleteAnnouncement(announcementId);
		}
		// 加载公告
		@Override
		public MAnnouncementVO loadAnnouncement(String announcementId){
			MAnnouncementVO mAnnouncementVO=mAnnouncementMapper.loadAnnouncement(announcementId);
			mAnnouncementVO.setListOrg(mAnnouncementMapper.searchReciverOrg(mAnnouncementVO));
			return mAnnouncementVO;
			}
		// 加载机构树
		@Override
		public List<MAnnouncementReciverVO> searchReciverOrg(MAnnouncementVO vo){
			List<MAnnouncementReciverVO> list=new ArrayList<MAnnouncementReciverVO>();
			list = mAnnouncementMapper.searchReciverOrg(vo);
			return list;
		}
		// 加载Pid
		@Override
		public List<MAnnouncementReciverVO> loadAnnouncementOrg(MAnnouncementReciverVO vo,UserVO userinfo){
			List<MAnnouncementReciverVO> list=new ArrayList<MAnnouncementReciverVO>();
			String orgId = userinfo.getOrgId();
			String businessId=mAnnouncementMapper.searBusinessId(orgId);
			vo.setBusinessId(CommonUtils.getSearchId(businessId));
			list = mAnnouncementMapper.searchOrgTree(vo);
			for(int i = 0 ; i< list.size();i++){   
		           list.get(i).setPid((CommonUtils.getParentId(list.get(i).getBusinessId())));
		        } 
			return list;
		}
		
		// 获取收到的公告列表
		@Override
		public Page<MAnnouncementVO> findMAnnouncementForPage(MAnnouncementVO mAnnouncementVO, int pageNo, int pageSize) {
			Page<MAnnouncementVO> page = Page.buildPageRequest(pageNo, pageSize);
			List <MAnnouncementVO> list = mAnnouncementMapper.findMAnnouncementForPage(mAnnouncementVO, page);
			for(int i=0;i<list.size();i++){
				MUserPesVO mUserPesVO = new MUserPesVO();
				mUserPesVO.setTableName("M_ANNOUNCEMENT");
				mUserPesVO.setUserId(mAnnouncementVO.getUserId());
				mUserPesVO.setDataId(list.get(i).getAnnouncementId());
				int readFlag = mAnnouncementMapper.findGGreadFlag(mUserPesVO);
				list.get(i).setReadFlag(readFlag);
			}
			page.setItems(list);
			return page;
		}
		
		@Override
		public int serchUnreadGG(MUserPesVO mUserPesVO){
			return mAnnouncementMapper.serchUnreadGG(mUserPesVO);
		}
		
		@Override
		public int saveReadGGJL(MUserPesVO mUserPesVO){
			int n = mAnnouncementMapper.findGGreadFlag(mUserPesVO);
			if(n==0){
				mAnnouncementMapper.saveReadGGJL(mUserPesVO);
			}
			return 0;
		}
		
		@Override
		public int findGGreadFlag(MUserPesVO mUserPesVO){
			return mAnnouncementMapper.findGGreadFlag(mUserPesVO);
		}
}
