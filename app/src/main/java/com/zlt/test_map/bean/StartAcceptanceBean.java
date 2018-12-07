package com.zlt.test_map.bean;

import java.util.List;

/***
 * 开始验收列表展示
 */
public class StartAcceptanceBean {

    /**
     * code : 200
     * msg : ok
     * data : {"lista":[{"routecode":"C200","routeclaim":"新街口街道","ingnum":"0.23","position":"路面","type":"裂缝","area":"12","images":"123"}],"listb":{"id":2,"images":"http://image.baidu.com/search/detail?ct=503316480&z=undefined&tn=baiduimagedetail&ipn=d&word=%E5%9B%BE%E7%89%87&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=undefined&hd=undefined&latest=undefined&copyright=undefined&cs=764856423,3994964277&os=3075148465,762977515&simid=30514997,583073039&pn=5&rn=1&di=75852428410&ln=1575&fr=&fmq=1543983548993_R&fm=&ic=undefined&s=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&is=0,0&istype=0&ist=&jit=&bdtype=0&spn=0&pi=0&gsm=0&objurl=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01f9ea56e282836ac72531cbe0233b.jpg%402o.jpg&rpstart=0&rpnum=0&selected_tags=0&adpicid=0","backup":"ffgcv df"}}
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
         * lista : [{"routecode":"C200","routeclaim":"新街口街道","ingnum":"0.23","position":"路面","type":"裂缝","area":"12","images":"123"}]
         * listb : {"id":2,"images":"http://image.baidu.com/search/detail?ct=503316480&z=undefined&tn=baiduimagedetail&ipn=d&word=%E5%9B%BE%E7%89%87&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=undefined&hd=undefined&latest=undefined&copyright=undefined&cs=764856423,3994964277&os=3075148465,762977515&simid=30514997,583073039&pn=5&rn=1&di=75852428410&ln=1575&fr=&fmq=1543983548993_R&fm=&ic=undefined&s=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&is=0,0&istype=0&ist=&jit=&bdtype=0&spn=0&pi=0&gsm=0&objurl=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01f9ea56e282836ac72531cbe0233b.jpg%402o.jpg&rpstart=0&rpnum=0&selected_tags=0&adpicid=0","backup":"ffgcv df"}
         */

        private ListbBean listb;
        private List<ListaBean> lista;

        public ListbBean getListb() {
            return listb;
        }

        public void setListb(ListbBean listb) {
            this.listb = listb;
        }

        public List<ListaBean> getLista() {
            return lista;
        }

        public void setLista(List<ListaBean> lista) {
            this.lista = lista;
        }

        public static class ListbBean {
            /**
             * id : 2
             * images : http://image.baidu.com/search/detail?ct=503316480&z=undefined&tn=baiduimagedetail&ipn=d&word=%E5%9B%BE%E7%89%87&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=undefined&hd=undefined&latest=undefined&copyright=undefined&cs=764856423,3994964277&os=3075148465,762977515&simid=30514997,583073039&pn=5&rn=1&di=75852428410&ln=1575&fr=&fmq=1543983548993_R&fm=&ic=undefined&s=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&is=0,0&istype=0&ist=&jit=&bdtype=0&spn=0&pi=0&gsm=0&objurl=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01f9ea56e282836ac72531cbe0233b.jpg%402o.jpg&rpstart=0&rpnum=0&selected_tags=0&adpicid=0
             * backup : ffgcv df
             */

            private int id;
            private String images;
            private String backup;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImages() {
                return images;
            }

            public void setImages(String images) {
                this.images = images;
            }

            public String getBackup() {
                return backup;
            }

            public void setBackup(String backup) {
                this.backup = backup;
            }
        }

        public static class ListaBean {
            /**
             * routecode : C200
             * routeclaim : 新街口街道
             * ingnum : 0.23
             * position : 路面
             * type : 裂缝
             * area : 12
             * images : 123
             */

            private String routecode;
            private String routeclaim;
            private String ingnum;
            private String position;
            private String type;
            private String area;
            private String images;

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
