package com.kunyong.rds.entity.TbSysTitleConfig;

import com.kunyong.rds.annotation.QueryAnnotation;
import com.kunyong.rds.enums.MatchType;

import javax.persistence.*;

/**
    * tb_sys_title_config 实体类 用户职位表
    * 2019-06-09 20:02:01 贺
    */
@Entity
@Table(name="tb_sys_title_config")
public class TbSysTitleConfig{
	//id
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer	id;
	//level_key
	private String	level_key;
	//level_desc
	private String	level_desc;
	//title_desc
	@QueryAnnotation(func = MatchType.like)
	private String	title_desc;
	//pos
	private String	pos;
	//title_key
	private String	title_key;

	public void setId(Integer id){
		this.id=id;
	}
	public Integer getId(){
		return id;
	}

	public void setLevel_key(String level_key){
		this.level_key=level_key;
	}
	public String getLevel_key(){
		return level_key;
	}

	public void setLevel_desc(String level_desc){
		this.level_desc=level_desc;
	}
	public String getLevel_desc(){
		return level_desc;
	}

	public void setTitle_desc(String title_desc){
		this.title_desc=title_desc;
	}
	public String getTitle_desc(){
		return title_desc;
	}

	public void setPos(String pos){
		this.pos=pos;
	}
	public String getPos(){
		return pos;
	}

	public void setTitle_key(String title_key){
		this.title_key=title_key;
	}
	public String getTitle_key(){
		return title_key;
	}

	@Override
	public String toString() {
		return "TbSysTitleConfig{" +
				"id=" + id +
				", level_key='" + level_key + '\'' +
				", level_desc='" + level_desc + '\'' +
				", title_desc='" + title_desc + '\'' +
				", pos='" + pos + '\'' +
				", title_key='" + title_key + '\'' +
				'}';
	}
}
