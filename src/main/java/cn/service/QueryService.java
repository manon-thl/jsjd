package cn.service;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

public interface QueryService {

    JSONObject getGcRws(String dydj,String name, int page, int rows);

    JSONObject getJdWts(int page, int rows);

    JSONObject getAttrs(int page, int rows);

    /***
     * 工程任务录入
     * @param gcRw
     * @return
     */
    String addGcRw(JSONObject gcRw);

    /***
     * 监督问题录入
     * @param jdWt
     * @return
     */
    String addJdWt(JSONObject jdWt);

    /***
     * 字段录入
     * @param attr
     * @return
     */
    String addAttr(JSONObject attr);
}
