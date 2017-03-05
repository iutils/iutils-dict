package cn.iutils.plug.dict.entity;

import cn.iutils.sys.entity.DataEntity;

/**
* 字典表
* @author iutils.cn
* @version 1.0
*/
public class Dict extends DataEntity<Dict>{

    private static final long serialVersionUID = 1L;

    private String value;//值
    private String label;//标签
    private String type;//类型
    private int sort = 0;//排序

    public Dict() {
        super();
    }
    public Dict(String id){
        super(id);
    }

    public String getValue(){
        return value;
    }

    public void setValue(String value){
        this.value = value;
    }

    public String getLabel(){
        return label;
    }

    public void setLabel(String label){
        this.label = label;
    }

    public String getType(){
        return type;
    }

    public void setType(String type){
        this.type = type;
    }

    public int getSort(){
        return sort;
    }

    public void setSort(int sort){
        this.sort = sort;
    }

}
