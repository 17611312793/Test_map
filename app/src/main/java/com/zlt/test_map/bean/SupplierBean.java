package com.zlt.test_map.bean;

import java.util.List;

/**
 *供应商接口
 */
public class SupplierBean {
    /**
     * code : 200
     * msg : ok
     * data : {"list":[{"id":8,"supplier":"兰州辉煌建材"},{"id":7,"supplier":"金河市防水材料有限公司"},{"id":6,"supplier":"中粮集团"}]}
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
             * id : 8
             * supplier : 兰州辉煌建材
             */

            private int id;
            private String supplier;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getSupplier() {
                return supplier;
            }

            public void setSupplier(String supplier) {
                this.supplier = supplier;
            }
        }
    }
}
