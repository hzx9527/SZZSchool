package com.shizhanzhe.szzschool.Bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hasee on 2016/11/2.
 */
public class MyProBean implements Serializable{

    /**
     * id : 39
     * userID : 849
     * oid : 14792008462544
     * integralAll : 0
     * integral : -0.01
     * sourceType : 1
     * addtime : 1479200846
     * sourceID :
     * sHash :
     * coid : 0
     * isshow : 1
     * systemid : 34
     * type : 1
     * pid : 0
     * yvip : 0
     * status : 0
     * catid : 2
     * sharetime :
     * shareurl :
     * tuangou :
     * viplast_time : 0
     * wxpay : 0
     * course : [{"id":"77","listorder":"0","updatetime":"1461222523","inputtime":"1461222523","ctitle":"企鹅媒体","style":"","choice_kc":[{"id":"262","name":"QQ群提取好友","sort":"0","mv_url":"d9a628711e944cc26c0073ee8f2d348c_d"},{"id":"290","name":"天涯论坛顶帖机","sort":"0","mv_url":"d9a628711e6c30477b9877decf2fae1d_d"},{"id":"291","name":"豆瓣顶帖机","sort":"0","mv_url":"d9a628711ed9512ab7f1d6ec23e137e1_d"},{"id":"292","name":"猫扑回帖助手","sort":"0","mv_url":"d9a628711ef83a2d2294e7bbe5d978da_d"},{"id":"293","name":"百度贴吧帖子采集器","sort":"0","mv_url":"d9a628711e80d534834f78d6e2351407_d"}],"choice_kc_idstr":"262,290,291,292,293","systemid":"34","thumb":"/var/upload/image/2016/05/20160503160613_55082.jpg","introduce":""},{"id":"87","listorder":"0","updatetime":"1470129274","inputtime":"1470129274","ctitle":"今日头条","style":"","choice_kc":[{"id":"298","name":"企业采集器","sort":"0","mv_url":"d9a628711e3378efe26effc9c5514013_d"},{"id":"299","name":"问问精准关键词用户提取","sort":"0","mv_url":"d9a628711e3b04b17bef9f10b6d4e166_d"},{"id":"303","name":"慧聪企业信息采集","sort":"0","mv_url":"d9a628711e4172a0faf5863ea1ac3d75_d"},{"id":"304","name":"生意宝","sort":"0","mv_url":"d9a628711e1359778211c64fcf070ba2_d"},{"id":"305","name":"新浪微博点赞评论","sort":"0","mv_url":"d9a628711e5c71039e4f435d78a7cd6b_d"},{"id":"306","name":"IM","sort":"0","mv_url":"d9a628711e85bebac2a72eeae807b0cd_d"},{"id":"307","name":"图片转换工具","sort":"0","mv_url":"d9a628711e44758f1a33eec29d8813f3_d"}],"choice_kc_idstr":"298,299,303,304,305,306,307","systemid":"34","thumb":"/var/upload/image/2016/08/20160802171452_94984.jpg","introduce":""},{"id":"107","listorder":"0","updatetime":"1476841260","inputtime":"1476841260","ctitle":"一点资讯","style":"","choice_kc":[],"choice_kc_idstr":"","systemid":"34","thumb":"","introduce":""},{"id":"108","listorder":"0","updatetime":"1476841271","inputtime":"1476841271","ctitle":"搜狐自媒体","style":"","choice_kc":[],"choice_kc_idstr":"","systemid":"34","thumb":"","introduce":""},{"id":"109","listorder":"0","updatetime":"1476841282","inputtime":"1476841282","ctitle":"网易媒体","style":"","choice_kc":[],"choice_kc_idstr":"","systemid":"34","thumb":"","introduce":""},{"id":"110","listorder":"0","updatetime":"1476841294","inputtime":"1476841294","ctitle":"UC自媒体","style":"","choice_kc":[],"choice_kc_idstr":"","systemid":"34","thumb":"","introduce":""}]
     * sysinfo : [{"id":"34","stitle":"新媒体运营","thumb":"/var/upload/image/2016/10/20161020164907_50943.jpg","listorder":"0","updatetime":"1461219300","inputtime":"1461219300","picture":"","introduce":"QQ群提取好友一款可以批量提取qq群里面所有群成员QQ号码的软件。主要是帮助客户 获得精准潜在客户, 懂得QQ营销的朋友都明白，QQ群成员是精准的潜在客户.","exception":"1,3,4","style":"","catid":"2","couClass":"233","sys_hours":"3秒29","keyword":"","description":"","l_nanyi":"1","l_jiage":"5","keshi":"1","status":"0","tfm":"0.00","pfm":"0.00","tcid":"0"}]
     */

    private String id;
    private String userID;
    private String oid;
    private String integralAll;
    private String integral;
    private String sourceType;
    private String addtime;
    private String sourceID;
    private String sHash;
    private String coid;
    private String isshow;
    private String systemid;
    private String type;
    private String pid;
    private String yvip;
    private String status;
    private String catid;
    private String sharetime;
    private String shareurl;
    private String tuangou;
    private String viplast_time;
    private String wxpay;
    /**
     * id : 77
     * listorder : 0
     * updatetime : 1461222523
     * inputtime : 1461222523
     * ctitle : 企鹅媒体
     * style :
     * choice_kc : [{"id":"262","name":"QQ群提取好友","sort":"0","mv_url":"d9a628711e944cc26c0073ee8f2d348c_d"},{"id":"290","name":"天涯论坛顶帖机","sort":"0","mv_url":"d9a628711e6c30477b9877decf2fae1d_d"},{"id":"291","name":"豆瓣顶帖机","sort":"0","mv_url":"d9a628711ed9512ab7f1d6ec23e137e1_d"},{"id":"292","name":"猫扑回帖助手","sort":"0","mv_url":"d9a628711ef83a2d2294e7bbe5d978da_d"},{"id":"293","name":"百度贴吧帖子采集器","sort":"0","mv_url":"d9a628711e80d534834f78d6e2351407_d"}]
     * choice_kc_idstr : 262,290,291,292,293
     * systemid : 34
     * thumb : /var/upload/image/2016/05/20160503160613_55082.jpg
     * introduce :
     */

    private List<CourseBean> course;
    /**
     * id : 34
     * stitle : 新媒体运营
     * thumb : /var/upload/image/2016/10/20161020164907_50943.jpg
     * listorder : 0
     * updatetime : 1461219300
     * inputtime : 1461219300
     * picture :
     * introduce : QQ群提取好友一款可以批量提取qq群里面所有群成员QQ号码的软件。主要是帮助客户 获得精准潜在客户, 懂得QQ营销的朋友都明白，QQ群成员是精准的潜在客户.
     * exception : 1,3,4
     * style :
     * catid : 2
     * couClass : 233
     * sys_hours : 3秒29
     * keyword :
     * description :
     * l_nanyi : 1
     * l_jiage : 5
     * keshi : 1
     * status : 0
     * tfm : 0.00
     * pfm : 0.00
     * tcid : 0
     */

    private List<SysinfoBean> sysinfo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getIntegralAll() {
        return integralAll;
    }

    public void setIntegralAll(String integralAll) {
        this.integralAll = integralAll;
    }

    public String getIntegral() {
        return integral;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getSourceID() {
        return sourceID;
    }

    public void setSourceID(String sourceID) {
        this.sourceID = sourceID;
    }

    public String getSHash() {
        return sHash;
    }

    public void setSHash(String sHash) {
        this.sHash = sHash;
    }

    public String getCoid() {
        return coid;
    }

    public void setCoid(String coid) {
        this.coid = coid;
    }

    public String getIsshow() {
        return isshow;
    }

    public void setIsshow(String isshow) {
        this.isshow = isshow;
    }

    public String getSystemid() {
        return systemid;
    }

    public void setSystemid(String systemid) {
        this.systemid = systemid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getYvip() {
        return yvip;
    }

    public void setYvip(String yvip) {
        this.yvip = yvip;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCatid() {
        return catid;
    }

    public void setCatid(String catid) {
        this.catid = catid;
    }

    public String getSharetime() {
        return sharetime;
    }

    public void setSharetime(String sharetime) {
        this.sharetime = sharetime;
    }

    public String getShareurl() {
        return shareurl;
    }

    public void setShareurl(String shareurl) {
        this.shareurl = shareurl;
    }

    public String getTuangou() {
        return tuangou;
    }

    public void setTuangou(String tuangou) {
        this.tuangou = tuangou;
    }

    public String getViplast_time() {
        return viplast_time;
    }

    public void setViplast_time(String viplast_time) {
        this.viplast_time = viplast_time;
    }

    public String getWxpay() {
        return wxpay;
    }

    public void setWxpay(String wxpay) {
        this.wxpay = wxpay;
    }

    public List<CourseBean> getCourse() {
        return course;
    }

    public void setCourse(List<CourseBean> course) {
        this.course = course;
    }

    public List<SysinfoBean> getSysinfo() {
        return sysinfo;
    }

    public void setSysinfo(List<SysinfoBean> sysinfo) {
        this.sysinfo = sysinfo;
    }

    public static class CourseBean {
        private String id;
        private String listorder;
        private String updatetime;
        private String inputtime;
        private String ctitle;
        private String style;
        private String choice_kc_idstr;
        private String systemid;
        private String thumb;
        private String introduce;
        /**
         * id : 262
         * name : QQ群提取好友
         * sort : 0
         * mv_url : d9a628711e944cc26c0073ee8f2d348c_d
         */

        private List<ChoiceKcBean> choice_kc;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getListorder() {
            return listorder;
        }

        public void setListorder(String listorder) {
            this.listorder = listorder;
        }

        public String getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }

        public String getInputtime() {
            return inputtime;
        }

        public void setInputtime(String inputtime) {
            this.inputtime = inputtime;
        }

        public String getCtitle() {
            return ctitle;
        }

        public void setCtitle(String ctitle) {
            this.ctitle = ctitle;
        }

        public String getStyle() {
            return style;
        }

        public void setStyle(String style) {
            this.style = style;
        }

        public String getChoice_kc_idstr() {
            return choice_kc_idstr;
        }

        public void setChoice_kc_idstr(String choice_kc_idstr) {
            this.choice_kc_idstr = choice_kc_idstr;
        }

        public String getSystemid() {
            return systemid;
        }

        public void setSystemid(String systemid) {
            this.systemid = systemid;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        public List<ChoiceKcBean> getChoice_kc() {
            return choice_kc;
        }

        public void setChoice_kc(List<ChoiceKcBean> choice_kc) {
            this.choice_kc = choice_kc;
        }

        public static class ChoiceKcBean {
            private String id;
            private String name;
            private String sort;
            private String mv_url;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getSort() {
                return sort;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }

            public String getMv_url() {
                return mv_url;
            }

            public void setMv_url(String mv_url) {
                this.mv_url = mv_url;
            }
        }
    }

    public static class SysinfoBean {
        private String id;
        private String stitle;
        private String thumb;
        private String listorder;
        private String updatetime;
        private String inputtime;
        private String picture;
        private String introduce;
        private String exception;
        private String style;
        private String catid;
        private String couClass;
        private String sys_hours;
        private String keyword;
        private String description;
        private String l_nanyi;
        private String l_jiage;
        private String keshi;
        private String status;
        private String tfm;
        private String pfm;
        private String tcid;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getStitle() {
            return stitle;
        }

        public void setStitle(String stitle) {
            this.stitle = stitle;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getListorder() {
            return listorder;
        }

        public void setListorder(String listorder) {
            this.listorder = listorder;
        }

        public String getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }

        public String getInputtime() {
            return inputtime;
        }

        public void setInputtime(String inputtime) {
            this.inputtime = inputtime;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        public String getException() {
            return exception;
        }

        public void setException(String exception) {
            this.exception = exception;
        }

        public String getStyle() {
            return style;
        }

        public void setStyle(String style) {
            this.style = style;
        }

        public String getCatid() {
            return catid;
        }

        public void setCatid(String catid) {
            this.catid = catid;
        }

        public String getCouClass() {
            return couClass;
        }

        public void setCouClass(String couClass) {
            this.couClass = couClass;
        }

        public String getSys_hours() {
            return sys_hours;
        }

        public void setSys_hours(String sys_hours) {
            this.sys_hours = sys_hours;
        }

        public String getKeyword() {
            return keyword;
        }

        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getL_nanyi() {
            return l_nanyi;
        }

        public void setL_nanyi(String l_nanyi) {
            this.l_nanyi = l_nanyi;
        }

        public String getL_jiage() {
            return l_jiage;
        }

        public void setL_jiage(String l_jiage) {
            this.l_jiage = l_jiage;
        }

        public String getKeshi() {
            return keshi;
        }

        public void setKeshi(String keshi) {
            this.keshi = keshi;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTfm() {
            return tfm;
        }

        public void setTfm(String tfm) {
            this.tfm = tfm;
        }

        public String getPfm() {
            return pfm;
        }

        public void setPfm(String pfm) {
            this.pfm = pfm;
        }

        public String getTcid() {
            return tcid;
        }

        public void setTcid(String tcid) {
            this.tcid = tcid;
        }
    }
}
