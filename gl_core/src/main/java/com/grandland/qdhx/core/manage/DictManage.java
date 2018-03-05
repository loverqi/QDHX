package com.grandland.qdhx.core.manage;

import com.grandland.qdhx.core.domain.GlSysCode;
import com.grandland.qdhx.core.mapper.GlSysCodeMapper;
import com.grandland.qdhx.core.utils.ConstantUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * 字典缓存
 */
@Component
public class DictManage {
    private static final Logger LOG = LoggerFactory.getLogger(DictManage.class);

    private static Map<String, String> dictMap = null;

    private static Map<String, String> dictMapCode = null;

    private static Map<String, List<GlSysCode>> fieldMap = null;


    @Autowired
    private GlSysCodeMapper codeMapper;

    @Scheduled(fixedRate = 1000L * 60 * 20)
    public void refresh() {
        LOG.info("refresh dict list ...");
        init();
    }

    public void init() {
        Map<String, String> tmpDictMap = new HashMap<>();
        Map<String, String> tmpDictMapCode = new HashMap<>();
        Map<String, List<GlSysCode>> tmpFieldMap = new HashMap<>();

        List<GlSysCode> list = codeMapper.findByEnable(true);
        if (!ObjectUtils.isEmpty(list)) {
            for (GlSysCode code : list) {
                String key = code.getType() + ConstantUtil.SYMBOL_HYPHEN + code.getCode();
                tmpDictMap.put(key, code.getComment());
                key = code.getType() + ConstantUtil.SYMBOL_HYPHEN + code.getComment();
                tmpDictMapCode.put(key, code.getCode());

                if (!tmpFieldMap.containsKey(code.getType())) {
                    List<GlSysCode> lists = new ArrayList<>();
                    lists.add(code);
                    tmpFieldMap.put(code.getType(), lists);
                } else {
                    tmpFieldMap.get(code.getType()).add(code);
                }
            }
        } else {
            LOG.warn("sys code is null, please check!");
        }
        dictMap = tmpDictMap;
        dictMapCode = tmpDictMapCode;
        fieldMap = tmpFieldMap;
        LOG.info("dic manage fineshed! dictType.size:{}", fieldMap.size());
    }


    /**
     * 根据代码类别获取所有代码值映射
     * @param type
     * @return
     */
    public Map<String, String> getField(String type) {
        Map<String, String> map = new LinkedHashMap<String, String>();
        List<GlSysCode> codeList = fieldMap.get(type);
        if (!ObjectUtils.isEmpty(codeList)) {
            for (GlSysCode code : codeList) {
                map.put(code.getCode(), code.getComment());
            }
        }
        return map;
    }

    /**
     * 根据代码类别获取代码列表
     * @param type
     * @return
     */
    public List<GlSysCode> getCodeList(String type) {
        if (fieldMap == null) {
            init();
        }
        return fieldMap.get(type);
    }

    /**
     * 根据类型和代码获取代码类
     * @param type
     * @param code
     * @return
     */
    public GlSysCode getSysCode(String type, String code) {
        if (fieldMap == null) {
            init();
        }
        List<GlSysCode> list = fieldMap.get(type);
        if (!ObjectUtils.isEmpty(list) && !StringUtils.isEmpty(code)) {
            for (GlSysCode sysCode : list) {
                if (code.equals(sysCode.getCode())) {
                    return sysCode;
                }
            }
        }
        return null;
    }


    /**
     * 获得关于字典值的描述
     * @return
     */
    public String getDictComment(String type, String code) {
        return getDictComment(type, code, "无");
    }

    public String getDictComment(String type, String code, String defaultValue) {
        if (dictMap == null) {
            init();
        }
        String key = type + ConstantUtil.SYMBOL_HYPHEN + code;
        String result = dictMap.get(key);
        return result == null ? defaultValue : result;
    }

    /**
     * 根据类型与字典描述获取字典
     * @return
     */
    public String getDictCode(String type, String comment) {
        if (dictMapCode == null) {
            init();
        }
        String key = type + ConstantUtil.SYMBOL_HYPHEN + comment;
        String result = dictMapCode.get(key);
        return result == null ? null : result;
    }

}
