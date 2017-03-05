package cn.iutils.plug.dict.utils;

import cn.iutils.common.config.JConfig;
import cn.iutils.common.controller.JsonMapper;
import cn.iutils.common.spring.SpringUtils;
import cn.iutils.common.utils.CacheUtils;
import cn.iutils.plug.dict.dao.DictDao;
import cn.iutils.plug.dict.entity.Dict;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * 字典工具类
 *
 * @author iutils.cn
 * @version 1.0
 */
public class DictUtils {

    private static DictDao dictDao = SpringUtils.getBean(DictDao.class);

    public static final String CACHE_DICT_MAP = "dictMap";

    /**
     * 获取标签
     * @param value
     * @param type
     * @param defaultValue
     * @return
     */
    public static String getDictLabel(String value, String type, String defaultValue) {
        if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(value)) {
            for (Dict dict : getDictList(type)) {
                if (type.equals(dict.getType()) && value.equals(dict.getValue())) {
                    return dict.getLabel();
                }
            }
        }
        return defaultValue;
    }

    /**
     * 获取字典标签(多个)
     * @param values
     * @param type
     * @param defaultValue
     * @return
     */
    public static String getDictLabels(String values, String type, String defaultValue) {
        if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(values)) {
            List<String> valueList = Lists.newArrayList();
            for (String value : StringUtils.split(values, ",")) {
                valueList.add(getDictLabel(value, type, defaultValue));
            }
            return StringUtils.join(valueList, ",");
        }
        return defaultValue;
    }

    /**
     * 获取字典值
     * @param label
     * @param type
     * @param defaultLabel
     * @return
     */
    public static String getDictValue(String label, String type, String defaultLabel) {
        if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(label)) {
            for (Dict dict : getDictList(type)) {
                if (type.equals(dict.getType()) && label.equals(dict.getLabel())) {
                    return dict.getValue();
                }
            }
        }
        return defaultLabel;
    }

    /**
     * 获取字典对象列表
     * @param type
     * @return
     */
    public static List<Dict> getDictList(String type) {
        Map<String, List<Dict>> dictMap = (Map<String, List<Dict>>) CacheUtils.get(CACHE_DICT_MAP);
        if (dictMap == null) {
            dictMap = Maps.newHashMap();
            for (Dict dict : dictDao.findList(null)) {
                List<Dict> dictList = dictMap.get(dict.getType());
                if (dictList != null) {
                    dictList.add(dict);
                } else {
                    dictMap.put(dict.getType(), Lists.newArrayList(dict));
                }
            }
            CacheUtils.put(CACHE_DICT_MAP, dictMap);
        }
        List<Dict> dictList = dictMap.get(type);
        if (dictList == null) {
            dictList = Lists.newArrayList();
        }
        return dictList;
    }

    /**
     * 返回字典列表（JSON）
     * @param type
     * @return
     */
    public static String getDictListJson(String type) {
        return JsonMapper.toJsonString(getDictList(type));
    }

}
