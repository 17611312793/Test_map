package com.zlt.test_map.bean;

import java.util.List;

/***
 * 选择道路类型
 */
public class WayTypeBean {

    /**
     * code : 200
     * msg : ok
     * data : {"list":[{"id":1,"type":"县道"},{"id":2,"type":"乡道"},{"id":3,"type":"村道"},{"id":4,"type":"桥梁"},{"id":5,"type":"隧道"},{"id":6,"type":"涵洞"},{"id":7,"type":"渡口"},{"id":9,"type":"旅游路线"}]}
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
             * id : 1
             * type : 县道
             */

            private int id;
            private String type;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }
    }
}
