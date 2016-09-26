package com.caitou.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.caitou.bean.Essay;
import com.caitou.bean.Famous;
import com.caitou.common.CountUtil;
import com.caitou.dao.IFamousDao;
import com.caitou.dto.PageParam;

@Service
public class FamousService {

	@Resource
	IFamousDao iFamousDao;

	@Resource
	EssayService essayService;

	public int getRowCount() {
		return iFamousDao.getRowCount();
	}

	public PageParam<List<Essay>> getFamousEssayByOffect(
			PageParam<List<Essay>> pageParam) {
		int offset = pageParam.getCurrentPage() * PageParam.getPageSize();
		int size = PageParam.getPageSize();
		List<Famous> famousList = iFamousDao.queryByOffect(offset, size);
		List<Essay> essayList = new ArrayList<Essay>();
		for (Famous famous : famousList) {
			Essay essay = essayService.getEssayById(famous.getEssayId());
			essayList.add(essay);
		}
		essayList = CountUtil.setSubTimeInEssay(essayList);
		pageParam.setData(essayList);
		return pageParam;
	}

	public void toBeFamous(Essay essay, int essayWordNumber) {
		int grade = essayWordNumber / 10 + essay.getEssayCommentNumber()
				+ essay.getEssayGoodNumber() + essay.getEssayReadingNumber();
		if (essay.getIsHot() != 1) {
			iFamousDao.insertFamous(essay.getId(), grade);
		} else {
			iFamousDao.updateGradeByEssayId(essay.getId(), grade);
		}
	};

	public void updateGradeByEssayId(int essayId, int grade) {
		iFamousDao.updateGradeByEssayId(essayId, grade);
	}
}
