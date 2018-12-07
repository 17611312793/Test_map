package com.zlt.test_map.bean;

import java.util.List;

/***
 * 养护管理列表展示接口
 */
public class CuringShowBean {
    /**
     * code : 200
     * msg : ok
     * data : {"list":[{"id":14,"routecode":"C200","routeclaim":"新街口街道","mileage":"0","grade":"0","routetype":"水泥路面","adminrank":"0","coverphoto":"0"},{"id":13,"routecode":"C200","routeclaim":"陶然亭街道","mileage":"0","grade":"0","routetype":"水泥路面","adminrank":"0","coverphoto":"0"},{"id":12,"routecode":"C200","routeclaim":"什刹海街道","mileage":"0","grade":"0","routetype":"水泥路面","adminrank":"0","coverphoto":"0"},{"id":11,"routecode":"C200","routeclaim":"大栅栏街道","mileage":"0","grade":"0","routetype":"水泥路面","adminrank":"0","coverphoto":"0"},{"id":10,"routecode":"C200","routeclaim":"白纸坊街道","mileage":"0","grade":"0","routetype":"水泥路面","adminrank":"0","coverphoto":"0"},{"id":9,"routecode":"C200","routeclaim":"展览路街道","mileage":"0","grade":"0","routetype":"水泥路面","adminrank":"0","coverphoto":"0"},{"id":8,"routecode":"C200","routeclaim":"月坛街道","mileage":"0","grade":"0","routetype":"水泥路面","adminrank":"0","coverphoto":"0"},{"id":7,"routecode":"C200","routeclaim":"天桥街道","mileage":"0","grade":"0","routetype":"水泥路面","adminrank":"0","coverphoto":"0"},{"id":6,"routecode":"C200","routeclaim":"德胜街道","mileage":"0","grade":"0","routetype":"水泥路面","adminrank":"0","coverphoto":"0"},{"id":5,"routecode":"C200","routeclaim":"椿树街道","mileage":"0","grade":"0","routetype":"水泥路面","adminrank":"0","coverphoto":"0"},{"id":4,"routecode":"C200","routeclaim":"广安门内街道","mileage":"0","grade":"0","routetype":"水泥路面","adminrank":"0","coverphoto":"0"},{"id":3,"routecode":"C200","routeclaim":"牛街街道","mileage":"0","grade":"0","routetype":"水泥路面","adminrank":"0","coverphoto":"0"},{"id":2,"routecode":"C165","routeclaim":"金融街街道","mileage":"0","grade":"0","routetype":"水泥路面","adminrank":"0","coverphoto":"0"},{"id":1,"routecode":"C165","routeclaim":"西长安街街道","mileage":"0","grade":"0","routetype":"水泥路面","adminrank":"0","coverphoto":"0"}]}
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
             * id : 14
             * routecode : C200
             * routeclaim : 新街口街道
             * mileage : 0
             * grade : 0
             * routetype : 水泥路面
             * adminrank : 0
             * coverphoto : 0
             */

            private int id;
            private String routecode;
            private String routeclaim;
            private String mileage;
            private String grade;
            private String routetype;
            private String adminrank;
            private String coverphoto;

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

            public String getMileage() {
                return mileage;
            }

            public void setMileage(String mileage) {
                this.mileage = mileage;
            }

            public String getGrade() {
                return grade;
            }

            public void setGrade(String grade) {
                this.grade = grade;
            }

            public String getRoutetype() {
                return routetype;
            }

            public void setRoutetype(String routetype) {
                this.routetype = routetype;
            }

            public String getAdminrank() {
                return adminrank;
            }

            public void setAdminrank(String adminrank) {
                this.adminrank = adminrank;
            }

            public String getCoverphoto() {
                return coverphoto;
            }

            public void setCoverphoto(String coverphoto) {
                this.coverphoto = coverphoto;
            }
        }
    }
}
