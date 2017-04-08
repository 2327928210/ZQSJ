package com.example.haier.sheji.zhubao;

import java.util.List;

/**
 * Created by Window 7 on 2017/3/29.
 */

public class ShiTiLei {


    /**
     * categoryindex : -1
     * context : [{"number":"A2501255","img":"170321163836115_cad2b646051b39f3057723c274502934.jpg","name":"慧智兰心-戒指"},{"number":"KQ-30011","img":"170321163836240_53cd15292764ed4c627621d29ad0b206.jpg","name":"鸿运当头"},{"number":"KQ-30012","img":"170321163836360_b1e6a8ecbd4f8796b176ceb8db79dc0b.jpg","name":"鸿运当头"},{"number":"T-EG00003","img":"170321163836451_5d8b7e24c2356ea41c5651d148ac7f60.jpg","name":"慧智兰心-耳勾"},{"number":"BBQD0475","img":"170321125608897_44c298bffdbd46e8926950d8fba1e6cc.jpg","name":"狐狸耳勾"},{"number":"BBQD0058","img":"170321125609909_a33d3695ea0c1f31065003368c23a3fd.jpg","name":"秀金爱心耳勾"},{"number":"BBQD0662","img":"170321125610958_1a35444c3653988d46f198912de9bd50.jpg","name":"秀金耳勾"},{"number":"BBQD0680","img":"170321125611969_8ead68692fcccc076b30311359e99923.jpg","name":"蛇骨穿珠耳勾"},{"number":"BBQD0372","img":"170321125612981_2f942024930e5f0cb7e32b0144f09e83.jpg","name":"秀金花形耳勾"},{"number":"BBQD0351","img":"170321125613995_8766a267276a72417da57c042216d87f.jpg","name":"秀金菱形耳勾"}]
     */

    private int categoryindex;
    private List<ContextBean> context;

    public int getCategoryindex() {
        return categoryindex;
    }

    public void setCategoryindex(int categoryindex) {
        this.categoryindex = categoryindex;
    }

    public List<ContextBean> getContext() {
        return context;
    }

    public void setContext(List<ContextBean> context) {
        this.context = context;
    }

    public static class ContextBean {
        /**
         * number : A2501255
         * img : 170321163836115_cad2b646051b39f3057723c274502934.jpg
         * name : 慧智兰心-戒指
         */

        private String number;
        private String img;
        private String name;

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


        @Override
        public String toString() {
            return "ContextBean{" +
                    "number='" + number + '\'' +
                    ", img='" + img + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
