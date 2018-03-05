package com.grandland.qdhx.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grandland.qdhx.core.domain.Level;
import com.grandland.qdhx.core.mapper.LevelMapper;
import com.grandland.qdhx.treedata.TreeBuilder;
import com.grandland.qdhx.web.service.LevelService;

/**
 *  层级菜单管理servie的实现类
 * @author loverqi
 * @date 2018年2月6日
 */
@Service
public class LevelServiceImpl implements LevelService {

    @Autowired
    private LevelMapper levelMapper;

    @Override
    public List<Level> getPostLevel(String deptName, String postName, String realName) {
        List<Level> levels = levelMapper.selectPostLevel(deptName, postName, realName);

        levels = new TreeBuilder<Level>().buildEmptyTree(levels);

        return levels;
    }

    @Override
    public List<Level> getPostLevelNotUser(String deptName, String postName) {
        List<Level> levels = levelMapper.selectPostLevelNotUser(deptName, postName);

        levels = new TreeBuilder<Level>().buildEmptyTree(levels);
        
        return levels;
    }

}
