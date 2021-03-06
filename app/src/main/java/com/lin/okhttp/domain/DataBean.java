package com.lin.okhttp.domain;

import java.util.List;

/**
 */
public class DataBean {

    /**
     * id : 63289
     * movieName : 《星际特工》中文先行预告片
     * coverImg : http://img5.mtime.cn/mg/2016/11/11/143347.84705153.jpg
     * movieId : 225003
     * url : http://vfx.mtime.cn/Video/2016/11/11/mp4/161111091703099158_480.mp4
     * hightUrl : http://vfx.mtime.cn/Video/2016/11/11/mp4/161111091703099158.mp4
     * videoTitle : 星际特工：千星之城 中文版先行预告片
     * videoLength : 97
     * rating : -1
     * type : ["动作","冒险","科幻"]
     * summary : 戴涵涵与迪瓦伊打情骂俏
     */

    private List<ItemData> trailers;

    public List<ItemData> getTrailers() {
        return trailers;
    }

    public void setTrailers(List<ItemData> trailers) {
        this.trailers = trailers;
    }

    public static class ItemData {
        private int id;
        private String movieName;
        private String coverImg;
        private int movieId;
        private String url;
        private String hightUrl;
        private String videoTitle;
        private int videoLength;
        private int rating;
        private String summary;
        private List<String> type;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMovieName() {
            return movieName;
        }

        public void setMovieName(String movieName) {
            this.movieName = movieName;
        }

        public String getCoverImg() {
            return coverImg;
        }

        public void setCoverImg(String coverImg) {
            this.coverImg = coverImg;
        }

        public int getMovieId() {
            return movieId;
        }

        public void setMovieId(int movieId) {
            this.movieId = movieId;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getHightUrl() {
            return hightUrl;
        }

        public void setHightUrl(String hightUrl) {
            this.hightUrl = hightUrl;
        }

        public String getVideoTitle() {
            return videoTitle;
        }

        public void setVideoTitle(String videoTitle) {
            this.videoTitle = videoTitle;
        }

        public int getVideoLength() {
            return videoLength;
        }

        public void setVideoLength(int videoLength) {
            this.videoLength = videoLength;
        }

        public int getRating() {
            return rating;
        }

        public void setRating(int rating) {
            this.rating = rating;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public List<String> getType() {
            return type;
        }

        public void setType(List<String> type) {
            this.type = type;
        }
    }
}
