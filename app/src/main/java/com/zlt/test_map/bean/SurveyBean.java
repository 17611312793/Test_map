package com.zlt.test_map.bean;

/***
 * 获取事件概况接口
 */
public class SurveyBean {
    /**
     * code : 200
     * msg : ok
     * data : {"list":{"id":2,"title":"路面塌陷","site":"龙潭路与迎宾路交叉口","x":"120度11分48秒","y":"32度42分23秒","conditions":"交口东侧山体滑坡导致泥石流","sceneimgs":""}}
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
        /**
         * list : {"id":2,"title":"路面塌陷","site":"龙潭路与迎宾路交叉口","x":"120度11分48秒","y":"32度42分23秒","conditions":"交口东侧山体滑坡导致泥石流","sceneimgs":""}
         */

        private ListBean list;

        public ListBean getList() {
            return list;
        }

        public void setList(ListBean list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 2
             * title : 路面塌陷
             * site : 龙潭路与迎宾路交叉口
             * x : 120度11分48秒
             * y : 32度42分23秒
             * conditions : 交口东侧山体滑坡导致泥石流
             * sceneimgs :
             */

            private int id;
            private String title;
            private String site;
            private String x;
            private String y;
            private String conditions;
            private String sceneimgs;

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

            public String getX() {
                return x;
            }

            public void setX(String x) {
                this.x = x;
            }

            public String getY() {
                return y;
            }

            public void setY(String y) {
                this.y = y;
            }

            public String getConditions() {
                return conditions;
            }

            public void setConditions(String conditions) {
                this.conditions = conditions;
            }

            public String getSceneimgs() {
                return sceneimgs;
            }

            public void setSceneimgs(String sceneimgs) {
                this.sceneimgs = sceneimgs;
            }
        }
    }
}
