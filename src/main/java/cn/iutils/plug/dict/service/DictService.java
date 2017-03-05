package cn.iutils.plug.dict.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.iutils.common.service.CrudService;
import cn.iutils.plug.dict.dao.DictDao;
import cn.iutils.plug.dict.entity.Dict;

/**
* 字典表 Service层
* @author iutils.cn
* @version 1.0
*/
@Service
@Transactional(readOnly = true)
public class DictService extends CrudService<DictDao, Dict> {

}
