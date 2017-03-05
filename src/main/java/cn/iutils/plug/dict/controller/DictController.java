package cn.iutils.plug.dict.controller;

import cn.iutils.common.utils.CacheUtils;
import cn.iutils.plug.dict.utils.DictUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.iutils.common.Page;
import cn.iutils.common.utils.JStringUtils;
import cn.iutils.common.controller.BaseController;
import cn.iutils.plug.dict.entity.Dict;
import cn.iutils.plug.dict.service.DictService;

/**
* 字典表 控制器
* @author iutils.cn
* @version 1.0
*/
@Controller
@RequestMapping("${adminPath}/plug/dict")
public class DictController extends BaseController {

    @Autowired
    private DictService dictService;

    @ModelAttribute
    public Dict get(@RequestParam(required = false) String id) {
        Dict entity = null;
        if (JStringUtils.isNotBlank(id)) {
            entity = dictService.get(id);
        }
        if (entity == null) {
            entity = new Dict();
        }
        return entity;
    }

    @RequiresPermissions("plug:dict:view")
    @RequestMapping()
    public String list(Dict dict,Model model, Page<Dict> page) {
        page.setEntity(dict);
        model.addAttribute("page", page.setList(dictService.findPage(page)));
        return "plug/dict/list";
    }

    @RequiresPermissions("plug:dict:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Dict dict,Model model) {
        model.addAttribute("dict", dict);
        return "plug/dict/form";
    }

    @RequiresPermissions("plug:dict:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(Dict dict, RedirectAttributes redirectAttributes) {
        dictService.save(dict);
        CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
        addMessage(redirectAttributes,"新增成功");
        return "redirect:"+ adminPath +"/plug/dict/update?id="+dict.getId();
    }

    @RequiresPermissions("plug:dict:update")
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(Dict dict, Model model) {
        model.addAttribute("dict", dict);
        return "plug/dict/form";
    }

    @RequiresPermissions("plug:dict:update")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Dict dict, RedirectAttributes redirectAttributes) {
        dictService.save(dict);
        CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
        addMessage(redirectAttributes,"修改成功");
        return "redirect:"+ adminPath +"/plug/dict/update?id="+dict.getId();
    }

    @RequiresPermissions("plug:dict:delete")
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable("id") String id,int pageNo,int pageSize, RedirectAttributes redirectAttributes) {
        dictService.delete(id);
        CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
        addMessage(redirectAttributes,"删除成功");
        return "redirect:"+ adminPath +"/plug/dict?pageNo="+pageNo+"&pageSize="+pageSize;
    }
}
