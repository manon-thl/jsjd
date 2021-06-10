package cn.service.controller;

import cn.service.QueryService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@CrossOrigin
@Controller
@RequestMapping("/jsjd")
public class JsjdController {

    @Autowired
    private QueryService queryService;

    @RequestMapping("getGcRw")
    @ResponseBody
    public JSONObject getGcRw(String dydj, String name, int page, int rows){
        return queryService.getGcRw(dydj,name,page,rows);
    }

    @RequestMapping("getJdWt")
    @ResponseBody
    public JSONObject getJdWt(int page, int rows){
        return queryService.getJdWt(page,rows);
    }

    @RequestMapping("getAttrs")
    @ResponseBody
    public JSONObject getAttrs(int page, int rows){
        return null;
    }

    @RequestMapping("addGcRw")
    @ResponseBody
    public String addGcRw(@RequestBody JSONObject gcRw){
        return queryService.addJdWt(gcRw);
    }

    @RequestMapping("addJdWt")
    @ResponseBody
    public String addJdWt(@RequestBody JSONObject jdWt){
        return queryService.addJdWt(jdWt);
    }
}
