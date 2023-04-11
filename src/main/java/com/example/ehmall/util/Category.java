package com.example.ehmall.util;

/**类别实体类
 * @author 施立豪
 * @time 2023/4/11
 */
public class Category {
    /**
     * 类别分数
     */
    private double score;
    /**
     * 类别名
     */
    private String cate_name;
    /**
     * 类别排序
     */
    private int rank;
    private int cate_id;

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getCate_name() {
        return cate_name;
    }

    public void setCate_name(String cate_name) {
        this.cate_name = cate_name;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getCate_id() {
        return cate_id;
    }

    public void setCate_id(int cate_id) {
        this.cate_id = cate_id;
    }

    public Category(){}

}
