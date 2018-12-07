package com.zlt.test_map.bean;

/***
 * 确认接单接口
 */
public class ReceiptBean {
    /**
     * code : 200
     * msg : ok
     * data : {"list":{"id":1,"routecode":"C200","routeclaim":"新街口街道","ingnum":"0.23","position":"路面","type":"裂缝","area":"12","images":"123"}}
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
         * list : {"id":1,"routecode":"C200","routeclaim":"新街口街道","ingnum":"0.23","position":"路面","type":"裂缝","area":"12","images":"123"}
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
             * id : 1
             * routecode : C200
             * routeclaim : 新街口街道
             * ingnum : 0.23
             * position : 路面
             * type : 裂缝
             * area : 12
             * images : 123
             */

            private int id;
            private String routecode;
            private String routeclaim;
            private String ingnum;
            private String position;
            private String type;
            private String area;
            private String images;

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

            public String getIngnum() {
                return ingnum;
            }

            public void setIngnum(String ingnum) {
                this.ingnum = ingnum;
            }

            public String getPosition() {
                return position;
            }

            public void setPosition(String position) {
                this.position = position;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public String getImages() {
                return images;
            }

            public void setImages(String images) {
                this.images = images;
            }
        }
    }
}
