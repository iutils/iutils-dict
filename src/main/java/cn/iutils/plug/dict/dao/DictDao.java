package cn.iutils.plug.dict.dao;

import cn.iutils.common.ICrudDao;
import cn.iutils.common.annotation.MyBatisDao;
import cn.iutils.plug.dict.entity.Dict;

/**
* 字典表 DAO接口
* @author iutils.cn
* @version 1.0
*/
@MyBatisDao
public interface DictDao extends ICrudDao<Dict> {

}
