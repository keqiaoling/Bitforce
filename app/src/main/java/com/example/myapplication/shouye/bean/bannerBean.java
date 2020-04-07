package com.example.myapplication.shouye.bean;

import java.util.List;

public class bannerBean {

    /**
     * code : 0
     * data : {"list":[{"addTime":"2020-04-07T03:23:11.252Z","bContent":"string","id":0,"img":"string","pIndex":0,"show":"string","type":"string"}],"pageNum":0,"pageSize":0,"total":0,"totalPage":0}
     * message : string
     */

    private int code;
    private DataBean data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        /**
         * list : [{"addTime":"2020-04-07T03:23:11.252Z","bContent":"string","id":0,"img":"string","pIndex":0,"show":"string","type":"string"}]
         * pageNum : 0
         * pageSize : 0
         * total : 0
         * totalPage : 0
         */

        private int pageNum;
        private int pageSize;
        private int total;
        private int totalPage;
        private List<ListBean> list;

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * addTime : 2020-04-07T03:23:11.252Z
             * bContent : string
             * id : 0
             * img : string
             * pIndex : 0
             * show : string
             * type : string
             */

            private String addTime;
            private String bContent;
            private int id;
            private String img;
            private int pIndex;
            private String show;
            private String type;

            public String getAddTime() {
                return addTime;
            }

            public void setAddTime(String addTime) {
                this.addTime = addTime;
            }

            public String getBContent() {
                return bContent;
            }

            public void setBContent(String bContent) {
                this.bContent = bContent;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public int getPIndex() {
                return pIndex;
            }

            public void setPIndex(int pIndex) {
                this.pIndex = pIndex;
            }

            public String getShow() {
                return show;
            }

            public void setShow(String show) {
                this.show = show;
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
