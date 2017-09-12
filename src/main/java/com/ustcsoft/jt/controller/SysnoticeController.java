package com.ustcsoft.jt.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ustcsoft.framework.vo.UserVO;
import com.ustcsoft.jt.mapper.MAnnouncementMapper;
import com.ustcsoft.jt.mybatis.Page;
import com.ustcsoft.jt.service.SysNoticeService;
import com.ustcsoft.jt.util.DateUtil;
import com.ustcsoft.system.model.AjaxObj;
import com.ustcsoft.system.model.MAnnouncementReciverVO;
import com.ustcsoft.system.model.MAnnouncementVO;

@RequestMapping("sysNotice")
@RestController
public class SysnoticeController extends AbstractRestController {
	@Resource
	private SysNoticeService sysNotice;
	@Resource
	private MAnnouncementMapper mannouncementMapper;

	@RequestMapping("getNewAnnouncement.do")
	public Map<String, String> getNewAnnouncement(HttpSession session) {
		UserVO userInfo = getCurrentUser();
		String newAnnFlag = (String) session.getAttribute("NewAnnFlag");
		String data = "";
		if (null != userInfo && !"true".equals(newAnnFlag)) {
			data = mannouncementMapper.serchId(userInfo.getUserId().toString(), "M_ANNOUNCEMENT");
			session.setAttribute("NewAnnFlag", "true");
		}
		Integer num = null;
		// 检查是否显示提醒
		num = mannouncementMapper.checkUser(userInfo.getUserId().toString());

		Map<String, String> re = new HashMap<String, String>();
		re.put("announcementId", data);
		if (num != null && num > 0) {
			re.put("checkUser", "true");
		} else {
			re.put("checkUser", "false");
		}

		return re;
	}

	// 系统公告列表查询
	@RequestMapping("searchAnnouncementList.do")
	public Page<MAnnouncementVO> searchAnnouncementList(
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "rows", defaultValue = "10") int rows,
			@RequestParam(value = "sidx", required = false) String sidx,
			@RequestParam(value = "sord", required = false) String sord)
			throws Exception {
		UserVO userInfo = getCurrentUser();
		MAnnouncementVO vo = new MAnnouncementVO();
		vo.setDelFlg("00");
		vo.setSenderOrgId(userInfo.getOrgId());
		return sysNotice.searchAnnouncementList(vo,page, rows);
	}
	// 编辑、新增公告
	@RequestMapping("editeAnnouncement.do")
	public AjaxObj insertAnnouncement(MAnnouncementVO setType) throws Exception {
		UserVO userInfo = getCurrentUser();
		setType.setSenderOrgId(userInfo.getOrgId());
		setType.setCreater(userInfo.getUserName());
		// 设置创建时间
		setType.setCreateDate(DateUtil.format("yyyy-MM-dd HH:mm:ss", new Date()));
		sysNotice.insertAnnouncement(setType);
		AjaxObj obj = new AjaxObj();
		obj.setCode(0);
		obj.setMsg("添加成功");
		obj.setId(setType.getTitle());
		return obj;
	}
	// 删除
	@RequestMapping("deleteAnnouncement.do")
	public int deleteAnnouncement(String announcementId) throws Exception {
		sysNotice.deleteAnnouncement(announcementId);
		return 0;
	}
	// 载入公告
	@RequestMapping("loadAnnouncement.do")
	public MAnnouncementVO loadAnnouncement(String announcementId) throws Exception {
		MAnnouncementVO mAnnouncementVO=(MAnnouncementVO)sysNotice.loadAnnouncement(announcementId);
		return mAnnouncementVO;
	}
	// 载入发布机构
	@RequestMapping("loadAnnouncementOrg.do")
	public List<MAnnouncementReciverVO> loadAnnouncementOrg(String announcementId) throws Exception {
	    MAnnouncementReciverVO vo = new MAnnouncementReciverVO();
	    vo.setAnnouncementId(announcementId);
	    UserVO userInfo = getCurrentUser();
	    List<MAnnouncementReciverVO> list =  sysNotice.loadAnnouncementOrg(vo,userInfo);
		return list;
	}
	// 载入接收单位
	@RequestMapping("searchReciverOrg.do")
	public List<MAnnouncementReciverVO> searchReciverOrg(String announcementId) throws Exception {
		MAnnouncementVO mAnnouncementVO = new MAnnouncementVO();
		mAnnouncementVO.setAnnouncementId(announcementId);
		List<MAnnouncementReciverVO> list =  sysNotice.searchReciverOrg(mAnnouncementVO);
		return list;
	}

	@RequestMapping("getUserGongLu.do")
	public boolean getUserGongLu() throws Exception {
		UserVO userInfo = getCurrentUser();
		Integer num = mannouncementMapper.checkUserGongLu(userInfo.getUserId().toString());
		if (num != null && num > 0) {
			return true;
		}
		return false;
	}
}
