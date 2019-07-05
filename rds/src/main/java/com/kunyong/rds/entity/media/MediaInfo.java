package com.kunyong.rds.entity.media;

import com.kunyong.rds.annotation.QueryAnnotation;
import com.kunyong.rds.enums.MatchType;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 我知道这里应该写注释,可是我奏似不想写，你说气人不气人
 *
 * @author 贺
 * @create 2019/6/9
 */
@Entity
@Table(name = "tb_media_info")
public class MediaInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String mType;

    @QueryAnnotation(func = MatchType.like)
    private String name;

    private String descc;

    @Column(name = "ope_user")
    @QueryAnnotation(func = MatchType.equal)
    private Integer opeUser;

    @Column(name = "ope_time")
    @QueryAnnotation(func = MatchType.equal)
    private String opeTime;

    private String url;

    private String status;

    @Column(name = "group_id")
    @QueryAnnotation(func = MatchType.equal)
    private Integer groupId;


    public String getmType() {
        return mType;
    }

    public void setmType(String mType) {
        this.mType = mType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescc() {
        return descc;
    }

    public void setDescc(String descc) {
        this.descc = descc;
    }

    public Integer getOpeUser() {
        return opeUser;
    }

    public void setOpeUser(Integer opeUser) {
        this.opeUser = opeUser;
    }

    public String getOpeTime() {
        if (!StringUtils.isEmpty(this.opeTime)){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date date = sdf.parse(this.opeTime);
                String format = sdf.format(date);
                return format;
            } catch (ParseException e) {
                e.printStackTrace ();
            }
        }
        return opeTime;
    }

    public void setOpeTime(String opeTime) {
        this.opeTime = opeTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "MediaInfo{" +
                "id=" + id +
                ", mType='" + mType + '\'' +
                ", name='" + name + '\'' +
                ", descc='" + descc + '\'' +
                ", opeUser=" + opeUser +
                ", opeTime='" + opeTime + '\'' +
                ", url='" + url + '\'' +
                ", status='" + status + '\'' +
                ", groupId=" + groupId +
                '}';
    }
}
