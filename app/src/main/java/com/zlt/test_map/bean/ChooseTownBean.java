package com.zlt.test_map.bean;

import java.util.List;

/***
 * 选择乡镇
 */
public class ChooseTownBean {

    /**
     * code : 200
     * msg : ok
     * data : {"list":[{"id":3,"town":"彬村山农场"},{"id":4,"town":"博鳌镇"},{"id":5,"town":"大路镇"},{"id":6,"town":"会山镇"},{"id":7,"town":"嘉积镇"},{"id":8,"town":"龙江镇"},{"id":9,"town":"石壁镇"},{"id":10,"town":"塔洋镇"}]}
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
             * id : 3
             * town : 彬村山农场
             */

            private int id;
            private String town;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTown() {
                return town;
            }

            public void setTown(String town) {
                this.town = town;
            }
        }
    }
}
