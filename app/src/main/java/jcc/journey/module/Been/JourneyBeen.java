package jcc.journey.module.Been;

import java.util.List;

/**
 * Created by Administrator on 2016/11/27.
 */

public class JourneyBeen {
    private String name;
    private String summary;
    private List<String>imgurls;
    private String content;
    private String address;
    private String attention;
    private String coupon;

    public JourneyBeen(String name, String summary, List<String> imgurls, String content, String address, String attention, String coupon) {
        this.name = name;
        this.summary = summary;
        this.imgurls = imgurls;
        this.content = content;
        this.address = address;
        this.attention = attention;
        this.coupon = coupon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<String> getImgurls() {
        return imgurls;
    }

    public void setImgurls(List<String> imgurls) {
        this.imgurls = imgurls;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAttention() {
        return attention;
    }

    public void setAttention(String attention) {
        this.attention = attention;
    }

    public String getCoupon() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }
}
