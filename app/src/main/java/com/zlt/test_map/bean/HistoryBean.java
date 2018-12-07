package com.zlt.test_map.bean;

/**
 * 历史事件接口
 */
public class HistoryBean {
    /**
     * code : 200
     * msg : ok
     * data : {"list":{"id":1,"event_type":"0","event":"道路塌陷","site":"北京市大兴区","x":"122","y":"12","starttime":"2018年12月4日","endtime":"2018年12月14日","participant":"AOC","leader":"领导","takesteps":"处置","car":"12","material":"100","processdetail":"surprised","timesummary":"很赞","regdate":"2018-12-04 18:47:43","update":"0"}}
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
         * list : {"id":1,"event_type":"0","event":"道路塌陷","site":"北京市大兴区","x":"122","y":"12","starttime":"2018年12月4日","endtime":"2018年12月14日","participant":"AOC","leader":"领导","takesteps":"处置","car":"12","material":"100","processdetail":"surprised","timesummary":"很赞","regdate":"2018-12-04 18:47:43","update":"0"}
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
             * event_type : 0
             * event : 道路塌陷
             * site : 北京市大兴区
             * x : 122
             * y : 12
             * starttime : 2018年12月4日
             * endtime : 2018年12月14日
             * participant : AOC
             * leader : 领导
             * takesteps : 处置
             * car : 12
             * material : 100
             * processdetail : surprised
             * timesummary : 很赞
             * regdate : 2018-12-04 18:47:43
             * update : 0
             */

            private int id;
            private String event_type;
            private String event;
            private String site;
            private String x;
            private String y;
            private String starttime;
            private String endtime;
            private String participant;
            private String leader;
            private String takesteps;
            private String car;
            private String material;
            private String processdetail;
            private String timesummary;
            private String regdate;
            private String update;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getEvent_type() {
                return event_type;
            }

            public void setEvent_type(String event_type) {
                this.event_type = event_type;
            }

            public String getEvent() {
                return event;
            }

            public void setEvent(String event) {
                this.event = event;
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

            public String getStarttime() {
                return starttime;
            }

            public void setStarttime(String starttime) {
                this.starttime = starttime;
            }

            public String getEndtime() {
                return endtime;
            }

            public void setEndtime(String endtime) {
                this.endtime = endtime;
            }

            public String getParticipant() {
                return participant;
            }

            public void setParticipant(String participant) {
                this.participant = participant;
            }

            public String getLeader() {
                return leader;
            }

            public void setLeader(String leader) {
                this.leader = leader;
            }

            public String getTakesteps() {
                return takesteps;
            }

            public void setTakesteps(String takesteps) {
                this.takesteps = takesteps;
            }

            public String getCar() {
                return car;
            }

            public void setCar(String car) {
                this.car = car;
            }

            public String getMaterial() {
                return material;
            }

            public void setMaterial(String material) {
                this.material = material;
            }

            public String getProcessdetail() {
                return processdetail;
            }

            public void setProcessdetail(String processdetail) {
                this.processdetail = processdetail;
            }

            public String getTimesummary() {
                return timesummary;
            }

            public void setTimesummary(String timesummary) {
                this.timesummary = timesummary;
            }

            public String getRegdate() {
                return regdate;
            }

            public void setRegdate(String regdate) {
                this.regdate = regdate;
            }

            public String getUpdate() {
                return update;
            }

            public void setUpdate(String update) {
                this.update = update;
            }
        }
    }
}
