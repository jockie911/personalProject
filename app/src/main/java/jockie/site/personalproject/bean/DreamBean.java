package jockie.site.personalproject.bean;

import java.util.List;

/**
 * Created by yc on 2017/6/28.
 */

public class DreamBean {
    /**
     * msg : success
     * result : {"list":[{"detail":"表示想洗净内心中的罪恶意识。当你做坏事时，通常会做这种梦。","name":"打 扫"},{"detail":"梦见自己挨打，生活会富裕。梦见陌生人挨打，会遇到困难。梦见家人被打死了，家里要增添人口。梦见打动物，要发财。囚犯梦见被打，很快会被释放。","name":"挨打"}],"page":1,"total":2}
     * retCode : 200
     */

    private String msg;
    private ResultBean result;
    private String retCode;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public static class ResultBean {
        /**
         * list : [{"detail":"表示想洗净内心中的罪恶意识。当你做坏事时，通常会做这种梦。","name":"打 扫"},{"detail":"梦见自己挨打，生活会富裕。梦见陌生人挨打，会遇到困难。梦见家人被打死了，家里要增添人口。梦见打动物，要发财。囚犯梦见被打，很快会被释放。","name":"挨打"}]
         * page : 1
         * total : 2
         */

        private int page;
        private int total;
        private List<ListBean> list;

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * detail : 表示想洗净内心中的罪恶意识。当你做坏事时，通常会做这种梦。
             * name : 打 扫
             */

            private String detail;
            private String name;

            public String getDetail() {
                return detail;
            }

            public void setDetail(String detail) {
                this.detail = detail;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
