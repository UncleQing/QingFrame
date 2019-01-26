package com.zidian.qingframe.library_common.widget.share;

public class ShareBean {

    private String title;
    private String url;
    private String siteUrl;
    private String site;
    private String text;
    private String imageUrl;
    private String imagePath;
    private String titleUrl;

    private ShareBean() {
    }

    public String getTitle() {
        return title;
    }

    public ShareBean setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public ShareBean setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public ShareBean setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
        return this;
    }

    public String getSite() {
        return site;
    }

    public ShareBean setSite(String site) {
        this.site = site;
        return this;
    }

    public String getText() {
        return text;
    }

    public ShareBean setText(String text) {
        this.text = text;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ShareBean setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getImagePath() {
        return imagePath;
    }

    public ShareBean setImagePath(String imagePath) {
        this.imagePath = imagePath;
        return this;
    }

    public String getTitleUrl() {
        return titleUrl;
    }

    public ShareBean setTitleUrl(String titleUrl) {
        this.titleUrl = titleUrl;
        return this;
    }

    public static ShareBean builder() {
        return new ShareBean();
    }

}
