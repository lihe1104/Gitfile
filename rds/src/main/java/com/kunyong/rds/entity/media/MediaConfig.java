package com.kunyong.rds.entity.media;

import com.kunyong.rds.entity.TbSysTitleConfig.TbSysTitleConfig;
import com.kunyong.rds.entity.org.Org;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 贺
 * @Description: TODO
 */
@Entity
@Table(name = "tb_media_config")
public class MediaConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 'TB_SYS_ORG的id'
     */
    private Integer store_id;
    /**
     * 'TB_Media_Info表ID'
     */
    private Integer info_id;

    @Transient
    private String name;

    @Transient
    private String opeTime;

    /**
     * 'tb_sys_title_config的id'
     */
    private Integer title_id;

    @ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
    @JoinColumn(name="store_id",insertable=false,updatable = false)
    private Org org;

    @ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
    @JoinColumn(name="info_id",insertable=false,updatable = false)
    private MediaInfo mediaInfo;

    @ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
    @JoinColumn(name="title_id",insertable=false,updatable = false)
    private TbSysTitleConfig tbSysTitleConfig;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStore_id() {
        return store_id;
    }

    public void setStore_id(Integer store_id) {
        this.store_id = store_id;
    }

    public Integer getInfo_id() {
        return info_id;
    }

    public void setInfo_id(Integer info_id) {
        this.info_id = info_id;
    }

    public Integer getTitle_id() {
        return title_id;
    }

    public void setTitle_id(Integer title_id) {
        this.title_id = title_id;
    }

    public Org getOrg() {
        return org;
    }

    public void setOrg(Org org) {
        this.org = org;
    }

    @Override
    public String toString() {
        return "MediaConfig{" +
                "id=" + id +
                ", store_id=" + store_id +
                ", info_id=" + info_id +
                ", title_id=" + title_id +
                ", org=" + org +
                ", mediaInfo=" + mediaInfo +
                ", tbSysTitleConfig=" + tbSysTitleConfig +
                '}';
    }

    public MediaInfo getMediaInfo() {
        return mediaInfo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpeTime() {
        return opeTime;
    }

    public void setOpeTime(String opeTime) {
        this.opeTime = opeTime;
    }

    public void setMediaInfo(MediaInfo mediaInfo) {
        if (mediaInfo!=null ){
            this.name=mediaInfo.getName();
            SimpleDateFormat sfm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = null;
            String s = mediaInfo.getOpeTime();
            try {
                if(null != s){
                Date parse = sfm.parse(s);
                date=  sfm.format(parse);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            this.opeTime=date;
        }
        this.mediaInfo = mediaInfo;
    }

    public TbSysTitleConfig getTbSysTitleConfig() {
        return tbSysTitleConfig;
    }

    public void setTbSysTitleConfig(TbSysTitleConfig tbSysTitleConfig) {
        this.tbSysTitleConfig = tbSysTitleConfig;
    }

}
