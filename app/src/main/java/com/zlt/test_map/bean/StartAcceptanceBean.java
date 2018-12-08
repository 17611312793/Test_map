package com.zlt.test_map.bean;

import java.util.List;

/***
 * 开始验收列表展示
 */
public class StartAcceptanceBean {


    /**
     * code : 200
     * msg : ok
     * data : {"list":[{"routecode":"Xingjhn","routeclaim":"克拉玛依 - 海南","ingnum":"892","position":"1","type":"2","area":"20","images":"11.png","id":1,"backup":"23"}]}
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
             * routecode : Xingjhn
             * routeclaim : 克拉玛依 - 海南
             * ingnum : 892
             * position : 1
             * type : 2
             * area : 20
             * images : 11.png
             * id : 1
             * backup : 23
             */

            private String routecode;
            private String routeclaim;
            private String ingnum;
            private String position;
            private String type;
            private String area;
            private String images;
            private int id;
            private String backup;

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

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getBackup() {
                return backup;
            }

            public void setBackup(String backup) {
                this.backup = backup;
            }
        }
    }
}
