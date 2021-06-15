package cn.service.impl;

import cn.service.QueryService;
import cn.service.consts.JsjdConst;
import cn.service.util.UUIDUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QueryServiceImpl implements QueryService {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public JSONObject getGcRws(String dydj, String name, int page, int rows) {
        String where_dydj = "";
        if (dydj != null && !"".equals(dydj)) {
            where_dydj = " and gcmc like '%'||:dydj||'%'";
        }
        String where_name = "";
        if (name != null && !"".equals(name)) {
            where_name = " and gcmc like '%'||:name||'%'";
        }
        Map<String, Object> params = new HashMap<>();
        params.put("dydj", dydj);
        params.put("name", name);
        params.put("page", page);
        params.put("rows", rows);
        String sql = "select * from app_jsjd_xcjd_gclr where 1=1" + where_dydj + where_name;
        String dataSql = "select * from (select rownum rn,a.* from (" + sql + ") a) where rn>(:page-1)*:rows and rn<=:page*:rows";
        String countSql = "select count(1) from (" + sql + ") ";
        List<Map<String, Object>> datas = jdbcTemplate.queryForList(dataSql, params);
        Integer total = jdbcTemplate.queryForObject(countSql, params, Integer.class);
        JSONObject res = new JSONObject();
        res.put("total", total);
        res.put("rows", datas);
        return res;
    }

    @Override
    public JSONObject getJdWts(int page, int rows) {
        Map<String, Object> params = new HashMap<>();
        params.put("page", page);
        params.put("rows", rows);
        String sql = "select * from app_jsjd_xcjd_wtlr";
        String countSql = "select count(1) from (" + sql + ") ";
        String dataSql = "select * from (select rownum rn,a.* from (" + sql + ") a) where rn>(:page-1)*:rows and rn<=:page*:rows";
        List<Map<String, Object>> datas = jdbcTemplate.queryForList(dataSql, params);
        Integer total = jdbcTemplate.queryForObject(countSql, params, Integer.class);
        JSONObject res = new JSONObject();
        res.put("total", total);
        res.put("rows", datas);
        return res;
    }

    @Override
    public JSONObject getAttrs(int page, int rows) {
        Map<String, Object> params = new HashMap<>();
        params.put("page", page);
        params.put("rows", rows);
        String sql = "select * from app_jsjd_om_attr";
        String countSql = "select count(1) from (" + sql + ")";
        String dataSql = "select * from (select rownum rn,a.* from (" + sql + ") a) where rn>(:page-1)*:rows and rn<=:page*:rows";
        List<Map<String, Object>> datas = jdbcTemplate.queryForList(dataSql, params);
        Integer total = jdbcTemplate.queryForObject(countSql, params, Integer.class);
        JSONObject res = new JSONObject();
        res.put("total", total);
        res.put("rows", datas);
        return res;
    }

    /***
     * 工程任务录入
     * @param gcrw
     * @return
     */
    @Override
    public String addGcRw(JSONObject gcrw) {
        HashMap<String, Object> params = new HashMap<>();
        String id = UUIDUtil.randomUUID().toString();
        //String id = gcrw.getString("ID");
        String gcmc = gcrw.getString("GCMC");
        String updateTime = gcrw.getString("UPDATE_TIME");
        String userName = gcrw.getString("USER_NAME");
        String userId = gcrw.getString("USER_ID");
        String yxdw = gcrw.getString("YXDW");
        String jgdw = gcrw.getString("JGDW");
        String sjdw = gcrw.getString("SJDW");
        String sgdw = gcrw.getString("SGDW");
        String gldw = gcrw.getString("GLDW");
        String wzgydw = gcrw.getString("WZGYDW");
        params.put("id", id);
        params.put("gcmc", gcmc);
        params.put("updateTime", updateTime);
        params.put("userName", userName);
        params.put("userId", userId);
        params.put("yxdw", yxdw);
        params.put("jgdw", jgdw);
        params.put("sjdw", sjdw);
        params.put("sgdw", sgdw);
        params.put("gldw", gldw);
        params.put("wzgydw", wzgydw);
        params.put("status", JsjdConst.STATUS0);
        String sql = "insert into app_jsjd_xcjd_gclr(id,gcmc,updateTime,userName,userId,yxdw,jgdw,sjdw,sgdw,gldw,wzgydw,status)" +
                " values(:id,:gcmc,:updateTime,:userName,:userId,:yxdw,:jgdw,:sjdw,:sgdw,:gldw,:wzgydw,:status)";
        int update = jdbcTemplate.update(sql, params);
        return update == 1 ? "工程任务录入成功" : "工程任务录入失败";
    }

    /***
     * 监督问题录入
     * @param jdwt
     * @return
     */
    @Override
    public String addJdWt(JSONObject jdwt) {
        HashMap<String, Object> params = new HashMap<>();
        String id = UUIDUtil.randomUUID().toString();
        //String id = jdwt.getString("ID");
        String gcmc = jdwt.getString("GCMC");
        String wtms = jdwt.getString("WTMS");
        String wfxz = jdwt.getString("WFXZ");
        String jdjd = jdwt.getString("JDJD");
        String jdzy = jdwt.getString("JDZY");
        String zgyj = jdwt.getString("ZGYJ");
        String picture = jdwt.getString("PICTURE");
        String gcid = jdwt.getString("GCID");
        params.put("id", id);
        params.put("gcmc", gcmc);
        params.put("wtms", wtms);
        params.put("wfxz", wfxz);
        params.put("jdjd", jdjd);
        params.put("jdzy", jdzy);
        params.put("zgyj", zgyj);
        params.put("picture", picture);
        params.put("gcid", gcid);
        params.put("status", JsjdConst.STATUS0);
        String sql = "insert into app_jsjd_xcjd_wtlr(id,gcmc,wtms,wfxz,jdjd,jdzy,picture,gcid,status)" +
                " values(:id,:gcmc,:wtms,:wfxz,:jdjd,:jdzy,:picture,:gcid,:status)";
        int update = jdbcTemplate.update(sql, params);
        return update == 1 ? "监督问题录入成功" : "监督问题录入失败";
    }

    /***
     * 字段录入
     * @param attr
     * @return
     */
    @Override
    public String addAttr(JSONObject attr) {
        HashMap<String,Object> params=new HashMap<>();
        String attrId = UUIDUtil.randomUUID().toString();
        //String attrId = attr.getString("ATTR_ID");
        String dt = attr.getString("DT");
        String attrName = attr.getString("ATTR_NAME");
        String attrColname = attr.getString("ATTR_COLNAME");
        String attrSize = attr.getString("ATTR_SIZE");
        String attrScale = attr.getString("ATTR_SCALE");
        String attrInitval = attr.getString("ATTR_INITVAL");
        String attrDisplay = attr.getString("ATTR_DISPLAY");
        String attrNullval = attr.getString("ATTR_NULLVAL");
        String attrDesc = attr.getString("ATTR_DESC");
        String attrSeqno = attr.getString("ATTR_SEQNO");
        String attrCtime = attr.getString("ATTR_CTIME");
        Object attrStatus = attr.get("ATTR_STATUS");
        params.put("attrId",attrId);
        params.put("dt",dt);
        params.put("attrName",attrName);
        params.put("attrColname",attrColname);
        params.put("attrSize",attrSize);
        params.put("attrScale",attrScale);
        params.put("attrInitval",attrInitval);
        params.put("attrDisplay",attrDisplay);
        params.put("attrNullval",attrNullval);
        params.put("attrDesc",attrDesc);
        params.put("attrSeqno",attrSeqno);
        params.put("attrCtime",attrCtime);
        params.put("attrStatus",attrStatus);
        String sql="insert into app_jsjd_om_attr(attrId,dt,attrName,attrColname,attrSize,attrScale,attrInitval,attrDisplay,attrNullval,attrDesc,attrSeqno,attrCtime,attrStatus)" +
                " values(:attrId,:dt,:attrName,:attrColname,:attrSize,:attrScale,:attrInitval,:attrDisplay,attrNullval,attrDesc,attrSeqno,attrCtime,attrStatus)";
        int update = jdbcTemplate.update(sql, params);
        return update==1?"字段录入成功":"字段录入失败";
    }
}
