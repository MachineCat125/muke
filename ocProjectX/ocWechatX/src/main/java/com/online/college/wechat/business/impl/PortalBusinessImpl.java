package com.online.college.wechat.business.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.college.service.core.consts.CourseEnum;
import com.online.college.service.core.course.domain.CourseSection;
import com.online.college.service.core.course.service.ICourseSectionService;
import com.online.college.wechat.business.IPortalBusiness;
import com.online.college.wechat.vo.CourseSectionVO;

/**
 *
 * @Description: 业务层
 * @author majinlan
 * @date 2018-02-12 14:58
 * @version V1.0
 */
@Service
public class PortalBusinessImpl implements IPortalBusiness {

    @Autowired
    private ICourseSectionService courseSectionService;

    /**
     * 获取课程章节
     */
    @Override
    public List<CourseSectionVO> queryCourseSection(Long courseId) {
        List<CourseSectionVO> resultList = new ArrayList<CourseSectionVO>();
        CourseSection queryEntity = new CourseSection();
        queryEntity.setCourseId(courseId);
        queryEntity.setOnsale(CourseEnum.ONSALE.value());

        Map<Long, CourseSectionVO> tmpMap = new LinkedHashMap<Long, CourseSectionVO>();
        Iterator<CourseSection> it = courseSectionService.queryAll(queryEntity).iterator();

        while (it.hasNext()) {
            CourseSection item = it.next();

            if (Long.valueOf(0).equals(item.getParentId())) {
                CourseSectionVO vo = new CourseSectionVO();
                BeanUtils.copyProperties(item, vo);
                tmpMap.put(vo.getId(), vo);
            } else {
                tmpMap.get(item.getParentId()).getSections().add(item);
            }
        }

        for (CourseSectionVO vo : tmpMap.values()) {
            resultList.add(vo);
        }
        return resultList;
    }

}
