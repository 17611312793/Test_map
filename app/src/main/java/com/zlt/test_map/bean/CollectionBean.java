package com.zlt.test_map.bean;

/***
 * 路况采集展示接口
 */
public class CollectionBean {

    /**
     * code : 200
     * msg : ok
     * data : {"list":{"id":13,"routecode":"C200","routeclaim":"陶然亭街道"}}
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
         * list : {"id":13,"routecode":"C200","routeclaim":"陶然亭街道"}
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
             * id : 13
             * routecode : C200
             * routeclaim : 陶然亭街道
             */

            private int id;
            private String routecode;
            private String routeclaim;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getRoutecode() {
                return routecode;
            }

            public void setRoutecode(String routecode) {
                this.routecode = routecode;
            }

            public String getRouteclaim() {
                return routeclaim;
            }

            public void setRouteclaim(String routeclaim) {
                this.routeclaim = routeclaim;
            }
        }
    }
}
