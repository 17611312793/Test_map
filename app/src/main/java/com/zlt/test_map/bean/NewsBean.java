package com.zlt.test_map.bean;

import java.util.List;

/***
 * 新事件列表
 */
public class NewsBean {
    /**
     * code : 200
     * msg : ok
     * data : {"list":[{"id":9,"title":"东华路出现堰塞湖威胁","site":"1","conditions":"现场情况","sceneimg":"现场照片"},{"id":8,"title":"泥石流阻断交通","site":"57号大道","conditions":"数据上传","sceneimg":""},{"id":7,"title":"路面塌陷","site":"龙潭路与迎宾路交叉口","conditions":"交口东侧山体滑坡导致泥石流","sceneimg":"https://www.baidu.com/img/bd_logo1.png"},{"id":6,"title":"路面塌陷","site":"龙潭路与迎宾路交叉口","conditions":"交口东侧山体滑坡导致泥石流","sceneimg":"https://www.baidu.com/img/bd_logo1.png"},{"id":5,"title":"泥石流阻断交通","site":"龙潭路与迎宾路交叉口","conditions":"交口东侧山体滑坡导致泥石流","sceneimg":"https://www.baidu.com/img/bd_logo1.png"},{"id":4,"title":"路面严重积水","site":"龙潭路与迎宾路交叉口","conditions":"交口东侧山体滑坡导致泥石流","sceneimg":"https://www.baidu.com/img/bd_logo1.png"},{"id":3,"title":"\b水毁","site":"龙潭路与迎宾路交叉口","conditions":"交口东侧山体滑坡导致泥石流","sceneimg":"https://www.baidu.com/img/bd_logo1.png"},{"id":2,"title":"路面塌陷","site":"龙潭路与迎宾路交叉口","conditions":"交口东侧山体滑坡导致泥石流","sceneimg":"https://www.baidu.com/img/bd_logo1.png"},{"id":1,"title":"泥石流切断","site":"龙潭路与迎宾路交叉路口","conditions":"交口东侧山体滑坡导致泥石流","sceneimg":"https://www.baidu.com/img/bd_logo1.png"}]}
     */

    private int code;
    private String msg;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 9
             * title : 东华路出现堰塞湖威胁
             * site : 1
             * conditions : 现场情况
             * sceneimg : 现场照片
             */

            private int id;
            private String title;
            private String site;
            private String conditions;
            private String sceneimg;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getSite() {
                return site;
            }

            public void setSite(String site) {
                this.site = site;
            }

            public String getConditions() {
                return conditions;
            }

            public void setConditions(String conditions) {
                this.conditions = conditions;
            }

            public String getSceneimg() {
                return sceneimg;
            }

            public void setSceneimg(String sceneimg) {
                this.sceneimg = sceneimg;
            }
        }
    }
}
